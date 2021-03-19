package com.samus.irdigital.backend.service;

import com.samus.irdigital.backend.api.client.dto.Client;
import com.samus.irdigital.backend.api.client.dto.ClientKPI;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    Client getClientById(UUID id);

    List<Client> getClients();

    ClientKPI getKPI();

    Client createClient(Client client);
}
