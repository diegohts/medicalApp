package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

// Aqui na interface define o contrato de validacao
public interface ValidadorAgendamentoDeConsulta {
	void validar(DadosAgendamentoConsulta dados);

}
