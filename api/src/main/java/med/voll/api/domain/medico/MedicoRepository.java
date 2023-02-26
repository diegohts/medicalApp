package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);

	// como o metodo abaixo escreveu em portugues, nao esta seguindo o padrao de nomenclatura em ingles. Entao vamos digital a consulta SQL
	// A consulta abaixo e para trazer um medico de maneira randomica cujo sejam ativos de uma determinada especialidade.
	// E que o id não seja de um medico que tenha consulta em uma determinada data marcada
	@Query("""
		select m from Medico m
		where
		m.ativo = 1
		and
		m.especialidade = :especialidade
		and
		m.id not in(
			select c.medico.id from Consulta c
			where
			c.data = :data
		)
		order by rand()
		limit 1
		""")
	Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
