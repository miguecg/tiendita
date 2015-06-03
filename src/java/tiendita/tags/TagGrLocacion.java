/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita.tags;

/**
 *
 * @author miguel
 */
import java.io.Serializable;
import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.http.HttpServletRequest;
import tiendita.beans.Locacion;


public class TagGrLocacion extends BodyTagSupport implements Serializable {
    
    @Override
    public int doStartTag() throws JspException {
        
         JspWriter out = pageContext.getOut();
         HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
         String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : "";
         
         try {
             
             out.println("<table style=\"width:100%\">");
            
             out.println("<tr>");
               out.println("<th class=\"headerTable\" style=\"width:100px\">&nbsp;</th>");
               out.println("<th class=\"headerTable\">Id Locaci&oacute;n</th>");
               out.println("<th class=\"headerTable\">Descripci&oacute;n</th>");
               out.println("<th class=\"headerTable\">Posici&oacute;n</th>");
             out.println("</tr>");
             Locacion loc = new Locacion();
             List<Locacion> l = loc.setCondicion(condicion);
             
             int i = 0;
             String estilo = null;
             for (Locacion ll : l) {
                 
                 if (i % 2 > 0) {
                     estilo = "renglonDos";
                 } else {
                     estilo = "renglonUno";
                 }
                 
                 out.println("<tr id=\"ren-"+i+"\" class=\""+estilo+"\">");
                   out.println("<td><input type=\"button\" class=\"bt\" value=\"Editar\" onclick=\"cf('/tiendita/app/locacion.jsp?locacion="+ll.getLocaLocacion()+"');\"/></td>");
                   out.println("<td id=\"col-"+i+"-1\">"+ll.getLocaLocacion()+"</td>");
                   out.println("<td id=\"col-"+i+"-2\">"+ll.getLocaDescrip()+"</td>");
                   out.println("<td id=\"col-"+i+"-3\">"+ll.getLocaPosicion()+"</td>");
                 out.println("</tr>");
              i++;   
             }
             
            out.println("</table>");
            
             
         } catch (IOException e) {
             throw new JspException(e);
         }
        
        
        
     return SKIP_BODY;   
    }
    
}
