package hust.project.productservice.usecase;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.constants.ImageType;
import hust.project.productservice.entity.CategoryEntity;
import hust.project.productservice.entity.ImageEntity;
import hust.project.productservice.entity.dto.request.UpdateCategoryRequest;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.port.ICategoryPort;
import hust.project.productservice.port.IImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateCategoryUseCase {
    private final ICategoryPort categoryPort;
    private final IImagePort imagePort;

    @Transactional
    public CategoryEntity updateCategory(Long id, UpdateCategoryRequest request) {
        CategoryEntity category = categoryPort.getCategoryById(id);

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setSlug(request.getSlug());
        category.setIsPublished(request.getIsPublished());

        if (request.getParentId() != null) {
            try {
                categoryPort.getCategoryById(request.getParentId());
            } catch (Exception e) {
                throw new AppException(ErrorCode.UPDATE_CATEGORY_FAILED);
            }
            category.setParentId(request.getParentId());
        }


        if (StringUtils.hasText(request.getImage())) {
            if (category.getImageId() != null) {
                ImageEntity image = imagePort.getImageById(category.getImageId());
                image.setImage(request.getImage());
                imagePort.save(image);
            } else {
                ImageEntity image = imagePort.save(ImageEntity.builder()
                            .entityType(ImageType.CATEGORY.name())
                            .entityId(id)
                            .image(request.getImage())
                        .build());
                category.setImageId(image.getId());
            }
        } else {
            if (category.getImageId() != null) {
                imagePort.deleteImagesByIds(List.of(category.getImageId()));
            }
        }

        return categoryPort.save(category);
    }
}
