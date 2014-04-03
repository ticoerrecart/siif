package ar.com.siif.negocio.exception;

public class AccesoDenegadoException extends Exception {

	public AccesoDenegadoException(){}
	
	public AccesoDenegadoException(String mensaje){
		super(mensaje);
	}
}
