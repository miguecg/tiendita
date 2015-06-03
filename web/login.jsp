<%-- 
    Document   : login
    Created on : 08-dic-2013, 22:59:08
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de Sesi&oacute;n</title>
        <link href="/tiendita/css/estilos.css" rel="stylesheet" type="text/css" media="screen"/>
        <link href="/tiendita/css/estilos.css"  type="text/css" rel="stylesheet" media="print"/>        
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.js"></script>
        <script type="text/javascript">
           
           function init(nform) {
               document.forms[nform].submit();
           }
           
           
        </script>
    </head>
    <body>
        
        <div style="text-align:center">
            <form name="sesf" action="/tiendita/app/index.jsp" method="POST">  
            <table style="margin:auto;width:600px">
                <tr>
                    <th class="headerTable" style="width:150px">Usuario:</th>                    
                    <td style="text-align:left"><input type="text" size="20" maxlength="32" name="j_username"/></td>
                </tr>
                <tr>
                    <th class="headerTable" style="width:150px">Contrase&ntilde;a:</th>                    
                    <td style="text-align:left"><input type="password" size="20" maxlength="32" name="j_password"/></td>
                </tr>
                
            </table>
                <div style="text-align:center">
                    <input type="hidden" name="opcion" value="auth"/>
                    <input type="button" value="Iniciar Sesi&oacute;n" class="bt" onclick="init('sesf');"/>
                </div>
            </form>
            <div style="text-align:center;color:red;font-weight:bold">
                <% if (request.getParameter("error") != null && request.getParameter("error").equals("1")) { %>
                El usuario o la contrase&ntilde;a son incorrectos!
                <% } %>
                <% if (request.getParameter("error") != null && request.getParameter("error").equals("2")) { %>
                No tiene permiso para iniciar sesi&oacute;n...
                <% } %>
                
                
            </div>
        </div>
        
    </body>
</html>
