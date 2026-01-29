package com.emmadev.ecommerce.repository;

import com.emmadev.ecommerce.entity.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemCompra, Long> {
}
