package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultaService {

	private ConsultaRepository consultaRepository;
	private MedicoRepository medicoRepository;
	private PacienteRepository pacienteRepository;
	private List<ValidadorAgendamentoDeConsulta> validadoresConsulta;
	private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

	@Autowired
	public ConsultaService(
		ConsultaRepository consultaRepository,
		MedicoRepository medicoRepository,
		PacienteRepository pacienteRepository,
		List<ValidadorAgendamentoDeConsulta> validadoresConsulta,
		List<ValidadorCancelamentoDeConsulta> validadoresCancelamento) {

			this.consultaRepository = consultaRepository;
			this.medicoRepository = medicoRepository;
			this.pacienteRepository = pacienteRepository;
			this.validadoresConsulta = validadoresConsulta;
			this.validadoresCancelamento = validadoresCancelamento;
	}

	@Transactional
	public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dados) {

		if (!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("ID do paciente informado não existe!");
		}

		if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("ID do médico informado não existe!");
		}

		validadoresConsulta.forEach(v -> v.validar(dados));

		var medico = escolherMedico(dados);

		if(medico == null) {
			throw new ValidacaoException("Não existe médico disponível nessa data!");
		}

		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		var consulta = new Consulta(null, medico, paciente, dados.data(), null);
		consulta = consultaRepository.save(consulta);

		return new DadosDetalhamentoConsulta(consulta);
	}

	@Transactional
	public Consulta cancelamentoConsulta(DadosCancelamentoConsulta dados) {
		if (!consultaRepository.existsById(dados.idConsulta())) {
			throw new ValidacaoException("ID da consulta informada não existe!");
		}

		validadoresCancelamento.forEach(v -> v.validar(dados));

		var consulta = consultaRepository.getReferenceById(dados.idConsulta());
		consulta.cancelar(dados.motivo());

		return consulta;
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
