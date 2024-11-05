package hust.project.inventoryservice.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWarehouseRequest {
    private Integer page;
    private Integer pageSize;
    private String name;

    private String provinceName;
    private String districtName;
    private String wardName;
}
