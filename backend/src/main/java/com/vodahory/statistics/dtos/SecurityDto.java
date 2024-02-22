package com.vodahory.statistics.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityDto {
    private int securityId;
    private String login;
    private String password;

    public SecurityDto() {
    }

    public SecurityDto(int securityId, String login, String password) {
        this.securityId = securityId;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "SecurityDto{" +
                "securityId=" + securityId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
