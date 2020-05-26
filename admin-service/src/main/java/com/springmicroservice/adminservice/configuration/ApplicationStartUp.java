package com.springmicroservice.adminservice.configuration;

import com.springmicroservice.adminservice.entities.Admin;
import com.springmicroservice.adminservice.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationStartUp {

    @Autowired
    private StartupProperties startupProperties;

    @Bean
    public CommandLineRunner loadData(AdminRepository adminRepository) {
        return (args) -> {
            List<Admin> admins = adminRepository.findAll();

            if (ObjectUtils.isEmpty(admins)) {
                adminRepository.save(saveAdmin());
            }
        };
    }

    public Admin saveAdmin() {
        Admin admin = new Admin();
        admin.setId(startupProperties.getId());
        admin.setUsername(startupProperties.getUsername());
        admin.setPassword(BCrypt.hashpw(startupProperties.getPassword(), BCrypt.gensalt()));
        admin.setEmailAddress(startupProperties.getEmailAddress());
        admin.setStatus(startupProperties.getStatus());
        admin.setLoginAttempt(startupProperties.getLoginAttempt());
        //admin.setRoles(Arrays.asList("ADMIN"));
        return admin;
    }
}
