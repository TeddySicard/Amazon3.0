<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.gestion.Panier" %>
<%@ page import="commerce.gestion.ListeCommande" %>
<%@ page import="commerce.catalogue.service.CatalogueManager" %>
<%
  if (session.getAttribute("panier")==null) {
    Panier lePanier = new Panier() ;
    session.setAttribute("panier", lePanier) ;
  }
if (session.getAttribute("commandes")==null) {
    ListeCommande commandes = new ListeCommande() ;
    session.setAttribute("commandes", commandes) ;
  }
  response.sendRedirect(response.encodeURL("afficheRecherche.jsp")) ;
%>