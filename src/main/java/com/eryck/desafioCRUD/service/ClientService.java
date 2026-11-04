package com.eryck.desafioCRUD.service;

import com.eryck.desafioCRUD.dto.ClientDTO;
import com.eryck.desafioCRUD.entities.Client;
import com.eryck.desafioCRUD.repository.ClientRepository;
import com.eryck.desafioCRUD.service.exceptions.DatabaseException;
import com.eryck.desafioCRUD.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso nao encontrado"));
        return new ClientDTO(client);
    }

    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));

    }

    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        copyDtoToclient(dto, client);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    public ClientDTO update(Long id, ClientDTO dto){

            Client client = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Recurso nao encontrado"));

            copyDtoToclient(dto, client);

            client = repository.save(client);

            return new ClientDTO(client);

    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso nao encotrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha da integridade referencial");
        }
    }

    private void copyDtoToclient(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
