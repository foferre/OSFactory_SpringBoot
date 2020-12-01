package com.felipe.osfactory.api.controller;

import com.felipe.osfactory.domain.model.ServiceOrder;
import com.felipe.osfactory.domain.repository.ServiceOrderRepository;
import com.felipe.osfactory.domain.service.ManagerServiceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

    @Autowired
    private ManagerServiceOrder managerServiceOrder;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrder create(@Valid @RequestBody ServiceOrder serviceOrder){
        return managerServiceOrder.create(serviceOrder);
    }

    @GetMapping
    public List<ServiceOrder> list(){
        return serviceOrderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrder> search(@PathVariable Long id){
        Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(id);

        if (serviceOrder.isPresent()){
            return ResponseEntity.ok(serviceOrder.get());
        }
        return ResponseEntity.notFound().build();
    }
}
