package spring.formation.controller;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.model.Commentaire;
import spring.formation.model.Produit;
import spring.formation.repo.ICommentaireRepository;
import spring.formation.repo.IProduitRepository;

@Controller
@RequestMapping("/commentaire")
public class CommentaireController {

	@Autowired
	private ICommentaireRepository commentaireRepo;
	@Autowired
	private IProduitRepository produitRepo;

	@GetMapping({ "", "/list" })
	public String list(Model model) {
		List<Commentaire> commentaires = commentaireRepo.findAll();

		model.addAttribute("commentaires", commentaires);

		return "commentaire/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("commentaire", new Commentaire());
		model.addAttribute("produits", produitRepo.findAll());

		return "commentaire/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		Optional<Commentaire> optCommentaire = commentaireRepo.findById(id);

		if (optCommentaire.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentaire non trouvé");
		}

		model.addAttribute("commentaire", optCommentaire.get());
		model.addAttribute("produits", produitRepo.findAll());

		return "commentaire/form";
	}

	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date date, @RequestParam int note,
			@RequestParam("commentaire") String comment, @RequestParam(required = false) Long idProduit) {

		Commentaire commentaire = null;

		if (id == null) {
			commentaire = new Commentaire();
		} else {
			commentaire = commentaireRepo.findById(id).get();
		}

		commentaire.setDate(date);
		commentaire.setNote(note);
		commentaire.setCommentaire(comment);

		if (idProduit != null) {
			Produit produit = new Produit();
			produit.setId(idProduit);

			commentaire.setProduit(produit);
		} else {
			commentaire.setProduit(null);
		}

		commentaireRepo.save(commentaire);

		return "redirect:list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/commentaire/list";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		if (!commentaireRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentaire non trouvé");
		}

		commentaireRepo.deleteById(id);

		return "redirect:../list";
	}
}
