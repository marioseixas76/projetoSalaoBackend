package br.com.projetosalao.controller;

import br.com.projetosalao.dominio.pessoa.entity.TipoUsuario;
import br.com.projetosalao.repository.TipoUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/tipousuario", "/tipousuario/"})
public class TipoUsuarioController {

    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping
    public ResponseEntity<Collection<TipoUsuario>> findAll() {
        List<TipoUsuario> tipoUsuarios = tipoUsuarioRepository.findAll();

        return ResponseEntity.ok(tipoUsuarios);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<TipoUsuario> findById(@PathVariable Long id) {
        Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(id);

        return tipoUsuarioOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> save(@RequestBody TipoUsuario tipoUsuario, UriComponentsBuilder uriComponentsBuilder) {
        TipoUsuario saved = tipoUsuarioRepository.save(tipoUsuario);
        if(saved != null) {
            URI uri = uriComponentsBuilder.path("/tipousuario/{id}").buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @PutMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<TipoUsuario> update(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario) {
        Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(id);

        if(tipoUsuarioOptional.isPresent()) {
            TipoUsuario retorno = tipoUsuarioOptional.get();

            if(Objects.nonNull(tipoUsuario.getTipo_usuario())) retorno.setTipo_usuario(tipoUsuario.getTipo_usuario());

            return ResponseEntity.ok(retorno);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(id);

        if(tipoUsuarioOptional.isPresent()) {
            tipoUsuarioRepository.delete(tipoUsuarioOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
