package hust.project.identityservice.service;

import hust.project.identityservice.entity.dto.request.AuthenticationRequest;
import hust.project.identityservice.entity.dto.request.IntrospectRequest;
import hust.project.identityservice.entity.dto.response.AuthenticationResponse;
import hust.project.identityservice.entity.dto.response.IntrospectResponse;

public interface IAuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
