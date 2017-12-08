package com.spn.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	
	@RequestMapping(value="/upload")
	public String file(){
		return "FileUpload";
	}
	private String UPLOADED_FOLDER="/images/";
	@PostMapping("/upload")
	public String upload(@RequestParam ("file") MultipartFile file){
		
		System.out.println("File : " + file.getOriginalFilename());
		try {
			byte[] bytes = file.getBytes();
			Path path=Paths.get(UPLOADED_FOLDER + generateFileName(file.getOriginalFilename()));
			Files.write(path,bytes);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "FileUpload";
	}
	
	private String generateFileName(String file){
		String ext=file.substring(file.lastIndexOf("."));
		String fileName=System.currentTimeMillis() + ext;
		return fileName;
	}
}
