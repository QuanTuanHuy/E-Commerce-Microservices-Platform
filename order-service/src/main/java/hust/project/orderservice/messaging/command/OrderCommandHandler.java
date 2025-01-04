package hust.project.orderservice.messaging.command;

import hust.project.common.command.*;
import hust.project.orderservice.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "order-service-request")
@Slf4j
public class OrderCommandHandler implements IOrderCommandHandler {
    private final IOrderService orderService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.create-order-saga-reply}")
    private String CREATE_ORDER_SAGA_REPLY_TOPIC;

    @Override
    @KafkaHandler
    public void handleCommand(OrderCommand command) {
        log.info("[OrderCommandHandler] handle command: {}", command);
        if (command instanceof ApproveOrderCommand) {
            approveOrder((ApproveOrderCommand) command);
        } else if (command instanceof RejectOrderCommand) {
            rejectOrder((RejectOrderCommand) command);
        }
    }

    private void rejectOrder(RejectOrderCommand command) {
        OrderCommandReply reply;
        try {
            orderService.rejectOrder(command.getOrderId());
            reply = RejectOrderCommandReply.builder()
                    .orderId(command.getOrderId())
                    .isSuccess(true)
                    .build();
        } catch (Exception e) {
            reply = RejectOrderCommandReply.builder()
                    .orderId(command.getOrderId())
                    .isSuccess(false)
                    .build();
        }

        // send reply to create-order-saga-reply topic
        sendCommandReply(reply);
    }

    private void approveOrder(ApproveOrderCommand command) {
        OrderCommandReply reply;
        try {
            orderService.approveOrder(command.getOrderId());
            reply = ApproveOrderCommandReply.builder()
                    .orderId(command.getOrderId())
                    .isSuccess(true)
                    .build();
        } catch (Exception e) {
            reply = ApproveOrderCommandReply.builder()
                    .orderId(command.getOrderId())
                    .isSuccess(false)
                    .build();
        }

        // send reply to create-order-saga-reply topic
        sendCommandReply(reply);
    }

    private void sendCommandReply(OrderCommandReply reply) {
        kafkaTemplate.send(CREATE_ORDER_SAGA_REPLY_TOPIC, reply);
    }
}
