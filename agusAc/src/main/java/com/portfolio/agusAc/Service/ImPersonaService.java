package com.portfolio.agusAc.Service;

import com.portfolio.agusAc.Entity.Persona;
import com.portfolio.agusAc.Interface.IPersonaService;
import com.portfolio.agusAc.Repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImPersonaService implements IPersonaService {

    @Autowired
    IPersonaRepository iPersonaRepository;

    @Override
    public List<Persona> getPersona() {
        List<Persona> personas = iPersonaRepository.findAll();
        return personas;
    }

    @Override
    public void guardar(Persona persona) {
        iPersonaRepository.save(persona);
    }

    @Override
    public void eliminar(int id) {
        iPersonaRepository.deleteById(id);
    }

    @Override
    public Persona buscar(int id) {
        return iPersonaRepository.findById(id).orElse(null);
    }
}
