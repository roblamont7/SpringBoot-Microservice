package com.springmicroservice.loginservice.service;

import com.springmicroservice.loginservice.dto.LoginRequestDTO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    String login(LoginRequestDTO requestDTO, HttpServletRequest request);
}
