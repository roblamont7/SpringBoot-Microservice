package com.springmicroservice.loginservice.feignInterface;

import com.springmicroservice.loginservice.dto.AdminRequestDTO;
import com.springmicroservice.loginservice.dto.AdminResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "admin-service")
@Service
@RequestMapping(value = "/api")
public interface AdminInterface {

    @RequestMapping(value = "/search")
    AdminResponseDTO searchAdmin(@RequestBody AdminRequestDTO requestDTO);

    @RequestMapping(value = "/update")
    void updateAdmin(@RequestBody AdminResponseDTO responseDTO);
}
