/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tiendita.serv;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import tiendita.beans.PedidoProducto;
import tiendita.beans.Pedido;

/**
 *
 * @author miguel
 */
public class ServPedidoProducto extends HttpServlet {
   
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("cerrar")) {
            
          String error = null;
          
          try {  
             
            Pedido pedido = new Pedido();
            pedido.setPediUsuario("MIGUEL");
            pedido.setPediEstatus("L");
            
            pedido = pedido.agrPedido(pedido);
              
            if (pedido.getError() == null) {
                
                
                for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {                
                   String element = String.valueOf(e.nextElement());

                   if (element.substring(0,5).equals("cant-")) {
                       String llave = element.substring(5,element.length());
                       Float cantidad = Float.parseFloat(request.getParameter("cant-"+llave));
                       Integer producto = Integer.parseInt(request.getParameter("prod-"+llave));
                       
                       PedidoProducto pp = new PedidoProducto();
                       pp.setPrpeCantidad(cantidad);
                       pp.setPrpeProducto(producto);
                       pp.setPrpePedido(pedido.getPediPedido());
                       
                       pp = pp.agrProductoPedido(pp);
                       
                       if (pp.getError() != null) {
                           error = pp.getError();
                        break;   
                       }                       
                   }               
                }
                
            } else {
                error = pedido.getError();
            }   
                
          } catch (Exception e) {
              error = e.getMessage();
          }   
            
          
          if (error != null) {
              response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
              this.VeaPagina("/", request, response);
          } else {
              
          }
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("cancelar")) {
            
            
            
        } else {
           response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
           this.VeaPagina("/error.jsp?msg=Objeto no encontrado!", request, response);
        }
        
    }


    private void VeaPagina (String direccion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(direccion);
            dispatcher.forward(request, response);
    }
}
