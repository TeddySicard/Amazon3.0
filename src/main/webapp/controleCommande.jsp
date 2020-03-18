<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="commerce.catalogue.domaine.modele.Article"%>
<%@ page import="commerce.gestion.Panier"%>
<%@ page import="commerce.gestion.LignePanier"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="commerce.gestion.Commande"%>
<%@ page import="commerce.gestion.ListeCommande"%>
<%
	if (session.getAttribute("panier") == null || session.getAttribute("commandes") == null) {
		response.sendRedirect("./index.jsp");
	} else {
		Panier lePanier = (Panier) session.getAttribute("panier");
		ListeCommande commandes = (ListeCommande) session.getAttribute("commandes");
		CatalogueManager catalogueManager = (CatalogueManager) application.getAttribute("catalogueManager");
		String requete = request.getParameter("requete");
		String dateCommande = request.getParameter("dateCommande");
		String hourCommande = request.getParameter("hourCommande");
		if ("effectuerCommande".equals(requete)) {
			commandes.ajouterCommande(new Commande(lePanier));
			lePanier.viderPanier();
		}
		if ("infoCommande".equals(requete)) {
			response.sendRedirect(response
					.encodeURL("./afficheInfoHistorique.jsp?dateHourCommande=" + dateCommande + hourCommande));

		} else if (commandes.getCommandes().isEmpty()) {
			response.sendRedirect(response.encodeURL("afficheHistoriqueVide.jsp"));
		} else {
			response.sendRedirect(response.encodeURL("afficheHistorique.jsp"));
		}

	}
%>