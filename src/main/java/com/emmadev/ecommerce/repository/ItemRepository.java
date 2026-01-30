package com.emmadev.ecommerce.repository;

import com.emmadev.ecommerce.entity.Compra;
import com.emmadev.ecommerce.entity.ItemCompra;
import com.emmadev.ecommerce.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemCompra, Long> {

    ItemCompra findByCompraAndProducto(Compra compra, Producto producto);
    List<ItemCompra> findByCompra(Compra c);
}
