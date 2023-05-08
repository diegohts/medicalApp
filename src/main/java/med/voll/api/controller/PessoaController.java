package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.pessoa.DadosAtualizacaoPessoa;
import med.voll.api.domain.pessoa.DadosDetalhamentoPessoa;
import med.voll.api.domain.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1/pessoas")
@SecurityRequirement(name = "bearer-key")
public class PessoaController {

	private static final Logger logger = LogManager.getLogger(PessoaController.class);

	private final PessoaService pessoaService;

	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@GetMapping("/{login}")
	public ResponseEntity<DadosDetalhamentoPessoa> detalhar(@PathVariable String login){
		DadosDetalhamentoPessoa dados = pessoaService.detalhar(login);

		logger.info("O usuário" + dados.nome() + " realizou o login com sucesso no sistema");

		return ResponseEntity.ok(dados);
	}

	@PutMapping
	public ResponseEntity<DadosDetalhamentoPessoa> atualizar(@RequestBody @Valid DadosAtualizacaoPessoa dadosAtualizacaoPessoa){
		DadosDetalhamentoPessoa dados = pessoaService.atualizar(dadosAtualizacaoPessoa);

		logger.info("O usuário" + dados.nome() + " atualizou seus dados no sistema");

		return ResponseEntity.ok(dados);
	}
}
