package org.portfolio.emailsender.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<Contact> contacts = new ArrayList<>();
    @ManyToMany
    private List<Group> groups = new ArrayList<>();

    public Contact(String name, String email, Client client) {
        this.name = name;
        this.email = email;
        this.client = client;

    }


}
