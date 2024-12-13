package hust.project.searchservice.usecase;

import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoCompleteProductNameUseCase {
    private final IProductPort productPort;

    public List<ProductEntity> autoCompleteProductName(String keyword) {
        return productPort.autoCompleteProductName(keyword);
    }
}
