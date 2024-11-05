package hust.project.identityservice.controller;

import hust.project.identityservice.entity.dto.request.CreateUserRequest;
import hust.project.identityservice.entity.dto.request.GetUserListRequest;
import hust.project.identityservice.entity.dto.request.GetUserRequest;
import hust.project.identityservice.entity.dto.request.UpdateUserRequest;
import hust.project.identityservice.entity.dto.response.Resource;
import hust.project.identityservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<Resource> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(new Resource(userService.createUser(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailUser(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(userService.getDetailUser(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Resource> getAllUserInfos(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "phone_number", required = false) String phoneNumber,
            @RequestParam(name = "role_id", required = false) Long roleId
    ) {
        var filter = GetUserRequest.builder()
                .page(page).pageSize(pageSize).phoneNumber(phoneNumber).roleId(roleId)
                .build();

        return ResponseEntity.ok(new Resource(userService.getAll(filter)));
    }

    @GetMapping("/list")
    public ResponseEntity<Resource> getAllUserInfos(
            @RequestParam(name = "user_ids", required = false) List<Long> userIds,
            @RequestParam(name = "role_idS", required = false) List<Long> roleIds,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone_number", required = false) String phoneNumber
    ) {
        var filter = GetUserListRequest.builder()
                .userIds(userIds).roleIds(roleIds).email(email).phoneNumber(phoneNumber)
                .build();

        return ResponseEntity.ok(new Resource(userService.getAllUserInfos(filter)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Resource> updateUser(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateUserRequest request
    ) {
        return ResponseEntity.ok(new Resource(userService.updateUserRole(id, request.getRoleId())));
    }
}
