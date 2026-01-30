package com.emmadev.ecommerce.controller;

import com.emmadev.ecommerce.DTO.ProductResponseDTO;
import com.emmadev.ecommerce.DTO.ProductoCreateDTO;
import com.emmadev.ecommerce.DTO.ProductoPublicResponseDTO;
import com.emmadev.ecommerce.DTO.ProductoUpdateDTO;
import com.emmadev.ecommerce.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ecommerce/productos")
public class ProductController {

    @Autowired
    private ProductoService service;

    @GetMapping("/public")
    public List<ProductoPublicResponseDTO> getProductos(){
        return service.findProductos();
    }


    @GetMapping
    public List<ProductResponseDTO> getAllProducto(){
        return service.getAllProducts();
    }


    @PostMapping
    public void registrarProducto(@Valid @RequestBody ProductoCreateDTO dto){
        service.registrarProducto(dto);
    }

    @PostMapping("/update")
    public ProductoUpdateDTO actualizarProducto(@Valid @RequestBody ProductoUpdateDTO dto){
        return service.updateProducto(dto);
    }

    @DeleteMapping("/{idProducto}")
    public void eliminarProducto(@PathVariable Long idProducto){
        service.eliminarProducto(idProducto);
    }
}
