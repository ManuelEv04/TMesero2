package com.virtualwaiter.crud.springbootvirtualwaiter.controller;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virtualwaiter.crud.springbootvirtualwaiter.models.Customer;
import com.virtualwaiter.crud.springbootvirtualwaiter.models.CustomerDto;
import com.virtualwaiter.crud.springbootvirtualwaiter.services.CustomersRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("customers")
public class CustomersController {

    @Autowired
    private CustomersRepo repo;

    @GetMapping({"", "/"})
    public String toList(Model model) {
        List<Customer> customers = repo.findAll();
        model.addAttribute("customers", customers);
        return "customers/index";
    }

    @GetMapping("/create")
    public String createPage(Model model, @RequestParam(value = "success", required = false) String success) {
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute(	"customerDto", customerDto);
        if (success != null) {
            model.addAttribute("success", true);
        }
        return "customers/create";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute CustomerDto customerDto,
            BindingResult result) {
        if(result.hasErrors()) {
            return "customers/create";
        }

        Customer customer = new Customer();
        customer.setNombre(customerDto.getNombre());
        customer.setApellido(customerDto.getApellido());
        customer.setCedula(customerDto.getCedula());
        customer.setTelefono(customerDto.getTelefono());
        customer.setBirthdate(customerDto.getBirthdate());
        customer.setCorreoElectronico(customerDto.getCorreoElectronico());

        repo.save(customer);
        return "redirect:/customers/create?success=true";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable int id, Model model){
        Optional<Customer> customerOpt = repo.findById(id);
        if(customerOpt.isPresent()){
            Customer customer = customerOpt.get();
            CustomerDto customerDto = new CustomerDto();
            customerDto.setNombre(customer.getNombre());
            customerDto.setApellido(customer.getApellido());
            customerDto.setCedula(customer.getCedula());
            customerDto.setTelefono(customer.getTelefono());
            customerDto.setBirthdate(customer.getBirthdate());
            customerDto.setCorreoElectronico(customer.getCorreoElectronico());
            model.addAttribute("customerId", id);
            model.addAttribute("customerDto", customerDto);
            return "customers/edit";
        } else {
            throw new NoSuchElementException("Customer not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String editCustomer(
            @PathVariable int id,
            @Valid @ModelAttribute CustomerDto customerDto,
            BindingResult result,
            Model model) {

        if(result.hasErrors()) {
            model.addAttribute("customerId", id);
            return "customers/edit";
        }

        Optional<Customer> customerOpt = repo.findById(id);
        if(customerOpt.isPresent()){
            Customer customer = customerOpt.get();
            customer.setNombre(customerDto.getNombre());
            customer.setApellido(customerDto.getApellido());
            customer.setCedula(customerDto.getCedula());
            customer.setTelefono(customerDto.getTelefono());
            customer.setBirthdate(customerDto.getBirthdate());
            customer.setCorreoElectronico(customerDto.getCorreoElectronico());
            repo.save(customer);
        } else {
            throw new NoSuchElementException("Product not found");
        }
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new NoSuchElementException("Customer not found");
        }
        return "redirect:/customers";
    }
}