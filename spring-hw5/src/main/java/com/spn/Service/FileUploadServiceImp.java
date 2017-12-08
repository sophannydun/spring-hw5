package com.spn.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImp implements FileUploadService {
	private String UPLOADED_FOLDER = "/images/";

	

	@Override
	public String fileUpload(MultipartFile file) {
		String fileName=null;
		try {
			
			fileName=generateFileName(file.getOriginalFilename());
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + fileName );
			Files.write(path, bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return location file uploaded
		return "/resource/" + fileName;
	}
	private String generateFileName(String file) {
		String ext = file.substring(file.lastIndexOf("."));
		String fileName = System.currentTimeMillis() + ext;
		return fileName;
	}
}
