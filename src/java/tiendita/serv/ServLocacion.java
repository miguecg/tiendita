/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tiendita.serv;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import tiendita.beans.Locacion;

/**
 *
 * @author miguel
 */
public class ServLocacion extends HttpServlet {
   
   

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agr")) {
            
            String error = null;
            
            try {
                
                Integer posicion = Integer.parseInt(request.getParameter("posicion"));
                String descrip = request.getParameter("descrip");
                
                Locacion loc = new Locacion();
                loc.setLocaDescrip(descrip);
                loc.setLocaPosicion(posicion);
                
                loc = loc.agrLocacion(loc);
                
                if (loc.getError() != null) {
                    error = loc.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/llocacion.jsp", request, response);
            }
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("act")) {    
            
            String error = null;
            
            try {
                
                Integer locacion = Integer.parseInt(request.getParameter("locacion"));
                Integer posicion = Integer.parseInt(request.getParameter("posicion"));
                String descrip = request.getParameter("descrip");
                
                Locacion loc = new Locacion();
                loc.setLocaLocacion(locacion);
                loc.setLocaDescrip(descrip);
                loc.setLocaPosicion(posicion);
                
                loc = loc.actLocacion(loc);
                
                if (loc.getError() != null) {
                    error = loc.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/llocacion.jsp", request, response);
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
