package org.portfolio.emailsender.DTO;


import lombok.Data;


public record ClientRequestDTO(Long id, String name, String email, String password) {
}
