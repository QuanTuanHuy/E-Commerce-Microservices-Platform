package hust.project.productservice.usecase;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.constants.ImageType;
import hust.project.productservice.entity.CategoryEntity;
import hust.project.productservice.entity.ImageEntity;
import hust.project.productservice.entity.dto.request.CreateCategoryRequest;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.mapper.CategoryMapper;
import hust.project.productservice.port.ICategoryPort;
import hust.project.productservice.port.IImagePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateCategoryUseCase {
    private final ICategoryPort categoryPort;
    private final IImagePort imagePort;

    @Transactional
    public CategoryEntity createCategory(CreateCategoryRequest request) {
        CategoryEntity category = CategoryMapper.INSTANCE.toEntityFromRequest(request);

        ImageEntity image = null;
        if (StringUtils.hasText(request.getImage())) {
            image = imagePort.save(ImageEntity.builder()
                    .entityType(ImageType.CATEGORY.name())
                    .image(request.getImage())
                    .build());

            category.setImageId(image.getId());
        }

        if (request.getParentId() != null) {
            try {
                categoryPort.getCategoryById(request.getParentId());
            } catch (Exception e) {
                log.error("[CreateCategoryUseCase] get parent category failed, id: {}", request.getParentId());
                throw new AppException(ErrorCode.CREATE_CATEGORY_FAILED);
            }
        }

        CategoryEntity savedCategory = categoryPort.save(category);
        savedCategory.setImage(image);

        return savedCategory;
    }
}
