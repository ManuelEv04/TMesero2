package com.virtualwaiter.crud.springbootvirtualwaiter.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.virtualwaiter.crud.springbootvirtualwaiter.models.Customer;

public interface CustomersRepo extends JpaRepository<Customer, Integer> {
}