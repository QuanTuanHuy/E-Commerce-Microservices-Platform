package hust.project.productservice.repository.adapter;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.entity.ProductRelatedEntity;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.mapper.ProductRelatedMapper;
import hust.project.productservice.model.ProductRelatedModel;
import hust.project.productservice.port.IProductRelatedPort;
import hust.project.productservice.repository.IProductRelatedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRelatedAdapter implements IProductRelatedPort {
    private final IProductRelatedRepository productRelatedRepository;


    @Override
    public List<ProductRelatedEntity> saveAll(List<ProductRelatedEntity> productRelatedEntities) {
        try {
            List<ProductRelatedModel> productRelatedModels = ProductRelatedMapper.INSTANCE
                    .toModelsFromEntities(productRelatedEntities);
            return ProductRelatedMapper.INSTANCE.toEntitiesFromModels(
                    productRelatedRepository.saveAll(productRelatedModels)
            );
        } catch (Exception e) {
            log.error("[ProductRelatedAdapter] save product related failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PRODUCT_CATEGORY_FAILED);
        }
    }

    @Override
    public List<ProductRelatedEntity> getProductRelatedByProductId(Long productId) {
        return ProductRelatedMapper.INSTANCE.toEntitiesFromModels(productRelatedRepository.findByProductId(productId));
    }

    @Override
    public void deleteProductRelatedByIds(List<Long> ids) {
        try {
            productRelatedRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            log.error("[ProductRelatedAdapter] delete product related failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_PRODUCT_RELATED_FAILED);
        }
    }

    @Override
    public void deleteProductRelatedByProductId(Long productId) {
        try {
            productRelatedRepository.deleteByProductId(productId);
        } catch (Exception e) {
            log.error("[ProductRelatedAdapter] delete product related failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_PRODUCT_RELATED_FAILED);
        }
    }
}
