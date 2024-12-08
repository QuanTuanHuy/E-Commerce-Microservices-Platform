package hust.project.ratingservice.repository.adapter;

import hust.project.ratingservice.port.IOrderPort;
import hust.project.ratingservice.repository.httpclient.IOrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAdapter implements IOrderPort {
    private final IOrderClient orderClient;

    @Override
    public boolean checkOrderExistedByUserId(List<Long> productIds) {
        var response = orderClient.getOrderExistedByUserId(productIds).getBody().getData();

        return response != null;
    }
}
