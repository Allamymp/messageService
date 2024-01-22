package org.portfolio.emailsender.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private Client client;
   @ManyToMany
    private List<Contact> contactList = new ArrayList<>();

    public Group(String name, Client client){
        this.name = name;
        this.client = client;
    }
    private void addContact(Contact contact){
        this.contactList.add(contact);
    }
    private void removeContact(Contact contact){
        this.contactList.remove(contact);
    }
}
