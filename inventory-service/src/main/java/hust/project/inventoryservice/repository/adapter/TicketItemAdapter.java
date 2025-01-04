package hust.project.inventoryservice.repository.adapter;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.TicketItemEntity;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.mapper.TicketItemMapper;
import hust.project.inventoryservice.model.TicketItemModel;
import hust.project.inventoryservice.port.ITicketItemPort;
import hust.project.inventoryservice.repository.ITicketItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketItemAdapter implements ITicketItemPort {
    private final ITicketItemRepository ticketItemRepository;

    @Override
    public List<TicketItemEntity> saveAll(List<TicketItemEntity> ticketItemEntities) {
        try {
            List<TicketItemModel> models = ticketItemEntities.stream()
                    .map(TicketItemMapper.INSTANCE::toModelFromEntity)
                    .toList();

            return ticketItemRepository.saveAll(models).stream()
                    .map(TicketItemMapper.INSTANCE::toEntityFromModel)
                    .toList();
        } catch (Exception e) {
            log.error("[TicketItemAdapter] save all ticket items failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_TICKET_ITEM_FAILED);
        }
    }

    @Override
    public List<TicketItemEntity> getTicketItemsByTicketId(Long ticketId) {
        return ticketItemRepository.findByTicketId(ticketId).stream()
                .map(TicketItemMapper.INSTANCE::toEntityFromModel)
                .toList();
    }
}
