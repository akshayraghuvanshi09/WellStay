package com.serviceAppartment.Utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class ApartmentUtils {
	 private static final String UPLOAD_DIR = "D:\\WellStay\\images";

	    public static String uploadImage(MultipartFile file) throws IOException {
	        // Generate a unique file name
	        String fileName = generateUniqueFileName(file.getOriginalFilename());

	        // Construct the path where the file will be saved
	        String filePath = UPLOAD_DIR + fileName;

	        // Save the file to the specified location
	        file.transferTo(new File(filePath));

	        // Return the file path or URL where the file is saved
	        return filePath;
	    }

	    private static String generateUniqueFileName(String originalFileName) {
	        // Generate a random UUID to ensure uniqueness
	        String uuid = UUID.randomUUID().toString();

	        // Extract file extension from the original file name
	        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));

	        // Concatenate UUID and extension to create a unique file name
	        return uuid + extension;
	    }
	}

