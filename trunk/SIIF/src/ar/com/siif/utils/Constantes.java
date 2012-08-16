package ar.com.siif.utils;

public class Constantes {

	public static final int COLECCION_BOLETAS_DEPOSITO_MAX = 10;

	public static final int COLECCION_VALES_TRANSPORTE_MAX = 10;

	public static final String USER_LABEL_SESSION = "usuario";

	public static final Long ID_ROL_ADMINISTRADOR = 2L;

	// FISCALIZACION
		public static final String EXITO_ALTA_FISCALIZACION = "La Fiscalizaci�n se ha dado de alta con exito";			
	
		public static final String EXITO_MODIFICACION_FISCALIZACION = "La Fiscalizaci�n se ha modificado con exito";
		
		public static final String ERROR_ALTA_FISCALIZACION = "Ha ocurrido un error en el alta de la fiscalizaci�n";		
		
		public static final String ERROR_MODIFICACION_FISCALIZACION = "Ha ocurrido un error en la modificaci�n de la fiscalizaci�n";
		
		public static final String ERROR_RECUPERAR_FISCALIZACION = "Ha ocurrido un error en la recuperaci�n de la fiscalizaci�n";
		
		
	// FIN FISCALIZACION
	
	// GUIA FORESTAL
		public static final String METODO_RECUPERAR_GUIAS_PAGO_BOLETA_DEPOSITO = "recuperarGuiasForestalesPagoBoletaDeposito";
		
		public static final String METODO_CARGAR_GUIA_PAGO_BOLETA_DEPOSITO = "cargarGuiaForestalPagoBoletaDeposito";
		
		public static final String METODO_REGISTRAR_PAGO_BOLETA_DEPOSITO = "registrarPagoBoletaDeposito";
		
		public static final String ERROR_REEMPLAZAR_BOLETA_DEPOSITO = "Ha ocurrido un error en el reemplazo de la boleta de dep�sito";
		
		public static final String ERROR_REEMPLAZAR_BOLETA_DEPOSITO_NRO_EXISTENTE = "El n�mero de boleta de deposito ya existe, por favor especifique otro";
		
		public static final String ERROR_REEMPLAZAR_VALE_TRANSPORTE = "Ha ocurrido un error en el reemplazo del vale de transporte";
		
		public static final String ERROR_REEMPLAZAR_VALE_TRANSPORTE_NRO_EXISTENTE = "El n�mero de vale de transporte ya existe, por favor especifique otro";		
		
		public static final String METODO_CARGAR_GUIA_DEVOLUCION_VALE = "cargarGuiaForestalDevolucionValeTransporte";
		
		public static final String NO_EXISTE_GUIA = "Gu�a Forestal no encontrada";
		
		public static final String EXITO_MODIFICACION_GUIA_FORESTAL = "La Gu�a Forestal se ha modificado con exito";
	// FIN GUIA FORESTAL
	
	// ENTIDAD
		public static final String TITULO_ALTA_ENTIDAD = "Alta de Entidad";
	
		public static final String TITULO_MODIFICACION_ENTIDAD = "Modificaci�n de Entidad";
	
		public static final String EXITO_MODIFICACION_ENTIDAD = "La Entidad se ha modificado con exito";
	
		public static final String EXITO_ALTA_ENTIDAD = "La Entidad se ha dado de alta con exito";
	
		public static final String EXISTE_ENTIDAD = "Ya existe una Entidad con �ste nombre";
	
		public static final String ENTIDAD_RN = "RN";
		
		public static final String ERROR_RECUPERAR_OFICINAS_FORESTALES = "Ha ocurrido un error en la recuperaci�n de las oficinas forestales";
		
		public static final String ERROR_MODIFICACION_ENTIDAD = "Ha ocurrido un error en la modificaci�n de la entidad";
		
		public static final String ERROR_RECUPERACION_ENTIDAD = "Ha ocurrido un error en la recuperaci�n de la entidad";
		
		public static final String ERROR_ALTA_ENTIDAD = "Ha ocurrido un error en el alta de la entidad";
		
		public static final String ERROR_RECUPERAR_PRODUCTORES = "Ha ocurrido un error en la recuperaci�n de los productores";
	// FIN ENTIDAD

	// LOCALIDAD
		public static final String EXISTE_LOCALIDAD = "Ya existe una Localidad con �ste nombre";
	
		public static final String EXITO_ALTA_LOCALIDAD = "La Localidad se ha dado de alta con exito";
	
		public static final String EXITO_MODIFICACION_LOCALIDAD = "La Localidad se ha modificado con exito";
	// FIN LOCALIDAD

	// USUARIO
		public static final String TITULO_ALTA_USUARIO = "Alta de Usuario";
	
		public static final String TITULO_MODIFICACION_USUARIO = "Modificaci�n de Usuario";//solo para cuando no es Administrador
	
		public static final String EXISTE_USUARIO = "Ya existe un Usuario con �ste nombre";
	
		public static final String EXITO_ALTA_USUARIO = "El Usuario se ha dado de alta con exito";
	
		public static final String EXITO_MODIFICACION_USUARIO = "El Usuario se ha modificado con exito";
		
		public static final String ERROR_ALTA_USUARIO = "Ha ocurrido un error en el alta del usuario";
		
		public static final String ERROR_MODIFICACION_USUARIO = "Ha ocurrido un error en la modificaci�n del usuario";
		
		public static final String ERROR_RECUPERACION_USUARIOS = "Ha ocurrido un error en la recuperaci�n de los usuarios";
		
		public static final String ERROR_RECUPERACION_USUARIO = "Ha ocurrido un error en la recuperaci�n del usuario";		
	// FIN USUARIO

	//TIPO PRODUCTO FORESTAL
		public static final String EXISTE_TIPO_PRODUCTO = "Ya existe un Tipo de Producto Forestal con ese nombre";
	
		public static final String EXITO_ALTA_TIPO_PRODUCTO = "El Tipo de Producto Forestal se ha dado de alta con exito";
	
		public static final String EXITO_MODIFICACION_TIPO_PRODUCTO = "El Tipo de Producto Forestal se ha modificado con exito";
	// FIN TIPO PRODUCTO FORESTAL

	//AFORO
		public static final String EXISTE_AFORO = "Ya existe un Aforo con esas descripciones";
	
		public static final String EXITO_ALTA_AFORO = "El Aforo se ha dado de alta con exito";
	
		public static final String EXITO_MODIFICACION_AFORO = "El Aforo se ha modificado con exito";
		
		public static final String ERROR_ALTA_AFORO = "Ha ocurrido un error en el alta del Aforo";
		
		public static final String ERROR_RECUPERACION_AFORO = "Ha ocurrido un error en la recuperaci�n del Aforo";
		
		public static final String ERROR_MODIFICACION_AFORO = "Ha ocurrido un error en la modificaci�n del Aforo";
	// FIN AFORO

	//ROL
		public static final String ADMINISTRADOR = "Administrador";
		
		public static final String EXISTE_ROL = "Ya existe un Rol con ese nombre";
	
		public static final String EXITO_ALTA_ROL = "El Rol se ha dado de alta con exito";
	
		public static final String EXITO_MODIFICACION_ROL = "El Rol se ha modificado con exito";
		
		public static final String ERROR_ALTA_ROL = "Ha ocurrido un error en el alta del rol";
		
		public static final String ERROR_MODIFICACION_ROL = "Ha ocurrido un error en la modificaci�n del rol";
		
		public static final String ERROR_RECUPERACION_ROLES = "Ha ocurrido un error en la recuperaci�n de los roles";
		
		public static final String ERROR_RECUPERACION_ROL = "Ha ocurrido un error en la recuperaci�n del rol";
		
		public static final String ERROR_RECUPERACION_MENUES = "Ha ocurrido un error en la recuperaci�n de los menues";
		
		public static final String ERROR_RECUPERACION_MENU = "Ha ocurrido un error en la recuperaci�n del men�";
	// FIN ROL	
	
	//UBICACION
		
		public static final String ERROR_EXISTE_PMF = "Ya Existe un Plan de Manejo Forestal con el nombre: ";
		
		public static final String ERROR_EXISTE_TRANZON = "Ya Existe un Tranzon con el n�mero: ";
		
		public static final String ERROR_EXISTE_MARCACION = "Ya Existe una Marcaci�n con la disposici�n: ";
		
		public static final String ERROR_EXISTE_RODAL = "Ya Existe un Rodal con el nombre: ";
		
		public static final String ERROR_ALTA_PMF = "Ha ocurrido un error en el alta del Plan de Manejo Forestal";
		
		public static final String ERROR_ALTA_TRANZON = "Ha ocurrido un error en el alta del Tranz�n";
		
		public static final String ERROR_ALTA_MARCACION = "Ha ocurrido un error en el alta de la Marcaci�n";
		
		public static final String ERROR_ALTA_RODAL = "Ha ocurrido un error en el alta del Rodal";
		
	//FIN UBICACION		
		
	//CONSULTAS GUIA FORESTAL (POR PRODUCTOR)
		public static final String TITULO_CONSULTA_GUIAS_FORESTALES_VIGENTES = "Consulta de Gu�as Forestales Vigentes";
		
		public static final String TITULO_CONSULTA_GUIAS_FORESTALES_NO_VIGENTES = "Consulta de Gu�as Forestales No Vigentes";
		
		public static final String TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_AFORO = "Consulta de Gu�as Forestales Con Deuda de Aforo";
		
		public static final String TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_VALE_TRANSPORTE = "Consulta de Gu�as Forestales Con Deuda de Vale de Transporte";
		
		public static final String METODO_RECUPERAR_GUIAS_VIGENTES = "recuperarGuiasForestalesVigentes";
		
		public static final String METODO_RECUPERAR_GUIAS_NO_VIGENTES = "recuperarGuiasForestalesNoVigentes";	
		
		public static final String METODO_RECUPERAR_GUIAS_CON_DEUDAS_AFORO = "recuperarGuiasForestalesConDeudasAforo";
		
		public static final String METODO_RECUPERAR_GUIAS_CON_DEUDAS_VALE_TRANSPORTE = "recuperarGuiasForestalesConDeudasValeTransporte";
	//FIN CONSULTA GUIA FORESTAL (POR PRODUCTOR)
		
	//CONSULTAS FISCALIZACIONES
		public static final String METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL = "recuperarFiscalizacionesConGuiaForestal";
		
		public static final String METODO_RECUPERAR_FISCALIZACIONES_SIN_GUIA_FORESTAL = "recuperarFiscalizacionesSinGuiaForestal";		
		
		public static final String TITULO_CONSULTA_FISCALIZACIONES_CON_GUIA_FORESTAL = "Consulta de Fiscalizaciones con Guia Forestal";
		
		public static final String TITULO_CONSULTA_FISCALIZACIONES_SIN_GUIA_FORESTAL = "Consulta de Fiscalizaciones sin Guia Forestal";
		
		public static final String ERROR_RECUPERAR_FISCALIZACIONES = "Ha ocurrido un error en la recuperaci�n de las fiscalizaciones";
	//FIN CONSULTAS FISCALIZACIONES
		
	//REPORTES
		public static final String TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS = "Volumen Fiscalizado Por Productor entre Fechas";
		
		public static final String TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTO_FORESTAL_ENTRE_FECHAS = "Volumen Fiscalizado Por Producto Forestal entre Fechas";
	//FIN REPORTES	
		
	//MENU
		public static final String ALTA_FISCALIZACION_MENU = "Alta de Fiscalizaci�n de Productos Forestales";
		
		public static final String MODIFICACION_FISCALIZACION_MENU = "Modificaci�n de Fiscalizaci�n de Productos Forestales";
		
		public static final String ALTA_GUIA_FORESTAL_MENU = "Alta de Gu�a Forestal B�sica";
		
		public static final String MODIFICACION_GUIA_FORESTAL_MENU = "Modificaci�n de Gu�a Forestal B�sica";
		
		public static final String REGISTRAR_PAGO_BOLETA_MENU = "Registrar Pago Boleta de Deposito";
		
		public static final String REEMPLAZAR_BOLETA_MENU = "Reemplazar Boleta de Deposito";
		
		public static final String DEVOLUCION_VALE_MENU = "Devoluci�n Vale de Transporte";		
		
		public static final String REEMPLAZAR_VALE_MENU = "Reemplazar Vale de Transporte";	
		
		public static final String CONSULTA_FISCALIZACIONES_CON_GUIA_MENU = "Fiscalizacion de Productos Forestales con Guia";
		
		public static final String CONSULTA_FISCALIZACIONES_SIN_GUIA_MENU = "Fiscalizacion de Productos Forestales sin Guia";
		
		public static final String CONSULTA_GUIA_VIGENTE_MENU = "Gu�as Forestales Vigentes";
		
		public static final String CONSULTA_GUIA_NO_VIGENTE_MENU = "Gu�as Forestales No Vigentes";
		
		public static final String CONSULTA_DEUDA_AFORO_MENU = "Deudas de Aforo";
		
		public static final String CONSULTA_DEUDA_VALE_MENU = "Deudas Vales de Transporte";
		
		public static final String REPORTE_VOL_FISC_PROD_FECHAS_MENU = "Volumen Fiscalizado Por Productor entre Fechas";
		
		public static final String ALTA_TIPO_PROD_FORESTAL_MENU = "Alta Tipo de Producto Forestal";
		
		public static final String MODIFICACION_TIPO_PROD_FORESTAL_MENU = "Modificacion Tipo de Producto Forestal";
		
		public static final String ALTA_ENTIDAD_MENU = "Alta de Entidad";
		
		public static final String MODIFICACION_ENTIDAD_MENU = "Modificaci�n de Entidad";
		
		public static final String ALTA_AFORO_MENU = "Alta de Aforo";	
		
		public static final String MODIFICACION_AFORO_MENU = "Modificaci�n de Aforo";
		
		public static final String ALTA_PMF_MENU = "Alta Plan de Manejo Forestal";
		
		public static final String MODIFICACION_PMF_MENU = "Modificaci�n Plan de Manejo Forestal";
		
		public static final String ALTA_LOCALIDAD_MENU = "Alta de Localidad";		
		
		public static final String MODIFICACION_LOCALIDAD_MENU = "Modificaci�n de Localidad";
		
		public static final String ALTA_ROL_MENU = "Alta de Rol";
		
		public static final String MODIFICACION_ROL_MENU = "Modificaci�n de Rol";
		
		public static final String ALTA_USUARIO_MENU = "Alta de Usuario";
		
		public static final String MODIFICACION_USUARIO_MENU = "Modificaci�n de Usuario";
	//FIN MENU


}
