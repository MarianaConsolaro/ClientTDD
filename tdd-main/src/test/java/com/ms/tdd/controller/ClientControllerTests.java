package com.ms.tdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.tdd.TddApplicationTests;
import com.ms.tdd.model.Client;
//import org.junit.Before;
//import org.junit.Test;
//import com.ms.tdd.repository.ClientRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Se for usar o delete com variável
//@SpringBootTest
//@ActiveProfiles("test")
public class ClientControllerTests extends TddApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ClientController controller;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    //TESTE POST
    @Test
    @Order(0)
    public void testCreateClient() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/clients")
                        .content(asJsonString(new Client(null, "Mariana", "mariana.consolaro@gmail.com", "02564897523", "9994545429")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    //TESTE READ
    @Test
    @Order(1)
    public void testClientList() throws Exception {
        this.mockMvc.perform(get("/clients"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    //TESTE READ BY ID
    @Test
    @Order(2)
    public void testFindById() throws Exception {
        String clientId = "65eb1a3669568b43470ef7df"; //--> TESTE PASSA
        //String clientId = "id"; --> TESTE NÃO PASSA

        this.mockMvc.perform(get("/clients/{id}", clientId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clientId));

        //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clientId));
    }
    // TINHA NO PROJETO DO NEUBER
    //this.mockMvc.perform( MockMvcRequestBuilders
    //.get("/clients/{id}", clientId)
    //.content(asJsonString(new Client(id:"65eb1a3669568b43470ef7df"))
    //.contentType(MediaType.APPLICATION_JSON)
    //.accept(MediaType.APPLICATION_JSON))
    //  .andExpect(MockMvcResultMatchers.status().isOk())
    //.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    //}

    @Test
    @Order(3)
    public void testUpdateClient() throws Exception {
        this.mockMvc.perform( MockMvcRequestBuilders
                        .put("/clients/65eb1a3669568b43470ef7df")
                        .content(asJsonString(new Client(null, "Mariana Perez UPDATE!", "mariana.consolaro.@gmail.com", "02564897523", "9994545429")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }


    @Test
    @Order(4)
    public void testDeleteById() throws Exception {
        //Deletar pelo ID? ou fazer um genérico?
        String clientId = "65ef3c74c4728036071757a3";
        // String clientId = id ou 1; ->> TESTE DE FALHA

        this.mockMvc.perform(delete("/clients/{id}", clientId))
                .andExpect(status().isOk());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clientId));
    }

    // Teste criando um usuário em uma variável para logo em seguida ser deletado
    /*
    @Test
    @Order(4)
    public void testDeleteClient() throws Exception {
        Client newClient = new Client(null, "Neuber", "neuber.paiva@gmail.com", "9994545429", "440120165656");

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newClient))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(response);
        String clientId = jsonObject.getString("id");

        this.mockMvc.perform( MockMvcRequestBuilders
                        .delete("/clients/" + clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());
    }
     */

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}