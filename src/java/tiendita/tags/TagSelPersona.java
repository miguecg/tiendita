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
import tiendita.beans.Persona;


public class TagSelPersona extends BodyTagSupport implements Serializable {
    
    private String nombre;
    private String select;
    
    public void setNombre(String nombre) {
        this.nombre = nombre;        
    }
    
    public void setSelect(String select) {
        this.select = select;
    }
    
    
    @Override
    public int doStartTag() throws JspException {
        
        JspWriter out = pageContext.getOut();
        
        try {
            
            out.println("<div style=\"height:200px;overflow:auto\">");
            
              out.println("<table style=\"width:100%;border:0px\">");
              
                out.println("<tr>");
                 out.println("<th class=\"headerTable\">&nbsp;</th>");
                 out.println("<th class=\"headerTable\">Id Persona</th>");
                 out.println("<th class=\"headerTable\">Nombre</th>");
                out.println("</tr>");
                
                Persona pers = new Persona();
                List<Persona> personas = pers.setCondicion(" Order by 1 ");
                
                int i = 0;
                String estilo = "";
                
                for (Persona p : personas) {                
                    if (i % 2 > 0) {
                        estilo = "renglonDos";
                    } else {
                        estilo = "renglonUno";
                    }
                    
                    out.println("<tr class=\""+estilo+"\">");
                    
                    out.println("<td style=\"text-align:left\">");
                    if ((select != null && !select.equals(""))  && p.getPersPersona().toString().equals(select)) {
                      out.println("<input type=\"radio\" name=\""+nombre+"\" value=\""+p.getPersPersona()+"\" checked />");                      
                    } else {
                      out.println("<input type=\"radio\" name=\""+nombre+"\" value=\""+p.getPersPersona()+"\"/>");  
                    }   
                    out.println("</td>");
                    out.println("<td style=\"text-align:left\">"+p.getPersPersona().toString()+"</td>");
                    out.println("<td style=\"text-align:left\">"+p.getPersNombre()+" "+p.getPersApepat()+" "+p.getPersApemat()+"</td>");
                    out.println("</tr>");

                }
                
              out.println("</table>");
            
            
            out.println("</div>");
            
        } catch (IOException e) {
            throw new JspException(e);
        }
        
        
        
     return SKIP_BODY;   
    }
    
    
}
