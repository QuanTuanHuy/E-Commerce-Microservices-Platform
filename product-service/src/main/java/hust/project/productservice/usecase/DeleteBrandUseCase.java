package hust.project.productservice.usecase;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.port.IBrandPort;
import hust.project.productservice.port.IImagePort;
import hust.project.productservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteBrandUseCase {
    private final IBrandPort brandPort;
    private final IImagePort imagePort;
    private final IProductPort productPort;

    @Transactional
    public void deleteBrand(Long id) {
        List<ProductEntity> products = productPort.getProductsByBrandId(id);
        if (!products.isEmpty()) {
            log.error("[DeleteBrandUseCase] delete brand failed, brand has products, id: {}", id);
            throw new AppException(ErrorCode.DELETE_BRAND_FAILED);
        }

        BrandEntity brand = brandPort.getBrandById(id);
        brandPort.deleteBrand(id);

        if (brand.getImageId() != null) {
            imagePort.deleteImagesByIds(List.of(brand.getImageId()));
        }
    }
}
