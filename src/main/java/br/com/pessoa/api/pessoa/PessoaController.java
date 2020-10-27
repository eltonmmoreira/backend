package br.com.pessoa.api.pessoa;

import br.com.pessoa.api.util.MessageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @GetMapping
    public String teste(@RequestParam String key) {
        return MessageUtil.get(key);
    }

}
