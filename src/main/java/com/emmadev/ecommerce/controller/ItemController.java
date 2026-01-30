package com.emmadev.ecommerce.controller;

import com.emmadev.ecommerce.entity.ItemCompra;
import com.emmadev.ecommerce.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ecommerce/carrito")
@AllArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping("/{idCompra}/{idProducto}/{cantidad}")
    public ItemCompra agregarItemCarrito(@PathVariable Long idCompra, @PathVariable Long idProducto, @PathVariable int cantidad){
        return service.agregarItem(idCompra, idProducto, cantidad);
    }

    @PostMapping("/{idItem}/{cantidad}")
    public void actualizarItemCantidad(@PathVariable Long idItem, @PathVariable int cantidad){
        service.actualizarCantidadItem(idItem, cantidad);
    }

    @GetMapping("/{idCompra}")
    public List<ItemCompra> getCarritoCompra(@PathVariable Long idCompra){
        return  service.ListaDeCompra(idCompra);
    }

    @DeleteMapping("/{idItem}")
    public void eliminarItem(@PathVariable Long idItem){
        service.eliminarItem(idItem);
    }
}