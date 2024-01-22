package org.portfolio.emailsender.controller;


import org.portfolio.emailsender.DTO.GroupResponseDTO;
import org.portfolio.emailsender.model.entities.Contact;
import org.portfolio.emailsender.model.entities.Group;
import org.portfolio.emailsender.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    GroupService service;

    @PostMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable @Validated Long id) {
        try {
            Optional<Group> groupOptional = service.getById(id);
            Group concretGroup = new Group();
            List<Long> contactId = new ArrayList<>();
            if (groupOptional.isPresent()) {
                concretGroup = groupOptional.get();
                for (Contact contact : concretGroup.getContactList()) {
                    contactId.add(contact.getId());
                }
            }
            return ResponseEntity.ok().body(new GroupResponseDTO(
                    concretGroup.getId(),
                    concretGroup.getName(),
                    concretGroup.getClient().getId(),
                    contactId
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Group> list = service.getAll();
        List<GroupResponseDTO> data = new ArrayList<>();

        for (Group group : list) {
            List<Long> contactId = new ArrayList<>();
            for (Contact contact : group.getContactList()) {
                contactId.add(contact.getId());
            }
            data.add(new GroupResponseDTO(
                    group.getId(),
                    group.getName(),
                    group.getClient().getId(),
                    contactId
            ));
        }
        return ResponseEntity.ok().body(data);
    }

}
