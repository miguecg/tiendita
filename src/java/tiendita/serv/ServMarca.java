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
import tiendita.beans.Marca;

/**
 *
 * @author miguel
 */
public class ServMarca extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agr")) {
            
            String descrip = request.getParameter("descrip");                      
            
            Marca marc = new Marca();
            marc.setMarcDescrip(descrip);
            int ret = 0;
            String msg = null;
                           
            marc = marc.agrMarca(marc) ;
             
            if (marc.getError() != null) {
                msg = marc.getError();
            }
            
            if (msg == null) {                
                this.VeaPagina("/app/lmarca.jsp", request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+msg, request, response);
            }
            
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("act")) {
            
            String descrip = request.getParameter("descrip");            
            Integer marca = Integer.parseInt(request.getParameter("marca"));
            
            Marca marc = new Marca();
            marc.setMarcMarca(marca);
            marc.setMarcDescrip(descrip);
            int ret = 0;
            String msg = null;
                            
            marc = marc.actMarca(marc);
                
            if (marc.getError() != null) {
                msg = marc.getError();
            }   
            
            if (msg == null) {                
                this.VeaPagina("/app/lmarca.jsp", request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);                
                this.VeaPagina("/error.jsp?msg="+msg, request, response);
            }            
            
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);            
            this.VeaPagina("/error.jsp?msg=No se encontro opcion!", request, response);
        }
    }

    private void VeaPagina (String direccion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(direccion);
            dispatcher.forward(request, response);
    }
    
}
