package hust.project.inventoryservice.mapper;

import hust.project.inventoryservice.entity.TicketItemEntity;
import hust.project.inventoryservice.model.TicketItemModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TicketItemMapper {
    public static final TicketItemMapper INSTANCE = Mappers.getMapper(TicketItemMapper.class);

    public abstract TicketItemModel toModelFromEntity(TicketItemEntity entity);

    public abstract TicketItemEntity toEntityFromModel(TicketItemModel model);
}
