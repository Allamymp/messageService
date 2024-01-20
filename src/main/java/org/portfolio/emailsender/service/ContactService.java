package org.portfolio.emailsender.service;


import org.portfolio.emailsender.DTO.ContactRequestDTO;
import org.portfolio.emailsender.model.entities.Contact;
import org.portfolio.emailsender.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    ContactRepository repository;


    public Optional<Contact> getById(Long id) {

    }

    public List<Contact> getAll() {
    }

    public void create(ContactRequestDTO data) {
    }

    public void edit(ContactRequestDTO data) {
    }

    public void delete(Long id) {
    }
}
