package hust.project.productservice.usecase;

import hust.project.productservice.constants.ImageType;
import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.entity.ImageEntity;
import hust.project.productservice.entity.dto.request.CreateBrandRequest;
import hust.project.productservice.mapper.BrandMapper;
import hust.project.productservice.port.IBrandPort;
import hust.project.productservice.port.IImagePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateBrandUseCase {
    private final IBrandPort brandPort;
    private final IImagePort imagePort;

    @Transactional
    public BrandEntity createBrand(CreateBrandRequest request) {
        BrandEntity brand = BrandMapper.INSTANCE.toEntityFromRequest(request);

        ImageEntity image = null;
        if (StringUtils.hasText(request.getImage())) {
            image = imagePort.save(ImageEntity.builder()
                            .entityType(ImageType.BRAND.name())
                            .image(request.getImage())
                    .build());

            brand.setImageId(image.getId());
        }

        brand = brandPort.save(brand);
        brand.setImage(image);

        return brand;
    }
}
