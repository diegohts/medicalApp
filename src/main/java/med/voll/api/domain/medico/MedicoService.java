package med.voll.api.domain.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {

	private final MedicoRepository medicoRepository;

	@Autowired
	public MedicoService(MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}

	@Transactional
	public DadosDetalhamentoMedico cadastrar(DadosCadastroMedico dados){
		Medico medico = this.medicoRepository.save(new Medico(dados));

		return new DadosDetalhamentoMedico(medico);
	}

	@Transactional
	public DadosDetalhamentoMedico atualizarInformacoes(DadosAtualizacaoMedico dados){
		Medico medico = this.medicoRepository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);

		return new DadosDetalhamentoMedico(medico);
	}

	public Page<DadosListagemMedico> listar(Pageable paginacao){
		return this.medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
	}

	@Transactional
	public DadosDetalhamentoMedico excluir(Long id) {
		// forma destrutiva, nao usar: this.medicoRepository.deleteById(id);
		Medico medico = medicoRepository.getReferenceById(id);
		medico.excluir();

		return new DadosDetalhamentoMedico(medico);
	}

	public DadosDetalhamentoMedico detalhar(Long id){
		Medico medico =  this.medicoRepository.getReferenceById(id);

		return new DadosDetalhamentoMedico(medico);
	}

	public Boolean crmMedicoJaCadastrado(String crm) {
		return this.medicoRepository.existisByCrm(crm);
	}

	public Boolean emailMedicoJaCadastrado(String email) {
		return this.medicoRepository.existisByEmail(email);
	}
}
