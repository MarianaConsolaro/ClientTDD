package domain.dto;

import com.ms.tdd.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
//USANDO DTO COM BeanUtils
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private String id;
    private String name;
    private String email;
    private String cel;
    private String cpf;


    public  ClientDTO (Client entity) {
        BeanUtils.copyProperties(entity, this);
    }

}



/*
// USANDO MODEL MAPPER
import com.ms.tdd.model.Client;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.modelmapper.ModelMapper;

@Data
public class ClientDTO {
    @Id
    private String id;
    private String name;
    private String email;
    private String cel;
    private String cpf;


    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.cel = client.getCel();
        this.cpf = client.getCel();
    }

    public static ClientDTO create(Client client) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientDTO.class);
    }


}

 */
