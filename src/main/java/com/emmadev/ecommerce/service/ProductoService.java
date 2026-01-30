package com.emmadev.ecommerce.service;

import com.emmadev.ecommerce.DTO.ProductResponseDTO;
import com.emmadev.ecommerce.DTO.ProductoCreateDTO;
import com.emmadev.ecommerce.DTO.ProductoPublicResponseDTO;
import com.emmadev.ecommerce.DTO.ProductoUpdateDTO;
import com.emmadev.ecommerce.entity.Categoria;
import com.emmadev.ecommerce.entity.Producto;
import com.emmadev.ecommerce.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;
    private final CategoriaService servicioCategoria;

    //Consulta de productos vista publica
    public List<ProductoPublicResponseDTO> findProductos(){
       return  repository.findAll().stream().map(
            producto -> new ProductoPublicResponseDTO(
                    producto.getNombre(),
                    producto.getPrecioVenta(),
                    producto.getCategoria() != null ?
                            producto.getCategoria().getNombre():null,
                    producto.getExistencia()
            )
        ).toList();
    }

    public List<ProductResponseDTO> getAllProducts(){
        return repository.findAll().stream().map(
            producto -> new ProductResponseDTO(
                    producto.getNombre(),
                    producto.getPrecioCompra(),
                    producto.getPrecioVenta(),
                    producto.getCategoria() != null ?
                            producto.getCategoria().getNombre():null,
                    producto.getExistencia(),
                    producto.getOperacion(),
                    producto.getUltimaModificacion()
            )
        ).toList();
    }

    //REGISTRO DE UN NUEVO PRODUCTO
    @Transactional
    public void registrarProducto(ProductoCreateDTO dto){

        if(dto.getPrecioVenta().compareTo(dto.getPrecioCompra())<0){
            throw new IllegalArgumentException("El precio de venta no puede ser menor al precio de compra");
        }

        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setPrecioCompra(dto.getPrecioCompra());
        p.setPrecioVenta(dto.getPrecioVenta());
        p.setExistencia(dto.getExistencia());
        p.setCategoria(servicioCategoria.findCategoria(dto.getIdCategoria()));
        repository.save(p);
    }


    @Transactional
    public ProductoUpdateDTO updateProducto(ProductoUpdateDTO dto){
       if(dto.getPrecioVenta().compareTo(dto.getPrecioCompra()) < 0){
            throw  new IllegalArgumentException("El precio de compra no puede ser mayor al precio de venta");
       }

        Producto p = repository.findById(dto.getId()).
                orElseThrow(()->new RuntimeException("Este producto no existe"));

        p.setNombre(dto.getNombre());
        p.setExistencia(dto.getExistencia());
        p.setPrecioVenta(dto.getPrecioVenta());
        p.setPrecioCompra(dto.getPrecioCompra());
        return dto;
    }

    public void eliminarProducto(Long idProducto){
        if(!repository.existsById(idProducto)){
            throw new RuntimeException("Este producto no existe en la base de datos");
        }

        repository.deleteById(idProducto);
    }
}
