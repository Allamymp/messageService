package org.portfolio.emailsender.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.portfolio.emailsender.model.channels.ContactChannel;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    @ManyToOne
    private Client client;
    @OneToMany
    private List<ContactChannel> channels = new ArrayList<>();

    public void addChannel(ContactChannel channel) {
        channels.add(channel);
        channel.setContact(this);
    }

    public void removeChannel(ContactChannel channel) {
        channels.remove(channel);
        channel.setContact(null);
    }
}
