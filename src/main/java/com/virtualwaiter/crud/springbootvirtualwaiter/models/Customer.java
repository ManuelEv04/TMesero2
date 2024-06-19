package com.virtualwaiter.crud.springbootvirtualwaiter.models;

import jakarta.persistence.Column;
import jakarta.persistence.*;

import java.time.LocalDate;


    @Entity
    @Table(name="customers")
    public class Customer {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private int id;

        private String nombre;
        private String apellido;
        private String cedula;
        private String telefono;
        @Temporal(TemporalType.DATE)
        private LocalDate Birthdate;
        private String correoElectronico;

        @Column(columnDefinition = "Text")
        private String description;

        // Getters and Setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

