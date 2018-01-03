package com.fabioindaiatuba.cursomc.services;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {
	private Logger LOG = LoggerFactory.getLogger(CloudinaryService.class);
	
	@Autowired
	private Cloudinary cloudinary;
	
	@Value("${cl.BUCKET}")
	private String bucketName;
	
	@SuppressWarnings({"rawtypes"})
	public void uploadFile(String localFilePath) {
		File file = new File(localFilePath);
		/*
		 * Doc coom opçoes do Cloudinary
		 * https://cloudinary.com/documentation/image_upload_api_reference#upload
		 */
		Map opcoes = ObjectUtils.asMap(
				"folder", bucketName, 
				"access_mode","public",
				"use_filename", true, 
				"unique_filename", false
		);
		try {
			LOG.info("Iniciando Upload");
			cloudinary.uploader().upload(file, opcoes);
			LOG.info("Upload Finalizado");
		} catch (IOException e) {
			LOG.info("Cloudinary IOException: "+e.getMessage());
		}	
	}
	
}
