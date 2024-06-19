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

import com.virtualwaiter.crud.springbootvirtualwaiter.models.Product;
import com.virtualwaiter.crud.springbootvirtualwaiter.models.ProductDto;
import com.virtualwaiter.crud.springbootvirtualwaiter.services.ProductsRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("products")

public class ProductsController {
	
	@Autowired
	private ProductsRepo repo;
	
	@GetMapping({"","/"})
	public String toList(Model model) {
		
		List<Product> products = repo.findAll();
		model.addAttribute("products", products);
		return "products/index";
	}
	
	@GetMapping("/create")
	public String createPage(Model model, @RequestParam(value = "success", required = false) String success) {
		ProductDto productDto = new ProductDto();
		model.addAttribute(	"productDto", productDto);
		if (success != null) {
			model.addAttribute("success", true);
		}
		return "products/create";
	}

	@PostMapping("/create")
	public String createProduct(
			@Valid @ModelAttribute ProductDto productDto,
			BindingResult result) {
		if(result.hasErrors()) {
			return "products/create";
		}
		
		Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        
        repo.save(product);
		return "redirect:/products/create?success=true";
	}
	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable("id") int id, Model model) {
		Optional<Product> productOpt = repo.findById(id);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			ProductDto productDto = new ProductDto();
			productDto.setName(product.getName());
			productDto.setDescription(product.getDescription());
			productDto.setPrice(product.getPrice());
			model.addAttribute("productDto", productDto);
			model.addAttribute("productId", id);
			return "products/edit";
		} else {
			throw new NoSuchElementException("Product not found");
		}

	}
	@PostMapping("/edit/{id}")
	public String editProduct(@PathVariable("id") int id, @Valid @ModelAttribute ProductDto productDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("productId", id);
			return "products/edit";
		}

		Optional<Product> productOpt = repo.findById(id);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setPrice(productDto.getPrice());
			repo.save(product);
		}else {
			throw new NoSuchElementException("Product not found");
		}
		return "redirect:/products";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
		} else {
			throw new NoSuchElementException("Product not found");
		}
		return "redirect:/products";
	}
}
