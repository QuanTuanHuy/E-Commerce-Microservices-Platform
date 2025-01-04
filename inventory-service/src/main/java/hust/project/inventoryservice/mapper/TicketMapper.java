package hust.project.inventoryservice.mapper;

import hust.project.inventoryservice.entity.TicketEntity;
import hust.project.inventoryservice.entity.dto.request.CreateTicketRequest;
import hust.project.inventoryservice.model.TicketModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TicketMapper {
    public static final TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    public abstract TicketModel toModelFromEntity(TicketEntity entity);

    public abstract TicketEntity toEntityFromModel(TicketModel model);

    @Mapping(target = "ticketItems", ignore = true)
    public abstract TicketEntity toEntityFromRequest(CreateTicketRequest request);
}
