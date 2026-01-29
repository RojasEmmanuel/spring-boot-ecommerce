package com.emmadev.ecommerce.controller;

import com.emmadev.ecommerce.entity.Categoria;
import com.emmadev.ecommerce.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/ecommerce/categoria")
public class CategoriaController {

    private CategoriaService service;

    @GetMapping
    public List<Categoria> getCategoria(){
        return service.findCategorias();
    }

    @PostMapping
    public void saveCategoria(@RequestBody Categoria c){
        service.saveCategoria(c);
    }

    @GetMapping("/{idCategoria}")
    public Categoria getCategoria(@PathVariable Long idCategoria){
        return  service.findCategoria(idCategoria);
    }

    @DeleteMapping("/{idCategoria}")
    public void eliminarCategoria(@PathVariable Long idCategoria){
        service.eliminarCategoria(idCategoria);
    }
}
