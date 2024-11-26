package hust.project.productservice.usecase;

import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.entity.ImageEntity;
import hust.project.productservice.entity.dto.request.GetBrandRequest;
import hust.project.productservice.entity.dto.response.BrandGetModel;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.mapper.BrandMapper;
import hust.project.productservice.port.IBrandPort;
import hust.project.productservice.port.IImagePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetBrandUseCase {
    private final IBrandPort brandPort;
    private final IImagePort imagePort;

    public BrandEntity getDetailBrand(Long id) {
        BrandEntity brand = brandPort.getBrandById(id);

        if (brand.getImageId() != null) {
            brand.setImage(imagePort.getImageById(brand.getImageId()));
        }

        return brand;
    }


    public Pair<PageInfo, List<BrandEntity>> getAllBrands(GetBrandRequest filter) {
        var result = brandPort.getAllBrands(filter);

        List<Long> imageIds = result.getSecond().stream()
                .map(BrandEntity::getImageId)
                .filter(Objects::nonNull)
                .toList();

        if (!imageIds.isEmpty()) {
            List<ImageEntity> images = imagePort.getImagesByIds(imageIds);

            var mapIdToImage = images.stream()
                    .collect(Collectors.toMap(ImageEntity::getId, Function.identity()));


            result.getSecond().forEach(brand -> {
                if (brand.getImageId() != null) {
                    brand.setImage(mapIdToImage.getOrDefault(brand.getImageId(), null));
                }
            });
        }

        return result;
    }

    public List<BrandGetModel> getBrandsByIds(List<Long> ids) {
        return brandPort.getBrandsByIds(ids).stream()
                .map(BrandMapper.INSTANCE::toGetModelFromEntity)
                .toList();
    }

}
