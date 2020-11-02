package br.com.pessoa.api.file;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload/pessoa/{id}")
    public void upload(MultipartHttpServletRequest file, @PathVariable Integer id) {
        fileService.upload(file.getFile("image"), id);
    }

    @GetMapping(value = "/pessoa/{id}")
    public String findFile(@PathVariable Integer id) {
        try {
            return fileService.findFile(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
