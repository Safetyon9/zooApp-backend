package com.betacom.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String username;
    private String ruolo;
    private Integer carrelloId;
    private Integer clienteId;
}

