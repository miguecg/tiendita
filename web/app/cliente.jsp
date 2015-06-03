<%-- 
    Document   : cliente
    Created on : 05-dic-2013, 0:54:16
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiendita.beans.Cliente" %>
<%@taglib prefix="marca" uri="/WEB-INF/tlds/sel_marca.tld" %>
<%@taglib prefix="loc" uri="/WEB-INF/tlds/sel_locacion.tld" %>
<%@taglib prefix="unidad" uri="/WEB-INF/tlds/sel_umed.tld" %>
      

<jsp:scriptlet>
  
    Integer clie = request.getParameter("cliente") != null ? Integer.parseInt(request.getParameter("cliente")) : null;  
    
    Cliente pr = null;
    
    if (clie != null) {
        pr = new Cliente();
        pr = pr.setId(clie);
    }    
    
</jsp:scriptlet>

<div id="errno"></div>

<form name="fclie" method="POST" action="">
    <div style="text-align:center">
        <b>Clientes <%= pr != null ? "[EDITAR]" : "[AGREGAR]" %> </b>
    </div>
    
    <input type="hidden" name="opcion" value="<%= pr != null ? "act" : "agr" %>"/>
    
    <table style="width:100%">
        
        <% if (pr != null) { %>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Id Cliente:</th>
            <td style="text-align:left" class="renglonUno">
                <jsp:scriptlet>
                  String ppr = pr != null ? pr.getClieCliente().toString() : "";
                </jsp:scriptlet>
                <input type="hidden" name="cliente" value="<%= ppr %>"/>                
            </td>
        </tr>
        <% } %>
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">RFC:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String nomb = pr != null && pr.getClieRfc() != null ? pr.getClieRfc() : "";
                </jsp:scriptlet>
                
                <input type="text" name="rfc" maxlength="35" size="35" value="<%= nomb %>"/>
            </td>
        </tr>
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Raz&oacute;n Social:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String apepat = pr != null && pr.getClieRazonSoc() != null ? pr.getClieRazonSoc() : "";
                </jsp:scriptlet>
                
                <input type="text" name="razonsoc" maxlength="35" size="35" value="<%= apepat %>"/>
            </td>
        </tr>
        
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Persona:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String pers = pr != null && pr.getCliePersona() != null ? String.valueOf(pr.getCliePersona()) : "";
                </jsp:scriptlet>
                
                <%@taglib prefix="persona" uri="/WEB-INF/tlds/sel_persona.tld" %>
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
                                            url: '/tiendita/app/cliente.do',
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