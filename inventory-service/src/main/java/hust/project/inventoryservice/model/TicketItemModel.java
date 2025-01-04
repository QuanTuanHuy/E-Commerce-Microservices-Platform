package hust.project.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket_items")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketItemModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "quantity")
    private Integer quantity;
}
