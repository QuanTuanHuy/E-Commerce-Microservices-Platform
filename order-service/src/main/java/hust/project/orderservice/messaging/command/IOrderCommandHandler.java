package hust.project.orderservice.messaging.command;

import hust.project.common.command.OrderCommand;

public interface IOrderCommandHandler {
    void handleCommand(OrderCommand command);
}
