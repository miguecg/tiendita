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
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.http.HttpServletRequest;
import tiendita.beans.Producto;
import tiendita.beans.Unidad;
import tiendita.util.CifraMD5;


public class TagUproducto extends BodyTagSupport implements Serializable {

    
    
    
    @Override
    public int doStartTag() throws JspException {
        
        
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Integer idProd = Integer.parseInt(request.getParameter("producto"));
                       
        try {
            
            Producto pro = new Producto();
            pro = pro.setId(idProd);
            
            String md = CifraMD5.hashAleatorio().substring(5);
            
            out.println("<div id=\""+md+"\">");            
             out.println("<table style=\"width:100%;border:1px solid azure\">");
              out.println("<tr>");
                out.println("<td style=\"width:10%;background-color:azure;color:#333\">");
                
                 if (pro.getProdUmedida().toString().equals("0")) {
                   out.println("<select id=\"c-"+md+"\" name=\"cant-"+md+"\" size=\"1\" onchange=\"calTotal('"+md+"')\">");  
                   int j = 1;
                     for (int i = 0; i < pro.getProdExistencia().intValue(); i++) { 
                         out.println("<option value=\""+j+"\">"+j+"</option>");
                      j++;   
                     }
                   out.println("</select>");  
                 } else {
                     out.println("<input id=\"c-"+md+"\" onkeypress=\"decod('"+md+"');\" value=\"1\" type=\"text\" size=\"3\" name=\"cant-"+md+"\" maxlength=\""+(pro.getProdExistencia().toString().length())+"\"/>");
                 }
                
                 Unidad uni = new Unidad();
                 uni = uni.setId(pro.getProdUmedida());
                 out.println(uni.getUmedSiglas());
                 
                out.println("</td>");
                out.println("<td style=\"background-color:azure;color:#333;width:70%\">");
                out.println(pro.getProdNombre());
                 out.println("<input id=\"p-"+md+"\" type=\"hidden\" name=\"prod-"+md+"\" value=\""+pro.getProdProducto().toString()+"\"/>");
                out.println("</td>");
                
                out.println("<td style=\"background-color:azure;color:#333;width:10%\">$ ");
                out.println("<span id=\"t-"+md+"\">"+pro.getProdPrecio()+"</span>");
                out.println("<input id=\"v-"+md+"\" type=\"hidden\" name=\"vt-"+md+"\" value=\""+pro.getProdPrecio()+"\" />");
                out.println("</td>");
                out.println("<td style=\"background-color:azure;color:#333;width:10%\">");
                out.println("<input type=\"button\" class=\"bt\" value=\"X\" onclick=\"eliminarDelPedido('"+md+"');\" />");
                out.println("</td>");
                

              out.println("</tr>");
             out.println("</table>");
            out.println("</div>");
            
        } catch (IOException e) {
            throw new JspException(e);
        }
        
     return SKIP_BODY;   
    }
    
    
    
    
}
