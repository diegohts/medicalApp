package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/user/login")
public class AutenticacaoController {

	private static final Logger logger = LogManager.getLogger(AutenticacaoController.class);

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@Autowired
	public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);

		String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

		logger.info("O login " + dados.login() + " foi realizado");
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}
}
