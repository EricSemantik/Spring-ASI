package spring.formation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.formation.model.Produit;

@Controller
@RequestMapping("/produit")
public class ProduitController {

	@GetMapping("")
	public String list(Model model) {
		List<Produit> liste = new ArrayList<>();
		liste.add(new Produit(5L,"IPhone 13", 1250.0, 1450.0, "I13XS", "IPhone 13 XS +", 50));
		
		model.addAttribute("mesProduits", liste);

		return "produit/list";
	}
}
