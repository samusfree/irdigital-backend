package com.samus.irdigital.backend.api.client.rest;

import com.samus.irdigital.backend.api.client.dto.Client;
import com.samus.irdigital.backend.api.client.dto.ClientKPI;
import com.samus.irdigital.backend.api.dto.RestResponse;
import com.samus.irdigital.backend.api.enums.CodeEnum;
import com.samus.irdigital.backend.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/client")
@RestController
@Tag(name = "Client service", description = "Rest Api for clients")
public class ClientRest {
    private final ClientService clientService;

    @Autowired
    public ClientRest(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Create a client")
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<Client> createClient(@Valid @RequestBody Client client) {
        return getResponse(clientService.createClient(client));
    }

    @Operation(summary = "Get client by UIID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<Client> getClientById(@PathVariable("id") UUID id) {
        return getResponse(clientService.getClientById(id));
    }

    @Operation(summary = "List clients")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<List<Client>> getClients() {
        return getResponse(clientService.getClients());
    }

    @Operation(summary = "Client KPI")
    @GetMapping(value = "/kpi", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<ClientKPI> getClientKPI() {
        return getResponse(clientService.getKPI());
    }

    @SuppressWarnings("unchecked")
    private <T> RestResponse<T> getResponse(T response) {
        return (RestResponse<T>) RestResponse.builder().data(response).code(CodeEnum.SUCESSS).build();
    }
}
