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
import tiendita.beans.Persona;

/**
 *
 * @author miguel
 */
public class ServPersona extends HttpServlet {
   
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agr")) {
            String error = null;
            try {
                
                String nombre = request.getParameter("nombre").trim();
                String apepat = request.getParameter("apepat").trim();
                String apemat = request.getParameter("apemat").trim();
                String correo = request.getParameter("correo").trim();
                String direccion = request.getParameter("direccion").trim();
                
                Persona p = new Persona();
                p.setPersNombre(nombre);
                p.setPersApepat(apepat);
                p.setPersApemat(apemat);
                p.setPersCorreo(correo);
                p.setPersDireccion(direccion);
                
                p = p.agrPersona(p);
                
                if (p.getError() != null) {
                    error = p.getError();
                }
                                                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lpersona.jsp", request, response);
            }
            
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("act")) {
            
            String error = null;
            try {
                Integer pers = Integer.parseInt(request.getParameter("persona"));
                String nombre = request.getParameter("nombre").trim();
                String apepat = request.getParameter("apepat").trim();
                String apemat = request.getParameter("apemat").trim();
                String correo = request.getParameter("correo").trim();
                String direccion = request.getParameter("direccion").trim();
                
                Persona p = new Persona();
                p.setPersPersona(pers);
                p.setPersNombre(nombre);
                p.setPersApepat(apepat);
                p.setPersApemat(apemat);
                p.setPersCorreo(correo);
                p.setPersDireccion(direccion);
                
                p = p.actPersona(p);
                
                if (p.getError() != null) {
                    error = p.getError();
                }
                                                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lpersona.jsp", request, response);
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
