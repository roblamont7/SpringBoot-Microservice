package com.springmicroservice.gatewayservice.feignInterface;

import com.springmicroservice.gatewayservice.dto.AdminResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@FeignClient(name = "admin-service")
@Service
@RequestMapping("/api")
public interface AdminInterface {

    @RequestMapping(value = "/fetch-admin/{username}")
    Optional<AdminResponseDTO> fetchAdminByUsername(@PathVariable("username") String username);
}
