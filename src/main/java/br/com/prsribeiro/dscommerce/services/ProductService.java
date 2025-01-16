package br.com.prsribeiro.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.prsribeiro.dscommerce.dto.ProductDTO;
import br.com.prsribeiro.dscommerce.entities.Product;
import br.com.prsribeiro.dscommerce.repositories.ProductRepository;
import br.com.prsribeiro.dscommerce.services.exceptions.DatabaseException;
import br.com.prsribeiro.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public ProductDTO findByID(Long id) {

		Product product = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não localizado!"));
		ProductDTO dto = new ProductDTO(product);

		return dto;

	}

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {

		Product entity = new Product();

		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);

		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {

		try {
			Product entity = repository.getReferenceById(id);

			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);

			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {

			throw new ResourceNotFoundException("Produto não localizado!");
		}

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long id) {

		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Produto não localizado!");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de Integridade Referencial!");
		}

	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {

		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
	}
}
