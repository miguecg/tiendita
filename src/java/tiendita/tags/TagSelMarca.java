/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita.tags;

/**
 *
 * @author miguel
 */
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import tiendita.beans.Marca;
        

public class TagSelMarca extends BodyTagSupport implements Serializable {
    
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
        Marca marc = new Marca();
        List<Marca> lista = marc.setCondicion("Order by 1");
        
        try {
            
            out.println("<select name=\""+nombre+"\">");
            
            if (this.select == null || (this.select != null && this.select.equals(""))) {
                out.println("<option value=\"\">Seleccione</option>");
            }
            
            for (Marca mr : lista) {
                if (this.select != null && !this.select.equals("") && this.select.equals(mr.getMarcMarca().toString())) {
                  out.println("<option value=\""+mr.getMarcMarca().intValue()+"\" selected>"+mr.getMarcDescrip()+"</option> ");
                } else {
                  out.println("<option value=\""+mr.getMarcMarca().intValue()+"\">"+mr.getMarcDescrip()+"</option> ");
                } 
            }
            
            out.println("</select>");
            
        } catch (IOException e) {
            throw new JspException(e);       
        }
        
      return SKIP_BODY;  
    }
    
}
