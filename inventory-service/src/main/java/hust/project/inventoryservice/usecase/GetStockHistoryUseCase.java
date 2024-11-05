package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.entity.StockHistoryEntity;
import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.GetStockHistoryRequest;
import hust.project.inventoryservice.entity.dto.request.GetUserListRequest;
import hust.project.inventoryservice.entity.dto.response.PageInfo;
import hust.project.inventoryservice.entity.dto.response.UserInfoResponse;
import hust.project.inventoryservice.port.IStockHistoryPort;
import hust.project.inventoryservice.port.IUserPort;
import hust.project.inventoryservice.port.IWarehousePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetStockHistoryUseCase {
    private final IStockHistoryPort stockHistoryPort;
    private final IWarehousePort warehousePort;
    private final IUserPort userPort;

    public Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter) {
        var result = stockHistoryPort.getAllStockHistories(filter);
        var stockHistories = result.getSecond();

        if (CollectionUtils.isEmpty(stockHistories)) {
            return Pair.of(result.getFirst(), List.of());
        }


        List<Long> userIds = stockHistories.stream()
                .map(StockHistoryEntity::getCreatedById)
                .distinct().toList();

        List<UserInfoResponse> users = userPort.getAllUserInfos(GetUserListRequest.builder()
                .userIds(userIds)
                .build());
        var mapIdToUser = users.stream()
                .collect(Collectors.toMap(UserInfoResponse::getId, Function.identity()));


        List<Long> warehouseIds = stockHistories.stream()
                .map(StockHistoryEntity::getWarehouseId)
                .distinct().toList();

        List<WarehouseEntity> warehouses = warehousePort.getWarehousesByIds(warehouseIds);
        var mapIdToWarehouse = warehouses.stream()
                .collect(Collectors.toMap(WarehouseEntity::getId, Function.identity()));


        stockHistories.forEach(stockHistory -> {
            stockHistory.setWarehouse(mapIdToWarehouse.getOrDefault(stockHistory.getWarehouseId(), null));
            stockHistory.setCreatedBy(mapIdToUser.getOrDefault(stockHistory.getCreatedById(), null));
        });


        return Pair.of(result.getFirst(), stockHistories);
    }
}
