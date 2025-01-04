package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.TicketEntity;

public interface ITicketPort {
    TicketEntity getTicketById(Long id);

    TicketEntity save(TicketEntity ticketEntity);
}
