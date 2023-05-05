package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(

		@NotNull(message = "{id.obrigatorio}") Long id,

		String nome,

		String telefone,

		@Valid DadosEndereco endereco) {
}
