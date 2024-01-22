package org.portfolio.emailsender.service;


import org.portfolio.emailsender.DTO.ContactRequestDTO;
import org.portfolio.emailsender.model.entities.Client;
import org.portfolio.emailsender.model.entities.Contact;
import org.portfolio.emailsender.repository.ClientRepository;
import org.portfolio.emailsender.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    ContactRepository repository;
    @Autowired
    ClientRepository clientRepository;


    public Optional<Contact> getById(Long id) {
       return repository.findById(id);
    }

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public void create(ContactRequestDTO data) {
        Optional<Client> client = clientRepository.findById(data.clientId());
        client.ifPresent(value -> repository.save(new Contact(
                data.name(),
                data.email(),
                client.get()
        )));
    }

    public void edit(ContactRequestDTO data) {
        Optional<Contact> contact = repository.findById(data.id());
        if(contact.isPresent()){
            contact.get().setName(data.name());
            repository.save(contact.get());
        }
    }

    public void delete(Long id) {

        Optional<Contact> contact = repository.findById(id);
        contact.ifPresent(delete-> repository.delete(contact.get()));
    }
}
