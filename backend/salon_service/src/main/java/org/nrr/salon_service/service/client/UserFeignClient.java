package org.nrr.salon_service.service.client;


import org.nrr.salon_service.payload.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("USER-SERVICE")
public interface UserFeignClient {
    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable Long userId
    )throws Exception;

    @GetMapping("/api/users/profile")
    public ResponseEntity<UserDto> getUserFromJwtToken(
            @RequestHeader("Authorization") String jwt) throws Exception;
}
