package hust.project.productservice.usecase;

import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.port.IBrandPort;
import hust.project.productservice.port.IImagePort;
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

    @Transactional
    public void deleteBrand(Long id) {
        // TODO: check if brand is used in product

        BrandEntity brand = brandPort.getBrandById(id);
        brandPort.deleteBrand(id);

        if (brand.getImageId() != null) {
            imagePort.deleteImagesByIds(List.of(brand.getImageId()));
        }
    }
}
