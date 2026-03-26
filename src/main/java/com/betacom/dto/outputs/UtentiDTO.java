package com.betacom.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UtentiDTO {

    private String userName;
    private String email;
    private String role;   // es. "USER", "ADMIN"

}