<%-- 
    Document   : usuario
    Created on : 05-dic-2013, 13:38:09
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiendita.beans.Usuario" %>
<%@taglib prefix="persona" uri="/WEB-INF/tlds/sel_persona.tld" %>
      

<jsp:scriptlet>
  
    String usu = request.getParameter("usuario") != null ? request.getParameter("usuario") : null;  
    
    Usuario pr = null;
    
    if (usu != null) {
        pr = new Usuario();
        pr = pr.setId(usu);
    }    
    
</jsp:scriptlet>

<div id="errno"></div>

<form name="fclie" method="POST" action="">
    <div style="text-align:center">
        <b>Usuarios <%= pr != null ? "[EDITAR]" : "[AGREGAR]" %> </b>
    </div>
    
    <input type="hidden" name="opcion" value="<%= pr != null ? "act" : "agr" %>"/>
    
    <table style="width:100%">
        
        <% if (pr != null) { %>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Id Usuario:</th>
            <td style="text-align:left" class="renglonUno">
                <jsp:scriptlet>
                  String ppr = pr != null ? pr.getUsuaUsuario() : "";
                </jsp:scriptlet>
                <input type="hidden" name="usuario" value="<%= ppr %>"/>                
            </td>
        </tr>
        <% } else { %>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Id Usuario:</th>
            <td style="text-align:left" class="renglonUno">
              
                <input type="text" name="usuario" size="32" maxlength="32"/>                
            </td>
        </tr>
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Contrase&ntilde;a:</th>
            <td style="text-align:left" class="renglonUno">                 
                <input type="password" name="password" maxlength="32" size="32"/>
            </td>
        </tr>
        
        <% } %>
        
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Estatus:</th>
            <td style="text-align:left" class="renglonUno">                 
            
                <jsp:scriptlet>
                    String estat = pr != null ? pr.getUsuaEstatus() : "A";
                </jsp:scriptlet>
                
                <% if (estat.equals("C")) { %>
                <input type="radio" name="estatus" value="A"/>Activo <input type="radio" name="estatus" value="C" checked/>No activo
                <% } else { %>
                <input type="radio" name="estatus" value="A" checked/>Activo <input type="radio" name="estatus" value="C"/>No activo
                <% } %>
                
            </td>
        </tr>
        
                       
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Persona:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String pers = pr != null && pr.getUsuaPersona() != null ? String.valueOf(pr.getUsuaPersona()) : "";
                </jsp:scriptlet>
                
                
                <persona:select nombre="persona" select="<%= pers %>"/>
            </td>
        </tr>
        
    </table> 
            
            <table style="width:100%;">
                <tr>
                    <td style="text-align:center">
                        <script type="text/javascript">
                            $(function() {
                                $('#envform').click(function() {
                                   $('#error').html('');
                                                                        
                                   $.ajax({
                                            url: '/tiendita/app/usuario.do',
                                            data: $('form[name="fclie"]').serialize(),
                                            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                                            type: 'POST',
                                            success: function(html) {
                                                $('#forms').html(html);
                                                $('#forms').show('slow');
                                                $('#producto').html('');
                                            }
                                        });
                                        
                                        $('#error').ajaxError(function(event, request, settings) {
                                            $('#error').html(request.responseText);                                            
                                        });
                                        

                                    });                                                                    
                            });
                        </script>
                        <input type="reset" value="Limpiar" class="bt"/>&nbsp;&nbsp;<input id="envform" type="button" value="Aceptar" class="bt"/>
                    </td>
                </tr>
            </table>       
            
 </form>
