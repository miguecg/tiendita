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
import tiendita.beans.Usuario;
import tiendita.util.CifraMD5;
import conector.Dao;
/**
 *
 * @author miguel
 */
public class ServUsuario extends HttpServlet {
   
  

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agr")) {
            
            String error = null;
            
            try {
                
                String usuario = request.getParameter("usuario").toUpperCase().trim();
                Integer persona = Integer.parseInt(request.getParameter("persona"));
                String password = request.getParameter("password");
                String estatus = request.getParameter("estatus");
                
                Usuario usu = new Usuario();
                usu.setUsuaUsuario(usuario);
                usu.setUsuaPassword(CifraMD5.hashPassword(password));
                usu.setUsuaPersona(persona);
                usu.setUsuaEstatus(estatus);
                
                usu = usu.agrUsuario(usu);
                
                if (usu.getError() != null) {
                    error = usu.getError();
                }
                                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lusuario.jsp", request, response);
            }
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("act")) {
            String error = null;
            
            try {
                
                String usuario = request.getParameter("usuario").toUpperCase().trim();
                Integer persona = Integer.parseInt(request.getParameter("persona"));                
                String estatus = request.getParameter("estatus");
                
                Usuario usu = new Usuario();
                usu.setUsuaUsuario(usuario);
                usu.setUsuaPersona(persona);
                usu.setUsuaEstatus(estatus);
                
                usu = usu.agrUsuario(usu);
                
                if (usu.getError() != null) {
                    error = usu.getError();
                }
                                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lusuario.jsp", request, response);
            }
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agrRol")) {    
            
            String error = null;
            
            try {
                
                Integer rol = Integer.parseInt(request.getParameter("rol"));
                String usuario = request.getParameter("usuario");
                
                Usuario usu = new Usuario();
                usu.agrRolUsuario(usuario, rol);
                
                if (usu.getError() != null) {
                    error = usu.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            }                      
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("borRol")) {        
            String error = null;
            
            try {
                
                Integer rol = Integer.parseInt(request.getParameter("rol"));
                String usuario = request.getParameter("usuario");
                
                Usuario usu = new Usuario();
                usu.borRolUsuario(usuario, rol);
                
                if (usu.getError() != null) {
                    error = usu.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } 
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("cambPass")) {            
            
            String error = null;
            Dao db = new Dao();
            try {
                String usuario = request.getParameter("usuario");
                String password = CifraMD5.hashPassword(request.getParameter("password"));
                
                Usuario.cambiarPasswd(usuario, password, db);
                
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lusuario.jsp", request, response);
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
