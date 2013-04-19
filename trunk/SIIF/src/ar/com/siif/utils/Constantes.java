package ar.com.siif.utils;

public class Constantes {

	public static final int COLECCION_BOLETAS_DEPOSITO_MAX = 10;

	public static final int COLECCION_VALES_TRANSPORTE_MAX = 10;

	public static final String USER_LABEL_SESSION = "usuario";

	public static final Long ID_ROL_ADMINISTRADOR = 2L;

	// FISCALIZACION
	public static final String EXITO_ALTA_FISCALIZACION = "La Fiscalización se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_FISCALIZACION = "La Fiscalización se ha modificado con exito";

	public static final String ERROR_ALTA_FISCALIZACION = "Ha ocurrido un error en el alta de la fiscalización";

	public static final String ERROR_MODIFICACION_FISCALIZACION = "Ha ocurrido un error en la modificación de la fiscalización";

	public static final String ERROR_RECUPERAR_FISCALIZACION = "Ha ocurrido un error en la recuperación de la fiscalización";

	public static final String TITULO_MODIFICACION_FISCALIZACION = "Modificación de Fiscalización de Productos Forestales ";

	public static final String TITULO_ANULACION_FISCALIZACION = "Anulación de Fiscalización de Productos Forestales ";

	// FIN FISCALIZACION

	// GUIA FORESTAL
	public static final String METODO_RECUPERAR_GUIAS_PAGO_BOLETA_DEPOSITO = "recuperarGuiasForestalesPagoBoletaDeposito";

	public static final String METODO_CARGAR_GUIA_PAGO_BOLETA_DEPOSITO = "cargarGuiaForestalPagoBoletaDeposito";

	public static final String METODO_REGISTRAR_PAGO_BOLETA_DEPOSITO = "registrarPagoBoletaDeposito";

	public static final String ERROR_REEMPLAZAR_BOLETA_DEPOSITO = "Ha ocurrido un error en el reemplazo de la boleta de depósito";

	public static final String ERROR_PAGO_BOLETA_DEPOSITO = "Ha ocurrido un error en el pago de la boleta de depósito";

	public static final String ERROR_REEMPLAZAR_BOLETA_DEPOSITO_NRO_EXISTENTE = "El número de boleta de deposito ya existe, por favor especifique otro";

	public static final String ERROR_REEMPLAZAR_VALE_TRANSPORTE = "Ha ocurrido un error en el reemplazo del vale de transporte";

	public static final String ERROR_REEMPLAZAR_VALE_TRANSPORTE_NRO_EXISTENTE = "El número de vale de transporte ya existe, por favor especifique otro";

	public static final String ERROR_DEVOLUCION_VALE_TRANSPORTE = "Ha ocurrido un error en la devolución del vale de transporte";

	public static final String METODO_CARGAR_GUIA_DEVOLUCION_VALE = "cargarGuiaForestalDevolucionValeTransporte";

	public static final String NO_EXISTE_GUIA = "Guía Forestal no encontrada";
	
	public static final String GUIA_ANULADA = "Guía Forestal Anulada";

	public static final String NRO_GUIA_EXISTENTE = "El número de Guía Forestal ya existe, especifique otro";

	public static final String EXITO_MODIFICACION_GUIA_FORESTAL = "La Guía Forestal se ha modificado con exito";

	public static final String EXITO_ANULACION_GUIA_FORESTAL = "La Guía Forestal se ha anulado con exito";
	
	public static final String EXITO_ALTA_GUIA_FORESTAL = "La Guía Forestal se ha dado de alta con exito";

	public static final String ERROR_RECUPERAR_GUIA_FORESTAL = "Ha ocurrido un error en la recuperación de la guía forestal";

	public static final String ERROR_RECUPERAR_GUIAS_FORESTALES = "Ha ocurrido un error en la recuperación de la guías forestales";

	public static final String ERROR_ALTA_GUIA_FORESTAL = "Ha ocurrido un error en el alta de la guía forestal";

	public static final String ERROR_VERIFICAR_BOLETAS_DEPOSITO_VENCIDAS_IMPAGAS = "Ha ocurrido un error al verificar las boletas de deposito vencidas e impagas";
	
	// FIN GUIA FORESTAL

	// ENTIDAD
	public static final String TITULO_ALTA_ENTIDAD = "Alta de Entidad";

	public static final String TITULO_MODIFICACION_ENTIDAD = "Modificación de Entidad";

	public static final String EXITO_MODIFICACION_ENTIDAD = "La Entidad se ha modificado con exito";

	public static final String EXITO_ALTA_ENTIDAD = "La Entidad se ha dado de alta con exito";

	public static final String EXISTE_ENTIDAD = "Ya existe una Entidad con éste nombre";
	
	public static final String EXISTE_ENTIDAD_CON_MATRICULA = "Ya existe una Entidad con éste nro de matrícula";

	public static final String ENTIDAD_RN = "RN";

	public static final String ERROR_RECUPERAR_OFICINAS_FORESTALES = "Ha ocurrido un error en la recuperación de las oficinas forestales";

	public static final String ERROR_MODIFICACION_ENTIDAD = "Ha ocurrido un error en la modificación de la entidad";

	public static final String ERROR_RECUPERACION_ENTIDAD = "Ha ocurrido un error en la recuperación de la entidad";

	public static final String ERROR_ALTA_ENTIDAD = "Ha ocurrido un error en el alta de la entidad";

	public static final String ERROR_RECUPERAR_PRODUCTORES = "Ha ocurrido un error en la recuperación de los productores";

	// FIN ENTIDAD

	// LOCALIDAD
	public static final String EXISTE_LOCALIDAD = "Ya existe una Localidad con éste nombre";
	
	public static final String EXISTE_LOCALIDAD_DESTINO = "Ya existe una Localidad con éste nombre para esta Provincia";
	
	public static final String EXISTE_PROVINCIA = "Ya existe una Provincia con éste nombre";

	public static final String EXITO_ALTA_LOCALIDAD = "La Localidad se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_LOCALIDAD = "La Localidad se ha modificado con exito";
	
	public static final String EXITO_MODIFICACION_PROVINCIA = "La Provincia se ha modificado con exito";
	
	public static final String EXITO_ALTA_PROVINCIA = "La Provincia se ha dado de alta con exito";
	
	public static final String ERROR_ALTA_PROVINCIA = "Ha ocurrido un error en el alta de la provincia";

	public static final String ERROR_ALTA_LOCALIDAD = "Ha ocurrido un error en el alta de la localidad";

	public static final String ERROR_RECUPERACION_LOCALIDAD = "Ha ocurrido un error en la recuperación de la localidad";

	public static final String ERROR_RECUPERACION_LOCALIDADES = "Ha ocurrido un error en la recuperación de las localidades";
	
	public static final String ERROR_RECUPERACION_PROVINCIAS = "Ha ocurrido un error en la recuperación de las provincias";
	
	public static final String ERROR_RECUPERACION_PROVINCIA = "Ha ocurrido un error en la recuperación de la provincia";

	// FIN LOCALIDAD

	// PERIODO
	public static final String EXISTE_PERIODO = "Ya existe un período con éste nombre";

	public static final String EXITO_ALTA_PERIODO = "El período se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_PERIODO = "El período se ha modificado con exito";

	public static final String ERROR_ALTA_PERIODO = "Ha ocurrido un error en el alta del período";

	public static final String ERROR_RECUPERACION_PERIODO = "Ha ocurrido un error en la recuperación del período";

	public static final String ERROR_RECUPERACION_PERIODOS = "Ha ocurrido un error en la recuperación de los períodos";

	// FIN PERIODO		

	// USUARIO
	public static final String TITULO_ALTA_USUARIO = "Alta de Usuario";

	public static final String TITULO_MODIFICACION_USUARIO = "Modificación de Usuario";//solo para cuando no es Administrador

	public static final String EXISTE_USUARIO = "Ya existe un Usuario con éste nombre";

	public static final String USUARIO_INVALIDO = "Usuario y/o Contraseña invalido";

	public static final String EXITO_ALTA_USUARIO = "El Usuario se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_USUARIO = "El Usuario se ha modificado con exito";

	public static final String ERROR_ALTA_USUARIO = "Ha ocurrido un error en el alta del usuario";

	public static final String ERROR_MODIFICACION_USUARIO = "Ha ocurrido un error en la modificación del usuario";

	public static final String ERROR_RECUPERACION_USUARIOS = "Ha ocurrido un error en la recuperación de los usuarios";

	public static final String ERROR_RECUPERACION_USUARIO = "Ha ocurrido un error en la recuperación del usuario";

	public static final String ERROR_LOGIN_USUARIO = "Ha ocurrido un error en el login del usuario";

	// FIN USUARIO

	//TIPO PRODUCTO FORESTAL
	public static final String EXISTE_TIPO_PRODUCTO = "Ya existe un Tipo de Producto Forestal con ese nombre";
	
	public static final String EXISTE_TIPO_PRODUCTO_EXPORTACION = "Ya existe un Tipo de Producto de Exportación con ese nombre";

	public static final String EXITO_ALTA_TIPO_PRODUCTO = "El Tipo de Producto Forestal se ha dado de alta con exito";
	
	public static final String EXITO_ALTA_TIPO_PRODUCTO_EXPORTACION = "El Tipo de Producto de Exportación se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_TIPO_PRODUCTO = "El Tipo de Producto se ha modificado con exito";	

	public static final String ERROR_ALTA_TIPO_PRODUCTO = "Ha ocurrido un error en el alta del tipo de producto";
	
	public static final String ERROR_ALTA_TIPO_PRODUCTO_EXPORTACION = "Ha ocurrido un error en el alta del Tipo de Producto de Exportación";

	public static final String ERROR_MODIFICACION_TIPO_PRODUCTO = "Ha ocurrido un error en la modificación del tipo de producto";

	public static final String ERROR_RECUPERACION_TIPOS_PRODUCTOS = "Ha ocurrido un error en la recuperacion del los tipos de producto";

	public static final String ERROR_RECUPERACION_TIPO_PRODUCTO = "Ha ocurrido un error en la recuperacion del tipo de producto";

	// FIN TIPO PRODUCTO FORESTAL

	//AFORO
	public static final String EXISTE_AFORO = "Ya existe un Aforo con esas descripciones";

	public static final String EXITO_ALTA_AFORO = "El Aforo se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_AFORO = "El Aforo se ha modificado con exito";

	public static final String ERROR_ALTA_AFORO = "Ha ocurrido un error en el alta del Aforo";

	public static final String ERROR_RECUPERACION_AFORO = "Ha ocurrido un error en la recuperación del Aforo";

	public static final String ERROR_MODIFICACION_AFORO = "Ha ocurrido un error en la modificación del Aforo";

	// FIN AFORO

	//ROL
	public static final String ADMINISTRADOR = "Administrador";

	public static final String EXISTE_ROL = "Ya existe un Rol con ese nombre";

	public static final String EXITO_ALTA_ROL = "El Rol se ha dado de alta con exito";

	public static final String EXITO_MODIFICACION_ROL = "El Rol se ha modificado con exito";

	public static final String ERROR_ALTA_ROL = "Ha ocurrido un error en el alta del rol";

	public static final String ERROR_MODIFICACION_ROL = "Ha ocurrido un error en la modificación del rol";

	public static final String ERROR_RECUPERACION_ROLES = "Ha ocurrido un error en la recuperación de los roles";

	public static final String ERROR_RECUPERACION_ROL = "Ha ocurrido un error en la recuperación del rol";

	public static final String ERROR_RECUPERACION_MENUES = "Ha ocurrido un error en la recuperación de los menues";

	public static final String ERROR_RECUPERACION_MENU = "Ha ocurrido un error en la recuperación del menú";

	// FIN ROL	

	//UBICACION

	public static final String ERROR_EXISTE_PMF = "Ya Existe un Plan de Manejo Forestal con el nombre: ";

	public static final String ERROR_EXISTE_AREA = "Ya Existe un Area de Cosecha con el nombre: ";
	
	public static final String ERROR_EXISTE_TRANZON = "Ya Existe un Tranzon con el número: ";

	public static final String ERROR_EXISTE_MARCACION = "Ya Existe una Marcación con la disposición: ";

	public static final String ERROR_EXISTE_RODAL = "Ya Existe un Rodal con el nombre: ";

	public static final String ERROR_ALTA_PMF = "Ha ocurrido un error en el alta del Plan de Manejo Forestal";

	public static final String ERROR_ALTA_TRANZON = "Ha ocurrido un error en el alta del Tranzón";

	public static final String ERROR_ALTA_MARCACION = "Ha ocurrido un error en el alta de la Marcación";

	public static final String ERROR_ALTA_RODAL = "Ha ocurrido un error en el alta del Rodal";

	public static final String ERROR_RECUPERACION_RODALES = "Ha ocurrido un error en la recuperación de los Rodales";

	public static final String ERROR_RECUPERACION_MARCACIONES = "Ha ocurrido un error en la recuperación de los Marcaciones";

	public static final String ERROR_RECUPERACION_TRANZONES = "Ha ocurrido un error en la recuperación de los Tranzones";

	public static final String ERROR_RECUPERACION_PMFS = "Ha ocurrido un error en la recuperación de los Planes de Manejo Forestal";

	//FIN UBICACION		

	//CONSULTAS GUIA FORESTAL (POR PRODUCTOR)
	public static final String TITULO_CONSULTA_GUIAS_FORESTALES_VIGENTES = "Consulta de Guías Forestales Vigentes";

	public static final String TITULO_CONSULTA_GUIAS_FORESTALES_NO_VIGENTES = "Consulta de Guías Forestales No Vigentes";

	public static final String TITULO_CONSULTA_GUIAS_FORESTALES_ANULADAS = "Consulta de Guías Forestales Anuladas";
	
	public static final String TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_AFORO = "Consulta de Guías Forestales Con Deuda de Aforo";

	public static final String TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_VALE_TRANSPORTE = "Consulta de Guías Forestales Con Deuda de Vale de Transporte";

	public static final String METODO_RECUPERAR_GUIAS_VIGENTES = "recuperarGuiasForestalesVigentes";

	public static final String METODO_RECUPERAR_GUIAS_NO_VIGENTES = "recuperarGuiasForestalesNoVigentes";
	
	public static final String METODO_RECUPERAR_GUIAS_ANULADAS = "recuperarGuiasForestalesAnuladas";

	public static final String METODO_RECUPERAR_GUIAS_CON_DEUDAS_AFORO = "recuperarGuiasForestalesConDeudasAforo";

	public static final String METODO_RECUPERAR_GUIAS_CON_DEUDAS_VALE_TRANSPORTE = "recuperarGuiasForestalesConDeudasValeTransporte";

	//FIN CONSULTA GUIA FORESTAL (POR PRODUCTOR)

	//CONSULTAS FISCALIZACIONES
	public static final String METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL = "recuperarFiscalizacionesConGuiaForestal";

	public static final String METODO_RECUPERAR_FISCALIZACIONES_SIN_GUIA_FORESTAL = "recuperarFiscalizacionesSinGuiaForestal";

	public static final String TITULO_CONSULTA_FISCALIZACIONES_CON_GUIA_FORESTAL = "Consulta de Fiscalizaciones con Guia Forestal";

	public static final String TITULO_CONSULTA_FISCALIZACIONES_SIN_GUIA_FORESTAL = "Consulta de Fiscalizaciones sin Guia Forestal";

	public static final String ERROR_RECUPERAR_FISCALIZACIONES = "Ha ocurrido un error en la recuperación de las fiscalizaciones";

	//FIN CONSULTAS FISCALIZACIONES

	//REPORTES
	public static final String TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS = "Volumen Fiscalizado Por Productor entre Fechas";

	public static final String TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTO_FORESTAL_ENTRE_FECHAS = "Volumen Fiscalizado Por Producto Forestal entre Fechas";

	public static final String TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_TOTAL = "Volumen Fiscalizado Total";

	public static final String ERROR_GENERACION_REPORTE = "Ha ocurrido un error en la generación del reporte";
	
	public static final String REPORTE_FISCALIZACION = "fiscalizacion";
	
	public static final String REPORTE_GUIA_FORESTAL = "guiaForestal";
	
	public static final String REPORTE_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_POR_PRODUCTO = "volumenFiscalizadoPorProductorPorProductos";
	
	public static final String REPORTE_VOLUMEN_FISCALIZADO_TOTAL_POR_PRODUCTOR = "volumenFiscalizadoTotalPorProductor";
	
	public static final String REPORTE_VOLUMEN_GFB_MONTOS_PAGOS = "volumenGFBMontosPagos";
	
	public static final String REPORTE_VOLUMEN_GFB_MONTOS_ADEUDADOS = "volumenGFBMontosAdeudados";

	public static final String REPORTE_LISTADO_BOLETAS_DEPOSITO_TOTALES = "listadoBoletasDepositoTotales";
	
	public static final String REPORTE_LISTADO_BOLETAS_DEPOSITO_PAGAS = "listadoBoletasDepositoPagas";
	
	public static final String REPORTE_LISTADO_BOLETAS_DEPOSITO_IMPAGAS = "listadoBoletasDepositoImpagas";
	
	public static final String REPORTE_LISTADO_VALES_TRANSPORTE_DEVUELTOS = "listadoValesTransporteDevueltos";
	
	public static final String REPORTE_LISTADO_VALES_TRANSPORTE_EN_USO = "listadoValesTransporteEnUso";
	
	public static final String REPORTE_LISTADO_VALES_TRANSPORTE_TOTALES = "listadoValesTransporteTotales";
	
	public static final String VOLUMEN_POR_PRODUCTOR_POR_UBICACION = "volumenPorProductorPorUbicacion";
	
	public static final String VOLUMEN_FISCALIZADO_POR_PRODUCTO_POR_ANIO_FORESTAL = "volumenFiscalizadoPorProductoPorAnioForestal";
	
	public static final String VOLUMEN_POR_PRODUCTO_POR_PRODUCTOR_ENTRE_FECHAS = "volumenPorProductoPorProductorEntreFechas";
	
	public static final String VOLUMEN_POR_PRODUCTO_POR_PRODUCTOR_POR_UBICACION = "volumenPorProductoPorProductorPorUbicacion";
	
	public static final String REPORTE_RECAUDACION_POR_ANIO_FORESTAL_POR_PRODUCTOR = "reporteRecaudacionPorAnioForestalPorProductor";
	
	public static final String REPORTE_RECAUDACION_POR_ANIO_FORESTAL_TOTAL_PRODUCTORES = "reporteRecaudacionPorAnioForestalTotalProductores";
	
	public static final String REPORTE_RECAUDACION_POR_PRODUCTOR_ENTRE_FECHAS = "reporteRecaudacionPorProductorEntreFechas";
	
	public static final String REPORTE_RECAUDACION_POR_PRODUCTOR_POR_ANIO_FORESTAL = "reporteRecaudacionPorProductorPorAnioForestal";
	
	public static final String REPORTE_RECAUDACION_POR_PRODUCTOR_POR_UBICACION = "reporteRecaudacionPorProductorPorUbicacion";
	
	public static final String REPORTE_RECAUDACION_TOTAL_PRODUCTORES_ENTRE_FECHAS = "reporteRecaudacionTotalProductoresEntreFechas";
	
	public static final String REPORTE_CERTIFICADO_ORIGEN = "reporteCertificadoOrigen";
	
	public static final String REPORTE_DETALLE_GUIAS_ENTRE_FECHAS = "reporteDetalleGuiasEntreFechas";
	
	public static final String REPORTE_GUIAS_FORESTALES_POR_PRODUCTOR_ENTRE_FECHAS = "reporteGuiasForestalesPorProductorEntreFechas";
	
	//FIN REPORTES	

	//MENU
	public static final String ALTA_FISCALIZACION_MENU = "Alta de Fiscalización de Productos Forestales";

	public static final String MODIFICACION_FISCALIZACION_MENU = "Modificación de Fiscalización de Productos Forestales";

	public static final String ALTA_GUIA_FORESTAL_MENU = "Alta de Guía Forestal Básica";

	public static final String MODIFICACION_GUIA_FORESTAL_MENU = "Modificación de Guía Forestal Básica";

	public static final String REGISTRAR_PAGO_BOLETA_MENU = "Registrar Pago Boleta de Deposito";

	public static final String REEMPLAZAR_BOLETA_MENU = "Reemplazar Boleta de Deposito";

	public static final String DEVOLUCION_VALE_MENU = "Devolución Vale de Transporte";

	public static final String REEMPLAZAR_VALE_MENU = "Reemplazar Vale de Transporte";

	public static final String CONSULTA_FISCALIZACIONES_CON_GUIA_MENU = "Fiscalizacion de Productos Forestales con Guia";

	public static final String CONSULTA_FISCALIZACIONES_SIN_GUIA_MENU = "Fiscalizacion de Productos Forestales sin Guia";

	public static final String CONSULTA_GUIA_VIGENTE_MENU = "Guías Forestales Vigentes";

	public static final String CONSULTA_GUIA_NO_VIGENTE_MENU = "Guías Forestales No Vigentes";

	public static final String CONSULTA_DEUDA_AFORO_MENU = "Deudas de Aforo";

	public static final String CONSULTA_DEUDA_VALE_MENU = "Deudas Vales de Transporte";

	public static final String REPORTE_VOL_FISC_PROD_FECHAS_MENU = "Volumen Fiscalizado Por Productor entre Fechas";

	public static final String ALTA_TIPO_PROD_FORESTAL_MENU = "Alta Tipo de Producto Forestal";

	public static final String MODIFICACION_TIPO_PROD_FORESTAL_MENU = "Modificacion Tipo de Producto Forestal";

	public static final String ALTA_ENTIDAD_MENU = "Alta de Entidad";

	public static final String MODIFICACION_ENTIDAD_MENU = "Modificación de Entidad";

	public static final String ALTA_AFORO_MENU = "Alta de Aforo";

	public static final String MODIFICACION_AFORO_MENU = "Modificación de Aforo";

	public static final String ALTA_PMF_MENU = "Alta Plan de Manejo Forestal";

	public static final String MODIFICACION_PMF_MENU = "Modificación Plan de Manejo Forestal";

	public static final String ALTA_LOCALIDAD_MENU = "Alta de Localidad";

	public static final String MODIFICACION_LOCALIDAD_MENU = "Modificación de Localidad";

	public static final String ALTA_ROL_MENU = "Alta de Rol";

	public static final String MODIFICACION_ROL_MENU = "Modificación de Rol";

	public static final String ALTA_USUARIO_MENU = "Alta de Usuario";

	public static final String MODIFICACION_USUARIO_MENU = "Modificación de Usuario";

	//FIN MENU

	public static final Long LENIA_ID = 3L;
	public static final String LENIA = "Leña";

	//diametro
	/*a. Rollizos: Largo: 2 a m7 y diámetro: 10 a 160cm
	b. Fustes: Largo 5 a 18m y diámetro : 10 a 160cm
	c. Postes: Largo 1.5 a 3m y diamtetro: 10 a 40cm
	d. Trineos: Largo 1.5 a 3m y diamtetro: 10 a 40cm*/

	public static final Integer minDiametroRollizo = 10;

	public static final Integer maxDiametroRollizo = 160;

	public static final Integer minDiametroFuste = 10;

	public static final Integer maxDiametroFuste = 160;

	public static final Integer minDiametroPoste = 10;

	public static final Integer maxDiametroPoste = 40;

	public static final Integer minDiametroTrineo = 10;

	public static final Integer maxDiametroTrineo = 40;

	//largo
	public static final Double minLargoRollizo = 2.0;

	public static final Double maxLargoRollizo = 7.0;

	public static final Double minLargoFuste = 5.0;

	public static final Double maxLargoFuste = 18.0;

	public static final Double minLargoPoste = 1.5;

	public static final Double maxLargoPoste = 3.0;

	public static final Double minLargoTrineo = 1.5;

	public static final Double maxLargoTrineo = 3.0;
	
	//CERTIFICADO ORIGEN
	public static final String EXITO_ALTA_CERTIFICADO_ORIGEN = "El Certificado de Orígen se ha dado de alta con exito";
	
	public static final String ERROR_ALTA_CERTIFICADO_ORIGEN = "Ha ocurrido un error en el alta del Certificado de Orígen";
	
	public static final String ERROR_OBTENER_VOLUMEN_EXPORTADO = "Ha ocurrido un error en la obtención del volumen exportado";
	
	public static final String ERROR_INTERNO = "Error Interno";	
	
	public static final String ERROR_OBTENER_CERTIFICADOS_ORIGEN = "Ha ocurrido un error en la recuperación de los Certificados de Orígen";
	
	public static final String ERROR_OBTENER_CERTIFICADO_ORIGEN = "Ha ocurrido un error en la recuperación del Certificado de Orígen";
	
	public static final String NO_EXISTE_CERTIFICADO = "Certificado de Orígen no encontrado";

	
}
