package com.emmadev.ecommerce.service;

import com.emmadev.ecommerce.DTO.ClientUpdateDTO;
import com.emmadev.ecommerce.DTO.ClienteCreateDTO;
import com.emmadev.ecommerce.DTO.ClienteReponseDTO;
import com.emmadev.ecommerce.DTO.ClienteResponsePublicDTO;
import com.emmadev.ecommerce.entity.Cliente;
import com.emmadev.ecommerce.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional(readOnly = true)
    public List<ClienteResponsePublicDTO> getClientes(){
        return repository.findAll().stream().map(
            cliente -> new ClienteResponsePublicDTO(
                    cliente.getNombre()+" "+cliente.getApellidos(),
                    cliente.getSexo(),
                    cliente.getFechaNacimiento(),
                    cliente.getEdad()
            )
        ).toList();
    }

    @Transactional(readOnly = true)
    public List<ClienteReponseDTO> getAllClientes(){
        return repository.findAll().stream().map(
            cliente -> new ClienteReponseDTO(
                cliente.getNombre()+" "+cliente.getApellidos(),
                    cliente.getSexo(),
                    cliente.getFechaNacimiento(),
                    cliente.getEdad(),
                    cliente.getOperacion(),
                    cliente.getUltimaModificacion(),
                    "Activo desde "+obtenerAnios(
                            cliente.getFechaCreacion().toLocalDate()
                    )

            )
        ).toList();
    }

    @Transactional
    public ClienteCreateDTO createCliente(ClienteCreateDTO dto){
        if(obtenerAnios(dto.getFechaNacimiento()) < 18){
            throw new IllegalArgumentException("No cumple la edad minima requerida");
        }
        Cliente c = new Cliente();
        c.setNombre(dto.getNombre());
        c.setApellidos(dto.getApellidos());
        c.setSexo(dto.getSexo());
        c.setFechaNacimiento(dto.getFechaNacimiento());
        c.setEdad(obtenerAnios(dto.getFechaNacimiento()));
        c.setFechaCreacion(LocalDateTime.now());
        repository.save(c);
        return dto;
    }

    private int obtenerAnios(LocalDate fecha){
        Period periodo = Period.between(fecha, LocalDate.now());
        return periodo.getYears();
    }

    @Transactional
    public ClientUpdateDTO updateCliente(ClientUpdateDTO dto){
        Cliente cliente = repository.findById(dto.getId()).
                orElseThrow(()->new IllegalArgumentException("Este usuario no existe"));

        cliente.setNombre(dto.getNombre());
        cliente.setApellidos(dto.getApellidos());
        cliente.setSexo(dto.getSexo());
        return dto;
    }

    public void eliminarCliente(Long idCliente){
        if(! repository.existsById(idCliente)){
            throw new IllegalArgumentException("ID inválido");
        }

        repository.deleteById(idCliente);
    }
}
