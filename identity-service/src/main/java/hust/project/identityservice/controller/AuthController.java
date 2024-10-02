package hust.project.identityservice.controller;

import hust.project.identityservice.entity.dto.request.AuthenticationRequest;
import hust.project.identityservice.entity.dto.request.IntrospectRequest;
import hust.project.identityservice.entity.dto.response.Resource;
import hust.project.identityservice.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/introspect")
    public ResponseEntity<Resource> introspect(@RequestBody IntrospectRequest request) {
        return ResponseEntity.ok(new Resource(authenticationService.introspect(request)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Resource> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new Resource(authenticationService.authenticate(request)));
    }

}
