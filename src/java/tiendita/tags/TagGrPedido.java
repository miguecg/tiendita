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
import tiendita.beans.Pedido;


public class TagGrPedido extends BodyTagSupport implements Serializable {
    
    @Override
    public int doStartTag() throws JspException {
        
        
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : " Order by 1 desc ";
        String operacion = request.getSession().getAttribute("operacion") != null ? String.valueOf(request.getSession().getAttribute("operacion")) : ""; 
        
        try {
            
            out.println("<table style=\"width:100%\">");
            
             out.println("<tr>");
               out.println("<th class=\"headerTable\" style=\"width:100px\">&nbsp;</th>");
               out.println("<th class=\"headerTable\">Id Pedido</th>");
               out.println("<th class=\"headerTable\">Fecha</th>");
               out.println("<th class=\"headerTable\">Usuario</th>");
               out.println("<th class=\"headerTable\">Estatus</th>");
               out.println("<th class=\"headerTable\">F. Cancel</th>");
             out.println("</tr>");
             Pedido ped = new Pedido();
             List<Pedido> p = ped.setCondicion(condicion);
             
             int i = 0;
             String estilo = null;
             for (Pedido lp : p) {
                 
                 if (i % 2 > 0) {
                     estilo = "renglonDos";
                 } else {
                     estilo = "renglonUno";
                 }
                 
                 out.println("<tr id=\"ren-"+i+"\" class=\""+estilo+"\">");
                   
                   out.println("<td><input type=\"button\" class=\"bt\" value=\"Consultar\" onclick=\"cf('/tiendita/app/pedido_producto.jsp?pedido="+lp.getPediPedido()+"');\"/></td>");
                   
                   out.println("<td id=\"col-"+i+"-1\">"+lp.getPediPedido()+"</td>");
                   out.println("<td id=\"col-"+i+"-2\">"+lp.getPediFecha()+"</td>");
                   out.println("<td id=\"col-"+i+"-3\">"+lp.getPediUsuario()+"</td>");
                   out.println("<td id=\"col-"+i+"-4\">");
                      if (lp.getPediEstatus().equals("C")) {
                          out.println("Cancelado");
                      } else if (lp.getPediEstatus().equals("L")) {
                          out.println("No saldado");
                      } else if (lp.getPediEstatus().equals("S")) {
                          out.println("Saldado");
                      }
                   out.println("</td>");
                   
                   out.println("<td id=\"col-"+i+"-5\">");
                      if (lp.getPediFechaCancel() != null) {
                          out.println(lp.getPediFechaCancel());
                      }
                   out.println("</td>");
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
