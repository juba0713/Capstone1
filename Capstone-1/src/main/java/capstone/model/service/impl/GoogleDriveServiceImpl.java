package capstone.model.service.impl;

import java.io.FileInputStream; // For reading files
import java.io.IOException; // For handling IOExceptions
import com.google.api.services.drive.Drive; // Google Drive API
import com.google.api.services.drive.model.File; // Google Drive File class
import com.google.api.client.http.InputStreamContent; // For file content
import java.util.Collections; // For Collections.singletonList


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;

import capstone.model.service.GoogleDriveService;
import jakarta.annotation.PostConstruct;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GoogleDriveServiceImpl  implements GoogleDriveService{
	
	@Autowired
	private Environment env;
	
	private Drive driveService;
	
	@PostConstruct
    public void init() {
        try {
            // This will authorize and create a Drive instance at application startup
            this.driveService = getInstance();
            System.out.println("Google Drive API authorized and ready.");
        } catch (GeneralSecurityException | IOException e) {
            System.err.println("Error initializing Google Drive API: " + e.getMessage());
            e.printStackTrace();
        }
    }
	
	public Drive getDriveService() {
        return driveService;
    }
	
	/**
	   * Application name.
	   */
	  private static final String APPLICATION_NAME = "TestProject";
	  /**
	   * Global instance of the JSON factory.
	   */
	  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	  /**
	   * Directory to store authorization tokens for this application.
	   */
	  private static final String TOKENS_DIRECTORY_PATH = "tokens";

	  /**
	   * Global instance of the scopes required by this quickstart.
	   * If modifying these scopes, delete your previously saved tokens/ folder.
	   */
	  private static final List<String> SCOPES =
	      Collections.singletonList(DriveScopes.DRIVE_FILE);
	  private static final String CREDENTIALS_FILE_PATH = "./client_secret_1043115598106-4p08qcud5ccfosccf6q8hqov5cl5r0gu.apps.googleusercontent.com.json";

	  /**
	   * Creates an authorized Credential object.
	   *
	   * @param HTTP_TRANSPORT The network HTTP Transport.
	   * @return An authorized Credential object.
	   * @throws IOException If the credentials.json file cannot be found.
	   */
	  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
	      throws IOException {
	    // Load client secrets.
	    InputStream in = GoogleDriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
	    if (in == null) {
	      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
	    }
	    GoogleClientSecrets clientSecrets =
	        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

	    // Build flow and trigger user authorization request.
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	        .setAccessType("offline")
	        .build();
	    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
	    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	    //returns an authorized Credential object.
	    return credential;
	  }

	  public Drive getInstance() throws GeneralSecurityException, IOException {
	      // Build a new authorized API client service.
	      final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	      Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	            .setApplicationName(APPLICATION_NAME)
	            .build();
	      return service;
	   }
	  
	  
	  
	  public InputStream  getFileContentByName(String fileName, Boolean isPdf) throws IOException, GeneralSecurityException {
		  
		// Retrieve the folder ID from properties
		    String folderId;

		    // Check if we are dealing with PDFs or certificates
		    if (isPdf) {
		        folderId = env.getProperty("pdf.folder.id"); // ID for the pdfs folder
		    } else {
		        System.out.println("CERTIFICATE");
		        folderId = env.getProperty("certificate.folder.id"); // ID for the certificate folder
		    }

		    Drive service = getInstance();

		    // Search for the file by name within the specified folder
		    String query = "name = '" + fileName + "' and '" + folderId + "' in parents";
		    FileList result = service.files().list()
		            .setQ(query)
		            .setSpaces("drive")
		            .setFields("files(id, name)")
		            .execute();

		    List<File> files = result.getFiles();

		    if (files == null || files.isEmpty()) {
		        return null; // No files found
		    } else {
		        File file = files.get(0); // Get the first matching file

		        // Return the InputStream for the file's content
		        return service.files().get(file.getId()).executeMediaAsInputStream();
		    }
		  }


	  public void uploadPdfFile(MultipartFile file, String fileName) {
		  
		  String folderId = env.getProperty("pdf.folder.id");
		  
		    try {
		        if (file.isEmpty()) {
		            throw new IllegalArgumentException("File is empty");
		        }

		        Drive service = getInstance();

		        File fileMetadata = new File();
		        fileMetadata.setName(fileName);
		        fileMetadata.setParents(Collections.singletonList(folderId));

		        InputStreamContent mediaContent = new InputStreamContent(
		                file.getContentType(),
		                file.getInputStream()
		        );

		        File uploadedFile = service.files().create(fileMetadata, mediaContent)
		                .setFields("id, parents")
		                .execute();

		        System.out.println("Uploaded File ID: " + uploadedFile.getId());

		    } catch (Exception e) {
		        System.err.println("Error uploading file: " + e.getMessage());
		        e.printStackTrace();
		    }
		}


	@Override
	public void uploadCertificateFile(java.io.File file, String fileName) {
		String folderId = env.getProperty("certificate.folder.id");
	    
	    try {
	        if (file.length() == 0) {
	            throw new IllegalArgumentException("File is empty");
	        }

	        Drive service = getInstance();

	        File fileMetadata = new File();
	        fileMetadata.setName(fileName);
	        fileMetadata.setParents(Collections.singletonList(folderId));

	        InputStreamContent mediaContent = new InputStreamContent(
	                "image/png", // Specify the correct content type
	                new FileInputStream(file) // Use FileInputStream to read the file
	        );

	        File uploadedFile = service.files().create(fileMetadata, mediaContent)
	                .setFields("id, parents")
	                .execute();

	        System.out.println("Uploaded File ID: " + uploadedFile.getId());

	    } catch (Exception e) {
	        System.err.println("Error uploading file: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
}
