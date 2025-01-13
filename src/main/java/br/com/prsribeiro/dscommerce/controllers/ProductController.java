package br.com.prsribeiro.dscommerce.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prsribeiro.dscommerce.entities.Product;
import br.com.prsribeiro.dscommerce.repositories.ProductRepository;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductRepository repository;

	@GetMapping
	public String getProduct(Long id) {
		Optional<Product> result = repository.findById(id); // L = long
		Product product = result.get();
		return product.getName();
	}

}
