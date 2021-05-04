package com.dalyTools.dalyTools.DAO.dto;

import lombok.Data;

@Data
public class JwtAuthDto {
    private String username;
    private String accessToken;
    private String refreshToken;

}
