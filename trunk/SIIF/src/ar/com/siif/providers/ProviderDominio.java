package ar.com.siif.providers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.siif.dto.AforoDTO;
import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.CertificadoOrigenDTO;
import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.LocalidadDTO;
import ar.com.siif.dto.LocalidadDestinoDTO;
import ar.com.siif.dto.LocalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.OperacionFiscalizacionDTO;
import ar.com.siif.dto.OperacionGuiaForestalDTO;
import ar.com.siif.dto.PeriodoDTO;
import ar.com.siif.dto.ProvinciaDestinoDTO;
import ar.com.siif.dto.RangoDTO;
import ar.com.siif.dto.RolDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TipoProductoEnCertificadoDTO;
import ar.com.siif.dto.TipoProductoForestalDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.CertificadoOrigen;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.LocalidadDestino;
import ar.com.siif.negocio.Localizacion;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.OperacionFiscalizacion;
import ar.com.siif.negocio.OperacionGuiaForestal;
import ar.com.siif.negocio.Periodo;
import ar.com.siif.negocio.ProvinciaDestino;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.SubImporte;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.TipoProductoEnCertificado;
import ar.com.siif.negocio.TipoProductoExportacion;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.utils.Fecha;

public abstract class ProviderDominio {

	public static Fiscalizacion getFiscalizacion(
			FiscalizacionDTO fiscalizacionDTO, List<MuestraDTO> muestrasDTO,
			Localizacion localizacion, Entidad productorForestal,
			Entidad oficinaForestal, TipoProductoForestal tipoProducto,
			Usuario usuario) {

		Fiscalizacion fiscalizacion = new Fiscalizacion();

		fiscalizacion.setCantidadMts(fiscalizacionDTO.getCantidadMts());
		fiscalizacion.setCantidadUnidades(fiscalizacionDTO
				.getCantidadUnidades());
		fiscalizacion.setFecha(Fecha.stringDDMMAAAAToUtilDate(fiscalizacionDTO
				.getFecha()));
		fiscalizacion.setOficinaAlta(oficinaForestal);
		fiscalizacion.setPeriodoForestal(fiscalizacionDTO.getPeriodoForestal());
		fiscalizacion.setProductorForestal(productorForestal);
		fiscalizacion.setLocalizacion(localizacion);
		fiscalizacion.setTamanioMuestra(fiscalizacionDTO.getTamanioMuestra());
		fiscalizacion.setTipoProducto(tipoProducto);
		
		List<OperacionFiscalizacion> operaciones= new ArrayList<OperacionFiscalizacion>();
		for (OperacionFiscalizacionDTO operacionFiscalizacionDTO : fiscalizacionDTO.getOperaciones()) {
			operaciones.add(ProviderDominio.getOperacionFiscalizacion(operacionFiscalizacionDTO, fiscalizacion, usuario)); 
		}
		fiscalizacion.setOperaciones(operaciones);
		
		List<Muestra> muestras = new ArrayList<Muestra>();
		for (MuestraDTO muestraDTO : muestrasDTO) {
			muestras.add(ProviderDominio.getMuestra(muestraDTO, fiscalizacion));
		}
		fiscalizacion.setMuestra(muestras);

		return fiscalizacion;

	}

	public static OperacionFiscalizacion getOperacionFiscalizacion(
													OperacionFiscalizacionDTO operacionDTO,
													Fiscalizacion fiscalizacion,
													Usuario usuario)
	{
		OperacionFiscalizacion operacion = new OperacionFiscalizacion();
		//operacion.setFecha(Fecha.stringDDMMAAAAToUtilDate(operacionDTO.getFecha()));
		operacion.setFecha(Fecha.stringAAAAMMDDHHMMSSToDateSlash(operacionDTO.getFecha()));
		operacion.setFiscalizacion(fiscalizacion);
		//operacion.setId(operacionDTO.getId());
		operacion.setTipoOperacion(operacionDTO.getTipoOperacion());
		operacion.setUsuario(usuario);
		
		return operacion;
	}
	
	public static OperacionGuiaForestal getOperacionGuiaForestal(
													OperacionGuiaForestalDTO operacionDTO,
													GuiaForestal guiaForestal,
													Usuario usuario){
		
		OperacionGuiaForestal operacion = new OperacionGuiaForestal();
		operacion.setFecha(Fecha.stringAAAAMMDDHHMMSSToDateSlash(operacionDTO.getFecha()));
		operacion.setGuiaForestal(guiaForestal);
		operacion.setTipoOperacion(operacionDTO.getTipoOperacion());
		operacion.setUsuario(usuario);
		
		return operacion;		
	}
	
	public static Muestra getMuestra(MuestraDTO muestraDTO,
			Fiscalizacion fiscalizacion) {

		Muestra muestra = new Muestra();

		muestra.setLargo(muestraDTO.getLargo());
		muestra.setDiametro1(muestraDTO.getDiametro1());
		muestra.setDiametro2(muestraDTO.getDiametro2());
		muestra.setFiscalizacion(fiscalizacion);

		return muestra;
	}

	public static TipoProducto getTipoProductoForestal(
			TipoProductoForestalDTO tipoProductoForestalDTO) {

		TipoProductoForestal tipoProducto = new TipoProductoForestal();
		tipoProducto.setNombre(tipoProductoForestalDTO.getNombre());
		tipoProducto.setCantDiametros(tipoProductoForestalDTO
				.getCantDiametros());
		tipoProducto.setDiam1Desde(tipoProductoForestalDTO.getDiam1Desde());
		tipoProducto.setDiam1Hasta(tipoProductoForestalDTO.getDiam1Hasta());
		tipoProducto.setDiam2Desde(tipoProductoForestalDTO.getDiam2Desde());
		tipoProducto.setDiam2Hasta(tipoProductoForestalDTO.getDiam2Hasta());
		tipoProducto.setLargoDesde(tipoProductoForestalDTO.getLargoDesde());
		tipoProducto.setLargoHasta(tipoProductoForestalDTO.getLargoHasta());
		tipoProducto.setEsDeExportacion(tipoProductoForestalDTO.isEsDeExportacion());
		tipoProducto.setHabilitado(tipoProductoForestalDTO.isHabilitado());
		
		return tipoProducto;
	}

	public static TipoProducto getTipoProductoExportacion(
			TipoProductoDTO tipoProductoForestalDTO) {

		TipoProductoExportacion tipoProducto = new TipoProductoExportacion();
		tipoProducto.setNombre(tipoProductoForestalDTO.getNombre());

		return tipoProducto;
	}

	public static Entidad getEntidad(EntidadDTO entidadDTO, Localidad localidad) throws Exception {

		Entidad entidad = (Entidad) TipoDeEntidad.valueOf(entidadDTO.getTipoEntidad()).getClase().newInstance();
		entidad.setDireccion(entidadDTO.getDireccion());
		entidad.setEmail(entidadDTO.getEmail());
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setTelefono(entidadDTO.getTelefono());
		entidad.setLocalidad(localidad);
		entidad.setNroMatricula(entidadDTO.getNroMatricula());
		entidad.setCuit(entidadDTO.getCuit());
		entidad.setCuil(entidadDTO.getCuil());
		entidad.setDni(entidadDTO.getDni());
		entidad.setTipoDocumento(entidadDTO.getTipoDocumento());		
		entidad.setCodigoPostal(entidadDTO.getCodigoPostal());
		
		return entidad;
	}

	public static Entidad getEntidad(Entidad entidad, EntidadDTO entidadDTO,
			Localidad localidad) {

		entidad.setDireccion(entidadDTO.getDireccion());
		entidad.setEmail(entidadDTO.getEmail());
		entidad.setLocalidad(localidad);
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setTelefono(entidadDTO.getTelefono());
		entidad.setNroMatricula(entidadDTO.getNroMatricula());
		entidad.setCuit(entidadDTO.getCuit());
		entidad.setCuil(entidadDTO.getCuil());
		entidad.setDni(entidadDTO.getDni());
		entidad.setTipoDocumento(entidadDTO.getTipoDocumento());		
		entidad.setCodigoPostal(entidadDTO.getCodigoPostal());
		return entidad;
	}

	public static Aforo getAforo(AforoDTO aforoDTO,
			TipoProductoForestal tipoProducto) {

		Aforo aforo = new Aforo();

		aforo.setEstado(aforoDTO.getEstado());
		aforo.setTipoProducto(tipoProducto);
		aforo.setTipoProductor(aforoDTO.getTipoProductor());
		aforo.setValorAforo(aforoDTO.getValorAforo());

		return aforo;
	}

	public static Aforo getAforo(Aforo aforo, AforoDTO aforoDTO,
			TipoProductoForestal tipoProducto) {

		aforo.setEstado(aforoDTO.getEstado());
		aforo.setTipoProducto(tipoProducto);
		aforo.setTipoProductor(aforoDTO.getTipoProductor());
		aforo.setValorAforo(aforoDTO.getValorAforo());

		return aforo;
	}

	public static Localidad getLocalidad(LocalidadDTO localidadDTO) {

		Localidad localidad = new Localidad();
		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}

	public static Localidad getLocalidad(Localidad localidad,
			LocalidadDTO localidadDTO) {

		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}

	public static Periodo getPeriodo(PeriodoDTO periodoDTO) {

		Periodo periodo = new Periodo();
		periodo.setPeriodo(periodoDTO.getPeriodo());
		return periodo;
	}

	public static Periodo getPeriodo(Periodo periodo, PeriodoDTO periodoDTO) {
		periodo.setPeriodo(periodoDTO.getPeriodo());
		return periodo;
	}

	public static Usuario getUsuario(UsuarioDTO usuarioDTO, Entidad entidad,
			Rol rol) {

		Usuario usuario = new Usuario();
		usuario.setEntidad(entidad);
		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);

		return usuario;
	}

	public static Usuario getUsuario(Usuario usuario, UsuarioDTO usuarioDTO,
			Entidad entidad, Rol rol) {

		usuario.setEntidad(entidad);
		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);

		return usuario;
	}

	public static Rol getRol(RolDTO rolDTO) {

		Rol rol = new Rol();
		rol.setRol(rolDTO.getRol());
		rol.setMenues(new ArrayList<ItemMenu>());

		return rol;
	}

	public static GuiaForestal getGuiaForestal(GuiaForestalDTO guiaDTO,
									List<BoletaDepositoDTO> listaBoletaDepositoDTO,
									List<RangoDTO> listaRangosDTO, Date fechaVencimiento,
									List<Fiscalizacion> listaFiscalizaciones,
									List<SubImporte> listaSubImportes, Entidad productorForestal,
									Localizacion localizacion, Localidad localidad, Usuario usuarioAlta, 
									Usuario usuarioModificacion,Usuario usuarioAnulacion) 
	{
		GuiaForestal guia = new GuiaForestal();

		if (guiaDTO.getId() != null && guiaDTO.getId() != 0) {
			guia.setId(guiaDTO.getId());
		}

		guia.setDistanciaAforoMovil(guiaDTO.getDistanciaAforoMovil());
		guia.setFecha(Fecha.stringDDMMAAAAToUtilDate(guiaDTO.getFecha()));
		guia.setFechaVencimiento(Fecha.stringDDMMAAAAToUtilDate(guiaDTO
				.getFechaVencimiento()));
		guia.setFiscalizaciones(listaFiscalizaciones);
		guia.setImporteTotal(guiaDTO.getImporteTotal());
		guia.setInspFiscalizacion(guiaDTO.getInspFiscalizacion());
		guia.setLocalidad(localidad);
		guia.setNroGuia(guiaDTO.getNroGuia());
		guia.setObservaciones(guiaDTO.getObservaciones());
		
		if(guiaDTO.getOperacionAlta() != null && 
		   guiaDTO.getOperacionAlta().getUsuario().getId() != null)
		{
			guia.setOperacionAlta(ProviderDominio.getOperacionGuiaForestal(
					guiaDTO.getOperacionAlta(),
					guia,usuarioAlta));
		}
		
		if(guiaDTO.getOperacionModificacion() != null && 
		   guiaDTO.getOperacionModificacion().getUsuario().getId() != null)
		{
			guia.setOperacionModificacion(ProviderDominio.getOperacionGuiaForestal(
													guiaDTO.getOperacionModificacion(),
													guia,usuarioModificacion));
		}		
		
		if(guiaDTO.getOperacionAnulacion().getId() != null &&
		   guiaDTO.getOperacionAnulacion().getUsuario().getId() != null)
		{
			guia.setOperacionAnulacion(ProviderDominio.getOperacionGuiaForestal(
													guiaDTO.getOperacionAnulacion(),
													guia,usuarioAnulacion));
		}		
		
		guia.setPeriodoForestal(guiaDTO.getPeriodoForestal());
		guia.setProductorForestal(productorForestal);
		guia.setLocalizacion(localizacion);

		for (BoletaDepositoDTO boletaDTO : listaBoletaDepositoDTO) {
			guia.getBoletasDeposito().add(
					ProviderDominio.getBoletaDeposito(guia, boletaDTO));
		}

		for (RangoDTO rangoDTO : listaRangosDTO) {
			guia.getValesTransporte().addAll(
					ProviderDominio.getValesTransportes(guia, rangoDTO,
							fechaVencimiento));
		}

		for (SubImporte subImporte : listaSubImportes) {
			subImporte.setGuiaForestal(guia);
		}
		guia.setSubImportes(listaSubImportes);
		guia.setAnulado(guiaDTO.isAnulado());
		return guia;
	}

	public static BoletaDeposito getBoletaDeposito(GuiaForestal guia,
			BoletaDepositoDTO boletaDTO) {

		BoletaDeposito boleta = new BoletaDeposito();

		if (boletaDTO.getIdBoleta() != 0) {
			boleta.setId(boletaDTO.getIdBoleta());
		}
		boleta.setArea(boletaDTO.getArea());
		boleta.setConcepto(boletaDTO.getConcepto());
		boleta.setEfectivoCheque(boletaDTO.getEfectivoCheque());
		if (boletaDTO.getFechaPago() != null
				&& !boletaDTO.getFechaPago().equals("")) {
			boleta.setFechaPago(Fecha.stringDDMMAAAAToUtilDate(boletaDTO
					.getFechaPago()));
		}
		boleta.setFechaVencimiento(Fecha.stringDDMMAAAAToUtilDate(boletaDTO
				.getFechaVencimiento()));
		boleta.setGuiaForestal(guia);
		boleta.setMonto(boletaDTO.getMonto());
		boleta.setNumero(boletaDTO.getNumero());

		return boleta;
	}

	public static List<ValeTransporte> getValesTransportes(GuiaForestal guia,
			RangoDTO rangoDTO, Date fechaVencimiento) {
		List<ValeTransporte> vales = new ArrayList<ValeTransporte>();
		for (long i = rangoDTO.getDesde(); i <= rangoDTO.getHasta(); i++) {
			ValeTransporte vale = new ValeTransporte();
			vale.setFechaVencimiento(fechaVencimiento);
			vale.setGuiaForestal(guia);
			vale.setNumero(i);
			vale.setOrigen(guia.getLocalizacion().getNombreLocalizacion());
			vales.add(vale);
		}
		return vales;
	}

	public static ValeTransporte getValeTransporte(GuiaForestal guia,
			ValeTransporteDTO valeDTO) {

		ValeTransporte vale = new ValeTransporte();

		vale.setCantidadMts(valeDTO.getCantidadMts());
		vale.setDestino(valeDTO.getDestino());
		vale.setDominio(valeDTO.getDominio());
		vale.setEspecie(valeDTO.getEspecie());
		if (valeDTO.getFechaDevolucion() != null
				&& !valeDTO.getFechaDevolucion().equals("")) {
			vale.setFechaDevolucion(Fecha.stringDDMMAAAAToUtilDate(valeDTO
					.getFechaDevolucion()));
		}
		vale.setFechaVencimiento(Fecha.stringDDMMAAAAToUtilDate(valeDTO
				.getFechaVencimiento()));
		vale.setGuiaForestal(guia);
		if (valeDTO.getId() != 0) {
			vale.setId(valeDTO.getId());
		}
		vale.setMarca(valeDTO.getMarca());
		vale.setNroPiezas(valeDTO.getNroPiezas());
		vale.setNumero(valeDTO.getNumero());
		vale.setOrigen(valeDTO.getOrigen());
		vale.setProducto(valeDTO.getProducto());
		vale.setVehiculo(valeDTO.getVehiculo());

		return vale;
	}

	public static SubImporte getSubImporte(GuiaForestal guia,
			TipoProductoForestal tipoProducto, SubImporteDTO subImporteDTO) {

		SubImporte subImporte = new SubImporte();

		subImporte.setCantidadMts(subImporteDTO.getCantidadMts());
		subImporte.setCantidadUnidades(subImporteDTO.getCantidadUnidades());
		subImporte.setEspecie(subImporteDTO.getEspecie());
		subImporte.setEstado(subImporteDTO.getEstado());
		subImporte.setGuiaForestal(guia);
		subImporte.setImporte(subImporteDTO.getImporte());
		subImporte.setTipoProducto(tipoProducto);
		subImporte.setValorAforos(subImporteDTO.getValorAforos());

		return subImporte;
	}

	public static ProvinciaDestino getProvincia(ProvinciaDestinoDTO provinciaDTO) {

		ProvinciaDestino provincia = new ProvinciaDestino();
		provincia.setNombre(provinciaDTO.getNombre());
		return provincia;
	}

	public static ProvinciaDestino getProvincia(ProvinciaDestino provincia,
			ProvinciaDestinoDTO provinciaDTO) {

		provincia.setNombre(provinciaDTO.getNombre());
		return provincia;
	}

	public static LocalidadDestino getLocalidadDestino(
			LocalidadDestinoDTO localidadDTO, ProvinciaDestino provincia) {

		LocalidadDestino localidad = new LocalidadDestino();
		localidad.setNombre(localidadDTO.getNombre());
		localidad.setProvinciaDestino(provincia);

		return localidad;
	}

	public static LocalidadDestino getLocalidadDestino(
			LocalidadDestinoDTO localidadDTO, LocalidadDestino localidad,
			ProvinciaDestino provincia) {

		localidad.setNombre(localidadDTO.getNombre());
		localidad.setProvinciaDestino(provincia);

		return localidad;
	}

	public static TipoProductoEnCertificado getTipoProductoEnCertificado(
			CertificadoOrigen certificadoOrigen,
			TipoProductoExportacion tipoProductoExportacion,
			TipoProductoEnCertificadoDTO tipoProductoEnCertificadoDTO) {
		TipoProductoEnCertificado tipoProdEnCertif = new TipoProductoEnCertificado();

		tipoProdEnCertif.setCertificadoOrigen(certificadoOrigen);
		tipoProdEnCertif.setTipoProductoExportacion(tipoProductoExportacion);
		tipoProdEnCertif.setVolumenTipoProducto(tipoProductoEnCertificadoDTO
				.getVolumenTipoProducto());

		return tipoProdEnCertif;
	}

	public static CertificadoOrigen getCertificadoOrigen(
			CertificadoOrigenDTO certificadoDTO, Usuario usuario,
			Entidad productor, Entidad exportador,
			LocalidadDestino localidadDestino, Localizacion localizacion,
			Date fecha, List<TipoProductoEnCertificado> listaTipoProdEnCert) {
		CertificadoOrigen certificado = new CertificadoOrigen();

		certificado.setExportador(exportador);
		certificado.setFecha(fecha);
		certificado.setLocalidadDestino(localidadDestino);
		certificado.setNroFactura(certificadoDTO.getNroFactura());
		certificado.setNroRemito(certificadoDTO.getNroRemito());
		certificado.setOrigenMateriaPrima(certificadoDTO
				.getOrigenMateriaPrima());
		certificado.setPeriodoForestal(certificadoDTO.getPeriodoForestal());
		certificado.setLocalizacion(localizacion);
		certificado.setProductor(productor);
		certificado.setReservaForestal(certificadoDTO.getReservaForestal());
		certificado.setUsuarioAlta(usuario);
		certificado.setVolumenTotalTipoProductos(certificadoDTO
				.getVolumenTotalTipoProductos());
		certificado.setVolumenTransferido(certificadoDTO
				.getVolumenTransferido());

		for (TipoProductoEnCertificado tipoProductoEnCertificado : listaTipoProdEnCert) {
			tipoProductoEnCertificado.setCertificadoOrigen(certificado);
		}
		certificado.setTiposProductoEnCertificado(listaTipoProdEnCert);

		return certificado;
	}

	public static Localizacion getLocalizacion(LocalizacionDTO localizacionDTO,
			Entidad entidad) {
		return localizacionDTO.getLocalizacion(entidad);
	}

	/*
	 * public static Fiscalizacion getActaMartillado(FiscalizacionForm form) {
	 * 
	 * Fiscalizacion acta = form.getFiscalizacion(); if (form.getFecha() != null
	 * && !form.getFecha().equals("")) {
	 * acta.setFecha(Fecha.stringDDMMAAAAToDate(form.getFecha())); }
	 * 
	 * return acta; }
	 * 
	 * public static void getActaMartilladoAModificar(Fiscalizacion actaBD,
	 * Fiscalizacion acta) {
	 * 
	 * actaBD.setCantidadMts(acta.getCantidadMts());
	 * actaBD.setCantidadUnidades(acta.getCantidadUnidades()); //
	 * actaBD.getUbicacion().setExpediente(acta.getUbicacion().getExpediente());
	 * if (acta.getFecha() != null) { actaBD.setFecha(acta.getFecha()); }
	 * actaBD.setPeriodoForestal(acta.getPeriodoForestal());
	 * actaBD.setProductorForestal(acta.getProductorForestal());
	 * actaBD.setTamanioMuestra(acta.getTamanioMuestra()); //
	 * actaBD.setUbicacionZonal(acta.getUbicacionZonal());
	 * 
	 * }
	 * 
	 * public static GuiaForestal getGuiaForestal(GuiaForestalForm guiaForm,
	 * Fiscalizacion actaMartillado, Usuario usuario) {
	 * 
	 * GuiaForestal guia = guiaForm.getGuiaForestal();
	 * guia.setFiscalizacion(actaMartillado);
	 * actaMartillado.setGuiaForestal(guia);
	 * guia.setFecha(Fecha.stringDDMMAAAAToDate(guiaForm.getFecha()));
	 * guia.setFechaVencimiento
	 * (Fecha.stringDDMMAAAAToDate(guiaForm.getFechaVencimiento()));
	 * 
	 * guia.setBoletasDeposito(getBoletasDeposito(guiaForm.getBoletasDeposito(),
	 * guia));
	 * guia.setValesTransporte(getValesTransporte(guiaForm.getValesTransporte(),
	 * guia));
	 * 
	 * guia.setUsuario(usuario);
	 * 
	 * return guia; }
	 * 
	 * public static List<BoletaDeposito>
	 * getBoletasDeposito(List<BoletaDeposito> boletas, GuiaForestal guia) {
	 * 
	 * List<BoletaDeposito> boletasAEliminar = new ArrayList<BoletaDeposito>();
	 * 
	 * for (BoletaDeposito boleta : boletas) {
	 * 
	 * if (boleta.getNumero() == 0) { boletasAEliminar.add(boleta); } else {
	 * boleta.setGuiaForestal(guia);
	 * 
	 * // BORRAR boleta.setFechaVencimiento(guia.getFechaVencimiento()); //
	 * boleta.setFechaPago(guia.getFecha()); // } }
	 * boletas.removeAll(boletasAEliminar);
	 * 
	 * return boletas; }
	 * 
	 * public static List<ValeTransporte>
	 * getValesTransporte(List<ValeTransporte> vales, GuiaForestal guia) {
	 * 
	 * List<ValeTransporte> valesAEliminar = new ArrayList<ValeTransporte>();
	 * 
	 * for (ValeTransporte vale : vales) {
	 * 
	 * if (vale.getNumero() == 0) { valesAEliminar.add(vale); } else {
	 * vale.setGuiaForestal(guia);
	 * 
	 * // BORRAR // vale.setFecha(guia.getFecha());
	 * vale.setFechaVencimiento(guia.getFechaVencimiento()); // } }
	 * 
	 * vales.removeAll(valesAEliminar);
	 * 
	 * return vales; }
	 * 
	 * public static GuiaForestal getGuiaForestalRegistrarPago(GuiaForestalForm
	 * guiaForm) {
	 * 
	 * GuiaForestal guia = guiaForm.getGuiaForestal(); int i = 0; for
	 * (BoletaDeposito boleta : guiaForm.getBoletasDeposito()) {
	 * 
	 * if (boleta != null && boleta.getFechaPagoTransient() != null &&
	 * !boleta.getFechaPagoTransient().equals("")) {
	 * 
	 * BoletaDeposito boletaGuia = guia.getBoletasDeposito().get(i);
	 * boletaGuia.setFechaPago
	 * (Fecha.stringDDMMAAAAToDate(boleta.getFechaPagoTransient())); } i++; }
	 * 
	 * return guia; }
	 */

	/*
	 * public static Entidad getEntidad(EntidadDTO entidadDTO){
	 * 
	 * Entidad entidad = new Entidad();
	 * entidad.setDireccion(entidadDTO.getDireccion());
	 * entidad.setEmail(entidadDTO.getEmail());
	 * entidad.setId(entidadDTO.getId());
	 * entidad.setLocalidad(entidadDTO.getLocalidad());
	 * entidad.setNombre(entidadDTO.getNombre());
	 * entidad.setTelefono(entidadDTO.getTelefono());
	 * 
	 * return entidad; }
	 * 
	 * public static ActaMartillado getActaMartillado(ActaMartilladoDTO
	 * actaDTO){
	 * 
	 * ActaMartillado acta = new ActaMartillado();
	 * acta.setNroOrden(actaDTO.getNroOrden());
	 * acta.setProductorForestal(getEntidad(actaDTO.getProductorForestal()));
	 * acta.setPeriodoForestal(actaDTO.getPeriodoForestal());
	 * acta.setExpediente(actaDTO.getExpediente());
	 * acta.setFecha(actaDTO.getFecha());
	 * acta.setUbicacionZonal(actaDTO.getUbicacionZonal());
	 * acta.setCantidadUnidades(actaDTO.getCantidadUnidades());
	 * acta.setTipoTransporte(actaDTO.getTipoTransporte());
	 * acta.setCantidadMts(actaDTO.getCantidadMts());
	 * acta.setTamanio(actaDTO.getTamanio()); if(actaDTO.getGuiaForestal() !=
	 * null && actaDTO.getGuiaForestal().getNroGuia() != 0){
	 * acta.setGuiaForestal(getGuiaForestal(actaDTO.getGuiaForestal(), acta)); }
	 * 
	 * return acta; }
	 * 
	 * public static GuiaForestal getGuiaForestal(GuiaForestalDTO guiaDTO,
	 * ActaMartillado acta){
	 * 
	 * GuiaForestal guia = new GuiaForestal();
	 * 
	 * guia.setNroGuia(guiaDTO.getNroGuia());
	 * guia.setLugarCorte(guiaDTO.getLugarCorte());
	 * guia.setFechaVencimiento(guiaDTO.getFechaVencimiento());
	 * guia.setDistanciaAforoMovil(guiaDTO.getDistanciaAforoMovil());
	 * guia.setNroExpediente(guiaDTO.getNroExpediente());
	 * guia.setPeriodoForestal(guiaDTO.getPeriodoForestal());
	 * guia.setDisposicionNro(guiaDTO.getDisposicionNro());
	 * guia.setTipo(guiaDTO.getTipo()); guia.setEstado(guiaDTO.getEstado());
	 * guia.setEspecie(guiaDTO.getEspecie());
	 * guia.setVolumenTotal(guiaDTO.getVolumenTotal());
	 * guia.setCantidad(guiaDTO.getCantidad());
	 * guia.setImporte(guiaDTO.getImporte()); guia.setAforo(guiaDTO.getAforo());
	 * guia.setInspFiscalizacion(guiaDTO.getInspFiscalizacion());
	 * guia.setValorAforos(guiaDTO.getValorAforos());
	 * guia.setObservaciones(guiaDTO.getObservaciones());
	 * guia.setLocalidad(guiaDTO.getLocalidad());
	 * guia.setFecha(guiaDTO.getFecha());
	 * guia.setValesTransporte(getValesTransporte
	 * (guiaDTO.getValesTransporte(),guia));
	 * guia.setBoletasDeposito(getBoletasDeposito
	 * (guiaDTO.getBoletasDeposito(),guia));
	 * guia.setUsuario(guiaDTO.getUsuario()); guia.setActaMartillado(acta);
	 * 
	 * return guia; }
	 * 
	 * public static List<BoletaDeposito>
	 * getBoletasDeposito(List<BoletaDepositoDTO> boletasDepositoDTO,
	 * GuiaForestal guia) { List<BoletaDeposito> boletasDeposito = new
	 * ArrayList<BoletaDeposito>();
	 * 
	 * for (BoletaDepositoDTO boletaDTO : boletasDepositoDTO) {
	 * 
	 * boletasDeposito.add(getBoletaDeposito(boletaDTO,guia)); }
	 * 
	 * return boletasDeposito; }
	 * 
	 * public static BoletaDeposito getBoletaDeposito(BoletaDepositoDTO
	 * boletaDTO, GuiaForestal guia) {
	 * 
	 * BoletaDeposito boleta = new BoletaDeposito();
	 * boleta.setNumero(boletaDTO.getNumero());
	 * boleta.setConcepto(boletaDTO.getConcepto());
	 * boleta.setArea(boletaDTO.getArea());
	 * boleta.setEfectivoCheque(boletaDTO.getEfectivoCheque());
	 * boleta.setMonto(boletaDTO.getMonto());
	 * boleta.setFechaPago(Fecha.stringDDMMAAAAToDate
	 * (boletaDTO.getFechaPago()));
	 * boleta.setFechaVencimiento(Fecha.stringDDMMAAAAToDate
	 * (boletaDTO.getFechaVencimiento())); boleta.setGuiaForestal(guia);
	 * 
	 * return boleta; }
	 * 
	 * public static List<ValeTransporte>
	 * getValesTransporte(List<ValeTransporteDTO> valesTransporteDTO,
	 * GuiaForestal guia) {
	 * 
	 * List<ValeTransporte> valesTransporte = new ArrayList<ValeTransporte>();
	 * 
	 * for (ValeTransporteDTO valeTransporteDTO : valesTransporteDTO) {
	 * 
	 * valesTransporte.add(getValeTransporte(valeTransporteDTO,guia)); }
	 * 
	 * return valesTransporte; }
	 * 
	 * private static ValeTransporte getValeTransporte(ValeTransporteDTO
	 * valeDTO, GuiaForestal guia) {
	 * 
	 * ValeTransporte vale = new ValeTransporte();
	 * 
	 * vale.setNumero(valeDTO.getNumero());
	 * vale.setFecha(Fecha.stringDDMMAAAAToDate(valeDTO.getFecha()));
	 * vale.setOrigen(valeDTO.getOrigen());
	 * vale.setDestino(valeDTO.getDestino());
	 * vale.setVehiculo(valeDTO.getVehiculo());
	 * vale.setMarca(valeDTO.getMarca()); vale.setDominio(valeDTO.getDominio());
	 * vale
	 * .setFechaVencimiento(Fecha.stringDDMMAAAAToDate(valeDTO.getFechaVencimiento
	 * ())); vale.setProducto(valeDTO.getProducto());
	 * vale.setNroPiezas(valeDTO.getNroPiezas());
	 * vale.setCantidadMts(valeDTO.getCantidadMts());
	 * vale.setEspecie(valeDTO.getEspecie()); vale.setGuiaForestal(guia);
	 * 
	 * return vale; }
	 */
}
