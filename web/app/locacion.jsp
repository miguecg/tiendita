<%-- 
    Document   : locacion
    Created on : 30-nov-2013, 1:32:53
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiendita.beans.Locacion" %>

<jsp:scriptlet>
    
    Locacion loc = null;
    Integer idm = request.getParameter("locacion") != null ? Integer.parseInt(request.getParameter("locacion")) : null;
    
    if (idm != null) {
        loc = new Locacion();
        loc = loc.setId(idm);
    }    
    
</jsp:scriptlet>

<form name="floc">
    <div style="text-align:center">
        <b>LOCACIONES <%= loc != null ? "[EDITAR]" : "[AGREGAR]" %> </b>
    </div>
    
    <input type="hidden" name="opcion" value="<%= loc != null ? "act" : "agr" %>"/>
    
    <table style="width:100%">
        
     <% if (loc != null) { %>   
        <tr>
            <th style="text-align:left;width:200px" class="headerTable">Id Locacio&oacute;n:</th>
            <td style="text-align:left" class="renglonUno">
                <%= loc.getLocaLocacion() %>
                <input type="hidden" name="locacion" value="<%= loc.getLocaLocacion() %>"/>
            </td>
        </tr>
     <% } %>    
        
        <tr>
            <th style="text-align:left;width:200px" class="headerTable">Descripci&oacute;n:</th>
            <td style="text-align:left" class="renglonUno">
                <input type="text" name="descrip" size="32" maxlength="100" value="<%= loc != null && loc.getLocaDescrip() != null ? loc.getLocaDescrip() : "" %>"/>
            </td>
        </tr>
        <tr>
            <th style="text-align:left;width:200px" class="headerTable">Posici&oacute;n:</th>
            <td style="text-align:left" class="renglonUno">
                <input type="text" name="posicion" size="11" maxlength="10" value="<%= loc != null && loc.getLocaPosicion().toString() != null ? loc.getLocaPosicion().toString() : "" %>"/>
            </td>
        </tr>
    </table>
     <table style="width:100%;">
                <tr>
                    <td style="text-align:center">
                        
                        <script type="text/javascript">
                            $(function() {
                                $('#envform').click(function() {
                                                                        
                                   $.ajax({
                                            url: '/tiendita/app/locacion.do',
                                            data: $('form[name="floc"]').serialize(),
                                            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                                            type: 'POST',
                                            success: function(html) {
                                                $('#forms').html(html);
                                                $('#forms').show('slow');
                                                $('#producto').html('');
                                            }
                                        })

                                        $('#error').ajaxError(function(event, request, settings) {
                                            $(this).html(request.responseText);
                                        });
                                    });                                                                    
                            });
                        </script>
                        <input type="reset" value="Limpiar" class="bt"/>&nbsp;&nbsp;<input id="envform" type="button" value="Aceptar" class="bt"/>
                    </td>
                </tr>
     </table>  
    
</form>