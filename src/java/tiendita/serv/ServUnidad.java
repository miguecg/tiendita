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
import tiendita.beans.Unidad;
/**
 *
 * @author miguel
 */
public class ServUnidad extends HttpServlet {
   
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agr")) {
            
            String error = null;
            
            try {
                
                String siglas = request.getParameter("siglas");
                String descrip = request.getParameter("descrip") != null ? request.getParameter("descrip") : "";
                
                Unidad uni = new Unidad();
                uni.setUmedSiglas(siglas);
                uni.setUmedDescrip(descrip);
                
                uni = uni.agrUnidad(uni);
                
                if (uni.getError() != null) {
                    error = uni.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lunidad.jsp", request, response);
            }
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("act")) {     
            String error = null;
            
            try {
                Integer unidad = Integer.parseInt(request.getParameter("unidad"));
                String siglas = request.getParameter("siglas");
                String descrip = request.getParameter("descrip") != null ? request.getParameter("descrip") : "";
                
                Unidad uni = new Unidad();
                uni.setUmedUmedida(unidad);
                uni.setUmedSiglas(siglas);
                uni.setUmedDescrip(descrip);
                
                uni = uni.actUnidad(uni);
                
                if (uni.getError() != null) {
                    error = uni.getError();
                }
                
            } catch (Exception e) {
                error = e.getMessage();
            }
            
            if (error != null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            } else {
                this.VeaPagina("/app/lunidad.jsp", request, response);
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
