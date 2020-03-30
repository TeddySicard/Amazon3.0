<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="commerce.catalogue.domaine.modele.Article"%>
<%@ page import="commerce.gestion.Panier"%>
<%@ page import="commerce.gestion.LignePanier"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="commerce.catalogue.service.InitAmazon" %>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>

<%
	CatalogueManager catalogueManager = (CatalogueManager) application.getAttribute("catalogueManager");
	String commande = request.getParameter("commande");
	int choice = Integer.parseInt(request.getParameter("allChoice"));
	String words = request.getParameter("choixArticle");
	Article unArticle;
	int indice;
	if (commande != null) {
		if (commande.equals("choixType")) {
			InitAmazon a = new InitAmazon(catalogueManager);
			a.init(choice,words);
		}
	}
	response.sendRedirect(response.encodeURL("afficheRecherche.jsp"));
%>