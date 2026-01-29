package com.emmadev.ecommerce.repository;

import com.emmadev.ecommerce.entity.Categoria;
import com.emmadev.ecommerce.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
