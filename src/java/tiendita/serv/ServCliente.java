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
import tiendita.beans.Cliente;
import tiendita.beans.Persona;
/**
 *
 * @author miguel
 */
public class ServCliente extends HttpServlet {
   
   

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agr")) {
            
            String error = null;
            
            try {
                
                Integer pers = Integer.parseInt(request.getParameter("persona"));
                String razoc = request.getParameter("rasonzoc").trim();
                String rfc = request.getParameter("rfc").toUpperCase().trim();
                
                Cliente c = new Cliente();
                c.setCliePersona(pers);
                c.setClieRazonSoc(razoc);
                c.setClieRfc(rfc);
                
                c = c.agrCliente(c);
                
                if (c.getError() != null) {
                    error = c.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lclientes.jsp", request, response);
            }
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agr")) {  
            
             String error = null;
            
            try {
                Integer clie = Integer.parseInt(request.getParameter("cliente"));
                Integer pers = Integer.parseInt(request.getParameter("persona"));
                String razoc = request.getParameter("rasonzoc").trim();
                String rfc = request.getParameter("rfc").toUpperCase().trim();
                
                Cliente c = new Cliente();
                c.setClieCliente(clie);
                c.setCliePersona(pers);
                c.setClieRazonSoc(razoc);
                c.setClieRfc(rfc);
                
                c = c.actCliente(c);
                
                if (c.getError() != null) {
                    error = c.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lclientes.jsp", request, response);
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
