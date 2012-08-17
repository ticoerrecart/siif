package ar.com.siif.struts.actions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.DispatchActionSupport;

import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IMenuFachada;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MenuJSCook;

public class MenuAction extends DispatchActionSupport {

	@SuppressWarnings("unchecked")
	public ActionForward getMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);

			WebApplicationContext ctx = getWebApplicationContext();
			IMenuFachada menuFachada = (IMenuFachada) ctx.getBean("menuFachada");

			String jsMenu = (String) request.getSession().getAttribute(usuario.getRol().getRol());

			if (jsMenu == null) {
				List<ItemMenu> menu = menuFachada.getItemsMenu(usuario.getRol().getRol());
				Collections.sort(menu);

				jsMenu = MenuJSCook.getJs(menu);

				request.getSession().setAttribute(usuario.getRol().getRol(), jsMenu);
			}

			List<String> rolesString = new ArrayList<String>();
			rolesString.add(usuario.getRol().getRol());
			String username = usuario.getNombreUsuario();
			String rolesStr = StringUtils.collectionToCommaDelimitedString(rolesString);

			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("ISO-8859-1");

			out.write(jsMenu + "@" + username + "@" + rolesStr + "@" + "1.0");
			out.flush();

			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("roles", rolesStr);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			mapping.findForward("error");
		}
		return null;
	}
}
