package hust.project.identityservice.repository;

import hust.project.identityservice.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleModel, Long> {
}
