package br.com.prsribeiro.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prsribeiro.dscommerce.dto.ProductDTO;
import br.com.prsribeiro.dscommerce.entities.Product;
import br.com.prsribeiro.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public ProductDTO findByID(Long id) {

		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);

		return dto;
	}
}
