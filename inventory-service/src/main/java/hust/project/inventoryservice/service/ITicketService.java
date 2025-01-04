package hust.project.inventoryservice.service;

import hust.project.inventoryservice.entity.TicketEntity;
import hust.project.inventoryservice.entity.dto.request.CreateTicketRequest;

public interface ITicketService {
    TicketEntity createTicket(CreateTicketRequest request);

    TicketEntity getDetailTicket(Long id);
}
