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

public class TagSelRol extends BodyTagSupport implements Serializable {

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String usuario = request.getParameter("usuario") != null ? request.getParameter("usuario") : null;
        String q = usuario != null ? " Where rol_rol not in (Select rlus_rol From RolUsuario Where rlus_usuario = '" + usuario + "') " : " Order by 1 ";

        try {

            Rol rol = new Rol();
            List<Rol> lr = rol.setCondicion(q);


            out.println("<table style=\"width:100%;border:0px\">");
            out.println("<tr>");
            out.println("<th class=\"headerTable\">&nbsp;</th>");
            out.println("<th class=\"headerTable\">Rol</th>");
            out.println("</tr>");

            int i = 0;
            String estilo = null;

            for (Rol r : lr) {

                if (i % 2 > 0) {
                    estilo = "renglonDos";
                } else {
                    estilo = "renglonUno";
                }

                out.println("<tr id=\"r-" + r.getRoleRol() + "\" class=\"" + estilo + "\">");
                out.println("<td style=\"width:100px\"><input class=\"bt\" type=\"button\" value=\"Seleccionar\" onclick=\"agRol('" + usuario + "','" + r.getRoleRol() + "');\"/></td>");
                out.println("<td>" + r.getRoleNombre() + "</td>");
                out.println("</tr>");
                i++;
            }

            out.println("</table>");

            out.println("<div id=\"selUsua\">");

            if (usuario != null) {
                rol = new Rol();
                lr = rol.setCondicion(" Where rol_rol in (Select rlus_rol From RolUsuario Where rlus_usuario = '" + usuario + "') ");
                out.println("<div class=\"headerTable\" style=\"margin-top:10px\">Roles Asignados al usuario " + usuario + "</div>");
                i = 0;
                for (Rol r : lr) {

                    if (i % 2 > 0) {
                        estilo = "renglonDos";
                    } else {
                        estilo = "renglonUno";
                    }
                    out.println("<div id=\"d-" + r.getRoleRol() + "\" class=\"" + estilo + "\">" + r.getRoleNombre() + " <a href=\"javascript:;\" onclick=\"borRol('" + usuario + "','" + r.getRoleRol() + "');\">[X]</a></div>");
                    i++;
                }
            }

            out.println("</div>");
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
