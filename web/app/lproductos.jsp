<%-- 
    Document   : lproductos
    Created on : 28-nov-2013, 14:43:40
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="grid-productos" uri="/WEB-INF/tlds/gsel_producto.tld" %>

<jsp:scriptlet>
    boolean editar = request.getParameter("opcion") != null && request.getParameter("opcion").equals("editar") ? true : false;
    
    if (editar) {
        request.getSession().setAttribute("opcion", "E");
    }
</jsp:scriptlet>
<grid-productos:productos/>