package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private int nbEtals = 0;
	private Marche[] etals;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		etals = new Marche[nbEtalsMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur + " cherche un endroit pour vendre " + nbProduit + produit + " .");
		etals[nbEtals].utiliserEtal(nbEtals,vendeur,produit,nbProduit);
		chaine.append("Le vendeur " + vendeur + " vend des " + produit + " à l'étal n° " + etals[nbEtals]);
		nbEtals++;
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Gaulois vendeur = new Gaulois(this.getNom(), this.);
		for (int i = 0; i < Marche.etals.length; i++) {
			if (etals[i].trouverEtals(produit) != null) {
				chaine.append("Seul le vendeur " + etals[i].trouverVendeur(vendeur) + " propose des " + produit + " au marché.");
				return chaine.toString();
			}
		}
		return "Il n'y a pas de vendeur qui propose des " + produit + " au marché.";
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		for (int i = 0; i < Marche.etals.length; i++) {
			if (etals[i].trouverVendeur(vendeur) != null) {
				etals[i] = null;
			}
			chaine.append("Le vendeur " + vendeur + " quitte son étal, il a vendu 20 fleurs parmi les 20 qu'il voulait vendre.");
			return chaine.toString();
		}
	}
	
	public class Marche {
		private static Etal[] etals;
		
		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			System.out.println("Le vendeur " + vendeur + " s'installe à l'étal " + etals[indiceEtal] + " pour vendre " + nbProduit + produit);
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		
		int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i] == null) {
					//System.out.println("L'étal à la position " + i + " est libre.");
					return i;
				}
			}
		    return -1;
		}
		
		Etal[] trouverEtals(String produit) {
			Etal[] etalsProduit = null;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					//System.out.println("Le produit " + produit + " se trouve à l'étal " + etals[i]);
					etalsProduit[i] = etals[i];
					return etalsProduit;
				}
			}
			return null;
			
		}
		
		Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					//System.out.println("Le vendeur " + gaulois + " se trouve à l'étal " + etals[i]);
					return etals[i];
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			int nbEtalVide = 0;
			StringBuilder chaine = new StringBuilder();
			for (int i = 0; i < etals.length; i++) {
				if (etals[i] != null) {
					etals[i].afficherEtal();
				}
				nbEtalVide++;
			}
			chaine.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
			return chaine.toString();
		}
	}
}
