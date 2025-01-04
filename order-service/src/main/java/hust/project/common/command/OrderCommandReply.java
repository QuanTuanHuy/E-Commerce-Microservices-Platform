package hust.project.common.command;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class OrderCommandReply extends CommandReply {
    protected Long orderId;
}
