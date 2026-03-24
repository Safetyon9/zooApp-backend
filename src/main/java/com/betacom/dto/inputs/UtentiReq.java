package com.betacom.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtentiReq {

	private Integer id;
    private String userName;
    private String email;
    private String password;
    private String role;   

}