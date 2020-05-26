package com.springmicroservice.adminservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springmicroservice.adminservice.dto.AdminRequestDTO;
import com.springmicroservice.adminservice.dto.ResponseDTO;
import com.springmicroservice.adminservice.service.AdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api")
@Api(value = "this is the admin controller")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }


    @PostMapping(value = "/save")
    @ApiOperation(value = "save new admin")
    @ResponseBody
    public ResponseEntity<?> saveAdmin(@RequestBody AdminRequestDTO requestDTO) {
        adminService.saveAdmin(requestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/search")
    @ApiOperation(value = "search admin by given parameters")
    public ResponseEntity<?> searchAdmin(@ApiParam(value = "Parameter value is admin username") @RequestBody AdminRequestDTO requestDTO) {
        return ok().body(adminService.searchAdmin(requestDTO));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "fetch a list of all active admins")
    public ResponseEntity<?> updateAdmin(@RequestBody AdminRequestDTO requestDTO) {
        return ok().body(adminService.updateAdmin(requestDTO));
    }

    @GetMapping(value = "/fetch-all-admins")
    @ApiOperation(value = "Fetch list of all active admins")
    public ResponseEntity<?> fetchAllAdmins() {
        return ok().body(adminService.fetchAllAdmins());
    }

    @GetMapping(value = "/fetch-admin/{username}")
    public ResponseEntity fetchAdminByUsername(@PathVariable("username")String username) {
        return ok(adminService.fetchAdminByUsername(username));
    }

    @GetMapping(value = "/admins")
    public ResponseEntity<ResponseDTO> getAdminToSendEmail() {
        return new ResponseEntity<>(adminService.adminsToSendEmails(), HttpStatus.OK);
    }

    @GetMapping("/sayHello/{name}")
    @HystrixCommand(fallbackMethod = "fallBackHello", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String sayHello(@PathVariable("name") String name) {
        if (name.equalsIgnoreCase("hello")) {
            throw new RuntimeException();
        }
        return "success METHOD";
    }

}
