package com.felipe.osfactory.domain.service;

import com.felipe.osfactory.domain.exception.BusinessException;
import com.felipe.osfactory.domain.model.Client;
import com.felipe.osfactory.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client){
        Client clientExists = clientRepository.findByEmail(client.getEmail());

        if (clientExists != null && !clientExists.equals(client)){
            throw new BusinessException("Já existe um cliente cadastrado com este e-mail.");
        }

        return clientRepository.save(client);
    }

    public void remove(Long id){
        clientRepository.deleteById(id);
    }
}
