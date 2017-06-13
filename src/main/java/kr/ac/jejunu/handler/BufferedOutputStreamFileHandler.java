package kr.ac.jejunu.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ghost9087 on 12/06/2017.
 */
@Component
public class BufferedOutputStreamFileHandler implements FileHandler {
    @Override
    public String handleUploadedFile(MultipartFile file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources/static/" + file.getOriginalFilename()));
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
        outputStream.write(file.getBytes());
        outputStream.close();

        return file.getOriginalFilename();
    }
}
