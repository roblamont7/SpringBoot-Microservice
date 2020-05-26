package com.springmicroservice.adminservice.service;

import com.springmicroservice.adminservice.constants.ErrorMessageConstants;
import com.springmicroservice.adminservice.dto.AdminRequestDTO;
import com.springmicroservice.adminservice.dto.AdminResponseDTO;
import com.springmicroservice.adminservice.dto.ResponseDTO;
import com.springmicroservice.adminservice.entities.Admin;
import com.springmicroservice.adminservice.exceptions.NoContentFoundException;
import com.springmicroservice.adminservice.repository.AdminRepository;
import com.springmicroservice.adminservice.utility.AdminUtils;
import com.springmicroservice.adminservice.utility.MapperUtility;
import com.springmicroservice.adminservice.utility.QueryCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService{

    @Autowired
    private final AdminRepository adminRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void saveAdmin(AdminRequestDTO requestDTO) {
        Admin admin = MapperUtility.map(requestDTO, Admin.class);
        admin.setPassword(BCrypt.hashpw(requestDTO.getPassword(), BCrypt.gensalt()));
        admin.setLoginAttempt(0);
        admin.setCreatedDate(new Date());
        //admin.setRoles(Arrays.asList("ROLE_USER"));
        adminRepository.save(admin);
    }

    @Override
    public AdminResponseDTO searchAdmin(AdminRequestDTO requestDTO) throws NoContentFoundException {
        List<Object[]> results = entityManager.createNativeQuery(QueryCreator.createQueryToFetchAdminDetails.apply(requestDTO)).getResultList();

        if (ObjectUtils.isEmpty(results)) {
            throw new NoContentFoundException(ErrorMessageConstants.AdminNotFoundException.MESSAGE, ErrorMessageConstants.AdminNotFoundException.DEVELOPER_MESSAGE);
        }

        return AdminUtils.convertToAdminResponse.apply(results);
    }

    @Override
    public Admin updateAdmin(AdminRequestDTO requestDTO) {
        Admin admin = this.adminRepository.getAdminById(requestDTO.getId()).orElseThrow(() -> {
           return new NoContentFoundException(ErrorMessageConstants.AdminNotFoundException.MESSAGE, ErrorMessageConstants.AdminNotFoundException.DEVELOPER_MESSAGE);
        });

        admin.setStatus(requestDTO.getStatus());
        admin.setLoginAttempt(requestDTO.getLoginAttempt());
        admin.setLastModifiedDate(new Date());

        return adminRepository.save(admin);
    }

    @Override
    public Admin fetchAdminByUsername(String username) {
        return adminRepository.getAdminByUsername(username).orElseThrow(() ->
                new NoContentFoundException(ErrorMessageConstants.AdminNotFoundException.MESSAGE, ErrorMessageConstants.AdminNotFoundException.DEVELOPER_MESSAGE));
    }

    @Override
    public ResponseDTO adminsToSendEmails() {
        List<Object[]> results = entityManager.createNativeQuery(QueryCreator.createQueryToFetchAdminsToSendEmail.get()).getResultList();
        List<AdminResponseDTO> responseDTOS = results.stream().map(AdminUtils.convertToResponse)
                .collect(Collectors.toList());
        return ResponseDTO.builder().adminResponseDTOS(responseDTOS).build();
    }

    @Override
    public List<Admin> fetchAllAdmins() {
        fetch();
        return fetchCriteria();
    }

    public List<Admin> fetch() {
        long startTime = System.currentTimeMillis();
        List<Admin> adminList = adminRepository.fetchAllAdmins();
        System.out.println(System.currentTimeMillis() - startTime);
        return adminList;
    }

    public List<Admin> fetchCriteria() {
        long startTime = System.currentTimeMillis();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admin> criteria = builder.createQuery(Admin.class);
        Root<Admin> admins = criteria.from(Admin.class);
        List<Admin> adminList = entityManager.createQuery(criteria).getResultList();
        System.out.println(System.currentTimeMillis() - startTime);
        return adminList;
    }
}
