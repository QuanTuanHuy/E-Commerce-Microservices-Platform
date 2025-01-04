package hust.project.common.command;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class CommandReply {
    protected Boolean isSuccess;
    protected String message;
}
