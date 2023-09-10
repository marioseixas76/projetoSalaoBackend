package br.com.projetosalao.controller;

import br.com.projetosalao.dominio.pessoa.entity.Profissional;
import br.com.projetosalao.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/profissional", "/profissional/"})
public class ProfissionalController {

    @Autowired
    ProfissionalRepository profissionalRepository;

    @GetMapping
    public ResponseEntity<Collection<Profissional>> findAll() {
        List<Profissional> profissionais = profissionalRepository.findAll();

        return ResponseEntity.ok(profissionais);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Profissional> findById(@PathVariable Long id){
        Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);

        return profissionalOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Profissional> save(@RequestBody Profissional profissional, UriComponentsBuilder uriComponentsBuilder) {
        profissional.setDataCadastro(LocalDateTime.now());
        profissional.setStatus("Ativo");
        Profissional saved = profissionalRepository.save(profissional);
        if(saved != null) {
            URI uri = uriComponentsBuilder.path("/profissional/{id}").buildAndExpand(saved.getCd_pessoa()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @PutMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Profissional> update(@PathVariable Long id, @RequestBody Profissional profissional) {
        Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);

        if(profissionalOptional.isPresent()) {
            Profissional retorno = profissionalOptional.get();

            if(Objects.nonNull(profissional.getCep())) retorno.setCep(profissional.getCep());
            if(Objects.nonNull(profissional.getLogradouro())) retorno.setLogradouro(profissional.getLogradouro());
            if(Objects.nonNull(profissional.getComplemento())) retorno.setComplemento(profissional.getComplemento());
            if(Objects.nonNull(profissional.getBairro())) retorno.setBairro(profissional.getBairro());
            if(Objects.nonNull(profissional.getCidade())) retorno.setCidade(profissional.getCidade());
            if(Objects.nonNull(profissional.getEstado())) retorno.setEstado(profissional.getEstado());
            if(Objects.nonNull(profissional.getTelefone())) retorno.setTelefone(profissional.getTelefone());
            if(Objects.nonNull(profissional.getTelefoneRecado())) retorno.setTelefoneRecado(profissional.getTelefoneRecado());
            if(Objects.nonNull(profissional.getEmail())) retorno.setEmail(profissional.getEmail());
            if(Objects.nonNull(profissional.isWhatsApp())) retorno.setWhatsApp(profissional.isWhatsApp());
            if(Objects.nonNull(profissional.getStatus())) retorno.setStatus(profissional.getStatus());
            if(Objects.nonNull(profissional.getCd_especialidade())) retorno.setCd_especialidade(profissional.getCd_especialidade());

            return ResponseEntity.ok(retorno);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);

        if(profissionalOptional.isPresent()) {
            profissionalRepository.delete(profissionalOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
