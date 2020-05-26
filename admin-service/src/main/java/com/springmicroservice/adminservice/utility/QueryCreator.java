package com.springmicroservice.adminservice.utility;

import com.springmicroservice.adminservice.dto.AdminRequestDTO;
import com.springmicroservice.adminservice.repository.AdminRepository;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class QueryCreator {

    public static Supplier<String> createQueryToFetchAdminsToSendEmail = () ->
        "SELECT a.username, a.email_address FROM admin a WHERE email_sent = 'N'";

    public static Function<AdminRequestDTO, String> createQueryToFetchAdminDetails = (requestDTO) -> {
        String query = "";
        query = " SELECT a.id" +
                ",a.password" +
                ",a.status" +
                ",a.login_attempt" +
                ",a.email_address" +
                " FROM admin a WHERE";

        if (!Objects.isNull(requestDTO.getUsername())) {
            query += " a.username= '" + requestDTO.getUsername() + "'";
            if (!Objects.isNull(requestDTO.getEmailAddress())) {
                query += "AND a.email_address= '" + requestDTO.getEmailAddress() + "'";
            }
        } else if (!Objects.isNull(requestDTO.getEmailAddress())) {
            query += " a.email_address= '" + requestDTO.getEmailAddress() + "'";
        }

        return query;
    };

}
