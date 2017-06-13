package kr.ac.jejunu.handler;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by ghost9087 on 12/06/2017.
 */
public interface FileHandler {
    String handleUploadedFile(MultipartFile file) throws IOException;
}
