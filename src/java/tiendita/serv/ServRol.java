/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tiendita.serv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import tiendita.beans.Rol;

/**
 *
 * @author miguel
 */
public class ServRol extends HttpServlet {
     
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agr")) {
            String error = null;
            try {
                
                String nombre = request.getParameter("nombre").trim();
                Rol rol = new Rol();
                rol.setRoleNombre(nombre);
                rol = rol.agrRol(rol);
               
                if (rol.getError() != null) {
                    error = rol.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error == null) {                
                this.VeaPagina("/app/lrol.jsp", request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            }            
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("act")) { 
            String error = null;
            try {
                Integer rl = Integer.parseInt(request.getParameter("rol"));
                String nombre = request.getParameter("nombre").trim();
                
                Rol rol = new Rol();
                rol.setRoleRol(rl);
                rol.setRoleNombre(nombre);
                rol = rol.actRol(rol);
               
                if (rol.getError() != null) {
                    error = rol.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error == null) {                
                this.VeaPagina("/app/lrol.jsp", request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
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
