package br.com.projetosalao.controller;

import br.com.projetosalao.dominio.pessoa.entity.Usuario;
import br.com.projetosalao.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/usuario", "/usuario/"})
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public ResponseEntity<Collection<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        return usuarioOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario, UriComponentsBuilder uriComponentsBuilder) {
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setStatus("Ativo");
        usuario.setTentativas_login(0);

        String senhaHash = passwordEncoder.encode(usuario.getPassword());
        usuario.setSenha(senhaHash);

        Usuario saved = usuarioRepository.save(usuario);
        if(saved != null) {
            URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(saved.getCd_pessoa()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @PutMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isPresent()) {
            Usuario retorno = usuarioOptional.get();

            if (Objects.nonNull(usuario.getPassword())) {
                // Hash da nova senha fornecida
                String senhaHash = passwordEncoder.encode(usuario.getPassword());
                retorno.setSenha(senhaHash);
            }
            retorno.setTentativas_login(0);
            if(Objects.nonNull(usuario.getCep())) retorno.setCep(usuario.getCep());
            if(Objects.nonNull(usuario.getLogradouro())) retorno.setLogradouro(usuario.getLogradouro());
            if(Objects.nonNull(usuario.getComplemento())) retorno.setComplemento(usuario.getComplemento());
            if(Objects.nonNull(usuario.getBairro())) retorno.setBairro(usuario.getBairro());
            if(Objects.nonNull(usuario.getCidade())) retorno.setCidade(usuario.getCidade());
            if(Objects.nonNull(usuario.getEstado())) retorno.setEstado(usuario.getEstado());
            if(Objects.nonNull(usuario.getTelefone())) retorno.setTelefone(usuario.getTelefone());
            if(Objects.nonNull(usuario.getTelefoneRecado())) retorno.setTelefoneRecado(usuario.getTelefoneRecado());
            if(Objects.nonNull(usuario.getEmail())) retorno.setEmail(usuario.getEmail());
            if(Objects.nonNull(usuario.isWhatsApp())) retorno.setWhatsApp(usuario.isWhatsApp());
            if(Objects.nonNull(usuario.getStatus())) retorno.setStatus(usuario.getStatus());


            return ResponseEntity.ok(retorno);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
