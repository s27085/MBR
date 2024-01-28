package org.carrental.model.service;

import org.carrental.exception.ClientNotFoundException;
import org.carrental.exception.ValidationException;
import org.carrental.model.client.Client;
import org.carrental.model.repository.ClientRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ClientService {
    final ClientRepository clientRepository;
    private static final Logger logger = LogManager.getLogger();

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    public Client getClientById(Integer id) {
        if (id == null) {
            throw new ValidationException("id", "cannot be null");
        }
        return clientRepository.getClientById(id)
                .orElseThrow(() -> new ClientNotFoundException("id doesn't match any client"));
    }

    public Client register(Client client) {
        ClientService.logger.info("Attempting to register a client...");
        validateClientFields(client);
        ClientService.logger.info("Client registered successfully");
        return clientRepository.create(client);
    }

    public void removeClient(Integer id) {
        if (id == null) {
            throw new ValidationException("id", "cannot be null");
        }
        clientRepository.removeById(id);
    }

    public void modifyClient(Client client) {
        ClientService.logger.info("Attempting to modify a client...");
        validateClientFields(client);
        ClientService.logger.info("Client modified successfully");
        clientRepository.modify(client)
                .orElseThrow(() -> new ClientNotFoundException("Id doesn't match any client"));
    }

    static void validateClientFields(Client client) {
        if (client.getName() == null || client.getName().isBlank()) {
            throw new ValidationException("name", "Cannot be null or blank");
        }
        if (client.getGender() == null) {
            throw new ValidationException("gender", "Cannot be null or blank");
        }
    }
}