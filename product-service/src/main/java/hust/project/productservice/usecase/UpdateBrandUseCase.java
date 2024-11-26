package hust.project.productservice.usecase;

import hust.project.productservice.constants.ImageType;
import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.entity.ImageEntity;
import hust.project.productservice.entity.dto.request.UpdateBrandRequest;
import hust.project.productservice.port.IBrandPort;
import hust.project.productservice.port.IImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateBrandUseCase {
    private final IBrandPort brandPort;
    private final IImagePort imagePort;

    @Transactional
    public BrandEntity updateBrand(Long id, UpdateBrandRequest request) {
        BrandEntity brand = brandPort.getBrandById(id);

        brand.setName(request.getName());
        brand.setSlug(request.getSlug());
        brand.setIsPublished(request.getIsPublished());


        if (StringUtils.hasText(request.getImage())) {
            if (brand.getImageId() != null) {
                ImageEntity existedImage = imagePort.getImageById(brand.getImageId());
                existedImage.setImage(request.getImage());

                brand.setImage(imagePort.save(existedImage));
            } else {
                ImageEntity image = imagePort.save(ImageEntity.builder()
                                .entityId(brand.getId())
                                .entityType(ImageType.BRAND.name())
                                .image(request.getImage())
                        .build());
                brand.setImage(image);
            }
        } else {
            if (brand.getImageId() != null) {
                imagePort.deleteImagesByIds(List.of(brand.getImageId()));
            }
        }

        return brandPort.save(brand);
    }
}
