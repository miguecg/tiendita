/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita.serv;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tiendita.beans.Producto;

/**
 *
 * @author miguel
 */
public class ServProducto extends HttpServlet {

   
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("buscar")) {
            String condicion = " Where 1=1 ";
            condicion += request.getParameter("nombre") != null && !request.getParameter("nombre").equals("") ? " And prod_nombre like '%"+request.getParameter("nombre")+"%' " : "";
            condicion += request.getParameter("marca") != null && !request.getParameter("marca").equals("") ? " And prod_marca = '"+request.getParameter("marca")+"' " : "";
            
            request.getSession().setAttribute("condicion", condicion); 
            request.getSession().setAttribute("opcion", "C");
            this.VeaPagina("/app/lproductos.jsp", request, response);
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agr")) {    

            String error = null;
            
            try {
                String nombre = request.getParameter("nombre");
                Integer marca = Integer.parseInt(request.getParameter("marca"));
                Integer umedida = Integer.parseInt(request.getParameter("umedida"));
                Float precio = Float.valueOf(request.getParameter("precio"));
                Integer loc = Integer.parseInt(request.getParameter("locacion"));
                Float existencia = Float.valueOf(request.getParameter("existencia"));
                
                Producto producto = new Producto();
                producto.setProdExistencia(existencia);
                producto.setProdPrecio(precio);
                producto.setProdNombre(nombre);
                producto.setProdUmedida(umedida);
                producto.setProdMarca(marca);
                producto.setProdLocacion(loc);
                
                producto = producto.agrProducto(producto);
                
                if (producto.getError() != null) {
                    error = producto.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);                
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                request.getSession().setAttribute("opcion", "E");
                this.VeaPagina("/app/lproductos.jsp", request, response);
            }
            
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("act")) {    
            
            
            String error = null;
            
            try {
                Integer prod = Integer.parseInt(request.getParameter("producto"));
                String nombre = request.getParameter("nombre");
                Integer marca = Integer.parseInt(request.getParameter("marca"));
                Integer umedida = Integer.parseInt(request.getParameter("umedida"));
                Float precio = Float.valueOf(request.getParameter("precio"));
                Integer loc = Integer.parseInt(request.getParameter("locacion"));
                Float existencia = Float.valueOf(request.getParameter("existencia"));
                
                Producto producto = new Producto();
                producto.setProdProducto(prod);
                producto.setProdExistencia(existencia);
                producto.setProdPrecio(precio);
                producto.setProdNombre(nombre);
                producto.setProdUmedida(umedida);
                producto.setProdMarca(marca);
                producto.setProdLocacion(loc);
                
                producto = producto.actProducto(producto);
                
                if (producto.getError() != null) {
                    error = producto.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lproductos.jsp", request, response);
            }
            
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
