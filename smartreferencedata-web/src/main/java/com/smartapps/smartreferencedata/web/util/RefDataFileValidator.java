package com.smartapps.smartreferencedata.web.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class RefDataFileValidator implements ConstraintValidator<ValidRefDataFile, MultipartFile>{

	private static String FILE_JSON = ".json";
	private static String FILE_CSV = ".csv";
	
	@Override
	public boolean isValid(MultipartFile input, ConstraintValidatorContext ctx) {
		boolean result = false;
		try {
			byte[] buffer = input.getBytes();
			String fileContent = new String(buffer, StandardCharsets.UTF_8);
			String fileName = StringUtils.isNotEmpty(input.getOriginalFilename()) ? input.getOriginalFilename() : "";
			result = (buffer.length != 0 
					&& StringUtils.isNotEmpty(fileContent)
					&& (fileName.endsWith(FILE_JSON) || fileName.endsWith(FILE_CSV)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
