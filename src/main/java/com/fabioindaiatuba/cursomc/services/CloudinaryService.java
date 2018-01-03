package com.fabioindaiatuba.cursomc.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fabioindaiatuba.cursomc.services.exceptions.FileException;

@Service
public class CloudinaryService {
	private Logger LOG = LoggerFactory.getLogger(CloudinaryService.class);
	
	@Autowired
	private Cloudinary cloudinary;
	
	@Value("${cl.BUCKET}")
	private String bucketName;
	
	public URI uploadFile(MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		String contentType = multipartFile.getContentType();
		
		return uploadFile(multipartFile, fileName, contentType);	
	}
	
	@SuppressWarnings("rawtypes")
	public URI uploadFile(MultipartFile multipartFile, String fileName, String contentType) {
		
		File arquivo_renomeado = new File(fileName);
		try {
			arquivo_renomeado.createNewFile();
		
			FileOutputStream fos = new FileOutputStream(arquivo_renomeado); 
			fos.write(multipartFile.getBytes());
			fos.close(); 
		    
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
			LOG.info("Iniciando Upload");
			cloudinary.uploader().upload(arquivo_renomeado, opcoes);
			LOG.info("Upload Finalizado");
			return new URI(cloudinary.url().generate(bucketName+'/'+arquivo_renomeado.getName()));
			
		} catch (RuntimeException e) {
			LOG.info("Cloudinary RuntimeException: "+ e.getMessage());
			throw new FileException("Cloudinary RuntimeException: "+e.getMessage());
		} catch (IOException e) {
			LOG.info("Cloudinary IOException: "+ e.getMessage());
			throw new FileException("Cloudinary IOException: "+e.getMessage());
		} catch (URISyntaxException e) {
			LOG.info("Cloudinary URISyntaxException: "+ e.getMessage());
			throw new FileException("Cloudinary URISyntaxException: "+e.getMessage());
		}
	}
}
