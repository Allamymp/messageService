package org.portfolio.emailsender.DTO;

import org.portfolio.emailsender.model.entities.Contact;

import java.util.List;

public record ClientResponseDTO(Long id, String name, String email, List<Contact> contacts) {
}
