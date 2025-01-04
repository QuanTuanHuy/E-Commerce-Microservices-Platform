package hust.project.inventoryservice.repository;

import hust.project.inventoryservice.model.TicketItemModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketItemRepository extends IBaseRepository<TicketItemModel> {
    List<TicketItemModel> findByTicketId(Long ticketId);
}
