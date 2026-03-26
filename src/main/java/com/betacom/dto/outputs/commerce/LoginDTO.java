package com.betacom.dto.outputs.commerce;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginDTO {

    private String id;
    private String ruolo;

}

