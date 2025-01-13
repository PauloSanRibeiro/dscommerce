package br.com.prsribeiro.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prsribeiro.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
}
