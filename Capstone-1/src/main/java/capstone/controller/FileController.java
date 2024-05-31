package capstone.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {
	
	@Autowired
	private Environment env;

	@GetMapping(value = "/view/{fileName}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> showPDF(@PathVariable String fileName) {

	    String fileDirectory = env.getProperty("file.path");

	    Path filePath = Paths.get(fileDirectory, fileName);

	    if (!Files.exists(filePath) || !Files.isRegularFile(filePath) || !Files.isReadable(filePath)) {
	        return ResponseEntity.notFound().build(); 
	    }

	    try {
	        byte[] pdfContent = Files.readAllBytes(filePath);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);

	        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
	    }
	}
	
	@GetMapping(value = "/view/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] responseImageJpg(@PathVariable String imageName) {
    	
		String fileDirectory = env.getProperty("certificate.path");
		
		String fileName = fileDirectory + imageName + ".png";
        
        String noImgFileName = fileDirectory + "no_image.png";

        try {
            if (imageName == null || !Files.exists(Paths.get(fileName))) {
                return Files.readAllBytes(Paths.get(noImgFileName));
            } else {
                return Files.readAllBytes(Paths.get(fileName));
            }
        } catch (IOException e) {
        	
            e.printStackTrace();
            
            return new byte[0]; 
        }
    }
}
