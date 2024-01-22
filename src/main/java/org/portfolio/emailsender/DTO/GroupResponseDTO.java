package org.portfolio.emailsender.DTO;

import java.util.List;

public record GroupResponseDTO(Long id, String name, Long clientId, List<Long> contactsId) {
}
