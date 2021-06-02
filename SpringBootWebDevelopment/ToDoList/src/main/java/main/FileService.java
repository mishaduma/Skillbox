package main;

import lombok.RequiredArgsConstructor;
import main.model.FileDescriptor;
import main.model.FilesRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FilesRepository filesRepository;

    public FileDescriptor uploadFile(MultipartFile file) {

        //unique naming
        String fileName = file.getName() + System.currentTimeMillis();

        //uploading file
        if (!file.isEmpty()) {
            try {
                if (!Files.exists(Paths.get("files"))) {
                    Files.createDirectory(Paths.get("files"));
                }
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("files/" + fileName)));
                stream.write(bytes);
                stream.close();

                //adding fileDescriptor to database
                FileDescriptor newFileDescriptor = new FileDescriptor();
                newFileDescriptor.setOriginalFileName(file.getOriginalFilename());
                newFileDescriptor.setFileName(fileName);
                newFileDescriptor.setExt(file.getContentType());
                newFileDescriptor.setCreatedTs(LocalDateTime.now());
                newFileDescriptor.setSize(file.getSize());
                filesRepository.save(newFileDescriptor);

                return newFileDescriptor;
            } catch (Exception e) {
                FileDescriptor exceptionFileDescriptor = new FileDescriptor();
                exceptionFileDescriptor.setFileName(e.getMessage());

                return exceptionFileDescriptor;
            }
        } else {
            return null;
        }
    }

    public FileDescriptor getFileDescriptor(int id) {
        Optional<FileDescriptor> optionalFileDescriptor = filesRepository.findById(id);
        if (!optionalFileDescriptor.isPresent()) {
            return null;
        }
        return optionalFileDescriptor.get();
    }

    public ResponseEntity<InputStreamResource> downloadFile(int id) throws IOException {
        //checking fileDescriptor is present
        Optional<FileDescriptor> optionalFileDescriptor = filesRepository.findById(id);
        if (!optionalFileDescriptor.isPresent()) {
            return null;
        }

        String fileName = optionalFileDescriptor.get().getFileName();
        MediaType mediaType = MediaType.parseMediaType(optionalFileDescriptor.get().getExt());
        File file = new File("files/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }

    public List<FileDescriptor> listFileDescriptors() {
        List<FileDescriptor> fileDescriptors = new ArrayList<>();
        filesRepository.findAll().forEach(fileDescriptors::add);
        return fileDescriptors;
    }

    public FileDescriptor deleteFile(int id) {
        //checking fileDescriptor is present
        Optional<FileDescriptor> optionalFileDescriptor = filesRepository.findById(id);
        if (!optionalFileDescriptor.isPresent()) {
            return null;
        }
        String fileName = optionalFileDescriptor.get().getFileName();
        File file = new File("files/" + fileName);
        if (file.delete()) {
            FileDescriptor fileDescriptor = optionalFileDescriptor.get();
            filesRepository.delete(fileDescriptor);
            return fileDescriptor;
        }
        return null;
    }

    public FileDescriptor editFile(int id, MultipartFile file) {

        //uploading file
        if (!file.isEmpty()) {
            try {
                //checking fileDescriptor is present
                Optional<FileDescriptor> optionalFileDescriptor = filesRepository.findById(id);
                if (!optionalFileDescriptor.isPresent()) {
                    return null;
                }

                //rewriting file
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("files/" + optionalFileDescriptor.get().getFileName())));
                stream.write(bytes);
                stream.close();

                //adding fileDescriptor to database
                FileDescriptor editedFD = optionalFileDescriptor.get();
                editedFD.setId(id);
                editedFD.setExt(file.getContentType());
                editedFD.setCreatedTs(LocalDateTime.now());
                editedFD.setSize(file.getSize());
                filesRepository.save(editedFD);

                return editedFD;
            } catch (Exception e) {
                FileDescriptor exceptionFileDescriptor = new FileDescriptor();
                exceptionFileDescriptor.setFileName(e.getMessage());

                return exceptionFileDescriptor;
            }
        } else {
            return null;
        }
    }
}