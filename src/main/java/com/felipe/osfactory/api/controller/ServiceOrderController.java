package com.felipe.osfactory.api.controller;

import com.felipe.osfactory.domain.model.ServiceOrder;
import com.felipe.osfactory.domain.service.ManagerServiceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

    @Autowired
    private ManagerServiceOrder managerServiceOrder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrder create(@Valid @RequestBody ServiceOrder serviceOrder){
        return managerServiceOrder.create(serviceOrder);
    }
}
