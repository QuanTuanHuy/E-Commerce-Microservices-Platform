package hust.project.orderservice.saga.createorder;

import hust.project.common.command.*;
import hust.project.common.event.OrderCreatedEvent;
import hust.project.orderservice.constant.ErrorCode;
import hust.project.orderservice.exception.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderSaga {
    private final ICreateOrderSagaDataRepository createOrderSagaDataRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.inventory-service-request}")
    private String INVENTORY_SERVICE_REQUEST_TOPIC;

    @Value("${kafka.topic.order-service-request}")
    private String ORDER_SERVICE_REQUEST_TOPIC;

    @KafkaListener(topics = "${kafka.topic.order-event}")
    @Transactional
    public void initSaga(OrderCreatedEvent event) {
        log.info("[CreateOrderSaga] Init saga for order {}", event.getOrderId());

        CreateOrderSagaData sagaData = CreateOrderSagaData.builder()
                .orderId(event.getOrderId())
                .customerId(event.getCustomerId())
                .totalPrice(event.getTotalPrice())
                .orderItems(event.getOrderItems())
                .shippingAddress(event.getShippingAddress())
                .build();

        sagaData = createOrderSagaDataRepository.save(sagaData);

        // send command create ticket in inventory service
        CreateTicketCommand createTicketCommand = CreateTicketCommand.builder()
                .orderId(sagaData.getOrderId())
                .customerId(sagaData.getCustomerId())
                .orderItems(sagaData.getOrderItems())
                .build();

        sendTicketCommand(createTicketCommand);
    }

    @KafkaListener(topics = "${kafka.topic.create-order-saga-reply}")
    @Transactional
    public void handleReply(CommandReply reply) {
        log.info("[CreateOrderSaga] Handle reply {}", reply);
        if (reply instanceof CreateTicketCommandReply) {
            handleCreateTicketCommandReply((CreateTicketCommandReply) reply);
        } else if (reply instanceof OrderCommandReply) {
            handleOrderCommandReply((OrderCommandReply) reply);
        } else {
            log.info("[CreateOrderSaga] Invalid reply");
        }
    }

    private void handleOrderCommandReply(OrderCommandReply reply) {
        if (reply.getIsSuccess().equals(Boolean.TRUE)) {
            log.info("[CreateOrderSaga] command success");
        } else {
            log.info("[CreateOrderSaga] command failed");
        }
    }

    private void handleCreateTicketCommandReply(CreateTicketCommandReply reply) {
        CreateOrderSagaData sagaData = getCreateOrderSagaData(reply.getOrderId());
        OrderCommand command;

        if (reply.getIsSuccess().equals(Boolean.TRUE)) {
            sagaData.setTicketId(reply.getTicketId());
            createOrderSagaDataRepository.save(sagaData);

            // send approve order command
            command = ApproveOrderCommand.builder()
                    .orderId(sagaData.getOrderId())
                    .build();

        } else {
            // send reject order command
            command = RejectOrderCommand.builder()
                    .orderId(sagaData.getOrderId())
                    .build();
        }

        sendOrderCommand(command);
        log.info("[CreateOrderSaga] Send order command{}", command);
    }

    private void sendTicketCommand(TicketCommand command) {
        log.info("[CreateOrderSaga] Send command {}", command);
        kafkaTemplate.send(INVENTORY_SERVICE_REQUEST_TOPIC, command);
    }

    private void sendOrderCommand(OrderCommand command) {
        kafkaTemplate.send(ORDER_SERVICE_REQUEST_TOPIC, command);
    }

    private CreateOrderSagaData getCreateOrderSagaData(long orderId) {
        return createOrderSagaDataRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.CREATE_ORDER_SAGA_DATA_NOT_FOUND));
    }

}
