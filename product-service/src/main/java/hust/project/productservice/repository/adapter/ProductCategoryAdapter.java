package hust.project.productservice.repository.adapter;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.entity.ProductCategoryEntity;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.mapper.ProductCategoryMapper;
import hust.project.productservice.model.ProductCategoryModel;
import hust.project.productservice.port.IProductCategoryPort;
import hust.project.productservice.repository.IProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryAdapter implements IProductCategoryPort {
    private final IProductCategoryRepository productCategoryRepository;


    @Override
    public List<ProductCategoryEntity> saveAll(List<ProductCategoryEntity> productCategoryEntities) {
        try {
            List<ProductCategoryModel> productCategoryModels =
                    ProductCategoryMapper.INSTANCE.toModelsFromEntities(productCategoryEntities);
            return ProductCategoryMapper.INSTANCE.toEntitiesFromModels(
                    productCategoryRepository.saveAll(productCategoryModels));
        } catch (Exception e) {
            log.error("[ProductCategoryAdapter] save product category failed, err: {} ", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PRODUCT_CATEGORY_FAILED);
        }
    }

    @Override
    public List<ProductCategoryEntity> getProductCategoriesByProductId(Long productId) {
        return ProductCategoryMapper.INSTANCE.toEntitiesFromModels(productCategoryRepository.findByProductId(productId));
    }

    @Override
    public List<ProductCategoryEntity> getProductCategoriesByProductIds(List<Long> productIds) {
        return ProductCategoryMapper.INSTANCE.toEntitiesFromModels(
                productCategoryRepository.findByProductIdIn(productIds)
        );
    }

    @Override
    public List<ProductCategoryEntity> getProductCategoriesByCategoryId(Long categoryId) {
        return ProductCategoryMapper.INSTANCE.toEntitiesFromModels(productCategoryRepository.findByCategoryId(categoryId));
    }

    @Override
    public void deleteProductCategoriesByIds(List<Long> ids) {
        try {
            productCategoryRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            log.error("[ProductCategoryAdapter] delete product category failed, err: {} ", e.getMessage());
            throw new AppException(ErrorCode.DELETE_PRODUCT_CATEGORY_FAILED);
        }
    }
}
