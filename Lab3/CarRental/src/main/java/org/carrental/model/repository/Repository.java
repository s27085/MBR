package org.carrental.model.repository;

import org.carrental.model.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Repository {
    protected List<RepositoryEntry> entryList = new ArrayList<>();

    public RepositoryEntry create(RepositoryEntry client){
        client.setId(entryList.size());
        entryList.add(client);
        return client;
    }
    public Optional<RepositoryEntry> getById(Integer id){
        return entryList.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }
    public void removeByID(Integer id){
        Optional<RepositoryEntry> entry = getById(id);
        entry.ifPresent(it -> entryList.remove(entry));
    }
    public void removeAll(){
        entryList = new ArrayList<>();
    }

}
