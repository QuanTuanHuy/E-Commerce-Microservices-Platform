package hust.project.productservice.repository;

import hust.project.productservice.model.ImageModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends IBaseRepository<ImageModel> {
    List<ImageModel> findByEntityIdAndEntityType(Long entityId, String entityType);

    List<ImageModel> findByEntityIdInAndEntityType(List<Long> entityIds, String entityType);

    List<ImageModel> findByIdIn(List<Long> ids);

    void deleteByIdIn(List<Long> ids);
}