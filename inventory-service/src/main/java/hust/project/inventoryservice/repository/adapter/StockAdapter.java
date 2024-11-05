package hust.project.inventoryservice.repository.adapter;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.mapper.StockMapper;
import hust.project.inventoryservice.model.StockModel;
import hust.project.inventoryservice.port.IStockPort;
import hust.project.inventoryservice.repository.IStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockAdapter implements IStockPort {
    private final IStockRepository stockRepository;

    @Override
    public StockEntity save(StockEntity stockEntity) {
        try {
            StockModel stockModel = StockMapper.INSTANCE.toModelFromEntity(stockEntity);
            return StockMapper.INSTANCE.toEntityFromModel(stockRepository.save(stockModel));
        } catch (Exception e) {
            log.error("[StockAdapter] save stock failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_STOCK_FAILED);
        }
    }

    @Override
    public List<StockEntity> saveAll(List<StockEntity> stockEntities) {
        try {
            List<StockModel> stockModels = StockMapper.INSTANCE.toModelsFromEntities(stockEntities);
            return StockMapper.INSTANCE.toEntitiesFromModels(stockRepository.saveAll(stockModels));
        } catch (Exception e) {
            log.error("[StockAdapter] save all stocks failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_STOCK_FAILED);
        }
    }

    @Override
    public StockEntity getStockById(Long id) {
        return StockMapper.INSTANCE.toEntityFromModel(stockRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[StockAdapter] get stock by id failed, id: {}", id);
                    return new AppException(ErrorCode.GET_STOCK_FAILED);
                }
        ));
    }

    @Override
    public List<StockEntity> getStocksByWarehouseId(Long warehouseId) {
        return StockMapper.INSTANCE.toEntitiesFromModels(stockRepository.findByWarehouseId(warehouseId));
    }

    @Override
    public List<StockEntity> getStocksByIds(List<Long> ids) {
        return StockMapper.INSTANCE.toEntitiesFromModels(stockRepository.findByIdIn(ids));
    }

    @Override
    public List<StockEntity> getStocksByWarehouseIdAndProductIdIn(Long warehouseId, List<Long> productIds) {
        return StockMapper.INSTANCE.toEntitiesFromModels(stockRepository.
                findByWarehouseIdAndProductIdIn(warehouseId, productIds));
    }


    @Override
    public void deleteStock(Long id) {
        try {
            stockRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[StockAdapter] delete stock failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_STOCK_FAILED);
        }
    }
}
