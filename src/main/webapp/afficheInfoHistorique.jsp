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
		Panier lePanier = (Panier) session.getAttribute("panier");
		ListeCommande commandes = (ListeCommande) session.getAttribute("commandes");
		CatalogueManager catalogueManager = (CatalogueManager) application.getAttribute("catalogueManager");
		String dateHourCommande = request.getParameter("dateHourCommande");
		Commande laCommande = commandes.getCommande(dateHourCommande);
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
			<span class="badge"><%
				out.print(lePanier.getNbArticlesCommandes());		
			%></span>
			</a>		</li>
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
			<h1 class="title entry-title">Résumé de la commande du <% out.println(laCommande.toStringDate()); %> à <% out.println(laCommande.toStringHour()); %></h1>
		</header>
		<section class="entry">
			<div class="woocommerce">
					<table class="shop_table cart" cellspacing="0">
						<thead>
							<tr>
								<th class="product-thumbnail">Aperçu</th>
								<th class="product-name">Produit</th>
								<th class="product-price">Prix</th>
								<th class="product-quantity">Quantité</th>
								<th class="product-subtotal">Total</th>
							</tr>
						</thead>
						<%
							Iterator it;
							Article unArticle;
							it = laCommande.getPanier().getLignesPanier().iterator();
									LignePanier uneLignePanier;
									while (it.hasNext()) {
										uneLignePanier = (LignePanier) it.next();
										unArticle = uneLignePanier.getArticle();
						%>
						<tbody>
							<tr class="cart_item">
								<td class="product-thumbnail"><img
									class="attachment-shop_thumbnail wp-post-image" width="145"
									height="145" alt="hoodie_4_front"
									src="<% if (unArticle.getImage().startsWith("http")) 
									    out.print(unArticle.getImage()) ;
							        else
							        	out.print("./images/"+unArticle.getImage()) ; %>"/></td>
								<td class="product-name"><%=unArticle.getTitre()%></td>
								<td class="product-price"><span class="amount"><%=uneLignePanier.getPrixUnitaire()%>€</span></td>
								<td class="product-quantity"><%=uneLignePanier.getQuantite()%></td>
								<td class="product-subtotal"><span class="amount"><%=uneLignePanier.getPrixTotal()%>€</span></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				<div class="cart-collaterals">
					<div class="cross-sells"></div>
					<div class="cart_totals ">
						<h2>Total de la commande</h2>
						<table cellspacing="0">
							<tbody>
								<tr class="cart-subtotal">
									<th>Sous-total</th>
									<td><span class="amount"><%=laCommande.getPanier().getTotal()%>€</span></td>
								</tr>
								<tr class="shipping">
									<th>Frait de port</th>
									<td>Gratuit</td>
								</tr>
								<tr class="order-total">
									<th>Total</th>
									<td><strong> <span class="amount"><%=laCommande.getPanier().getTotal()%>€</span>
									</strong></td>
								</tr>
							</tbody>
						</table>
						<div class="wc-proceed-to-checkout">
							<a
								href="<%=response
							.encodeURL("./controleCommande.jsp")%>"
								class="checkout-button button alt">Retour à l'historique</a>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>
</div>
<%
	}
%>
<%@ include file="piedDePage.html"%>