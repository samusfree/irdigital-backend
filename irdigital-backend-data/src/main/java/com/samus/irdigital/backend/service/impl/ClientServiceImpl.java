package com.samus.irdigital.backend.service.impl;

import com.samus.irdigital.backend.api.client.dto.Client;
import com.samus.irdigital.backend.api.client.dto.ClientKPI;
import com.samus.irdigital.backend.data.entity.ClientEntity;
import com.samus.irdigital.backend.data.repository.ClientRepository;
import com.samus.irdigital.backend.exceptions.AgeAndBornDateDoesntCorrespondException;
import com.samus.irdigital.backend.exceptions.ClientNotFoundException;
import com.samus.irdigital.backend.service.ClientService;
import com.samus.irdigital.backend.utils.CalculateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CalculateUtils calculateUtils;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CalculateUtils calculateUtils) {
        this.clientRepository = clientRepository;
        this.calculateUtils = calculateUtils;
    }

    @Override
    public Client getClientById(UUID id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        Client client = new Client();
        BeanUtils.copyProperties(clientEntity, client);
        client.setPossibleDateOfDeath(calculateUtils.getPossibleDateOfDeath(client.getAge()));
        return client;
    }

    @Override
    public List<Client> getClients() {
        List<ClientEntity> entities = clientRepository.findAll();
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }
        List<Client> clients = new ArrayList<>();
        for (ClientEntity entity : entities) {
            Client client = new Client();
            BeanUtils.copyProperties(entity, client);
            client.setPossibleDateOfDeath(calculateUtils.getPossibleDateOfDeath(client.getAge()));
            clients.add(client);
        }
        return clients;
    }

    @Override
    public ClientKPI getKPI() {
        List<Client> clients = new ArrayList<>(clientRepository.findAll());
        return ClientKPI.builder()
                .averageAge(calculateUtils.getAverageAgeClients(clients))
                .standardDeviation(calculateUtils.getStandardDeviation(clients))
                .build();
    }

    @Override
    public Client createClient(Client client) {
        long years = ChronoUnit.YEARS.between(client.getBornDate(), LocalDate.now());
        if (years != client.getAge()) {
            throw new AgeAndBornDateDoesntCorrespondException();
        }
        ClientEntity entity = new ClientEntity();
        BeanUtils.copyProperties(client, entity);
        entity.setId(null);
        entity = clientRepository.save(entity);
        BeanUtils.copyProperties(entity, client);
        client.setPossibleDateOfDeath(calculateUtils.getPossibleDateOfDeath(client.getAge()));
        return client;
    }
}
