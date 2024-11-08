package hust.project.orderservice.repository.adapter;

import hust.project.orderservice.entity.dto.request.DeleteCartItemRequest;
import hust.project.orderservice.port.ICartItemPort;
import hust.project.orderservice.repository.httpclient.ICartItemClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemAdapter implements ICartItemPort {
    private final ICartItemClient cartItemClient;

    @Override
    public void adjustOrDeleteCartItems(List<DeleteCartItemRequest> requests) {
        cartItemClient.adjustOrDeleteCartItems(requests);
    }
}
