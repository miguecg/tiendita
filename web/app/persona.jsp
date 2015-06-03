<%-- 
    Document   : persona
    Created on : 04-dic-2013, 23:19:08
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiendita.beans.Persona" %>
<%@taglib prefix="marca" uri="/WEB-INF/tlds/sel_marca.tld" %>
<%@taglib prefix="loc" uri="/WEB-INF/tlds/sel_locacion.tld" %>
<%@taglib prefix="unidad" uri="/WEB-INF/tlds/sel_umed.tld" %>
      

<jsp:scriptlet>
  
    Integer pers = request.getParameter("persona") != null ? Integer.parseInt(request.getParameter("persona")) : null;  
    
    Persona pr = null;
    
    if (pers != null) {
        pr = new Persona();
        pr = pr.setId(pers);
    }    
    
</jsp:scriptlet>

<div id="errno"></div>

<form name="fpers" method="POST" action="">
    <div style="text-align:center">
        <b>Personas <%= pr != null ? "[EDITAR]" : "[AGREGAR]" %> </b>
    </div>
    
    <input type="hidden" name="opcion" value="<%= pr != null ? "act" : "agr" %>"/>
    
    <table style="width:100%">
        
        <% if (pr != null) { %>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Id Persona:</th>
            <td style="text-align:left" class="renglonUno">
                <jsp:scriptlet>
                  String ppr = pr != null ? pr.getPersPersona().toString() : "";
                </jsp:scriptlet>
                <input type="hidden" name="persona" value="<%= ppr %>"/>   <%= ppr %>     
            </td>
        </tr>
        <% } %>
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Nombre:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String nomb = pr != null && pr.getPersNombre() != null ? pr.getPersNombre() : "";
                </jsp:scriptlet>
                
                <input type="text" name="nombre" maxlength="35" size="35" value="<%= nomb %>"/>
            </td>
        </tr>
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Primer apellido:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String apepat = pr != null && pr.getPersApepat() != null ? pr.getPersApepat() : "";
                </jsp:scriptlet>
                
                <input type="text" name="apepat" maxlength="35" size="35" value="<%= apepat %>"/>
            </td>
        </tr>
       
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Segundo apellido:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String apemat = pr != null && pr.getPersApemat() != null ? pr.getPersApemat() : "";
                </jsp:scriptlet>
                
                <input type="text" name="apemat" maxlength="35" size="35" value="<%= apemat %>"/>
            </td>
        </tr>
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Correo electr&oacute;nico:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String correo = pr != null && pr.getPersCorreo() != null ? pr.getPersCorreo() : "";
                </jsp:scriptlet>
                
                <input type="text" name="correo" maxlength="60" size="35" value="<%= correo %>"/>
            </td>
        </tr>
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Direcci&oacute;n:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String direccion = pr != null && pr.getPersDireccion() != null ? pr.getPersDireccion() : "";
                </jsp:scriptlet>
                
                <input type="text" name="direccion" maxlength="255" size="35" value="<%= direccion %>"/>
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
                                            url: '/tiendita/app/persona.do',
                                            data: $('form[name="fpers"]').serialize(),
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
