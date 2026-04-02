package com.betacom.dto.inputs;

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