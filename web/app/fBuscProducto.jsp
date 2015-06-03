<%-- 
    Document   : fBuscProducto
    Created on : 28-nov-2013, 13:50:08
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form name="fbp">

    <input type="hidden" name="opcion" value="buscar"/>
    
    <table style="width:100%">

        <tr>
            <th style="width:200px;text-align:left" class="headerTable">
                Nombre:
            </th>
            <td style="text-align:left" class="renglonUno">
                <input type="text" name="nombre" size="32" maxlength="100"/>
            </td>           
        </tr>
        <tr>
            <th style="width:200px;text-align:left" class="headerTable">
                Marca:
            </th>
            <td style="text-align:left" class="renglonUno">
                <%@taglib prefix="marca" uri="/WEB-INF/tlds/sel_marca.tld" %>
                <marca:select nombre="marca"/>
            </td>           
        </tr>

    </table>
    <table style="width:100%">
        <tr>
            <td style="text-align:center">
                <script type="text/javascript">

                    $(function() {

                        $('#bp').click(function() {

                            $.ajax({
                                url: '/tiendita/app/producto.do',
                                data: $('form[name="fbp"]').serialize(),
                                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                                type: 'POST',
                                success: function(html) {
                                    $('#producto').html(html);
                                }
                            })

                            $('#error').ajaxError(function(event, request, settings) {
                                $(this).html(request.responseText);
                            });

                        });

                    });

                </script>
                
                <input id="lp" type="reset" value="Limpiar" class="bt"/>&nbsp;&nbsp;<input id="bp" type="button" value="Buscar" class="bt"/> 
            </td>
        </tr>
    </table>


</form>
