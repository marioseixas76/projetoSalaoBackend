package br.com.projetosalao.controller;

import br.com.projetosalao.dominio.pessoa.entity.Servico;
import br.com.projetosalao.repository.ServicoRepository;
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
@RequestMapping(value = {"/servico", "/servico/"})
public class ServicoController {
    @Autowired
    ServicoRepository servicoRepository;

    @GetMapping
    public ResponseEntity<Collection<Servico>> findAll() {
        List<Servico> servicos = servicoRepository.findAll();

        return  ResponseEntity.ok(servicos);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Servico> findById(@PathVariable Long id){
        Optional<Servico> servicoOptional = servicoRepository.findById(id);

        return servicoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servico> save(@RequestBody Servico servico, UriComponentsBuilder uriComponentsBuilder) {
        servico.setStatus("Ativo");
        Servico saved = servicoRepository.save(servico);
        if(saved!=null) {
            URI uri = uriComponentsBuilder.path("/servico/{id}").buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @PutMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody Servico servico) {
        Optional<Servico> servicoOptional = servicoRepository.findById(id);

        if(servicoOptional.isPresent()) {
            Servico retorno = servicoOptional.get();

            if(Objects.nonNull(servico.getDs_servico())) retorno.setDs_servico(servico.getDs_servico());
            if(Objects.nonNull(servico.getVl_servico())) retorno.setVl_servico(servico.getVl_servico());
            if(Objects.nonNull(servico.getStatus())) retorno.setStatus(servico.getStatus());

            return ResponseEntity.ok(retorno);
        }

        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Servico> servicoOptional = servicoRepository.findById(id);

        if(servicoOptional.isPresent()) {
            servicoRepository.delete(servicoOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
