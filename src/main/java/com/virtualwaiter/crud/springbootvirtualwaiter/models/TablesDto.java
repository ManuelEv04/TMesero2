package com.virtualwaiter.crud.springbootvirtualwaiter.models;

import jakarta.validation.constraints.*;

public class TablesDto {

    @NotNull(message = "El número es obligatorio")
    private Integer number;

    @NotNull(message = "la capacidad es obligatorio")
    private Integer capacity;

    @NotEmpty(message = "El estado es obligatorio")
    private String status;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
