<%-- 
    Document   : unidad
    Created on : 30-nov-2013, 1:40:19
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiendita.beans.Unidad" %>

<jsp:scriptlet>
    
    Unidad uni = null;
    Integer idm = request.getParameter("unidad") != null ? Integer.parseInt(request.getParameter("unidad")) : null;
    
    if (idm != null) {
        uni = new Unidad();
        uni = uni.setId(idm);
    }    
    
</jsp:scriptlet>

<form name="funi">
    <div style="text-align:center">
        <b>UNIDADES <%= uni != null ? "[EDITAR]" : "[AGREGAR]" %> </b>
    </div>
    
    <input type="hidden" name="opcion" value="<%= uni != null ? "act" : "agr" %>"/>
    
    <table style="width:100%">
        
        <% if (uni != null) { %>
        <tr>
            <th style="text-align:left;width:200px" class="headerTable">Id Unidad:</th>
            <td style="text-align:left" class="renglonUno">
                <%= uni.getUmedUmedida() %>
                <input type="hidden" name="unidad" value="<%= uni.getUmedUmedida() %>"/>
            </td>
        </tr>
        <% } %>
        
        <tr>
            <th style="text-align:left;width:200px" class="headerTable">Descripci&oacute;n:</th>
            <td style="text-align:left" class="renglonUno">
                <input type="text" name="descrip" size="32" maxlength="100" value="<%= uni != null && uni.getUmedDescrip() != null ? uni.getUmedDescrip() : "" %>"/>
            </td>
        </tr>
        <tr>
            <th style="text-align:left;width:200px" class="headerTable">Siglas:</th>
            <td style="text-align:left" class="renglonUno">
                <input type="text" name="siglas" size="6" maxlength="5" value="<%= uni != null && uni.getUmedSiglas().toString() != null ? uni.getUmedSiglas().toString() : "" %>"/>
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
                                            url: '/tiendita/app/unidad.do',
                                            data: $('form[name="funi"]').serialize(),
                                            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                                            type: 'POST',
                                            success: function(html) {
                                                $('#forms').html(html);
                                                $('#forms').show('slow');
                                                $('#producto').html('');
                                            }
                                        })

                                        $('#error').ajaxError(function(event, request, settings) {                                            
                                            $('#error').html(request.responseText);
                                            alert(request.responseText);
                                        });
                                    });
                                                                    
                            });
                        </script>
                        <input type="reset" value="Limpiar" class="bt"/>&nbsp;&nbsp;<input id="envform" type="button" value="Aceptar" class="bt"/>
                    </td>
                </tr>
     </table>  
    
</form>
