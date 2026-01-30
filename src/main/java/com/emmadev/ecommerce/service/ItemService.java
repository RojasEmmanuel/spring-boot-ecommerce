package com.emmadev.ecommerce.service;

import com.emmadev.ecommerce.domain.EstatusVenta;
import com.emmadev.ecommerce.entity.Compra;
import com.emmadev.ecommerce.entity.ItemCompra;
import com.emmadev.ecommerce.entity.Producto;
import com.emmadev.ecommerce.repository.CompraRepository;
import com.emmadev.ecommerce.repository.ItemRepository;
import com.emmadev.ecommerce.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class ItemService {

    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
    private final ItemRepository repository;

    @Transactional
    public ItemCompra agregarItem(Long idCompra, Long idProducto, int cantidad){

        Compra c = compraRepository.findById(idCompra).orElseThrow(
            ()->new RuntimeException("No existe una compra con este ID")
        );

        validarCompra(c);

        Producto producto = productoRepository.findById(idProducto).orElseThrow(
            ()->new RuntimeException("No existe un producto con este ID")
        );

        validarProducto(producto, cantidad);

        ItemCompra item = repository.findByCompraAndProducto(c, producto);

        if(item == null){
            item = new ItemCompra();
            item.setCompra(c);
            item.setProducto(producto);
            item.setCantidad(cantidad);
            calcularSubTotal(item);
        }else{
            item.setCantidad(item.getCantidad()+cantidad);
            calcularSubTotal(item);
        }

        producto.setExistencia(producto.getExistencia() - cantidad);
        productoRepository.save(producto);

        repository.save(item);
        recalcularTotalCompra(c);
        return item;
    }

    private void calcularSubTotal(ItemCompra item){
        item.setSubtotal(
            item.getProducto().getPrecioVenta().
                multiply(BigDecimal.valueOf(item.getCantidad()))
        );
    }

    private void validarCompra(Compra c){
        if(!c.getEstatus().equals(EstatusVenta.INICIADA)){
            throw  new RuntimeException("Esta compra ya no es posible modificar");
        }
    }

    private void validarProducto(Producto p, int cantidad){
        if(p.getExistencia() < cantidad){
            throw  new RuntimeException("El producto no cuenta con el stock suficiente");
        }
    }

    @Transactional
    public void actualizarCantidadItem(Long idItem, int cantidad) {
        ItemCompra item = repository.findById(idItem).orElseThrow(
                () -> new IllegalArgumentException("No existe un item con este id")
        );

        Compra compra = item.getCompra();
        Producto producto = item.getProducto();

        validarCompra(compra);

        int diferencia = cantidad - item.getCantidad();

        if(diferencia > 0){
            validarProducto(producto, cantidad);
            producto.setExistencia(producto.getExistencia()-diferencia);
        }else if(diferencia < 0){
            producto.setExistencia(producto.getExistencia() + Math.abs(diferencia));
        }

        item.setCantidad(cantidad);
        calcularSubTotal(item);
        recalcularTotalCompra(compra);
    }

    @Transactional
    public void eliminarItem(Long idItem){

        ItemCompra item = repository.findById(idItem).orElseThrow(
                () -> new IllegalArgumentException("No existe un item con este id")
        );

        Compra compra = item.getCompra();
        recalcularTotalCompra(compra);
        repository.delete(item);
    }

    // función secundaria que da por hecho que si existe la compra
    @Transactional
    public void recalcularTotalCompra(Compra c){
        List<ItemCompra> carrito = repository.findByCompra(c);

        if (carrito.isEmpty()) {
            throw new RuntimeException("Esta compra aun no tiene elementos");
        }

        BigDecimal total = carrito.stream().map(
                ItemCompra::getSubtotal).
                reduce(BigDecimal.ZERO, BigDecimal::add);
        c.setTotal(total);
        compraRepository.save(c);
    }

    @Transactional(readOnly = true)
    public List<ItemCompra> ListaDeCompra(Long idCompra){
        Compra compra = compraRepository.findById(idCompra).orElseThrow(
                ()->new IllegalArgumentException("No existe una compra con este ID")
        );

        return repository.findByCompra(compra);
    }
}
