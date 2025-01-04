package hust.project.inventoryservice.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity {
    private Long id;
    private Long orderId;
    private Long customerId;

    private String status;

    private List<TicketItemEntity> ticketItems;
}
