package hust.project.inventoryservice.entity.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTicketRequest {
    private Long orderId;
    private Long customerId;

    private List<CreateTicketItemRequest> ticketItems;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateTicketItemRequest {
        private Long productId;
        private Integer quantity;
    }
}
