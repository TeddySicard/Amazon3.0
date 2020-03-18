package commerce.gestion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Commande {

	private Panier panier;
	private Date date;

	public Commande(Panier panier) {
		this.panier = new Panier(panier);
		date = new Date();
	}

	public Commande(Commande commande) {
		this.panier = new Panier(commande.panier);
		date = (Date) commande.date.clone();
	}

	public String toStringDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM. yyyy");
		String displayedDate = sdf.format(date);
		return displayedDate;
	}

	public String toStringHour() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String displayedHour = sdf.format(date);
		return displayedHour;
	}

	public Date getDate() {
		return date;
	}

	public Panier getPanier() {
		return panier;
	}

}
