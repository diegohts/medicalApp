package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

	private static final Logger logger = LogManager.getLogger(MedicoController.class);

	private final MedicoService medicoService;

	@Autowired
	public MedicoController(MedicoService medicoService) {
		this.medicoService = medicoService;
	}

	@PostMapping
	public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados,
			UriComponentsBuilder uriBuilder) {
		DadosDetalhamentoMedico medico = this.medicoService.cadastrar(dados);

		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.id()).toUri();

		logger.info("O medico " + medico.nome() + " foi cadastrado com sucesso no sistema");

		return ResponseEntity.created(uri).body(medico);
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(
			@PageableDefault(size = 10, page = 0, sort = { "nome" }) Pageable paginacao) {
		Page<DadosListagemMedico> page = this.medicoService.listar(paginacao);

		return ResponseEntity.ok(page);
	}

	@PutMapping
	public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		DadosDetalhamentoMedico medico = this.medicoService.atualizarInformacoes(dados);

		logger.info("O medico " + medico.nome() + " foi atualizado com sucesso no sistema");

		return ResponseEntity.ok(medico);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.medicoService.excluir(id);

		logger.info("O medico de ID " + id + " foi inativado com sucesso no sistema");

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long id) {
		DadosDetalhamentoMedico medico = medicoService.detalhar(id);

		return ResponseEntity.ok(medico);
	}

	@GetMapping("/existe")
	public ResponseEntity<Boolean> verificarExistencia(
			@RequestParam(name = "tipo") Optional<String> tipoInformado,
			@RequestParam(name = "email", required = false) Optional<String> emailInformado,
			@RequestParam(name = "crm", required = false) Optional<String> crmInformado) {
		Boolean resultado = true;
		String tipo = tipoInformado.orElseThrow();

		if (Objects.equals(tipo, "email")) {
			resultado = medicoService.emailMedicoJaCadastrado(emailInformado.orElseThrow());
		}
		if (Objects.equals(tipo, "crm")) {
			resultado = medicoService.crmMedicoJaCadastrado(crmInformado.orElseThrow());
		}

		return ResponseEntity.ok(resultado);
	}
}
