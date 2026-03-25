package com.betacom.services.implementations;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.betacom.services.interfaces.IUploadServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadImpl implements IUploadServices{
	
	private final Path uploadPath;
	
	public UploadImpl(@Value(
			"${app.upload.dir:uploads}") String uploadDir
			) {
		this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
		
	}

	@Override
	public String saveImage(MultipartFile file, Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeImage(String fileName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String buildUrl(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}
