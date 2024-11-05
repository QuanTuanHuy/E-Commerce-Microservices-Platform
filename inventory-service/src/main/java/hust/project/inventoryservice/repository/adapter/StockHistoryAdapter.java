package hust.project.inventoryservice.repository.adapter;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.StockHistoryEntity;
import hust.project.inventoryservice.entity.dto.request.GetStockHistoryRequest;
import hust.project.inventoryservice.entity.dto.response.PageInfo;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.mapper.StockHistoryMapper;
import hust.project.inventoryservice.model.StockHistoryModel;
import hust.project.inventoryservice.port.IStockHistoryPort;
import hust.project.inventoryservice.repository.IStockHistoryRepository;
import hust.project.inventoryservice.repository.specification.StockHistorySpecification;
import hust.project.inventoryservice.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockHistoryAdapter implements IStockHistoryPort {
    private final IStockHistoryRepository stockHistoryRepository;

    @Override
    public void saveAll(List<StockHistoryEntity> stockHistoryEntities) {
        try {
            List<StockHistoryModel> stockHistoryModels = StockHistoryMapper.INSTANCE.toModelsFromEntities(stockHistoryEntities);
            stockHistoryRepository.saveAll(stockHistoryModels);
        } catch (Exception e) {
            throw new AppException(ErrorCode.CREATE_STOCK_HISTORY_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by("id").descending());

        var result = stockHistoryRepository.findAll(StockHistorySpecification.getAllStockHistories(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, StockHistoryMapper.INSTANCE.toEntitiesFromModels(result.getContent()));
    }
}
