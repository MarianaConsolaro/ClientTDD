package com.ms.tdd.service;

import com.ms.tdd.model.Client;
import com.ms.tdd.repository.ClientRepository;
import domain.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;
    public List<ClientDTO> findAll() {
        List<Client> listEntidade = repository.findAll();
        List<ClientDTO> listDTO = listEntidade.stream().map(ClientDTO::new).toList();
        return listDTO ;

    }
    public ClientDTO create(ClientDTO entity) {
        Client client = new Client(entity);

        repository.save(client);

        ClientDTO clientDTO = new ClientDTO(client);

        return clientDTO;

    }


    public ClientDTO findById(String id) {

        Client client =  repository.findById(id).get();

        ClientDTO clientDTO = new ClientDTO(client);

        return clientDTO;
    }

    public ClientDTO update(String id , ClientDTO clientDTO){

        Client entity = repository.findById(id).get();

        entity.setName(clientDTO.getName());
        entity.setCel(clientDTO.getCel());
        entity.setEmail(clientDTO.getEmail());
        entity.setCpf(clientDTO.getCpf());

        repository.save(entity);

        ClientDTO newClientDTO = new ClientDTO(entity);

        return newClientDTO;
    }

    public void delete(String id) {
        Client entity  = repository.findById(id).get();
        repository.delete(entity);

    }
}



// ANTIGO SERVICE

//    private final ClientRepository clientRepository;

//    @Autowired
//    public ClientService(ClientRepository clientRepository) {
//        this.clientRepository = clientRepository;
//    }

//GET
//public List<ClientDTO> getAllClients() {

//return clientRepository.findAll();


//public Client createClient(Client client) {
//        return clientRepository.save(client);
//    }
//
////    public Client updateClient(String id, Client updatedClient) {
////        updatedClient.setId(id);
////        return clientRepository.save(updatedClient);
////    }

//CREATE

// ANTIGO FIND BY ID
//    public Optional<Client> getClientById(String id) {
//
//        return clientRepository.findById(id);
//    }


//UPDATE

//    public Client updateClient(String id , Client client){
//
//        Client entity =  clientRepository.findById(id).get();
//
//        entity.setName(client.getName());
//        entity.setEmail(client.getEmail());
//        entity.setCel(client.getCel());
//        entity.setCpf(client.getCpf());
//
//        return  clientRepository.save(entity);
//    }
//ANTIGO DELETE
//
//    public void deleteClient(String id) {
//
//        clientRepository.deleteById(id);
//    }
//}

