<%-- 
    Document   : error
    Created on : 01-dic-2013, 22:27:32
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:scriptlet>
    System.out.println(request.getParameter("msg"));
</jsp:scriptlet>

<div id="er">
    <h3 style="color:red"><%= request.getParameter("msg") %></h3>
</div>
