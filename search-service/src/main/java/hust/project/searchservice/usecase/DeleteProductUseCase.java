package hust.project.searchservice.usecase;

import hust.project.searchservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final IProductPort productPort;

    public void deleteProduct(Long id) {
        productPort.deleteProductById(id);
    }
}
