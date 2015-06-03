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
import tiendita.beans.Usuario;

public class TagGrUsuario extends BodyTagSupport implements Serializable {

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String condicion = request.getSession().getAttribute("condicion") != null ? String.valueOf(request.getSession().getAttribute("condicion")) : "Order by 1";

        try {

            out.println("<table style=\"width:100%;border:0px\">");

            out.println("<tr>");
            out.println("<th class=\"headerTable\" style=\"width:100px\">&nbsp;</th>");
            out.println("<th class=\"headerTable\" style=\"width:100px\">&nbsp;</th>");
            out.println("<th class=\"headerTable\" style=\"width:100px\">&nbsp;</th>");
            out.println("<th class=\"headerTable\">Usuario</th>");
            out.println("<th class=\"headerTable\">Estatus</th>");
            out.println("</tr>");

            Usuario us = new Usuario();
            List<Usuario> lu = us.setCondicion(condicion);

            int i = 0;
            String estilo = "";

            for (Usuario u : lu) {

                if (i % 2 > 0) {
                    estilo = "renglonDos";
                } else {
                    estilo = "renglonUno";
                }

                out.println("<tr class=\"" + estilo + "\">");
                out.println("<td><input type=\"button\" class=\"bt\" value=\"Editar\" onclick=\"cf('/tiendita/app/usuario.jsp?usuario=" + u.getUsuaUsuario() + "');\"/> </td>");
                out.println("<td><input type=\"button\" class=\"bt\" value=\"Cambiar contrase&ntilde;a\" onclick=\"cf('/tiendita/app/password.jsp?usuario=" + u.getUsuaUsuario() + "');\"/> </td>");
                out.println("<td><input type=\"button\" class=\"bt\" value=\"Agregar Rol\" onclick=\"cf('/tiendita/app/rol_usuario.jsp?usuario=" + u.getUsuaUsuario() + "');\"/> </td>");
                out.println("<td>" + u.getUsuaUsuario() + "</td>");

                out.println("<td>");

                if (u.getUsuaEstatus().equals("A")) {
                    out.println("Activo");
                } else {
                    out.println("No activo");
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
