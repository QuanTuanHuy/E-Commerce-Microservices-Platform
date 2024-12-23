package hust.project.orderservice.repository.adapter;

import hust.project.orderservice.constant.ErrorCode;
import hust.project.orderservice.entity.ProductEntity;
import hust.project.orderservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.orderservice.exception.AppException;
import hust.project.orderservice.mapper.ProductMapper;
import hust.project.orderservice.model.ProductModel;
import hust.project.orderservice.port.IProductPort;
import hust.project.orderservice.repository.IProductRepository;
import hust.project.orderservice.repository.httpclient.IProductClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements IProductPort {
    private final IProductClient productClient;

    private final IProductRepository productRepository;

    @Override
    public void updateProductQuantity(List<UpdateProductQuantityRequest> requests) {
        productClient.updateProductQuantity(requests);
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        try {
            ProductModel productModel = ProductMapper.INSTANCE.toModel(productEntity);
            return ProductMapper.INSTANCE.toEntity(productRepository.save(productModel));
        } catch (Exception e) {
            log.error("[ProductAdapter] save product failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PRODUCT_FAILED);
        }
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return ProductMapper.INSTANCE.toEntity(productRepository.findById(id).orElseThrow(() -> {
            log.error("[ProductAdapter] get product by id failed, id: {}", id);
            return new AppException(ErrorCode.GET_PRODUCT_FAILED);
        }));
    }

    @Override
    public List<ProductEntity> getProductByIds(List<Long> ids) {
        return productRepository.findByIdIn(ids).stream()
                .map(ProductMapper.INSTANCE::toEntity)
                .toList();
    }

    @Override
    public void deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[ProductAdapter] delete product failed, id: {}, err: {}", id, e.getMessage());
            throw new AppException(ErrorCode.DELETE_PRODUCT_FAILED);
        }
    }
}
