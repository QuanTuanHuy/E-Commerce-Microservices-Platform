package hust.project.productservice.usecase;

import hust.project.common.event.ProductDeletedEvent;
import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.port.IProductEventPort;
import hust.project.productservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final IProductPort productPort;
    private final IProductEventPort productEventPort;

    @Transactional
    public void deleteProduct(Long id) {
        List<ProductEntity> deleteProducts = new ArrayList<>();

        ProductEntity product = productPort.getProductById(id);
        product.setIsPublished(false);
        deleteProducts.add(product);

        List<ProductEntity> productVariants = productPort.getProductsByParentId(id);
        productVariants.forEach(productVariant -> productVariant.setIsPublished(false));
        deleteProducts.addAll(productVariants);

        productPort.saveAll(deleteProducts);

        deleteProducts.forEach(dp -> productEventPort.publishProductDomainEvent(ProductDeletedEvent.builder()
                        .id(dp.getId())
                .build()));
    }
}
