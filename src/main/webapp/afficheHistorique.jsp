<%@ page pageEncoding="UTF-8"%>
<%@ include file="enTetePage.html"%>
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
		ListeCommande commandes = (ListeCommande) session.getAttribute("commandes");
		CatalogueManager catalogueManager = (CatalogueManager) application.getAttribute("catalogueManager");
		Panier lePanier = (Panier) session.getAttribute("panier");
%>
<nav id="navigation" class="col-full" role="navigation">
	<ul id="main-nav" class="nav fl">
		<li id="menu-item-290"
			class="menu-item menu-item-type-custom menu-item-object-custom">
			<a href="<%=response.encodeURL("./afficheRecherche.jsp")%>">Rechercher
				un article</a>
		</li>
		<li id="menu-item-290"
			class="menu-item menu-item-type-custom menu-item-object-custom">
			<a href="<%=response.encodeURL("./controlePanier.jsp")%>">Panier
				<span class="badge">
					<%
						out.print(lePanier.getNbArticlesCommandes());
					%>
			</span>
		</a>
		</li>
		<li id="menu-item-290"
			class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item">
			<a href="<%=response.encodeURL("./controleCommande.jsp")%>">Historique
				des commandes</a>
		</li>
	</ul>
</nav>
<div id="content" class="col-full">
	<div id="main-sidebar-container">
		<header>
			<h1 class="title entry-title">Historique des commandes</h1>
		</header>
		<section class="entry">
			<div class="woocommerce">

				<table class="shop_table cart" cellspacing="0">
					<thead>
						<tr>
							<th class="order-date">Date de la commande</th>
							<th class="order-time">Heure de la commande</th>
							<th class="order-price">Montant</th>

						</tr>
					</thead>
					<%
						Iterator it;
							Commande uneCommande;
							it = commandes.getIterableCommandes().iterator();
							while (it.hasNext()) {
								uneCommande = (Commande) it.next();
					%>
					<tbody>

						<td class="order-date"><a
							href="<%=response.encodeURL("./controleCommande.jsp?requete=infoCommande&amp;dateCommande="
							+ uneCommande.toStringDate() + "&amp;hourCommande=" + uneCommande.toStringHour())%>"><%=uneCommande.toStringDate()%></a></td>
						<td class="order-date"><%=uneCommande.toStringHour()%></td>
						<td class="order-price"><span class="amount"><%=uneCommande.getPanier().getTotal()%>€</span></td>

						</tr>
						<%
							}
						%>

					</tbody>
				</table>
			</div>
		</section>
	</div>
</div>
</div>
<%
	}
%>
<%@ include file="piedDePage.html"%>