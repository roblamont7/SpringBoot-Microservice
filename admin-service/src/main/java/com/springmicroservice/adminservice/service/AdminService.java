package com.springmicroservice.adminservice.service;

import com.springmicroservice.adminservice.dto.AdminRequestDTO;
import com.springmicroservice.adminservice.dto.AdminResponseDTO;
import com.springmicroservice.adminservice.dto.ResponseDTO;
import com.springmicroservice.adminservice.entities.Admin;

import java.util.List;

public interface AdminService {

    void saveAdmin(AdminRequestDTO requestDTO);
    AdminResponseDTO searchAdmin(AdminRequestDTO requestDTO);
    Admin updateAdmin(AdminRequestDTO requestDTO);
    Admin fetchAdminByUsername(String username);
    ResponseDTO adminsToSendEmails();
    List<Admin> fetchAllAdmins();
}
