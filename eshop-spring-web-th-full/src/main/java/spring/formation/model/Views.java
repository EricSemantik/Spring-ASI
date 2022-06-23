package spring.formation.model;

public class Views {
	public static class ViewCommon {}
	
	public static class ViewProduit extends ViewCommon {}

	public static class ViewProduitDetail extends ViewProduit {}
	
	public static class ViewFournisseur extends ViewCommon {}
	
	public static class ViewFournisseurDetail extends ViewCommon {}
}

