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
import tiendita.beans.Producto;
import tiendita.beans.Marca;
import tiendita.beans.Locacion;


public class TagGrProductos extends BodyTagSupport implements Serializable {
    
    @Override
    public int doStartTag() throws JspException {
        
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : "";
        String opcion = request.getSession().getAttribute("opcion") != null ? String.valueOf(request.getSession().getAttribute("opcion")) : "E";
        
        
        try {
            
            out.println("<table style=\"width:100%\">");
            
              out.println("<tr>");
               out.println("<th class=\"headerTable\" style=\"width:100px\">&nbsp;</th>");
               out.println("<th class=\"headerTable\">Id Producto</th>");
               out.println("<th class=\"headerTable\">Nombre</th>");
               out.println("<th class=\"headerTable\">Marca</th>");
               out.println("<th class=\"headerTable\">Precio</th>");
               out.println("<th class=\"headerTable\">U. medida</th>");
               out.println("<th class=\"headerTable\">Existencia</th>");
               out.println("<th class=\"headerTable\">Locacion</th>");
              out.println("</tr>");
              
              Producto pr = new Producto();
              List<Producto> lp = pr.setCondicion(condicion);
              
              int i = 0;
              String estilo = null;
              for (Producto p : lp) {
                  
                  if (i % 2 > 0) {
                      estilo = "renglonDos";
                  } else {
                      estilo = "renglonUno";
                  }
                  
                out.println("<tr id=\"ren-"+i+"\" class=\""+estilo+"\">");
                
                out.println("<td>");
                  if (opcion != null && opcion.equals("C")) {
                    out.println("<input type=\"button\" value=\"+\" onclick=\"ap('/tiendita/app/uproducto.jsp?producto="+p.getProdProducto()+"');br('ren-"+i+"');\" class=\"bt\"/>");  
                  } else {
                    out.println("<input type=\"button\" value=\"Editar\" onclick=\"cf('/tiendita/app/producto.jsp?producto="+p.getProdProducto()+"');\" class=\"bt\"/>");
                  }  
                out.println("</td>");  
                    out.println("<td id=\"col-"+i+"-1\">"+p.getProdProducto()+"</td>");
                    out.println("<td id=\"col-"+i+"-2\">"+p.getProdNombre()+"</td>");
                    
                    Marca marc = new Marca();
                    marc = marc.setId(p.getProdMarca()); 
                    
                    out.println("<td id=\"col-"+i+"-3\">"+marc.getMarcDescrip()+"</td>");
                    out.println("<td id=\"col-"+i+"-4\">"+p.getProdPrecio()+"</td>");
                    out.println("<td id=\"col-"+i+"-5\">"+p.getProdUmedida()+"</td>");
                    out.println("<td id=\"col-"+i+"-6\">"+p.getProdExistencia()+"</td>");
                    
                    Locacion loc = new Locacion();
                    loc = loc.setId(p.getProdLocacion());
                    
                    out.println("<td id=\"col-"+i+"-7\">"+loc.getLocaPosicion()+" "+loc.getLocaDescrip()+"</td>");
                    
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
