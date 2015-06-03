<%-- 
    Document   : producto
    Created on : 14-nov-2013, 14:55:42
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiendita.beans.Producto" %>
<%@taglib prefix="marca" uri="/WEB-INF/tlds/sel_marca.tld" %>
<%@taglib prefix="loc" uri="/WEB-INF/tlds/sel_locacion.tld" %>
<%@taglib prefix="unidad" uri="/WEB-INF/tlds/sel_umed.tld" %>

<jsp:scriptlet>
  
    Integer prod = request.getParameter("producto") != null ? Integer.parseInt(request.getParameter("producto")) : null;  
    
    Producto pr = null;
    
    if (prod != null) {
        pr = new Producto();
        pr = pr.setId(prod);
    }    
    
</jsp:scriptlet>

<div id="errno"></div>

<form name="fpr" method="POST" action="">
    <div style="text-align:center">
        <b>PRODUCTOS <%= pr != null ? "[EDITAR]" : "[AGREGAR]" %> </b>
    </div>
    
    <input type="hidden" name="opcion" value="<%= pr != null ? "act" : "agr" %>"/>
    
    <table style="width:100%">
        
        <% if (pr != null) { %>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Id Producto:</th>
            <td style="text-align:left" class="renglonUno">
                <jsp:scriptlet>
                  String ppr = pr != null ? pr.getProdProducto().toString() : "";
                </jsp:scriptlet>
                <input type="hidden" name="producto" value="<%= ppr %>"/>                
            </td>
        </tr>
        <% } %>
        
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Nombre:</th>
            <td style="text-align:left" class="renglonUno"> 
                <jsp:scriptlet>
                    String nomb = pr != null && pr.getProdNombre() != null ? pr.getProdNombre() : "";
                </jsp:scriptlet>
                
                <input type="text" name="nombre" maxlength="100" size="32" value="<%= nomb %>"/>
            </td>
        </tr>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Marca:</th>
            <td style="text-align:left" class="renglonUno">
                <jsp:scriptlet>
                    String mar = pr != null && pr.getProdMarca() != null ? pr.getProdMarca().toString() : ""; 
                </jsp:scriptlet>
                <marca:select nombre="marca" select="<%= mar %>" />
            </td>
        </tr>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Unidad de Medida:</th>
            <td style="text-align:left" class="renglonUno">
                <jsp:scriptlet>
                    String med = pr != null && pr.getProdUmedida() != null ? pr.getProdUmedida().toString() : "";
                </jsp:scriptlet>
                <unidad:unidades nombre="umedida" select="<%= med %>"/>               
            </td>
        </tr>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Precio:</th>
            <td style="text-align:left" class="renglonUno">
                <jsp:scriptlet>
                    String prec = pr != null && pr.getProdPrecio() != null ? pr.getProdPrecio().toString() : "";
                </jsp:scriptlet>
                <input type="text" name="precio" maxlength="10" size="11" value="<%= prec %>"/>
            </td>
        </tr>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Existencia:</th>
            <td style="text-align:left" class="renglonUno">
                <jsp:scriptlet>
                    String exis = pr != null && pr.getProdExistencia() != null ? pr.getProdExistencia().toString() : "";
                </jsp:scriptlet>
                <input type="text" name="existencia" maxlength="10" size="11" value="<%= exis %>"/>
            </td>
        </tr>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">Locaci&oacute;n:</th>
            <td style="text-align:left" class="renglonUno">
                <%
                    String loc = pr != null && pr.getProdLocacion() != null ? pr.getProdLocacion().toString() : ""; 
                %>
                <loc:locacion nombre="locacion" select="<%= loc %>"/>
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
                                            url: '/tiendita/app/producto.do',
                                            data: $('form[name="fpr"]').serialize(),
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
