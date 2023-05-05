package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoAntecedencia implements ValidadorCancelamentoDeConsulta {

	private final ConsultaRepository consultaRepository;

	@Autowired
	public ValidadorCancelamentoAntecedencia(ConsultaRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}

	@Override
	public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {

		var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
		var horaConsulta = consulta.getData();
		var agora = LocalDateTime.now();

		Long diferencaEmHoras = Duration.between(agora, horaConsulta).toHours();

		if (diferencaEmHoras < 24) {
			throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
		}
	}
}
