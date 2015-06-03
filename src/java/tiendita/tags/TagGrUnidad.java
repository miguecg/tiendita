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
import tiendita.beans.Unidad;


public class TagGrUnidad extends BodyTagSupport implements Serializable {
    
    @Override
    public int doStartTag() throws JspException {
    
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : "";
        
        try {
            
            out.println("<table style=\"width:100%\">");
            
             out.println("<tr>");
               out.println("<th class=\"headerTable\" style=\"width:100px\">&nbsp;</th>");
               out.println("<th class=\"headerTable\">Id Unidad</th>");
               out.println("<th class=\"headerTable\">Siglas</th>");
               out.println("<th class=\"headerTable\">Descripci&oacute;n</th>");
             out.println("</tr>");
             Unidad uni = new Unidad();
             List<Unidad> u = uni.setCondicion(condicion);
             
             int i = 0;
             String estilo = null;
             for (Unidad ln : u) {
                 
                 if (i % 2 > 0) {
                     estilo = "renglonDos";
                 } else {
                     estilo = "renglonUno";
                 }
                 
                 out.println("<tr id=\"ren-"+i+"\" class=\""+estilo+"\">");
                   out.println("<td><input type=\"button\" value=\"Editar\" class=\"bt\" onclick=\"cf('/tiendita/app/unidad.jsp?unidad="+ln.getUmedUmedida()+"');\"/> </td>");
                   out.println("<td id=\"col-"+i+"-1\">"+ln.getUmedUmedida()+"</td>");
                   out.println("<td id=\"col-"+i+"-2\">"+ln.getUmedSiglas()+"</td>");
                   out.println("<td id=\"col-"+i+"-3\">"+ln.getUmedDescrip()+"</td>");
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
