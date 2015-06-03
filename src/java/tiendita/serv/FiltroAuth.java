/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita.serv;

/**
 *
 * @author miguel
 */
import java.io.Serializable;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.CallableStatement;
import javax.servlet.*;
import javax.servlet.http.*;
import tiendita.beans.Usuario;
import tiendita.beans.Rol;
import tiendita.util.CifraMD5;



public class FiltroAuth implements Filter {
    
    private FilterConfig filterConfig = null;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        response.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Cache-control:", "no-cache,no-store,must-revalidate");
        resp.setHeader("Pragma:","no.cache");

        if (req.getSession().getAttribute("USUA_AUTH") == null && req.getSession().getAttribute("USUA_ROL") == null) {
           if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("auth")) { 
             if (request.getParameter("j_username") != null && request.getParameter("j_password") != null) {
                 
                 String usuario = req.getParameter("j_username").toUpperCase().trim();
                 String password = CifraMD5.hashPassword(req.getParameter("j_password").trim());
                  
                 Usuario usua = new Usuario();
                 List<Usuario> lu = usua.setCondicion(" Where usua_usuario = '"+usuario+"' And usua_passwd = '"+password+"'  ");
                 
                 if (lu.size() > 0) {
                 
                     Rol rol = new Rol();
                     List<Rol> roles = rol.setCondicion( " Where rol_rol in (Select rlus_rol From RolUsuario Where rlus_usuario = '"+usuario+"' ) ");

                     if (roles.size() > 0) {                         
                         req.getSession().setAttribute("USUA_AUTH", lu.get(0));
                         req.getSession().setAttribute("USUA_ROL", roles);      
                         chain.doFilter(request, response);
                     } else {
                         resp.sendRedirect("/tiendita/login.jsp?error=2");
                     }
                                          
                 } else {
                     resp.sendRedirect("/tiendita/login.jsp?error=1");
                 }
                 
             } else {
               resp.sendRedirect("/tiendita/login.jsp?error=1");
             }   
           } else {
               resp.sendRedirect("/tiendita/login.jsp");
           } 
        } else {
            chain.doFilter(request, response);
        }
        
    }
    
    @Override
    public void destroy() { 
    }

    @Override
    public void init(FilterConfig filterConfig) { 
	  this.filterConfig = filterConfig;
    }
    
}
