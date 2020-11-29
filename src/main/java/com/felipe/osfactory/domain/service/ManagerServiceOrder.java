package com.felipe.osfactory.domain.service;

import com.felipe.osfactory.domain.exception.BusinessException;
import com.felipe.osfactory.domain.model.Client;
import com.felipe.osfactory.domain.model.ServiceOrder;
import com.felipe.osfactory.domain.model.StatusServiceOrder;
import com.felipe.osfactory.domain.repository.ClientRepository;
import com.felipe.osfactory.domain.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ManagerServiceOrder {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ServiceOrder create(ServiceOrder serviceOrder){
        Client client = clientRepository.findById(serviceOrder.getClient().getId())
                .orElseThrow(() -> new BusinessException("Cliente não encontrado!"));

        serviceOrder.setClient(client);
        serviceOrder.setStatus(StatusServiceOrder.OPEN);
        serviceOrder.setOpenDate(LocalDateTime.now());

        return serviceOrderRepository.save(serviceOrder);
    }
}
