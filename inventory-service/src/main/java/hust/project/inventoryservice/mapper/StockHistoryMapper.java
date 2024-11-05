package hust.project.inventoryservice.mapper;

import hust.project.inventoryservice.entity.StockHistoryEntity;
import hust.project.inventoryservice.model.StockHistoryModel;
import hust.project.inventoryservice.model.StockModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class StockHistoryMapper {
    public static final StockHistoryMapper INSTANCE = Mappers.getMapper(StockHistoryMapper.class);

    public abstract StockHistoryEntity toEntityFromModel(StockModel model);

    public abstract StockHistoryModel toModelFromEntity(StockHistoryEntity entity);

    public abstract List<StockHistoryEntity> toEntitiesFromModels(List<StockHistoryModel> models);

    public abstract List<StockHistoryModel> toModelsFromEntities(List<StockHistoryEntity> entities);
}
