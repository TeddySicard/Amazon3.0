/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-03-18 18:01:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import commerce.gestion.Panier;

public final class affichePanierVide_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/piedDePage.html", Long.valueOf(1584436850910L));
    _jspx_dependants.put("/enTetePage.html", Long.valueOf(1584476325205L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Amazon 3.0</title>\r\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"./css/style.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" id=\"storefront-woocommerce-style-css\" href=\"./css/themes/storefont/assets/sass/woocommerce/woocommerce.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"./js/jplayer-2.9.2/skin/blue.monday/css/jplayer.blue.monday.css\" type=\"text/css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"./js/jquery-1.11.1.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"./js/jplayer-2.9.2/jplayer/jquery.jplayer.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"./js/jplayer-2.9.2/add-on/jplayer.playlist.min.js\"></script>\r\n");
      out.write("<style id='storefront-style-inline-css' type='text/css'>\r\n");
      out.write("\t\t\ttable.cart td.product-remove,\r\n");
      out.write("\t\t\ttable.cart td.actions {\r\n");
      out.write("\t\t\t\tborder-top-color: #ffffff;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<div id=\"inner-wrapper\">\r\n");
      out.write("\t\t\t<header id=\"header\" class=\"col-full\">\r\n");
      out.write("\t\t\t\t<div id=\"logo\">\r\n");
      out.write("\t\t\t\t\t<h1 class=\"site-title\">Amazon 3.0</h1>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</header>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	if (session.getAttribute("panier") == null) {
		response.sendRedirect("./index.jsp");
	} else {
		Panier lePanier = (Panier) session.getAttribute("panier");

      out.write("\r\n");
      out.write("<nav id=\"navigation\" class=\"col-full\" role=\"navigation\">\r\n");
      out.write("\t<ul id=\"main-nav\" class=\"nav fl\">\r\n");
      out.write("\t\t<li id=\"menu-item-290\"\r\n");
      out.write("\t\t\tclass=\"menu-item menu-item-type-custom menu-item-object-custom\">\r\n");
      out.write("\t\t\t<a href=\"");
      out.print(response.encodeURL("./afficheRecherche.jsp"));
      out.write("\">Rechercher\r\n");
      out.write("\t\t\t\tun article</a>\r\n");
      out.write("\t\t</li>\r\n");
      out.write("\t\t<li id=\"menu-item-290\"\r\n");
      out.write("\t\t\tclass=\"menu-item menu-item-type-custom menu-item-object-custom current-menu-item\">\r\n");
      out.write("\t\t\t<a href=\"");
      out.print(response.encodeURL("./controlePanier.jsp"));
      out.write("\">Panier\r\n");
      out.write("\t\t\t\t<span class=\"badge\">\r\n");
      out.write("\t\t\t\t\t");

						out.print(lePanier.getNbArticlesCommandes());
					
      out.write("\r\n");
      out.write("\t\t\t</span>\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t\t</li>\r\n");
      out.write("\t\t<li id=\"menu-item-290\"\r\n");
      out.write("\t\t\tclass=\"menu-item menu-item-type-custom menu-item-object-custom\">\r\n");
      out.write("\t\t\t<a href=\"");
      out.print(response.encodeURL("./controleCommande.jsp"));
      out.write("\">Historique\r\n");
      out.write("\t\t\t\tdes commandes</a>\r\n");
      out.write("\t\t</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("</nav>\r\n");
      out.write("<div id=\"content\" class=\"col-full\">\r\n");
      out.write("\t<div id=\"main-sidebar-container\">\r\n");
      out.write("\t\t<header>\r\n");
      out.write("\t\t\t<h1 class=\"title entry-title\">Panier</h1>\r\n");
      out.write("\t\t</header>\r\n");
      out.write("\t\t<div class=\"woocommerce\">\r\n");
      out.write("\t\t\t<section class=\"entry\">\r\n");
      out.write("\t\t\t\t<p class=\"cart-empty\">Votre panier est vide.</p>\r\n");
      out.write("\t\t\t</section>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");

	}

      out.write('\r');
      out.write('\n');
      out.write("\t\t<footer id=\"footer\" class=\"col-full\">\r\n");
      out.write("\t\t\t<div id=\"credit\" class=\"col-right\">\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t<object type=\"image/svg+xml\" data=\"./images/deezer.svg\" width=\"152\" height=\"34\"></object>\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\tDesigned by\r\n");
      out.write("\t\t\t\t\t<a title=\"Premium WordPress Themes & Plugins by WooThemes\" alt=\"Premium WordPress Themes & Plugins by WooThemes\" href=\"http://www.woothemes.com/\">\r\n");
      out.write("\t\t\t\t\t\t<img width=\"74\" height=\"19\" alt=\"WooThemes\" src=\"./images/woothemes.png\">\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</footer>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
