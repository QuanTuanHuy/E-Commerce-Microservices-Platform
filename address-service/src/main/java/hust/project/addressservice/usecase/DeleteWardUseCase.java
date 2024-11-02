package hust.project.addressservice.usecase;

import hust.project.addressservice.port.IWardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteWardUseCase {
    private final IWardPort wardPort;

    public void deleteWard(Long id) {
        wardPort.deleteWard(id);
    }
}
