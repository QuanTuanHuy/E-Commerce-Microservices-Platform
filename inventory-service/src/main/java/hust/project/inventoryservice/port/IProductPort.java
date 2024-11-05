package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.dto.request.GetProductListRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.inventoryservice.entity.dto.response.ProductThumbnailResponse;

import java.util.List;

public interface IProductPort {
    List<ProductThumbnailResponse> getProductList(GetProductListRequest request);

    void updateProductQuantity(List<UpdateProductQuantityRequest> requests);
}
