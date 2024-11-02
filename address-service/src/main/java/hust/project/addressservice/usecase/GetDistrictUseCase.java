package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.dto.request.GetDistrictRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import hust.project.addressservice.port.IDistrictPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDistrictUseCase {
    private final IDistrictPort districtPort;

    public DistrictEntity getDetailDistrict(Long id) {
        return districtPort.getDistrictById(id);
    }

    public Pair<PageInfo, List<DistrictEntity>> getAllDistricts(GetDistrictRequest filter) {
        return districtPort.getAllDistricts(filter);
    }
}
