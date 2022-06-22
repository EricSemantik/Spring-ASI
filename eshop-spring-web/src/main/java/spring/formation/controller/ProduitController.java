package spring.formation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.formation.repo.IProduitRepository;

@Controller
@RequestMapping("/produit")
public class ProduitController {
	
	@Autowired
	private IProduitRepository produitRepo;

	@GetMapping({"", "/list"})
	public String list(Model model) {
		model.addAttribute("mesProduits", produitRepo.findAll());

		return "produit/list";
	}
	
	@GetMapping("/add")
	public String add() {
		// envoyer un produit vite

		return "produit/form";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam Long id) {
		// envoyer le produit en recherchant par id
		
		return "produit/form";
	}
	
	@PostMapping("/save")
	public String save() {
		// récupérer toutes les données du formulaire et mettre à jour ou créer le produit
		
		return "redirect:/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		// supprimer le produit par l'id

		return "forward:list";
	}
	
	@GetMapping("/cancel")
	public String cancel() {

		return "forward:list";
	}
}
