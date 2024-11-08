package hust.project.orderservice.port;

import hust.project.orderservice.entity.dto.request.UpdateProductQuantityRequest;

import java.util.List;

public interface IProductPort {
    void updateProductQuantity(List<UpdateProductQuantityRequest> requests);
}
