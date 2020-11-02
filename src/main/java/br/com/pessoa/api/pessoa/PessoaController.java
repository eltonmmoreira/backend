package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.core.CrudService;
import br.com.pessoa.api.core.RestControllerImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessoa")
public class PessoaController extends RestControllerImpl<Pessoa, Integer>
        implements br.com.pessoa.api.core.RestController<Pessoa, Integer> {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Override
    protected CrudService<Pessoa, Integer> getService() {
        return pessoaService;
    }

    @PostMapping("/list")
    public Page<PessoaDto> findAll(@RequestParam int page,
                                   @RequestParam int size,
                                   @RequestBody(required = false) PessoaFiltro filtro) {
        return pessoaService.findAll(filtro, PageRequest.of(page, size));
    }

}
