package com.betacom.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtentiReq {

    private String username;
    private String email;
    private String pwd;
    private String role;   
    private String oldPwd;
    private String newPwd;
    private Boolean isValidate;
    private String validationToken;

}