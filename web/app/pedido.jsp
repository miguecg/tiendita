<%-- 
    Document   : pedido
    Created on : 03-dic-2013, 13:58:05
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="ped" style="border:1px solid #333; height:200px;overflow:auto">
    <table style="width:200px;border:0px;">
        <tr>
            <th class="headerTable">
                <input id="cPedido" type="button" class="bt" value="Cerrar Pedido" onclick="cPedido();"/>
            </th>
            <th class="headerTable">
                <input id="lPedido" type="button" class="bt" value="Limpiar Pedido" onclick="lPedido();"/>
            </th>
            <th class="headerTable">
                <input type="button" class="bt" value="Total" onclick="calGranTotal();"
            </th>
            
        </tr>
    </table>    
    <div id="ttot" style="text-align:center;font-size:14px;color:#333;font-weight:bold;padding:5px"></div>
    <form name="pp" id="lpp">
        
    </form>
    <div id="btot" style="text-align:center;font-size:14px;color:#333;font-weight:bold;padding:5px"></div>
</div>
