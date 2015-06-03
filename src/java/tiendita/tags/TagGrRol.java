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
import tiendita.beans.Rol;

public class TagGrRol extends BodyTagSupport implements Serializable {

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : " Order by 1 ";

        try {

            Rol rol = new Rol();
            List<Rol> roles = rol.setCondicion(condicion);

            out.println("<table style=\"width:100%\">");
            out.println("<tr>");
            out.println("<th class=\"headerTable\">&nbsp;</th>");
            out.println("<th class=\"headerTable\">Id Rol</th>");
            out.println("<th class=\"headerTable\">Descripci&oacute;n</th>");
            out.println("</tr>");

            int i = 0;
            String estilo = "";
            for (Rol r : roles) {

                if (i % 2 > 0) {
                    estilo = "renglonDos";
                } else {
                    estilo = "renglonUno";
                }

                out.println("<tr class=\"" + estilo + "\">");
                out.println("<td>");

                out.println("<input type=\"button\" value=\"Editar\" class=\"bt\" id=\"edr\" onclick=\"cf('/tiendita/app/rol.jsp?rol=" + r.getRoleRol() + "');\"/>");

                out.println("</td>");

                out.println("<td>" + r.getRoleRol().toString() + "</td>");
                out.println("<td>" + r.getRoleNombre() + "</td>");

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
