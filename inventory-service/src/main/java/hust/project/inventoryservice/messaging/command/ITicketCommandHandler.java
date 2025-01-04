package hust.project.inventoryservice.messaging.command;

import hust.project.common.command.TicketCommand;

public interface ITicketCommandHandler {
    void handleCommand(TicketCommand command);
}
