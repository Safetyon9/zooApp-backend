package com.betacom.dto.inputs.commerce;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginReq {
    private String username;
    private String pwd;
}