package capstone.model.service;


import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.services.drive.Drive;

@Service
public interface GoogleDriveService {
	
	public Drive getInstance() throws GeneralSecurityException, IOException;
	
	public  InputStream  getFileContentByName(String fileName, Boolean isPdf)  throws IOException, GeneralSecurityException;
	
	public void uploadPdfFile(MultipartFile file, String fileName);
	
	public void uploadCertificateFile(java.io.File file, String fileName);
}
