package br.com.projetosalao.controller;

import br.com.projetosalao.dominio.pessoa.DadosAutenticacao;
import br.com.projetosalao.dominio.pessoa.entity.Usuario;
import br.com.projetosalao.infra.DadosTokenJWT;
import br.com.projetosalao.infra.TokenService;
import br.com.projetosalao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final int MAX_TENTATIVAS_PERMITIDAS = 6; // Por exemplo, definindo 3 tentativas como limite


    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(dados.email());


        if(usuario != null) {

            if ("Inativo".equals(usuario.getStatus())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sua conta está inativa. Entre em contato com o suporte.");
            }


            if (usuario.getTentativas_login() >= MAX_TENTATIVAS_PERMITIDAS) {
                usuario.setStatus("Inativo");
                usuarioRepository.save(usuario); // Adicione esta linha para salvar a alteração do status
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Conta bloqueada devido a múltiplas tentativas de login malsucedidas.");
            }

            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            try {
                var authentication = manager.authenticate(authenticationToken);

                Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

                var tokenJWT = tokenService.gerarToken(usuarioAutenticado);

                // Zere o contador de tentativas de login após um login bem-sucedido
                usuario.setTentativas_login(0);
                usuarioRepository.save(usuario);

                Long cd_pessoa = usuarioAutenticado.getCd_pessoa();
                var tipoUsuario = usuarioAutenticado.getAuthority();
                var nomeUsuario = usuarioAutenticado.getNome();

                return ResponseEntity.ok(new DadosTokenJWT(tokenJWT, cd_pessoa, tipoUsuario, nomeUsuario));
            } catch (AuthenticationException e) {
                // Se a autenticação falhar, incremente o contador de tentativas de login
                usuario.incrementarTentativasLogin();
                usuarioRepository.save(usuario);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }
}
