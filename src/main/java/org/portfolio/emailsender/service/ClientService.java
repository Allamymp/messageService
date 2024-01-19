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
    }

    public List<Client> getAll() {
    }

    public void create(ClientRequestDTO data) {
    }

    public void edit(ClientRequestDTO data) {
    }

    public void delete(Optional<Client> client) {
    }
}
