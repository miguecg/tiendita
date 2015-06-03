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
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.http.HttpServletRequest;
import tiendita.beans.Marca;


public class TagGrMarca extends BodyTagSupport implements Serializable {
    
    @Override
    public int doStartTag() throws JspException {
        
        JspWriter out = pageContext.getOut();
        Marca marca = new Marca();        
        
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : "";
                
        try {
            
            out.println("<table style=\"width:100%\">");
            
             out.println("<tr>");
               out.println("<th class=\"headerTable\" style=\"width:100px\">&nbsp;</th>");
               out.println("<th class=\"headerTable\">Id Marca</th>");
               out.println("<th class=\"headerTable\">Descripci&oacute;n</th>");
             out.println("</tr>");
            
             List<Marca> lm = marca.setCondicion(condicion);
             
             int i = 0;
             String estilo = null;
             for (Marca mr : lm) {               
                 
                 if (i % 2 > 0) {
                     estilo = "renglonDos";
                 } else {
                     estilo = "renglonUno";
                 }
                 
                 out.println("<tr id=\"ren-"+i+"\" class=\""+estilo+"\">");
                   out.println("<td><input type=\"button\" value=\"Editar\" class=\"bt\" onclick=\"cf('/tiendita/app/marca.jsp?marca="+mr.getMarcMarca()+"');\"/></td>");
                   out.println("<td id=\"col-"+i+"-1\">"+mr.getMarcMarca()+"</td>");
                   out.println("<td id=\"col-"+i+"-2\">"+mr.getMarcDescrip()+"</td>");
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
