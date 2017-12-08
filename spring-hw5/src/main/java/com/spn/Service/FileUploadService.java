package com.spn.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileUploadService {
	public String fileUpload(MultipartFile file);
}
