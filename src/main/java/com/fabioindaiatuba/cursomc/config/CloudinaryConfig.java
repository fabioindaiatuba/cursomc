package com.fabioindaiatuba.cursomc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	@Value("${cl.ACCESS_NAME}")
	private String access_name;
	@Value("${cl.ACCESS_KEY_ID}")
	private String access_key_id;
	@Value("${cl.SECRET_ACCESS_KEY}")
	private String secret_access_key;
	
	@Bean
	public Cloudinary cloudinary(){
		System.out.println("STORAGE >>>>>>> "+access_name);
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", access_name,
				  "api_key", access_key_id,
				  "api_secret", secret_access_key));
		
		return cloudinary;
	}
	
}
