package hust.project.productservice.usecase;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UpdateProductUseCase {
    private final IProductPort productPort;

    public void updateProductQuantity(List<UpdateProductQuantityRequest> requests) {
        List<Long> productIds = requests.stream()
                .map(UpdateProductQuantityRequest::getProductId)
                .distinct().toList();

        List<ProductEntity> products = productPort.getProductsByIds(productIds);

        var mapIdToProduct = products.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));


        requests.forEach(request -> {
            ProductEntity product = mapIdToProduct.get(request.getProductId());
            long newQuantity = product.getStockQuantity() + request.getQuantity();
            if (newQuantity < 0) {
                log.error("[UpdateProductUseCase] not enough quantity for product: {}", product.getId());
                throw new AppException(ErrorCode.UPDATE_PRODUCT_QUANTITY_FAILED);
            }
            product.setStockQuantity(newQuantity);
        });


        productPort.saveAll(products);
    }
}
