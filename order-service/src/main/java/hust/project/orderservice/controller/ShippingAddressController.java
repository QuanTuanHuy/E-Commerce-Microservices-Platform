package hust.project.orderservice.controller;

import hust.project.orderservice.entity.dto.request.GetShippingAddressRequest;
import hust.project.orderservice.entity.dto.response.Resource;
import hust.project.orderservice.service.IShippingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipping_addresses")
public class ShippingAddressController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IShippingAddressService shippingAddressService;


    @GetMapping
    public ResponseEntity<Resource> getAll(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "contact_name", required = false) String contactName,
            @RequestParam(name = "phone_number", required = false) String phoneNumber,
            @RequestParam(name = "province_id", required = false) Long provinceId,
            @RequestParam(name = "district_id", required = false) Long districtId,
            @RequestParam(name = "ward_id", required = false) Long wardId
    ) {
        var filter = GetShippingAddressRequest.builder()
                .page(page).pageSize(pageSize)
                .contactName(contactName)
                .phoneNumber(phoneNumber)
                .provinceId(provinceId)
                .districtId(districtId)
                .wardId(wardId)
                .build();
        return ResponseEntity.ok(new Resource(shippingAddressService.getAll(filter)));
    }
}
