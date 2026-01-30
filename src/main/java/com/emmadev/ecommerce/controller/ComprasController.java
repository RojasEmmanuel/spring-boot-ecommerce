package com.emmadev.ecommerce.controller;


import com.emmadev.ecommerce.DTO.CompraReponseDTO;
import com.emmadev.ecommerce.service.CompasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ecommerce/compras")
public class ComprasController {

    @Autowired
    private CompasService service;

    @GetMapping("/{idCliente}")
    public List<CompraReponseDTO> getCompras(@PathVariable Long idCliente){
        return service.getCompras(idCliente);
    }

    @PostMapping("/{idCliente}")
    public void empezarCompra(@PathVariable Long idCliente){
        service.empezarCompra(idCliente);
    }

    @PatchMapping("/{idCompra}/{estatus}")
    public CompraReponseDTO finalizarCompra(@PathVariable Long idCompra, @PathVariable boolean estatus){
        return service.finalizarCompra(idCompra, estatus);
    }

    @DeleteMapping("/{idCompra}")
    public void eliminarCompra(@PathVariable Long idCompra){
        service.eliminarCompra(idCompra);
    }

}
