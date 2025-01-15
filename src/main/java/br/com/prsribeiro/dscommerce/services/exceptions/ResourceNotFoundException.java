package br.com.prsribeiro.dscommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}
}
