package hust.project.common.event;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryChangedEvent {
    private List<ProductQuantity> productQuantities;
}
