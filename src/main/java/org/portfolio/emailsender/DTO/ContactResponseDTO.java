package org.portfolio.emailsender.DTO;


import org.portfolio.emailsender.model.channels.ContactChannel;

import java.util.List;

public record ContactResponseDTO(Long id, String name, String email, Long clientId, List<ContactChannel> contactChannels) {
}
