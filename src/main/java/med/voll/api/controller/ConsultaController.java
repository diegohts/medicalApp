package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaService;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

	private static final Logger logger = LogManager.getLogger(ConsultaController.class);

	private final ConsultaService consultaService;

	@Autowired
	public ConsultaController(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}

	@PostMapping
	public ResponseEntity<DadosDetalhamentoConsulta> agendar(
			@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta) {
		var dto = consultaService.agendarConsulta(dadosAgendamentoConsulta);

		logger.info("A consulta de numero " + dto.id() + " foi agendada com sucesso");

		return ResponseEntity.ok(dto);
	}

	@DeleteMapping
	public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta) {
		consultaService.cancelamentoConsulta(dadosCancelamentoConsulta);

		logger.info("A consulta de numero " + dadosCancelamentoConsulta.idConsulta() + " foi cancelada com sucesso");

		return ResponseEntity.noContent().build();
	}

}
