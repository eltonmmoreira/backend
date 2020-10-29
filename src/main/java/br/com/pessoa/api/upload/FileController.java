package br.com.pessoa.api.upload;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "upload/pessoa/{id}")
    public void upload(MultipartHttpServletRequest file, @PathVariable Long id) {
        fileService.upload(file.getFile("image"), id);
    }

    @GetMapping(value = "file/pessoa/{id}")
    public String findFile(@PathVariable Long id) {
        return fileService.findFile(id);
    }

}
