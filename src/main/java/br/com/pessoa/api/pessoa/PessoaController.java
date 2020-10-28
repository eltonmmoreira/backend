package br.com.pessoa.api.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/list")
    public Page<PessoaDto> findAll(@RequestParam int page,
                                   @RequestParam int size,
                                   @RequestBody(required = false) PessoaFiltro filtro) {
        return pessoaService.findAll(filtro, PageRequest.of(page, size));
    }
}
