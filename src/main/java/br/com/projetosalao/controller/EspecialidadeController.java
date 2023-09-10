package br.com.projetosalao.controller;

import br.com.projetosalao.dominio.pessoa.entity.Especialidade;
import br.com.projetosalao.repository.EspecialidadeRepository;
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
@RequestMapping(value = {"/especialidade", "/especialidade/"})
public class EspecialidadeController {

    @Autowired
    EspecialidadeRepository especialidadeRepository;

    @GetMapping
    public ResponseEntity<Collection<Especialidade>> findAll() {
        List<Especialidade> especialidades = especialidadeRepository.findAll();

        return ResponseEntity.ok(especialidades);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Especialidade> findById(@PathVariable Long id){
        Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(id);

        return especialidadeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Especialidade> save(@RequestBody Especialidade especialidade, UriComponentsBuilder uriComponentsBuilder) {
        Especialidade saved = especialidadeRepository.save(especialidade);
        if(saved != null) {
            URI uri = uriComponentsBuilder.path("/especialidade/{id}").buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return  ResponseEntity.badRequest().build();
    }

    @Transactional
    @PutMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Especialidade> update(@PathVariable Long id, @RequestBody Especialidade especialidade) {
        Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(id);

        if(especialidadeOptional.isPresent()) {

            Especialidade retorno = especialidadeOptional.get();

            if(Objects.nonNull(especialidade.getNome())) retorno.setNome(especialidade.getNome());

            return  ResponseEntity.ok(retorno);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(id);

        if(especialidadeOptional.isPresent()) {
            especialidadeRepository.delete(especialidadeOptional.get());
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }


}
