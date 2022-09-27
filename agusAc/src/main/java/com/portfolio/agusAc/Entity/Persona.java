package com.portfolio.agusAc.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 1, max = 40, message = "Completar campo en 1 y 40 caracteres")
    private String nombre;
    @NotNull
    @Size(min = 1, max = 40, message = "Completar campo en 1 y 40 caracteres")
    private String descripcion;
    @NotNull
    @Size(min = 1, max = 40, message = "Completar campo en 1 y 40 caracteres")
    private String img;
    @NotNull
    @Size(min = 1, max = 40, message = "Completar campo en 1 y 40 caracteres")
    private String titulo;

}