package com.emmadev.ecommerce.service;

import com.emmadev.ecommerce.entity.Categoria;
import com.emmadev.ecommerce.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CategoriaService {

    private final CategoriaRepository repository;

    @Transactional
    public void saveCategoria(Categoria c){
        repository.save(c);
    }

    @Transactional(readOnly = true)
    public List<Categoria> findCategorias(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Categoria findCategoria(Long idCategoria){
        return repository.findById(idCategoria).
                orElseThrow(()->new RuntimeException("No existe una categoria con el id"+idCategoria));
    }

    @Transactional
    public void eliminarCategoria(Long idCategoria){
        if(!repository.existsById(idCategoria)){
            throw  new RuntimeException("No existe una categoria con este id");
        }

        repository.deleteById(idCategoria);
    }
}
