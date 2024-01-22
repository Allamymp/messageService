package org.portfolio.emailsender.DTO;

import java.util.List;

public record SendEmailDTO(String message, String myEmail, String myPassword, List<Long>contactId) {
}
