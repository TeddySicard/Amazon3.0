<%@ page pageEncoding="UTF-8"%>
<%@ include file="enTetePage.html"%>
<%@ page import="commerce.gestion.Panier"%>


<%
	if (session.getAttribute("panier") == null) {
		response.sendRedirect("./index.jsp");
	} else {
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
			class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item">
			<a href="<%=response.encodeURL("./controlePanier.jsp")%>">Panier
				<span class="badge">
					<%
						out.print(lePanier.getNbArticlesCommandes());
					%>
			</span>
		</a>
		</li>
		<li id="menu-item-290"
			class="menu-item menu-item-type-custom menu-item-object-custom">
			<a href="<%=response.encodeURL("./controleCommande.jsp")%>">Historique
				des commandes</a>
		</li>
	</ul>
</nav>
<div id="content" class="col-full">
	<div id="main-sidebar-container">
		<header>
			<h1 class="title entry-title">Panier</h1>
		</header>
		<div class="woocommerce">
			<section class="entry">
				<p class="cart-empty">Votre panier est vide.</p>
			</section>
		</div>
	</div>
</div>
<%
	}
%>
<%@ include file='piedDePage.html'%>

