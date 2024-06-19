package com.virtualwaiter.crud.springbootvirtualwaiter.models;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class CustomerDto {

    @NotEmpty(message = "El nombre es obligatorio")
    private String nombre;

    @NotEmpty(message = "El apellido es obligatorio")
    private String apellido;

    @NotEmpty(message = "La cédula es obligatoria")
    private String cedula;

    @NotEmpty(message = "El teléfono es obligatorio")
    private String telefono;

    @NotNull(message = "La fecha de creación es obligatoria")
    @PastOrPresent(message = "La fecha de creación no puede estar en el futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate Birthdate;

    @Email(message = "El correo electrónico no es válido")
    @NotEmpty(message = "El correo electrónico es obligatorio")
    private String correoElectronico;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(LocalDate Birthdate) {
        this.Birthdate = Birthdate;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}