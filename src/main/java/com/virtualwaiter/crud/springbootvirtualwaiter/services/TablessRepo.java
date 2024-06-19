package com.virtualwaiter.crud.springbootvirtualwaiter.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtualwaiter.crud.springbootvirtualwaiter.models.Tables;

public interface TablessRepo extends JpaRepository<Tables, Integer> {

}