package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.port.IStockPort;
import hust.project.inventoryservice.port.IWarehouseAddressPort;
import hust.project.inventoryservice.port.IWarehousePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteWarehouseUseCase {
    private final IWarehousePort warehousePort;
    private final IWarehouseAddressPort warehouseAddressPort;
    private final IStockPort stockPort;

    public void deleteWarehouse(Long id) {
        WarehouseEntity warehouse = warehousePort.getWarehouseById(id);

        //TODO: check if warehouse has stock
        List<StockEntity> stocks = stockPort.getStocksByWarehouseId(id);
        if (!CollectionUtils.isEmpty(stocks)) {
            log.error("[DeleteWarehouseUseCase] Warehouse has stock, cannot delete");
            throw new AppException(ErrorCode.DELETE_WAREHOUSE_FAILED);
        }

        warehouseAddressPort.deleteWarehouseAddress(warehouse.getAddressId());
        warehousePort.deleteWarehouse(id);
    }
}
