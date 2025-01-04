package hust.project.common.command;

import hust.project.common.dto.OrderItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTicketCommand extends TicketCommand {
    private Long orderId;
    private Long customerId;

    private List<OrderItem> orderItems;

}
