package com.betacom.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtentiReq {

	private Integer id;
    private String username;
    private String email;
    private String pwd;
    private String role;   

}