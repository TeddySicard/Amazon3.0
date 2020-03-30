/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * @author  A. Corbière
 */
package commerce.catalogue.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import com.zeloon.deezer.client.DeezerClient;
import com.zeloon.deezer.domain.Albums;
import com.zeloon.deezer.domain.Artists;
import com.zeloon.deezer.domain.Tracks;
import com.zeloon.deezer.domain.internal.AlbumId;
import com.zeloon.deezer.domain.internal.ArtistId;
import com.zeloon.deezer.domain.internal.search.SearchArtist;
import amazon.apaIO.ApaiIO;
import amazon.apaIO.configuration.GenericConfiguration;
import amazon.apaIO.operations.Search;
import commerce.catalogue.domaine.modele.DVD;
import commerce.catalogue.domaine.modele.Livre;
import commerce.catalogue.domaine.modele.Musique;
import commerce.catalogue.domaine.modele.Piste;
import commerce.catalogue.domaine.modele.VideoGame;

public class InitAmazon {
	private static final int NB_MAX_ARTICLE = 6;
	private CatalogueManager catalogueManager ;

	public InitAmazon(CatalogueManager catalogueManager) {
		this.catalogueManager = catalogueManager ;
	}
	
	public void init(int type,String words) {
		catalogueManager.clear();
		switch (type) {
		case 1:
			this.initLivre(words);
			this.initMusique(words);
			this.initPiste(words);
			this.initJeuxVideo(words);
			this.initDVD(words);
			break;
		case 2:
			this.initLivre(words);
			break;
		case 3:
			this.initMusique(words);
			break;
		case 4: 
			this.initPiste(words);
			break;
		case 5: 
			this.initJeuxVideo(words);
			break;
		case 6: 
			this.initDVD(words);
			break;
	}
	}

	public void init() {
		this.init(1,"Ibrahim Maalouf");
	}
	
	private void initLivre(String words) {
		String ENDPOINT = "odp.tuxfamily.org";
		String AWS_ACCESS_KEY_ID = "YOUR_ACCESS_KEY_ID_HERE";
		String AWS_SECRET_KEY = "YOUR_SECRET_KEY_HERE";

		GenericConfiguration conf = new GenericConfiguration();
		conf.setAccessKey(AWS_ACCESS_KEY_ID) ;
		conf.setSecretKey(AWS_SECRET_KEY);
		conf.setEndPoint(ENDPOINT);


		ApaiIO apaiIO = new ApaiIO();
		apaiIO.setConfiguration(conf) ;
		Search search = new Search();
		search.setCategory("Books");
		search.setResponseGroup("Offers,ItemAttributes,Images") ;
		String keywords = words;
		search.setKeywords(keywords);
		
		Livre livre ;
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);
		Document document ;
		Element racine = null ;
		Namespace espaceNom = null ;

		try {
			document = builder.build(new StringReader(apaiIO.runOperation(search)));
			racine = document.getRootElement() ;
			espaceNom = Namespace.getNamespace(racine.getNamespaceURI());

			try {
				FileWriter writer = new FileWriter("amazonResponse.xml");
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				outputter.output(racine, writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (espaceNom != null && !racine.getName().equals("ItemSearchErrorResponse")) {
				Element items = racine.getChild("Items",espaceNom) ;
				Iterator<Element> itemIterator = items.getChildren("Item",espaceNom).iterator() ;
				Element item ;
				Element itemAttributes ;
				Element image ;
				int i = 0 ;
				while (itemIterator.hasNext() && i != NB_MAX_ARTICLE) {
					item = itemIterator.next() ;
					itemAttributes = item.getChild("ItemAttributes",espaceNom);
					image = item.getChild("LargeImage",espaceNom);
					livre = new Livre();
					try 
					{
						if (itemAttributes.getChild("ProductGroup",espaceNom).getText().equals("Book")) {
							livre.setRefArticle(item.getChild("ASIN",espaceNom).getText());
							livre.setTitre(itemAttributes.getChild("Title",espaceNom).getText());
							livre.setImage(image.getChild("URL",espaceNom).getText());
							livre.setPrix(Integer.parseInt(item.getChild("OfferSummary",espaceNom).getChild("LowestNewPrice",espaceNom).getChild("Amount",espaceNom).getText())/100.0);
							livre.setDisponibilite(1);
							livre.setAuteur(itemAttributes.getChild("Author",espaceNom).getText());
							livre.setISBN(itemAttributes.getChild("ISBN",espaceNom).getText());
							livre.setNbPages(Integer.parseInt(itemAttributes.getChild("NumberOfPages",espaceNom).getText()));
							catalogueManager.soumettreArticle(livre) ;
							i ++ ;
						}
					}
					catch (NullPointerException e) {
						e.printStackTrace() ;
					}
					catch (Exception e) {
						e.printStackTrace() ;
					}
				}
			}
			else {
				try { 
					livre = new Livre();
					livre.setRefArticle("1141555677821");
					livre.setTitre("Le seigneur des anneaux");
					livre.setAuteur("J.R.R. TOLKIEN");
					livre.setISBN("2070612880");
					livre.setImage("61PEbZ1QDfL-300x300.jpg");
					livre.setNbPages(736);
					livre.setPrix("8.50");
					livre.setDisponibilite("1");
					catalogueManager.soumettreArticle(livre);
					livre = new Livre();
					livre.setRefArticle("1141555897821");
					livre.setTitre("Un paradis trompeur");
					livre.setAuteur("Henning Mankell");
					livre.setISBN("275784797X");
					livre.setImage("61NfUluHsML-300x300.jpg");
					livre.setNbPages(400);
					livre.setPrix("7.90");
					livre.setDisponibilite("1");
					catalogueManager.soumettreArticle(livre);
					livre = new Livre();
					livre.setRefArticle("1141556299459");
					livre.setTitre("Dôme tome 1");
					livre.setAuteur("Stephen King");
					livre.setISBN("2212110685");
					livre.setImage("61sGE8edJmL-300x300.jpg");
					livre.setNbPages(840);
					livre.setPrix("8.90");
					livre.setDisponibilite("1");
					catalogueManager.soumettreArticle(livre);
				}
				catch (Exception e) {
					e.printStackTrace() ;
				}
			}
		}
		catch (JDOMException e) {
			e.printStackTrace() ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}
	
	private void initJeuxVideo(String words) {
		String ENDPOINT = "odp.tuxfamily.org";
		String AWS_ACCESS_KEY_ID = "YOUR_ACCESS_KEY_ID_HERE";
		String AWS_SECRET_KEY = "YOUR_SECRET_KEY_HERE";

		GenericConfiguration conf = new GenericConfiguration();
		conf.setAccessKey(AWS_ACCESS_KEY_ID) ;
		conf.setSecretKey(AWS_SECRET_KEY);
		conf.setEndPoint(ENDPOINT);


		ApaiIO apaiIO = new ApaiIO();
		apaiIO.setConfiguration(conf) ;
		Search search = new Search();
		search.setCategory("VideoGames");
		search.setResponseGroup("Offers,ItemAttributes,Images") ;
		String keywords = words;
		search.setKeywords(keywords);
		
		VideoGame jeux;
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);
		Document document ;
		Element racine = null ;
		Namespace espaceNom = null ;

		try {
			document = builder.build(new StringReader(apaiIO.runOperation(search)));
			racine = document.getRootElement() ;
			espaceNom = Namespace.getNamespace(racine.getNamespaceURI());

			try {
				FileWriter writer = new FileWriter("amazonResponse.xml");
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				outputter.output(racine, writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (espaceNom != null && !racine.getName().equals("ItemSearchErrorResponse")) {
				Element items = racine.getChild("Items",espaceNom) ;
				Iterator<Element> itemIterator = items.getChildren("Item",espaceNom).iterator() ;
				Element item ;
				Element itemAttributes ;
				Element image ;
				int i = 0 ;
				while (itemIterator.hasNext() && i != NB_MAX_ARTICLE) {
					item = itemIterator.next() ;
					itemAttributes = item.getChild("ItemAttributes",espaceNom);
					image = item.getChild("LargeImage",espaceNom);
					jeux = new VideoGame();
					try 
					{
						if (itemAttributes.getChild("ProductGroup",espaceNom).getText().equals("VideoGames")) {
							jeux.setRefArticle(item.getChild("ASIN",espaceNom).getText());
							jeux.setTitre(itemAttributes.getChild("Title",espaceNom).getText());
							jeux.setImage(image.getChild("URL",espaceNom).getText());
							jeux.setPrix(Integer.parseInt(item.getChild("OfferSummary",espaceNom).getChild("LowestNewPrice",espaceNom).getChild("Amount",espaceNom).getText())/100.0);
							jeux.setDisponibilite(1);
							catalogueManager.soumettreArticle(jeux) ;
							i ++ ;
						}
					}
					catch (NullPointerException e) {
						e.printStackTrace() ;
					}
					catch (Exception e) {
						e.printStackTrace() ;
					}
				}
			}
		}
		catch (JDOMException e) {
			e.printStackTrace() ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}
	
	private void initDVD(String words) {
		String ENDPOINT = "odp.tuxfamily.org";
		String AWS_ACCESS_KEY_ID = "YOUR_ACCESS_KEY_ID_HERE";
		String AWS_SECRET_KEY = "YOUR_SECRET_KEY_HERE";

		GenericConfiguration conf = new GenericConfiguration();
		conf.setAccessKey(AWS_ACCESS_KEY_ID) ;
		conf.setSecretKey(AWS_SECRET_KEY);
		conf.setEndPoint(ENDPOINT);


		ApaiIO apaiIO = new ApaiIO();
		apaiIO.setConfiguration(conf) ;
		Search search = new Search();
		search.setCategory("DVD");
		search.setResponseGroup("Offers,ItemAttributes,Images") ;
		String keywords = words;
		search.setKeywords(keywords);
		
		DVD dvd;
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);
		Document document ;
		Element racine = null ;
		Namespace espaceNom = null ;

		try {
			document = builder.build(new StringReader(apaiIO.runOperation(search)));
			racine = document.getRootElement() ;
			espaceNom = Namespace.getNamespace(racine.getNamespaceURI());

			try {
				FileWriter writer = new FileWriter("amazonResponse.xml");
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				outputter.output(racine, writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (espaceNom != null && !racine.getName().equals("ItemSearchErrorResponse")) {
				Element items = racine.getChild("Items",espaceNom) ;
				Iterator<Element> itemIterator = items.getChildren("Item",espaceNom).iterator() ;
				Element item ;
				Element itemAttributes ;
				Element image ;
				int i = 0 ;
				while (itemIterator.hasNext() && i != NB_MAX_ARTICLE) {
					item = itemIterator.next() ;
					itemAttributes = item.getChild("ItemAttributes",espaceNom);
					image = item.getChild("LargeImage",espaceNom);
					dvd = new DVD();
					try 
					{
						if (itemAttributes.getChild("ProductGroup",espaceNom).getText().equals("DVD")) {
							dvd.setRefArticle(item.getChild("ASIN",espaceNom).getText());
							dvd.setTitre(itemAttributes.getChild("Title",espaceNom).getText());
							dvd.setImage(image.getChild("URL",espaceNom).getText());
							dvd.setPrix(Integer.parseInt(item.getChild("OfferSummary",espaceNom).getChild("LowestNewPrice",espaceNom).getChild("Amount",espaceNom).getText())/100.0);
							dvd.setDisponibilite(1);
							catalogueManager.soumettreArticle(dvd) ;
							i ++ ;
						}
					}
					catch (NullPointerException e) {
						e.printStackTrace() ;
					}
					catch (Exception e) {
						e.printStackTrace() ;
					}
				}
			}
		}
		catch (JDOMException e) {
			e.printStackTrace() ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}
	
	private void initPiste(String words) {
		String ENDPOINT = "odp.tuxfamily.org";
		String AWS_ACCESS_KEY_ID = "YOUR_ACCESS_KEY_ID_HERE";
		String AWS_SECRET_KEY = "YOUR_SECRET_KEY_HERE";

		GenericConfiguration conf = new GenericConfiguration();
		conf.setAccessKey(AWS_ACCESS_KEY_ID) ;
		conf.setSecretKey(AWS_SECRET_KEY);
		conf.setEndPoint(ENDPOINT);


		ApaiIO apaiIO = new ApaiIO();
		apaiIO.setConfiguration(conf) ;
		Search search = new Search();
		search.setCategory("Music");
		search.setResponseGroup("Offers,ItemAttributes,Images") ;
		String keywords = words;
		search.setKeywords(keywords);
		
		Musique musique;
		Piste piste;
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);
		Document document ;
		Element racine = null ;
		Namespace espaceNom = null ;

		try {
			document = builder.build(new StringReader(apaiIO.runOperation(search)));
			racine = document.getRootElement() ;
			espaceNom = Namespace.getNamespace(racine.getNamespaceURI());

			try {
				FileWriter writer = new FileWriter("amazonResponse.xml");
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				outputter.output(racine, writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (espaceNom != null && !racine.getName().equals("ItemSearchErrorResponse")) {
				Element items = racine.getChild("Items", espaceNom);
				Iterator<Element> itemIterator = items.getChildren("Item", espaceNom).iterator();
				Element item;
				Element itemAttributes;
				Element image;
				int i = 0;
				while (itemIterator.hasNext() && i != NB_MAX_ARTICLE) {
					item = itemIterator.next();
					itemAttributes = item.getChild("ItemAttributes", espaceNom);
					image = item.getChild("LargeImage", espaceNom);
					musique = new Musique();
					try {
						if (itemAttributes.getChild("ProductGroup", espaceNom).getText().equals("Music")) { // faire un if else pour les autres categorie
							musique.setRefArticle(item.getChild("ASIN", espaceNom).getText());
							musique.setTitre(itemAttributes.getChild("Title", espaceNom).getText());
							musique.setEAN(itemAttributes.getChild("EAN", espaceNom).getText());
							musique.setImage(image.getChild("URL", espaceNom).getText());
							musique.setPrix(Integer.parseInt(item.getChild("OfferSummary", espaceNom)
									.getChild("LowestNewPrice", espaceNom).getChild("Amount", espaceNom).getText())
									/ 100.0);
							musique.setDisponibilite(1);

							DeezerClient deezerClient = new DeezerClient();
							Artists artists = deezerClient.search(new SearchArtist(keywords));
							Albums albums = deezerClient.getAlbums(new ArtistId(artists.getData().get(0).getId()));
							int j = 0;
							Boolean sortir = (j == albums.getData().size());
							Boolean albumTrouve = false;
							while (!sortir) {
								String titreDeezer = albums.getData().get(j).getTitle().toLowerCase().replaceAll(" ",
										"");
								String titreAmazon = musique.getTitre().toLowerCase().replaceAll(" ", "");
								titreDeezer.replaceAll("-", "");
								titreAmazon.replaceAll("-", "");
								albumTrouve = titreDeezer.equals(titreAmazon);
								if (titreAmazon.length() > titreDeezer.length())
									albumTrouve = albumTrouve || (titreAmazon.indexOf(titreDeezer) >= 0);
								if (titreDeezer.length() > titreAmazon.length())
									albumTrouve = albumTrouve || (titreDeezer.indexOf(titreAmazon) >= 0);

								j++;
								sortir = albumTrouve || (j == albums.getData().size());
							}
							if (albumTrouve) {
								Tracks tracks = deezerClient
										.getTracks(new AlbumId(albums.getData().get(j - 1).getId()));
								j = 0;
								List<Piste> listePistes = new ArrayList<Piste>();
								while (j < tracks.getData().size()) {
									piste = new Piste();
									piste.setTitre(tracks.getData().get(j).getTitle());
									piste.setUrl(tracks.getData().get(j).getPreview());
									catalogueManager.soumettrePiste(piste);
									listePistes.add(piste);
									j++;
								}
								if (tracks.getData().size() != 0)
									musique.setPistes(listePistes);
								catalogueManager.soumettreArticle(musique);
							}							
							i++;
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		catch (JDOMException e) {
			e.printStackTrace() ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}
	
	private void initMusique(String words) {
		String ENDPOINT = "odp.tuxfamily.org";
		String AWS_ACCESS_KEY_ID = "YOUR_ACCESS_KEY_ID_HERE";
		String AWS_SECRET_KEY = "YOUR_SECRET_KEY_HERE";

		GenericConfiguration conf = new GenericConfiguration();
		conf.setAccessKey(AWS_ACCESS_KEY_ID) ;
		conf.setSecretKey(AWS_SECRET_KEY);
		conf.setEndPoint(ENDPOINT);


		ApaiIO apaiIO = new ApaiIO();
		apaiIO.setConfiguration(conf) ;
		Search search = new Search();
		search.setCategory("Music");
		search.setResponseGroup("Offers,ItemAttributes,Images") ;
		String keywords = words;
		search.setKeywords(keywords);
		
		Musique musique;
		Piste piste;
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);
		Document document ;
		Element racine = null ;
		Namespace espaceNom = null ;

		try {
			document = builder.build(new StringReader(apaiIO.runOperation(search)));
			racine = document.getRootElement() ;
			espaceNom = Namespace.getNamespace(racine.getNamespaceURI());

			try {
				FileWriter writer = new FileWriter("amazonResponse.xml");
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				outputter.output(racine, writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (espaceNom != null && !racine.getName().equals("ItemSearchErrorResponse")) {
				Element items = racine.getChild("Items", espaceNom);
				Iterator<Element> itemIterator = items.getChildren("Item", espaceNom).iterator();
				Element item;
				Element itemAttributes;
				Element image;
				int i = 0;
				while (itemIterator.hasNext() && i != NB_MAX_ARTICLE) {
					item = itemIterator.next();
					itemAttributes = item.getChild("ItemAttributes", espaceNom);
					image = item.getChild("LargeImage", espaceNom);
					musique = new Musique();
					try {
						if (itemAttributes.getChild("ProductGroup", espaceNom).getText().equals("Music")) { // faire un if else pour les autres categorie
							musique.setRefArticle(item.getChild("ASIN", espaceNom).getText());
							musique.setTitre(itemAttributes.getChild("Title", espaceNom).getText());
							musique.setEAN(itemAttributes.getChild("EAN", espaceNom).getText());
							musique.setImage(image.getChild("URL", espaceNom).getText());
							musique.setPrix(Integer.parseInt(item.getChild("OfferSummary", espaceNom)
									.getChild("LowestNewPrice", espaceNom).getChild("Amount", espaceNom).getText())
									/ 100.0);
							musique.setDisponibilite(1);

							DeezerClient deezerClient = new DeezerClient();
							Artists artists = deezerClient.search(new SearchArtist(keywords));
							Albums albums = deezerClient.getAlbums(new ArtistId(artists.getData().get(0).getId()));
							int j = 0;
							Boolean sortir = (j == albums.getData().size());
							Boolean albumTrouve = false;
							while (!sortir) {
								String titreDeezer = albums.getData().get(j).getTitle().toLowerCase().replaceAll(" ",
										"");
								String titreAmazon = musique.getTitre().toLowerCase().replaceAll(" ", "");
								titreDeezer.replaceAll("-", "");
								titreAmazon.replaceAll("-", "");
								albumTrouve = titreDeezer.equals(titreAmazon);
								if (titreAmazon.length() > titreDeezer.length())
									albumTrouve = albumTrouve || (titreAmazon.indexOf(titreDeezer) >= 0);
								if (titreDeezer.length() > titreAmazon.length())
									albumTrouve = albumTrouve || (titreDeezer.indexOf(titreAmazon) >= 0);

								j++;
								sortir = albumTrouve || (j == albums.getData().size());
							}
							if (!albumTrouve) {
								catalogueManager.soumettreArticle(musique);
							}							
							i++;
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		catch (JDOMException e) {
			e.printStackTrace() ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}
}
