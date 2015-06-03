<%-- 
    Document   : marca
    Created on : 14-nov-2013, 14:14:27
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiendita.beans.Marca" %>

<jsp:scriptlet>
    
    Marca marc = null;
    Integer idm = request.getParameter("marca") != null ? Integer.parseInt(request.getParameter("marca")) : null;
    
    System.out.println(request.getParameter("marca")+" "+idm);
    
    if (idm != null) {
        marc = new Marca();
        marc = marc.setId(idm);
    }    
    
</jsp:scriptlet>

<form name="fmar">
    
    <input type="hidden" name="opcion" value="<%= marc != null ? "act" : "agr" %>"/>
    
    <div style="text-align:center">
        <b>MARCAS <%= marc != null ? "[EDITAR]" : "[AGREGAR]" %> </b>
    </div>
    <table style="width:100%">
       <% if (marc != null) { %>
        <tr>
            <th style="text-align:left;width:200px" class="headerTable">Id Marca:</th>
            <td style="text-align:left" class="renglonUno">
                <%= idm %>
                <input type="hidden" name="marca" value="<%= idm %>"/>
            </td>
        </tr>
       <% } %>         
        
        <tr>
            <th style="text-align:left;width:200px" class="headerTable">Descripci&oacute;n:</th>
            <td style="text-align:left" class="renglonUno">
                <input type="text" name="descrip" size="32" maxlength="100" value="<%= marc != null && marc.getMarcDescrip() != null ? marc.getMarcDescrip() : "" %>"/>
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
                                            url: '/tiendita/app/marca.do',
                                            data: $('form[name="fmar"]').serialize(),
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

