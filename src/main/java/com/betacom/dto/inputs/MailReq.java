package com.betacom.dto.inputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MailReq {
	private String to;
	private String oggetto;
	private String body;
	private byte[] attachment;
}
