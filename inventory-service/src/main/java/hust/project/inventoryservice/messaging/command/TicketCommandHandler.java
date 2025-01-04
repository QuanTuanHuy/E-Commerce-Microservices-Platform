package hust.project.inventoryservice.messaging.command;

import hust.project.common.command.CreateTicketCommand;
import hust.project.common.command.CreateTicketCommandReply;
import hust.project.common.command.TicketCommand;
import hust.project.common.command.TicketCommandReply;
import hust.project.inventoryservice.entity.TicketEntity;
import hust.project.inventoryservice.entity.dto.request.CreateTicketRequest;
import hust.project.inventoryservice.service.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${kafka.topic.inventory-service-request}")
public class TicketCommandHandler implements ITicketCommandHandler {
    private final ITicketService ticketService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.create-order-saga-reply}")
    private String CREATE_ORDER_SAGA_REPLY_TOPIC;

    @Override
    @KafkaHandler
    public void handleCommand(TicketCommand command) {
        if (command instanceof CreateTicketCommand) {
            createTicket((CreateTicketCommand) command);
        }
    }

    private void createTicket(CreateTicketCommand command) {
        CreateTicketRequest request = CreateTicketRequest.builder()
                .orderId(command.getOrderId())
                .customerId(command.getCustomerId())
                .ticketItems(command.getOrderItems().stream()
                        .map(oi -> CreateTicketRequest.CreateTicketItemRequest.builder()
                                .productId(oi.getProductId())
                                .quantity(oi.getQuantity())
                                .build())
                        .toList()
                )
                .build();

        TicketCommandReply reply;

        try {
            TicketEntity ticket = ticketService.createTicket(request);

            reply = CreateTicketCommandReply.builder()
                    .isSuccess(true)
                    .ticketId(ticket.getId())
                    .orderId(ticket.getOrderId())
                    .build();
        } catch (Exception e) {
            reply = CreateTicketCommandReply.builder()
                    .orderId(command.getOrderId())
                    .isSuccess(false)
                    .build();
        }

        kafkaTemplate.send(CREATE_ORDER_SAGA_REPLY_TOPIC, reply);
    }
}
