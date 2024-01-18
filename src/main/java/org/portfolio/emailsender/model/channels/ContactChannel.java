package org.portfolio.emailsender.model.channels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.portfolio.emailsender.model.entities.Contact;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "channel_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class ContactChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;
    public abstract void sendMessage(String message);
}