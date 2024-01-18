package org.portfolio.emailsender.model.channels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.portfolio.emailsender.service.EmailService;

@Entity
@DiscriminatorValue("email")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email extends ContactChannel {
    private String emailAddress;

    @Override
    public void sendMessage(String message) {
      new EmailService().send(message);
    }
}