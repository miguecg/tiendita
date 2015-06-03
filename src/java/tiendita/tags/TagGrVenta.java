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


public class TagGrVenta extends BodyTagSupport implements Serializable {
    
    @Override
    public int doStartTag() throws JspException {
        
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : "";
        String operacion = request.getSession().getAttribute("operacion") != null ? String.valueOf(request.getSession().getAttribute("operacion")) : ""; 
        
        try {
            
            out.println("<table style=\"width:100%\">");
            
             out.println("<tr>");
               out.println("<th class=\"headerTable\">Id Venta</th>");
               out.println("<th class=\"headerTable\">Pedido</th>");
               out.println("<th class=\"headerTable\">Total</th>");
               out.println("<th class=\"headerTable\">Fecha</th>");
               out.println("<th class=\"headerTable\">Usuario</th>");
               out.println("<th class=\"headerTable\">Estatus</th>");
             out.println("</tr>");
             Venta vent = new Venta();
             List<Venta> v = vent.setCondicion(condicion);
             
             int i = 0;
             String estilo = null;
             for (Venta lv : v) { 
                 
                 if (i % 2 > 0) {
                     estilo = "renglonDos";
                 } else {
                     estilo = "renglonUno";
                 }
                 
                 out.println("<tr id=\"ren-"+i+"\" class=\""+estilo+"\" "+operacion+">");
                   out.println("<td id=\"col-"+i+"-1\">"+lv.getVentVenta()+"</td>");
                   out.println("<td id=\"col-"+i+"-2\">"+lv.getVentPedido()+"</td>");
                   out.println("<td id=\"col-"+i+"-3\">"+lv.getVentTotal()+"</td>");
                   out.println("<td id=\"col-"+i+"-4\">"+lv.getVentFecha()+"</td>");
                   out.println("<td id=\"col-"+i+"-5\">"+lv.getVentUsuario()+"</td>");
                   out.println("<td id=\"col-"+i+"-5\">"+lv.getVentEstatus()+"</td>");
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
