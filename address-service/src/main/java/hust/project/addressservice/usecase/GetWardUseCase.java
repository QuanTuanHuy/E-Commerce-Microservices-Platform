package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.GetWardRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import hust.project.addressservice.port.IWardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetWardUseCase {
    private final IWardPort wardPort;

    public WardEntity getDetailWard(Long id) {
        return wardPort.getWardById(id);
    }

    public Pair<PageInfo, List<WardEntity>> getAllWards(GetWardRequest filter) {
        return wardPort.getAllWards(filter);
    }
}
