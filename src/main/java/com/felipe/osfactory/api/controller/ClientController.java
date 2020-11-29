package com.felipe.osfactory.api.controller;

import com.felipe.osfactory.domain.model.Client;
import com.felipe.osfactory.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> listAll(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> search(@PathVariable Long id){
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()){
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@Valid @RequestBody Client client){
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@Valid @PathVariable Long id, @RequestBody Client client){
        if (!clientRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        client.setId(id);
        client = clientRepository.save(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (!clientRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        clientRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
