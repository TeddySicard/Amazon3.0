<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="commerce.catalogue.domaine.modele.Article"%>
<%@ page import="commerce.gestion.Panier"%>
<%@ page import="commerce.gestion.LignePanier"%>
<%@ page import="java.util.Iterator"%>
<%
	Iterator it;
	if (session.getAttribute("panier") == null) {
		response.sendRedirect("./index.jsp");
	} else {
		Panier lePanier = (Panier) session.getAttribute("panier");
		CatalogueManager catalogueManager = (CatalogueManager) application.getAttribute("catalogueManager");
		String requete = request.getParameter("requete");
		String refArticle = request.getParameter("refArticle");
		Article unArticle;
		if ("ajouterLigne".equals(requete)) {
			unArticle = new Article();
			unArticle = catalogueManager.chercherArticleParRef(refArticle);
			lePanier.ajouterLigne(unArticle);
		} else if ("recalculerPanier".equals(requete)) {
			it = lePanier.getLignesPanier().iterator();
			LignePanier uneLignePanier;
			while (it.hasNext()) {
				uneLignePanier = (LignePanier) it.next();
				unArticle = uneLignePanier.getArticle();
				uneLignePanier.setQuantite(
						Integer.parseInt(request.getParameter("cart[" + unArticle.getRefArticle() + "][qty]")));
			}
			lePanier.recalculer();
		} else if ("supprimerLigne".equals(requete)) {
			lePanier.supprimerLigne(refArticle);
		} else if ("viderPanier".equals(requete)) {
			lePanier.viderPanier();
		}

		it = lePanier.getLignesPanier().iterator();
		if ("ajouterLigne".equals(requete)) {
			response.sendRedirect(response.encodeURL("afficheRecherche.jsp"));
		} else {
			if (!it.hasNext()) {
				response.sendRedirect(response.encodeURL("affichePanierVide.jsp"));
			} else {
				response.sendRedirect(response.encodeURL("affichePanier.jsp"));
			}
		}
	}
%>