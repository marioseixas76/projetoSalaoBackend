package br.com.projetosalao.controller;

import br.com.projetosalao.dominio.pessoa.entity.Cliente;
import br.com.projetosalao.repository.ClienteRepository;
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
@RequestMapping(value = {"/cliente", "/cliente/"})
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<Collection<Cliente>> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        return clienteOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente, UriComponentsBuilder uriComponentsBuilder) {
        cliente.setDataCadastro(LocalDateTime.now());
        cliente.setStatus("Ativo");
        Cliente saved = clienteRepository.save(cliente);


        if(saved != null) {
            URI uri = uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(saved.getCd_pessoa()).toUri();
            return ResponseEntity.created(uri).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @PutMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()){
            Cliente retorno = clienteOptional.get();

            if(Objects.nonNull(cliente.getCep())) retorno.setCep(cliente.getCep());
            if(Objects.nonNull(cliente.getLogradouro())) retorno.setLogradouro(cliente.getLogradouro());
            if(Objects.nonNull(cliente.getComplemento())) retorno.setComplemento(cliente.getComplemento());
            if(Objects.nonNull(cliente.getBairro())) retorno.setBairro(cliente.getBairro());
            if(Objects.nonNull(cliente.getCidade())) retorno.setCidade(cliente.getCidade());
            if(Objects.nonNull(cliente.getEstado())) retorno.setEstado(cliente.getEstado());
            if(Objects.nonNull(cliente.getTelefone())) retorno.setTelefone(cliente.getTelefone());
            if(Objects.nonNull(cliente.getTelefoneRecado())) retorno.setTelefoneRecado(cliente.getTelefoneRecado());
            if(Objects.nonNull(cliente.getEmail())) retorno.setEmail(cliente.getEmail());
            if(Objects.nonNull(cliente.isWhatsApp())) retorno.setWhatsApp(cliente.isWhatsApp());
            if(Objects.nonNull(cliente.getStatus())) retorno.setStatus(cliente.getStatus());

            return ResponseEntity.ok(retorno);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity delete(@PathVariable Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()){
            clienteRepository.delete(clienteOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
