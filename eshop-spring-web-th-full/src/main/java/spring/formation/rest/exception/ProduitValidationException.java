package spring.formation.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Le produit n'a pas pu être validé")
public class ProduitValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
