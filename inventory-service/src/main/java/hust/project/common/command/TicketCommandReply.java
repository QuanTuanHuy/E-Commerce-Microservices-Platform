package hust.project.common.command;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public abstract class TicketCommandReply extends CommandReply {
    protected Long orderId;
    protected Long ticketId;
}
