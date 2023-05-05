package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import med.voll.api.domain.medico.Especialidade;
import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

	@JsonAlias({"id_medico", "medico_id"})
	Long idMedico,

	@JsonAlias({"id_paciente", "paciente_id"})
	@NotNull
	Long idPaciente,

	@JsonAlias({"data_consulta", "data_agendamento"})
	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	LocalDateTime data,

	Especialidade especialidade) {
}
