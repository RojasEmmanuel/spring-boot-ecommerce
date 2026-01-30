package com.emmadev.ecommerce.controller;

import com.emmadev.ecommerce.DTO.ClientUpdateDTO;
import com.emmadev.ecommerce.DTO.ClienteCreateDTO;
import com.emmadev.ecommerce.DTO.ClienteReponseDTO;
import com.emmadev.ecommerce.DTO.ClienteResponsePublicDTO;
import com.emmadev.ecommerce.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ecommerce/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/public")
    public List<ClienteResponsePublicDTO> getClientes(){
        return service.getClientes();
    }

    @PostMapping
    public ClienteCreateDTO registrarCliente(@Valid  @RequestBody ClienteCreateDTO dto){
        return service.createCliente(dto);
    }

    @GetMapping
    public List<ClienteReponseDTO> getAllClientes(){
        return service.getAllClientes();
    }

    @PatchMapping
    public ClientUpdateDTO updateCliente(@Valid @RequestBody ClientUpdateDTO dto){
        return service.updateCliente(dto);
    }

    @DeleteMapping("/{idCliente}")
    public void eliminarCliente(@PathVariable Long idCliente){
        service.eliminarCliente(idCliente);
    }
}
