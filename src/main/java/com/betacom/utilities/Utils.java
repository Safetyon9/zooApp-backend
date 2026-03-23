package com.betacom.utilities;

import java.time.LocalDate;
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
}
