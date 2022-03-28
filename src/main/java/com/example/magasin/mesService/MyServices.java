package com.example.magasin.mesService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class MyServices {
    
    public static void uploadFile( String fileName, String uploadDir, MultipartFile multipartFile ) throws IOException {
        
        Path uplaodPath = Paths.get( uploadDir );
        
        try( InputStream inputStream = multipartFile.getInputStream() ){
            Path filePath = uplaodPath.resolve( fileName );
            
            Files.copy( inputStream, filePath, StandardCopyOption.REPLACE_EXISTING );
        }catch (Exception e) {
            System.out.println( "sauvegarde impossible " + fileName );
        }
        
    }

}
