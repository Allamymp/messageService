package org.portfolio.emailsender.controller;

import org.portfolio.emailsender.DTO.ContactRequestDTO;
import org.portfolio.emailsender.DTO.ContactResponseDTO;
import org.portfolio.emailsender.model.entities.Contact;
import org.portfolio.emailsender.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    ContactService service;

    @PostMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable @Validated Long id) {
        try {
            Optional<Contact> contact = service.getById(id);
            if (contact.isPresent()) {
                return ResponseEntity.ok().body(new ContactResponseDTO(
                        contact.get().getId(),
                        contact.get().getName(),
                        contact.get().getClient().getId(),
                        contact.get().getChannels()
                ));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            List<Contact> list = service.getAll();
            List<ContactResponseDTO> data = new ArrayList<>();
            for (Contact contact : list) {
                data.add(new ContactResponseDTO(
                        contact.getId(),
                        contact.getName(),
                        contact.getClient().getId(),
                        contact.getChannels()
                ));
            }
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated ContactRequestDTO data) {
        try {
            service.create(data);
            return ResponseEntity.ok().body("Contact " + data.name() + " created!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody @Validated ContactRequestDTO data) {
        try {
            service.edit(data);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Validated Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
