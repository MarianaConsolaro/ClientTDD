package com.ms.tdd.service;

import com.ms.tdd.model.Client;
import com.ms.tdd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {

        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(String id) {

        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

//    public Client updateClient(String id, Client updatedClient) {
//        updatedClient.setId(id);
//        return clientRepository.save(updatedClient);
//    }

    public Client updateClient(String id , Client client){

        Client entity =  clientRepository.findById(id).get();

        entity.setName(client.getName());
        entity.setEmail(client.getEmail());
        entity.setCel(client.getCel());
        entity.setCpf(client.getCpf());

        return  clientRepository.save(entity);
    }

    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }
}
