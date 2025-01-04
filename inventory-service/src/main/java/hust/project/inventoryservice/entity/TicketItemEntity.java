package hust.project.inventoryservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketItemEntity {
    private Long id;
    private Long ticketId;
    private Long stockId;
    private Integer quantity;
}
