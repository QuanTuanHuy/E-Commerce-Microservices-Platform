package hust.project.addressservice.controller;

import hust.project.addressservice.entity.dto.request.*;
import hust.project.addressservice.entity.dto.response.Resource;
import hust.project.addressservice.service.IAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final IAddressService addressService;

    @PostMapping
    public ResponseEntity<Resource> createAddress(@RequestBody @Valid CreateAddressRequest request) {
        return ResponseEntity.ok(new Resource(addressService.createAddress(request)));
    }

    @GetMapping("/multi_get")
    public ResponseEntity<Resource> getAddressByIds(
            @RequestParam(name = "ids") List<Long> ids
    ) {
        return ResponseEntity.ok(new Resource(addressService.getAddressByIds(ids)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailAddress(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(addressService.getDetailAddress(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateAddress(
            @PathVariable Long id,
            @RequestBody @Valid UpdateAddressRequest request
    ) {
        return ResponseEntity.ok(new Resource(addressService.updateAddress(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
