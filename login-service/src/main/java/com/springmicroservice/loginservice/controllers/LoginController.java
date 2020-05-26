package com.springmicroservice.loginservice.controllers;

import com.netflix.discovery.converters.Auto;
import com.springmicroservice.loginservice.dto.LoginRequestDTO;
import com.springmicroservice.loginservice.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api")
@Api(value = "this is the login controller")
public class LoginController {

    //@Autowired
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "this is login api", notes = "request contains a username and password")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO requestDTO, HttpServletRequest request) {
        String token = loginService.login(requestDTO, request);
        return ok().body(token);
    }

    @GetMapping("/test")
    public String test() {
        return "test done";
    }
}
