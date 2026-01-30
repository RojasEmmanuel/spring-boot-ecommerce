package com.emmadev.ecommerce.repository;

import com.emmadev.ecommerce.entity.Cliente;
import com.emmadev.ecommerce.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByCliente(Cliente cliente);
}
