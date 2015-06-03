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
import tiendita.beans.Unidad;
        


public class TagSelUnidad extends BodyTagSupport implements Serializable {
    
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
            
            out.println("<select name=\""+nombre+"\" size=\"1\">");
            
             if (select == null || (select != null && select.equals(""))) {
                 out.println("<option value=\"\">Elija</option>");
             }
             
             Unidad uni = new Unidad();
             List<Unidad> lu = uni.setCondicion("Order by 1");
            
             for (Unidad unidad : lu) {
                 if (select != null && !select.equals("") && unidad.getUmedUmedida().toString().equals(select)) {
                     out.println("<option value=\""+unidad.getUmedUmedida()+"\" selected>"+unidad.getUmedSiglas()+"</option>");
                 } else {
                     out.println("<option value=\""+unidad.getUmedUmedida()+"\">"+unidad.getUmedSiglas()+"</option>");
                 }
             }
            
            out.println("</select>");
            
        } catch (IOException e) {
            throw new JspException(e);
        }
        
      return SKIP_BODY;  
    }
    
    
    
}
