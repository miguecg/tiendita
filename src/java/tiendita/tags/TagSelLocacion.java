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
import tiendita.beans.Locacion;


public class TagSelLocacion extends BodyTagSupport implements Serializable {
    
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
        
            out.println("<select name=\""+nombre+"\">");
            
            if (select == null || (select != null && select.equals(""))) {
                out.println("<option value=\"\">-- Elija --</option>");
            }
            
            
            Locacion loc = new Locacion();
            List<Locacion> locc = loc.setCondicion(" Order by 1 ");
            
            for (Locacion lc : locc) {
              if (select != null && !select.equals("") && lc.getLocaLocacion().toString().equals(select)) {  
                out.println("<option value=\""+lc.getLocaLocacion()+"\" selected>Posici&oacute;n: "+lc.getLocaPosicion()+" Descripci&oacute;n: "+lc.getLocaDescrip()+"</option>");
              } else {
                out.println("<option value=\""+lc.getLocaLocacion()+"\">Posici&oacute;n: "+lc.getLocaPosicion()+" Descripci&oacute;n: "+lc.getLocaDescrip()+"</option>");  
              }  
            }
                    
            out.println("</select>");    
            
        } catch (IOException e) {
            throw new JspException(e);
        } 
        
      return SKIP_BODY;  
    }
    
    
}
