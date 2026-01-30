package com.emmadev.ecommerce.service;

import com.emmadev.ecommerce.DTO.CompraReponseDTO;
import com.emmadev.ecommerce.domain.EstatusVenta;
import com.emmadev.ecommerce.entity.Cliente;
import com.emmadev.ecommerce.entity.Compra;
import com.emmadev.ecommerce.repository.ClienteRepository;
import com.emmadev.ecommerce.repository.CompraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor

public class CompasService {
    private  final CompraRepository repository;
    private  final ClienteRepository clienteRepository;

    @Transactional
    public void empezarCompra(Long idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).
                orElseThrow(()->new RuntimeException("Cliente no encontrado"));

        Compra compra = new Compra();
        compra.setFechaCreacion(LocalDateTime.now());
        compra.setEstatus(EstatusVenta.INICIADA);
        compra.setCliente(cliente);
        compra.setTotal(BigDecimal.ZERO);
        repository.save(compra);
    }

    //Lista todas las compras de un cliente
    @Transactional(readOnly = true)
    public List<CompraReponseDTO> getCompras(Long idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).
                orElseThrow(()->new RuntimeException("Cliente no encontrado"));

        return repository.findByCliente(cliente).stream().map(
            compra -> new CompraReponseDTO(
                compra.getId(),
                compra.getFechaCreacion(),
                compra.getEstatus(),
                compra.getCliente().getNombre()+" "+compra.getCliente().getApellidos(),
                compra.getTotal()
            )
        ).toList();
    }

    @Transactional
    public CompraReponseDTO finalizarCompra(Long idCompra, boolean estatus){
        Compra compra = repository.findById(idCompra).
                orElseThrow(()->new RuntimeException("No existe esta compra"));

        if(estatus){
            compra.setEstatus(EstatusVenta.FINALIZADA);
        }else{
            compra.setEstatus(EstatusVenta.CANCELADA);
        }

        repository.save(compra);

        CompraReponseDTO c = new CompraReponseDTO(
            compra.getId(),
            compra.getFechaCreacion(),
            compra.getEstatus(),
            compra.getCliente() != null ?
                    compra.getCliente().getNombre() +" "+compra.getCliente().getApellidos() : null,
            compra.getTotal()
        );

        return c;
    }

    public void eliminarCompra(Long idCompra){
        if(! repository.existsById(idCompra)){
            throw  new IllegalArgumentException("Esta compra no existe");
        }


        repository.deleteById(idCompra);
    }
}
