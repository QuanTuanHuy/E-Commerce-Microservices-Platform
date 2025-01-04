package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.TicketItemEntity;

import java.util.List;

public interface ITicketItemPort {
    List<TicketItemEntity> saveAll(List<TicketItemEntity> ticketItemEntities);

    List<TicketItemEntity> getTicketItemsByTicketId(Long ticketId);
}
