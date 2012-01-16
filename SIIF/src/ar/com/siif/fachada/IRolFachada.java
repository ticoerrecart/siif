package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.exception.AccesoDenegadoException;
import ar.com.siif.negocio.exception.NegocioException;

public interface IRolFachada {

	public List<Rol> getRoles();

	public Rol getRol(Long id);

	public List<ItemMenu> recuperarMenues();

	public void altaRol(Rol rol, List<ItemMenu> menues) throws NegocioException;

	public Rol recuperarRol(long idRol);

	public void modificacionRol(Rol rol, List<ItemMenu> menues) throws NegocioException;

	public boolean existeRol(Rol rol);
	
	public void verificarMenu(String pNombreMenu,Rol pRol)throws AccesoDenegadoException;
}
