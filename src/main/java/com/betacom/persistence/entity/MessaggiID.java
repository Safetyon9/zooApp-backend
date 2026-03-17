package com.betacom.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class MessaggiID {
	
	@Column (length = 4)
	private String lang;
	
	@Column (length = 50)
	private String code;
}