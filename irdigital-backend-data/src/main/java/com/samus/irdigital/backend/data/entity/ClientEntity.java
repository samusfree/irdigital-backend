package com.samus.irdigital.backend.data.entity;


import com.samus.irdigital.backend.api.client.dto.Client;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class ClientEntity extends Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clients_id")
    @Override
    public UUID getId() {
        return super.getId();
    }

    @Column(name = "first_name")
    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Column(name = "last_name")
    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Column
    @Override
    public Integer getAge() {
        return super.getAge();
    }

    @Column(name = "born_date")
    @Override
    public LocalDate getBornDate() {
        return super.getBornDate();
    }
}
