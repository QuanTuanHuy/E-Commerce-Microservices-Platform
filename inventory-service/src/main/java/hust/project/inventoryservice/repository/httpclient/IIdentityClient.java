package hust.project.inventoryservice.repository.httpclient;

import hust.project.inventoryservice.config.FeignClientConfig;
import hust.project.inventoryservice.entity.dto.response.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "identity-service",
        url = "${app.services.identity-service.url}",
        configuration = FeignClientConfig.class
)
public interface IIdentityClient {
    @GetMapping("/api/v1/users/list")
    ResponseEntity<Resource> getAllUserInfos(
            @RequestParam(name = "user_ids") List<Long> userIds
    );
}
