package br.com.prsribeiro.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prsribeiro.dscommerce.dto.ProductDTO;
import br.com.prsribeiro.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		return service.findByID(id);

	}

}
