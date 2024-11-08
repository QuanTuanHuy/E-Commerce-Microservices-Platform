package hust.project.orderservice.repository.adapter;

import hust.project.orderservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.orderservice.port.IProductPort;
import hust.project.orderservice.repository.httpclient.IProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements IProductPort {
    private final IProductClient productClient;

    @Override
    public void updateProductQuantity(List<UpdateProductQuantityRequest> requests) {
        productClient.updateProductQuantity(requests);
    }
}
