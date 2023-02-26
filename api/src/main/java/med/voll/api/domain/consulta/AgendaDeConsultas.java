package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;

@Service
public class AgendaDeConsultas {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	public void agendar(DadosAgendamentoConsulta dados) {

		if (!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("ID do paciente informado não existe!");
		}

		if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("ID do médico informado não existe!");
		}

		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		var medico = escolherMedico(dados);

		var consulta = new Consulta(null, medico, paciente, dados.data());
		consultaRepository.save(consulta);
	}

	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		if (dados.idMedico() != null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}

		if (dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
		}

		return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
	}
}
