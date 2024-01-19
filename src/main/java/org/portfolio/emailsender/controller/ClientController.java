package org.portfolio.emailsender.controller;

import org.portfolio.emailsender.DTO.ClientRequestDTO;
import org.portfolio.emailsender.DTO.ClientResponseDTO;
import org.portfolio.emailsender.model.entities.Client;
import org.portfolio.emailsender.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientService service;

    @PostMapping("/{id}")
    public ResponseEntity<?> getClientByID(@PathVariable @Validated Long id) {
        try {
            Optional<Client> clientOptional = service.getByID(id);
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                ClientResponseDTO data = new ClientResponseDTO(client.getId(), client.getName(), client.getEmail(), client.getContactList());
                return ResponseEntity.ok().body(data);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllClients() {
        try {
            List<Client> clientList = service.getAll();
            List<ClientResponseDTO> data = new ArrayList<>();
            for (Client client : clientList) {
                data.add(new ClientResponseDTO(client.getId(), client.getName(), client.getEmail(), client.getContactList()));
            }
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody @Validated ClientRequestDTO data) {
        try {
            service.create(data);
            return ResponseEntity.ok().body("Client " + data.name() + " created!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editClient(@RequestBody @Validated ClientRequestDTO data) {
        try {
            Optional<Client> client = service.getByID(data.id());
            if (client.isPresent()) {
                service.edit(data);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable @Validated Long id) {
        try {
            Optional<Client> client = service.getByID(id);
            if (client.isPresent()) {
                service.delete(client);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}