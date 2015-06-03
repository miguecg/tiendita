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
import tiendita.beans.Venta;
import tiendita.beans.PedidoProducto;
import tiendita.beans.Marca;
import tiendita.beans.Pedido;


public class TagDesVenta extends BodyTagSupport implements Serializable {
    
    @Override
    public int doStartTag() throws JspException {
        
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : "";
        String operacion = request.getSession().getAttribute("operacion") != null ? String.valueOf(request.getSession().getAttribute("operacion")) : ""; 
        
        try {
            
            out.println("");
            
        } catch (IOException e) {
            throw new JspException(e);
        }
        
        
     return SKIP_BODY;   
    }
    
    
}
