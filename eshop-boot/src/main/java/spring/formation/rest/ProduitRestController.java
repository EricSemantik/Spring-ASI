package spring.formation.rest;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import spring.formation.model.Produit;
import spring.formation.model.Views;
import spring.formation.repo.IProduitRepository;
import spring.formation.rest.exception.ProduitValidationException;

@RestController
@RequestMapping("/api/produit")
@CrossOrigin("*")
public class ProduitRestController {
	@Autowired
	private IProduitRepository produitRepo;

	@GetMapping("")
	@JsonView(Views.ViewProduit.class)
	public List<Produit> findAll() {
		List<Produit> produits = produitRepo.findAll();

		return produits;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewProduitDetail.class)
	public Produit findById(@PathVariable Long id) {
		Optional<Produit> optProduit = produitRepo.findById(id);

		if (optProduit.isPresent()) {
			return optProduit.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
	}

	@PostMapping("")
	@JsonView(Views.ViewProduit.class)
	@PreAuthorize("hasRole('FOURNISSEUR')")
	public Produit create(@RequestBody @Valid Produit produit, BindingResult result) {
		if (result.hasErrors()) {
			throw new ProduitValidationException();
		}

		produit = produitRepo.save(produit);

		return produit;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewProduit.class)
	public Produit update(@PathVariable Long id, @RequestBody Produit produit) {
		if (!produitRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
		}

		produit = produitRepo.save(produit);

		return produit;
	}

	@PatchMapping("/{id}")
	@JsonView(Views.ViewProduit.class)
	public Produit partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		Optional<Produit> optProduit = produitRepo.findById(id);

		if (optProduit.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
		}

		Produit produit = optProduit.get();

		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Produit.class, key);
			ReflectionUtils.makeAccessible(field);

			if (field.getType().equals(BigDecimal.class)) {
				value = new BigDecimal((Double) value);
			} else if (field.getType().equals(Long.class)) {
				value = ((Integer) value).longValue();
			}

			ReflectionUtils.setField(field, produit, value);
		});

		produitRepo.save(produit);

		return produit;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!produitRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
		}

		produitRepo.deleteById(id);
	}
}
