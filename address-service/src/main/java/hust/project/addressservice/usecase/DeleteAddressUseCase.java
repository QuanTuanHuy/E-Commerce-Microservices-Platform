package hust.project.addressservice.usecase;

import hust.project.addressservice.port.IAddressPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteAddressUseCase {
    private final IAddressPort addressPort;

    public void deleteAddress(Long id) {
        addressPort.deleteAddress(id);
    }
}
