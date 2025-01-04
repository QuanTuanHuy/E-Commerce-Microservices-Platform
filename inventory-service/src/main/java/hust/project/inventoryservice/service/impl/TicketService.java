package hust.project.inventoryservice.service.impl;

import hust.project.inventoryservice.entity.TicketEntity;
import hust.project.inventoryservice.entity.dto.request.CreateTicketRequest;
import hust.project.inventoryservice.service.ITicketService;
import hust.project.inventoryservice.usecase.CreateTicketUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {
    private final CreateTicketUseCase createTicketUseCase;

    @Override
    public TicketEntity createTicket(CreateTicketRequest request) {
        return createTicketUseCase.createTicket(request);
    }

    @Override
    public TicketEntity getDetailTicket(Long id) {
        return null;
    }
}
