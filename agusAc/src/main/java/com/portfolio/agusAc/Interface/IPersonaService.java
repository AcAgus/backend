package com.portfolio.agusAc.Interface;

import com.portfolio.agusAc.Entity.Persona;

import java.util.List;

public interface IPersonaService {

    public List<Persona> getPersona();

    public void guardar(Persona persona);

    public void eliminar(int id);

    public Persona buscar(int id);
}
