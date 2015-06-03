<%-- 
    Document   : index
    Created on : 14-nov-2013, 14:08:34
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiendita.beans.Rol" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos</title>
        <link href="/tiendita/css/estilos.css" rel="stylesheet" type="text/css" media="screen"/>
        <link href="/tiendita/css/estilos.css"  type="text/css" rel="stylesheet" media="print"/>        
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.js"></script>
    </head>

    <body>
        <div id="ban" class="headerTable" style="padding:10px;font-size:14px;color:azure;font-weight:bold;font-family:Verdana,arial,helvetica,sans-serif;text-align:left">
            T I E N D I T A
        </div>
        <div id="error"></div>
        <div id="menu" style="width:100%">
            <script type="text/javascript">

                function cf(ruta) {
                    $('#error').html('');
                    $.ajax({
                        url: ruta,
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
                }

                function ap(ruta) {

                    if (document.getElementById('lpp') == undefined) {
                        $.ajax({
                            url: '/tiendita/app/pedido.jsp',
                            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                            type: 'POST',
                            success: function(html) {
                                $('#pedido').html(html);
                                $('#pedido').show('slow');
                            }
                        });
                    }

                    $('#error').html('');

                    $.ajax({
                        url: ruta,
                        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                        type: 'POST',
                        success: function(html) {
                            $('#lpp').html($('#lpp').html() + html);
                            $('#lpp').show('slow');
                        }
                    });

                    $('#error').ajaxError(function(event, request, settings) {
                        $('#error').html(request.responseText);
                    });
                }

                function br(idc) {
                    $('#' + idc).hide('slow');
                }

                function calTotal(idp) {
                    var tot = parseFloat($('#v-' + idp).val());
                    var cant = parseFloat($('#c-' + idp).val());
                    var cantidad = parseFloat(tot * cant);
                    $('#t-' + idp).html(cantidad);
                    calGranTotal();
                }

                function eliminarDelPedido(idp) {
                    if (confirm('Eliminar el producto del pedido?')) {
                        $('#' + idp).html('');
                        calGranTotal();
                    }
                }


                function cPedido() {
                    $('#error').html('');
                    if (confirm('Cerrar Pedido?')) {
                        $.ajax({
                            url: '/tiendita/app/pedido.do?opcion=cerrar',
                            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                            type: 'POST',
                            data: $('form[name="pp"]').serialize(),
                            success: function(html) {
                                $('#lpp').html(html);
                                $('#lpp').show('slow');
                            }
                        });

                        $('#error').ajaxError(function(event, request, settings) {
                            $('#error').html(request.responseText);
                        });
                    }
                }

                function lPedido() {
                    $('#lPedido').click(function() {
                        $('#error').html('');
                        if (confirm('Limpiar Pedido?')) {
                            $('#lpp').html('');
                            $('#ttot').html('');
                            $('#btot').html('');
                        }
                    });
                }

                function calGranTotal() {

                    var ar = $('form[name="pp"]').serializeArray();
                    var st = "";
                    var total = 0;
                    for (var i = 0; i < ar.length; i++) {
                        st = ((ar[i]).name).substring(5, ((ar[i]).name).length);
                        if ((((ar[i]).name)).substring(0, 5) == 'cant-') {
                            total += parseFloat($('#c-' + st).val()) * parseFloat($('#v-' + st).val());
                        }
                    }
                    $('#ttot').html('$ ' + total);
                    $('#btot').html('$ ' + total);
                }


                <% if (Rol.autorizado(((List<Rol>) request.getSession().getAttribute("USUA_ROL")), 4)) {%>

                function agRol(usuario, rol) {
                    $.ajax({
                        url: '/tiendita/app/usuario.do?opcion=agrRol&usuario=' + usuario + '&rol=' + rol,
                        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                        type: 'POST',
                        success: function(html) {
                            cf('/tiendita/app/rol_usuario.jsp?usuario=' + usuario);
                        }
                    });
                    $('#error').ajaxError(function(event, request, settings) {
                        $('#error').html(request.responseText);
                    });
                }

                function borRol(usuario, rol) {
                    $.ajax({
                        url: '/tiendita/app/usuario.do?opcion=borRol&usuario=' + usuario + '&rol=' + rol,
                        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                        type: 'POST',
                        success: function(html) {
                            cf('/tiendita/app/rol_usuario.jsp?usuario=' + usuario);
                        }
                    });
                    $('#error').ajaxError(function(event, request, settings) {
                        $('#error').html(request.responseText);
                    });
                }
                <% } %>

            </script>
            <table style="width:100%;border:1px solid appworkspace">
                <tr>
                    <td>


                        <ul>
                            <li>Producto:
                                <ul>

                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/fBuscProducto.jsp');">Buscar</a></li>                                

                                    <% if (Rol.autorizado(((List<Rol>) request.getSession().getAttribute("USUA_ROL")), 4)) {%>  
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/lproductos.jsp?opcion=editar');">Editar</li>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/producto.jsp');">Agregar</li>     
                                        <% }%>  
                                </ul>
                            </li>
                        </ul>                            
                    </td>
                    <td>
                        <ul>
                            <li>Pedido:
                                <ul>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/pedido_producto.jsp');">Pedidos</a></li>
                                </ul>
                            </li>
                        </ul>
                    </td>


                    <% if (Rol.autorizado(((List<Rol>) request.getSession().getAttribute("USUA_ROL")), 4)) {%>  
                    <td>
                        <ul>
                            <li>Marca
                                <ul>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/lmarca.jsp');">Editar</a></li>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/marca.jsp');">Agregar</a></li>
                                </ul>
                            </li>
                        </ul>

                    </td>
                    <td>
                        <ul>
                            <li>Locaci&oacute;n:
                                <ul>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/llocacion.jsp');">Editar</a></li>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/locacion.jsp');">Agregar</a></li>
                                </ul>

                            </li>
                        </ul>

                    </td>
                    <td>
                        <ul>
                            <li>Unidades
                                <ul>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/lunidad.jsp');">Editar</a></li>
                                    <li>
                                        <a href="javascript:;" onclick="cf('/tiendita/app/unidad.jsp');">Agregar</a>
                                    </li>
                                </ul>

                            </li>
                        </ul>

                    </td>

                    <td>
                        <ul>
                            <li>Persona
                                <ul>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/lpersona.jsp');">Editar</a></li>
                                    <li>
                                        <a href="javascript:;" onclick="cf('/tiendita/app/persona.jsp');">Agregar</a>
                                    </li>
                                </ul>

                            </li>
                        </ul>

                    </td>



                    <td>
                        <ul>
                            <li>Usuario
                                <ul>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/lusuario.jsp');">Editar</a></li>
                                    <li>
                                        <a href="javascript:;" onclick="cf('/tiendita/app/usuario.jsp');">Agregar</a>
                                    </li>
                                </ul>

                            </li>
                        </ul>

                    </td>
                    <td>
                        <ul>
                            <li>Rol
                                <ul>
                                    <li><a href="javascript:;" onclick="cf('/tiendita/app/lrol.jsp');">Editar</a></li>
                                    <li>
                                        <a href="javascript:;" onclick="cf('/tiendita/app/rol.jsp');">Agregar</a>
                                    </li>
                                </ul>

                            </li>
                        </ul>

                    </td>
                </tr>
                <% }%>   


            </table>    

        </div>
        <div id="forms" style="width:100%;display:none">


        </div>
        <div id="pedido" style="width:100%;display:none"></div>
        <div id="producto" style="width:100%"></div>


    </body>
</html>
