package hust.project.productservice.repository.adapter;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.GetProductRequest;
import hust.project.productservice.entity.dto.request.GetProductListRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.mapper.ProductMapper;
import hust.project.productservice.model.ProductModel;
import hust.project.productservice.port.IProductPort;
import hust.project.productservice.repository.IProductRepository;
import hust.project.productservice.repository.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements IProductPort {
    private final IProductRepository productRepository;

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        try {
            ProductModel productModel = ProductMapper.INSTANCE.toModelFromEntity(productEntity);
            return ProductMapper.INSTANCE.toEntityFromModel(productRepository.save(productModel));
        } catch (Exception e) {
            log.error("[ProductAdapter] save product failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PRODUCT_FAILED);
        }
    }

    @Override
    public List<ProductEntity> saveAll(List<ProductEntity> productEntities) {
        try {
            List<ProductModel> productModels = ProductMapper.INSTANCE.toModelsFromEntities(productEntities);
            return ProductMapper.INSTANCE.toEntitiesFromModels(productRepository.saveAll(productModels));
        } catch (Exception e) {
            log.error("[ProductAdapter] save products failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PRODUCT_FAILED);
        }
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return ProductMapper.INSTANCE.toEntityFromModel(productRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[ProductAdapter] get product failed, id: {}", id);
                    return new AppException(ErrorCode.GET_PRODUCT_FAILED);
                }
        ));
    }

    @Override
    public List<ProductEntity> getProductsByIds(List<Long> ids) {
        return ProductMapper.INSTANCE.toEntitiesFromModels(productRepository.findByIdIn(ids));
    }

    @Override
    public List<ProductEntity> getAllProducts(GetProductListRequest filter) {
        return ProductMapper.INSTANCE.toEntitiesFromModels(
                productRepository.findAll(ProductSpecification.getAllProducts(filter)));
    }

    @Override
    public List<ProductEntity> getProductsByParentId(Long parentId) {
        return ProductMapper.INSTANCE.toEntitiesFromModels(productRepository.findByParentId(parentId));
    }

    @Override
    public List<ProductEntity> getProductsByBrandId(Long brandId) {
        return ProductMapper.INSTANCE.toEntitiesFromModels(productRepository.findByBrandId(brandId));
    }

    @Override
    public List<ProductEntity> getProductsByBrandIds(List<Long> brandIds) {
        return ProductMapper.INSTANCE.toEntitiesFromModels(productRepository.findByBrandIdIn(brandIds));
    }

    @Override
    public Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter) {
        var result = productRepository.getAllProducts(filter);

        return Pair.of(result.getFirst(), ProductMapper.INSTANCE.toEntitiesFromModels(result.getSecond()));
    }

}
