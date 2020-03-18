/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Mans
 * @author  A. Corbière
 */

package commerce.gestion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ListeCommande {
	private NavigableMap<Date, Commande> commandes;

	public ListeCommande() {
		commandes = new TreeMap<Date, Commande>();
	}

	public NavigableMap<Date, Commande> getCommandes() {
		return commandes;
	}

	public void ajouterCommande(Commande commande) {
		Commande res = new Commande(commande);
		commandes.put(res.getDate(), res);

	}

	public List<Commande> getIterableCommandes() {
		List<Commande> res = new ArrayList<>();
		for (Map.Entry<Date, Commande> entry : commandes.entrySet()) {
			res.add(entry.getValue());
		}
		Collections.reverse(res);
		return res;
	}

	public Commande getCommande(String dateHour) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM. yyyyHH:mm:ss");
		Date recherche = sdf.parse(dateHour);
		return commandes.get(commandes.tailMap(recherche).firstKey());
	}

	public int getNbCommandes() {
		return commandes.size();
	}

}
