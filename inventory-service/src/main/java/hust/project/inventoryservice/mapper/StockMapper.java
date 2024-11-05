package hust.project.inventoryservice.mapper;

import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.model.StockModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class StockMapper {
    public static final StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    public abstract StockEntity toEntityFromModel(StockModel model);

    public abstract StockModel toModelFromEntity(StockEntity entity);

    public abstract List<StockEntity> toEntitiesFromModels(List<StockModel> models);

    public abstract List<StockModel> toModelsFromEntities(List<StockEntity> entities);
}
