package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaService;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

	private final ConsultaService consultaService;

	@Autowired
	public ConsultaController(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}

	@PostMapping
	public ResponseEntity<DadosDetalhamentoConsulta> agendar(
			@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta) {
		var dto = consultaService.agendarConsulta(dadosAgendamentoConsulta);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping
	public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta) {
		consultaService.cancelamentoConsulta(dadosCancelamentoConsulta);
		return ResponseEntity.noContent().build();
	}

}
