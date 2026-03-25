package com.betacom.utilities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.betacom.exceptions.ZooException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
	public static LocalDate stringToDate(String date) {
		try {
			log.debug("Date:", date);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(date, formatter);
		}catch(DateTimeParseException e) {
			throw new ZooException("Data Invalida.");
		}
	}
	
	public static String stringFormatter(String s) {
		if (s == null) {
	        return null;
	    }

	    String trimmed = s.trim();
	    if (trimmed.isEmpty()) {
	        return trimmed;
	    }

	    String inizio = trimmed.substring(0, 1).toUpperCase();
	    String fine  = trimmed.substring(1).toLowerCase();

	    return inizio + fine;
	}
	
	public static BigDecimal calcolaPrezzoTotale(Integer quantita, BigDecimal prezzoUnitario) {
	    
		BigDecimal q = BigDecimal.valueOf(quantita);
		
	    return q.multiply(prezzoUnitario);

	}
	
}
