package main;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.FileDescriptor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/files")
    public FileDescriptor uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @GetMapping("/files/{id}/descriptor")
    public FileDescriptor getFileDescriptor(@PathVariable int id) {
        return fileService.getFileDescriptor(id);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable int id) throws IOException {
        return fileService.downloadFile(id);
    }

    @GetMapping("/files")
    public List<FileDescriptor> listFileDescriptors() {
        return fileService.listFileDescriptors();
    }

    @DeleteMapping("/files/{id}")
    public FileDescriptor deleteFile(@PathVariable int id) {
        return fileService.deleteFile(id);
    }

    @PutMapping("/files/{id}")
    public FileDescriptor editFile(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        return fileService.editFile(id, file);
    }
}