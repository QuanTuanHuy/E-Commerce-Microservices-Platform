package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.constants.TicketStatus;
import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.TicketEntity;
import hust.project.inventoryservice.entity.TicketItemEntity;
import hust.project.inventoryservice.entity.dto.request.CreateTicketRequest;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.mapper.TicketMapper;
import hust.project.inventoryservice.port.IStockPort;
import hust.project.inventoryservice.port.ITicketItemPort;
import hust.project.inventoryservice.port.ITicketPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CreateTicketUseCase {
    private final ITicketPort ticketPort;
    private final ITicketItemPort ticketItemPort;
    private final IStockPort stockPort;

    public TicketEntity createTicket(CreateTicketRequest request) {
        TicketEntity ticket = TicketMapper.INSTANCE.toEntityFromRequest(request);
        ticket.setStatus(TicketStatus.ACCEPTED.name());
        ticket = ticketPort.save(ticket);

        final long ticketId = ticket.getId();

        List<TicketItemEntity> ticketItems = new ArrayList<>();

        List<Long> productIds = request.getTicketItems().stream()
                .map(CreateTicketRequest.CreateTicketItemRequest::getProductId).toList();

        List<StockEntity> stocks = stockPort.getStocksByProductIds(productIds);

        request.getTicketItems().forEach(ticketItemRequest -> {
            List<StockEntity> currentStocks = stocks.stream()
                    .filter(stock -> stock.getProductId().equals(ticketItemRequest.getProductId()) && stock.getAvailableQuantity() > 0)
                    .toList();

            Long totalQuantity = currentStocks.stream().map(StockEntity::getAvailableQuantity).reduce(0L, Long::sum);

            if (totalQuantity < ticketItemRequest.getQuantity()) {
                log.info("[CreateTicketUseCase] stock not enough, productId: {}", ticketItemRequest.getProductId());
                throw new AppException(ErrorCode.STOCK_NOT_ENOUGH);
            }

            int requireQuantity = ticketItemRequest.getQuantity();
            for (StockEntity stock : currentStocks) {
                int subtractQuantity = Math.min(stock.getAvailableQuantity().intValue(), requireQuantity);

                stock.setAvailableQuantity(stock.getAvailableQuantity() - subtractQuantity);
                stock.setSoldQuantity(stock.getSoldQuantity() + subtractQuantity);

                TicketItemEntity ticketItem = TicketItemEntity.builder()
                        .ticketId(ticketId)
                        .stockId(stock.getId())
                        .quantity(subtractQuantity)
                        .build();
                ticketItems.add(ticketItem);

                requireQuantity -= subtractQuantity;
                if (requireQuantity == 0) {
                    break;
                }
            }
        });

        ticket.setTicketItems(ticketItemPort.saveAll(ticketItems));
        stockPort.saveAll(stocks);

        return ticket;
    }

}
