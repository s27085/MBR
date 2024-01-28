package org.carrental.model.repository;

import org.carrental.model.client.Client;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class ClientRepository {
    private List<Client> clientList = new ArrayList<>();

    public Client create(Client client){
        client.setId(clientList.size());
        clientList.add(client);
        return client;
    }
    public Optional<Client> getClientById(Integer id){
        return clientList.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }
    public void removeById(Integer id){
        Optional<Client> client = getClientById(id);
        client.ifPresent(it -> clientList.remove(it));
    }
    public void truncateList(){clientList = new ArrayList<>();}
    public Optional<Client> modify(Client client){
        Optional<Client> clientToModify = getClientById(client.getId());
        return clientToModify.map(it ->{
            it.setGender(client.getGender());
            it.setName(client.getName());
            return it;
        });
    }
    public List<Client> getAll() {return clientList;}
}
