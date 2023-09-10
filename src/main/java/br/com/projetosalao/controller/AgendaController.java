package br.com.projetosalao.controller;

import br.com.projetosalao.dominio.pessoa.entity.Agenda;
import br.com.projetosalao.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/agenda", "/agenda/"})
public class AgendaController {

    @Autowired
    AgendaRepository agendaRepository;

    @GetMapping
    public ResponseEntity<Collection<Agenda>> findAll() {
        List<Agenda> agendamentos = agendaRepository.findAll();

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Agenda> findById(@PathVariable Long id) {
        Optional<Agenda> agendaOptional = agendaRepository.findById(id);

        return agendaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Agenda> save(@RequestBody Agenda agenda, UriComponentsBuilder uriComponentsBuilder) {
        Agenda saved = agendaRepository.save(agenda);
        if(saved != null) {
            URI uri = uriComponentsBuilder.path("/agenda/{id}").buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

}
