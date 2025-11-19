package br.com.stockflowapi.service;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final Path uploadPath;

    public String storeFile(MultipartFile file, Long productCode){

        if(file.isEmpty()){
            throw new IllegalArgumentException("File is empty");
        }

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = getFileExtension(originalFilename).toLowerCase();

        List<String> extensions = List.of("png", "jpg", "webp");

        if(!List.of(extensions).contains(extension)){
            throw new IllegalArgumentException("Invalid image file type");
        }

        if (originalFilename.contains("..")) {
            throw new IllegalArgumentException("Invalid file path");
        }

        String filename = "%d-%s-%s.%s".formatted(productCode,getCurrentDatetime(),UUID.randomUUID(), extension);

        //String filename = "%d%s.%s".formatted(productCode, UUID.randomUUID(), extension);

        try {
            Path target = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String getFileExtension(String filename){
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    private String getCurrentDatetime(){

        String PATTERN = "yyyyMMddHHmmssSSS";
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN));
    }

}
