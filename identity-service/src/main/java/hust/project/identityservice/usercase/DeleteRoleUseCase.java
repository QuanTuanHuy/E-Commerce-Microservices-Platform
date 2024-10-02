package hust.project.identityservice.usercase;

import hust.project.identityservice.port.IRolePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRoleUseCase {
    private final IRolePort rolePort;

    public void deleteRole(Long id) {
        rolePort.deleteById(id);
    }
}
