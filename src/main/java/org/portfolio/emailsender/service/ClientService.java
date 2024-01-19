package org.portfolio.emailsender.service;

import org.portfolio.emailsender.DTO.ClientRequestDTO;
import org.portfolio.emailsender.model.entities.Client;
import org.portfolio.emailsender.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public Optional<Client> getByID(Long id) {
        return repository.findById(id);
    }

    public List<Client> getAll() {
        return repository.findAll();
    }

    public void create(ClientRequestDTO data) {
        Client client = new Client(data.name(), data.email(), data.password());
        repository.save(client);
    }

    public void edit(ClientRequestDTO data) {
        Optional<Client> clientOptional = repository.findById(data.id());
        clientOptional.ifPresent(client -> {
            if (!data.name().isBlank()) {
                client.setName(data.name());
            }
            if (!data.email().isBlank()) {
                client.setEmail(data.email());
            }
            if (!data.password().isBlank()) {
                client.setPassword(data.password());
            }
            repository.save(client);
        });
    }

    public void delete(Optional<Client> clientOptional) {
        clientOptional.ifPresent(repository::delete);
    }
}
