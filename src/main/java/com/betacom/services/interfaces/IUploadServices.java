package com.betacom.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadServices {

		String saveImage(MultipartFile file, Integer id, String tipo) throws Exception;
		
		void removeImage(String fileName) throws Exception;
		
		String buildUrl(String fileName);
}
