package org.portfolio.emailsender.DTO;


import org.portfolio.emailsender.model.channels.ContactChannel;

import java.util.List;

public record ContactResponseDTO(Long id, String name, Long clientId, List<ContactChannel> contactChannels) {
}
