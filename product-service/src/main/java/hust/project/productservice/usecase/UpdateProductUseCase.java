package hust.project.productservice.usecase;

import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.productservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
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
            product.setStockQuantity(product.getStockQuantity() + request.getQuantity());
        });


        productPort.saveAll(products);
    }
}
