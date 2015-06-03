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
import tiendita.beans.Persona;

public class TagGrPersona extends BodyTagSupport implements Serializable {

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : " Order by 1 ";

        try {
            Persona per = new Persona();
            List<Persona> lp = per.setCondicion(condicion);

            out.println("<table style=\"width:100%;border:0px\">");
            out.println("<tr>");
            out.println("<th class=\"headerTable\">&nbsp;</th>");
            out.println("<th class=\"headerTable\">Nombre</th>");
            out.println("<th class=\"headerTable\">Primer Apellido</th>");
            out.println("<th class=\"headerTable\">Segundo Apellido</th>");
            out.println("<th class=\"headerTable\">Correo electr&oacute;nico</th>");
            out.println("<th class=\"headerTable\">Direcci&oacute;n</th>");
            out.println("</tr>");

            int i = 0;
            String estilo = "";

            for (Persona p : lp) {

                if (i % 2 > 0) {
                    estilo = "renglonDos";
                } else {
                    estilo = "renglonUno";
                }

                out.println("<tr class=\"" + estilo + "\">");
                out.println("<td><input type=\"button\" class=\"bt\" value=\"Editar\" onclick=\"cf('/tiendita/app/persona.jsp?persona=" + p.getPersPersona() + "');\" /></td>");
                out.println("<td>" + p.getPersNombre() + "</td>");
                out.println("<td>" + p.getPersApepat() + "</td>");
                out.println("<td>" + p.getPersApemat() + "</td>");
                out.println("<td>" + p.getPersCorreo() + "</td>");
                out.println("<td>" + p.getPersDireccion() + "</td>");
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
