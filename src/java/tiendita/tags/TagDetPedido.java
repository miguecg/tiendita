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
import tiendita.beans.PedidoProducto;
import tiendita.beans.Producto;
import tiendita.beans.Marca;

public class TagDetPedido extends BodyTagSupport implements Serializable {

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Integer idp = Integer.decode(request.getParameter("pedido"));
        PedidoProducto pedp = new PedidoProducto();
        List<PedidoProducto> lp = pedp.setCondicion(" Where prpe_pedido = '" + idp.toString() + "' ");


        try {

            out.println("<table style=\"width:100%;border:0px\">");
            out.println("<tr>");
            out.println("<th class=\"headerTable\">Cantidad</th>");
            out.println("<th class=\"headerTable\">Nombre del producto</th>");
            out.println("<th class=\"headerTable\">Total</th>");
            out.println("</tr>");

            int i = 0;
            String estilo = "";
            Float granTotal = 0.0f;
            for (PedidoProducto pp : lp) {
                if (i % 2 > 0) {
                    estilo = "renglonDos";
                } else {
                    estilo = "renglonUno";
                }

                out.println("<tr class=\"" + estilo + "\">");
                out.println("<td>" + pp.getPrpeCantidad().toString() + "</td>");
                out.println("<td>");

                Producto p = new Producto();
                p = p.setId(pp.getPrpeProducto());
                Marca m = new Marca();
                m = m.setId(p.getProdMarca());
                out.println(p.getProdNombre() + " " + m.getMarcDescrip());

                out.println("</td>");

                out.println("<td>");

                Float total = pp.getPrpeCantidad() * p.getProdPrecio();

                out.println("$ " + total);

                out.println("</td>");

                out.println("</tr>");
            }


            out.println("</table>");
            out.println("<div style=\"text-align:right;font-size:14px;font-weight:bold;color:#333;background-color:azure\">$" + granTotal + "</div>");


        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
