package capstone.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.security.GeneralSecurityException;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ContentDisposition;
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

	@Autowired
	private ResourceLoader resourceLoader;

	@GetMapping(value = "/view/{fileName}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> showPDF(@PathVariable String fileName) {

		// String fileDirectory = env.getProperty("new.file.path");
		//
		// if (!Files.exists(filePath) || !Files.isRegularFile(filePath) ||
		// !Files.isReadable(filePath)) {
		// return ResponseEntity.notFound().build();
		// }
		// try {
		// byte[] pdfContent = Files.readAllBytes(filePath);
		// // Get the InputStream for the file's content from Google Drive
		// //InputStream inputStream = googleDriveService.getFileContentByName(fileName,
		// true);
		//
		//// if (inputStream == null) {
		//// return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // File not
		// found
		//// }
		////
		//// byte[] pdfContent = inputStream.readAllBytes(); // Read the InputStream
		// into a byte array
		//
		// HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.APPLICATION_PDF);
		//
		// return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
		// } catch (IOException e) {
		// e.printStackTrace();
		// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		// } catch (GeneralSecurityException e) {
		// e.printStackTrace();
		// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		// }
		String fileDirectory = env.getProperty("new.file.path");
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
	public @ResponseBody byte[] responseImageJpg(@PathVariable String imageName) throws GeneralSecurityException {
		// Resource noImgResource =
		// resourceLoader.getResource("classpath:static/images/no_image.png");
		//
		// System.out.println(imageName);
		//
		// // Attempt to get the image from Google Drive
		// InputStream inputStream = null;
		// try {
		// // Use the getFileContentByName method to retrieve the image from Google
		// Drive
		// InputStream googleDriveImageStream =
		// googleDriveService.getFileContentByName(imageName, false);
		//
		// if (googleDriveImageStream != null) {
		// return IOUtils.toByteArray(googleDriveImageStream); // Return the image from
		// Google Drive
		// } else {
		// // If not found in Google Drive, return the no_image.png
		// return Files.readAllBytes(noImgResource.getFile().toPath());
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// return new byte[0]; // Return an empty byte array on error
		// }
		// }
		String fileDirectory = env.getProperty("new.certificate.path");

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
		
	@GetMapping(value = "/download/certificate/{imageName}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable String imageName) {
	    String fileDirectory = env.getProperty("new.certificate.path"); // Path to the directory
	    Path filePath = Paths.get(fileDirectory, imageName);

	    // Validate file existence and readability
	    if (!Files.exists(filePath) || !Files.isRegularFile(filePath) || !Files.isReadable(filePath)) {
	        return ResponseEntity.notFound().build();
	    }

	    try {
	        // Read the image content as bytes
	        byte[] imageContent = Files.readAllBytes(filePath);

	        // Determine the content type based on the file extension
	        String contentType = Files.probeContentType(filePath);

	        if (contentType == null || !contentType.startsWith("image/")) {
	            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
	        }

	        // Set headers to force download
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // Set as generic binary content
	        headers.setContentLength(imageContent.length);
	        headers.setContentDisposition(ContentDisposition.builder("attachment")
	                .filename(imageName)
	                .build());

	        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


	
}
