package com.ms.tdd.controller;

//import com.ms.tdd.model.Client;
//import com.ms.tdd.repository.ClientRepository;
import com.ms.tdd.model.Client;
import com.ms.tdd.repository.ClientRepository;
import com.ms.tdd.service.ClientService;
//import org.bson.types.ObjectId;
import domain.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.annotation.Id;
//import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.Arrays;
import java.util.Arrays;
import java.util.List;
//import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    ClientRepository repository;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> FindAll() {
        return ResponseEntity.ok(service.findAll());
        /*return Arrays.asList(Client.builder().
                name("Neuber")
                .email("neuber.paiva@gmail.com")
                .cel("9994545429").build());*/
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO entity) {
        //entity.setId(ObjectId.get().toString());
        return ResponseEntity.ok().body(service.create(entity));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ClientDTO> FindById(@PathVariable String id){

        return ResponseEntity.status(HttpStatus.OK).body( service.findById(id));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ClientDTO> updateById(@PathVariable String id , @RequestBody ClientDTO clientDTO){

        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, clientDTO));

    }

    @DeleteMapping(value="/{id}")
    public void  delete(@PathVariable String id){
        service.delete(id);
    }
}




//ANTIGO CONTROLLER QUE FUNCIONAVA
//    private final ClientService clientService;
//
//    @Autowired
//    public ClientController(ClientService clientService) {
//
//        this.clientService = clientService;
//    }
//
//    @GetMapping
//    public List<Client> getAllClients() {
//
//        return clientService.getAllClients();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Client> getClientById(@PathVariable String id) {
//
//        return clientService.getClientById(id);
//    }
//
//    @PostMapping
//    public Client createClient(@RequestBody Client client) {
//
//        return clientService.createClient(client);
//    }
//
//    @PutMapping("/{id}")
//    public Client updateClient(@PathVariable String id, @RequestBody Client updatedClient) {
//        return clientService.updateClient(id, updatedClient);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteClient(@PathVariable String id) {
//        clientService.deleteClient(id);
//    }
//
//}




    /*@Autowired
    private ClientRepository repository;

    @GetMapping
    public List<Client> list() {
        return repository.findAll();


        /*return Arrays.asList(Client.builder().

                name("Neuber")
                .email("neuber.paiva@gmail.com")
                .cel("9994545429").build());



    }

    @PostMapping
    public Client create(@RequestBody Client entity) {
        entity.setId(ObjectId.get().toString());
        return repository.save(entity);
    }



    // com controller pra funcionar só com ele
    // OPTIONAL TEM QUE VIR PRA NÃO FICAR NULO -------------------------------------------
    @GetMapping("/{id}")
    public Optional<Client> findById(@PathVariable String id) {
        return repository.findById(id);
    }




    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    /*@DeleteMapping("/clients/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable String id) {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    } */


