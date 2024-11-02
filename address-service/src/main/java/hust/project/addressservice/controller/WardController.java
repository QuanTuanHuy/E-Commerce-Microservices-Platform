package hust.project.addressservice.controller;

import hust.project.addressservice.entity.dto.request.CreateWardRequest;
import hust.project.addressservice.entity.dto.request.GetWardRequest;
import hust.project.addressservice.entity.dto.request.UpdateWardRequest;
import hust.project.addressservice.entity.dto.response.Resource;
import hust.project.addressservice.service.IWardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wards")
public class WardController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IWardService wardService;

    @PostMapping
    public ResponseEntity<Resource> createWard(@RequestBody @Valid CreateWardRequest request) {
        return ResponseEntity.ok(new Resource(wardService.createWard(request)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllWards(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "district_id", required = false) Long districtId
    ) {
        var filter = GetWardRequest.builder()
                .page(page).pageSize(pageSize)
                .name(name)
                .districtId(districtId)
                .build();
        return ResponseEntity.ok(new Resource(wardService.getAllWards(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailWard(@PathVariable Long id) {
        return ResponseEntity.ok(new Resource(wardService.getDetailWard(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateWard(
            @PathVariable Long id,
            @RequestBody @Valid UpdateWardRequest request
    ) {
        return ResponseEntity.ok(new Resource(wardService.updateWard(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteWard(@PathVariable Long id) {
        wardService.deleteWard(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
