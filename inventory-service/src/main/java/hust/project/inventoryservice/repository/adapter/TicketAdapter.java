package hust.project.inventoryservice.repository.adapter;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.TicketEntity;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.mapper.TicketMapper;
import hust.project.inventoryservice.model.TicketModel;
import hust.project.inventoryservice.port.ITicketPort;
import hust.project.inventoryservice.repository.ITicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketAdapter implements ITicketPort {
    private final ITicketRepository ticketRepository;

    @Override
    public TicketEntity getTicketById(Long id) {
        return TicketMapper.INSTANCE.toEntityFromModel(ticketRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.GET_TICKET_FAILED)));
    }

    @Override
    public TicketEntity save(TicketEntity ticketEntity) {
        try {
            TicketModel model = TicketMapper.INSTANCE.toModelFromEntity(ticketEntity);
            return TicketMapper.INSTANCE.toEntityFromModel(ticketRepository.save(model));
        } catch (Exception e) {
            log.error("[TicketAdapter] save ticket failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_TICKET_FAILED);
        }
    }
}
