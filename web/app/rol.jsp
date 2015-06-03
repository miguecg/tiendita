<%-- 
    Document   : rol
    Created on : 05-dic-2013, 13:52:38
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiendita.beans.Rol" %>
<%@taglib prefix="persona" uri="/WEB-INF/tlds/sel_persona.tld" %>
      

<%
  
    Integer rl = request.getParameter("rol") != null ? Integer.parseInt(request.getParameter("rol")) : null;  
    
    Rol pr = null;
    
    if (rl != null) {
        pr = new Rol();
        pr = pr.setId(rl);
    }    
    
%>

<div id="errno"></div>

<form name="fclie" method="POST" action="">
    <div style="text-align:center">
        <b>Roles <%= pr != null ? "[EDITAR]" : "[AGREGAR]" %> </b>
    </div>
    
    <input type="hidden" name="opcion" value="<%= pr != null ? "act" : "agr" %>"/>
    
    <table style="width:100%">
        
        <% if (pr != null) { %>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Id Rol:</th>
            <td style="text-align:left" class="renglonUno">
                <%
                  String ppr = pr != null ? pr.getRoleRol().toString() : "";
                %>
                <input type="hidden" name="rol" value="<%= ppr.toString() %>"/> <%= ppr.toString() %>         
            </td>
        </tr>
        <% } %>
        
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Descripci&oacute;n:</th>
            <td style="text-align:left" class="renglonUno">                 
            
                <jsp:scriptlet>
                    String descrip = pr != null ? pr.getRoleNombre() : "";
                </jsp:scriptlet>
             
                <input type="text" size="32" maxlength="35" name="nombre" value="<%= descrip %>"/>
                
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
                                            url: '/tiendita/app/rol.do',
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
