package ar.com.siif.providers;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dto.AforoDTO;
import ar.com.siif.dto.AreaDeCosechaDTO;
import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.CertificadoOrigenDTO;
import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.ItemMenuDTO;
import ar.com.siif.dto.LocalidadDTO;
import ar.com.siif.dto.LocalidadDestinoDTO;
import ar.com.siif.dto.LocalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.OperacionFiscalizacionDTO;
import ar.com.siif.dto.OperacionGuiaForestalDTO;
import ar.com.siif.dto.PMFDTO;
import ar.com.siif.dto.PeriodoDTO;
import ar.com.siif.dto.ProvinciaDestinoDTO;
import ar.com.siif.dto.RolDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TipoProductoEnCertificadoDTO;
import ar.com.siif.dto.TipoProductoForestalDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.dto.ValeTransporteDTO;
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
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.utils.Fecha;
import ar.com.siif.utils.MathUtils;

public abstract class ProviderDTO {

	public static EntidadDTO getEntidadDTO(Entidad entidad) {

		EntidadDTO entidadDTO = new EntidadDTO();

		entidadDTO.setId(entidad.getId());
		entidadDTO.setNombre(entidad.getNombre());
		entidadDTO.setDireccion(entidad.getDireccion());
		entidadDTO.setTelefono(entidad.getTelefono());
		entidadDTO.setEmail(entidad.getEmail());
		entidadDTO.setLocalidad(ProviderDTO.getLocalidadDTO(entidad
				.getLocalidad()));
		entidadDTO.setTipoEntidadDesc(entidad.getTipoEntidad());
		entidadDTO.setTipoEntidad(entidad.getIdTipoEntidad());
		entidadDTO.setNroMatricula(entidad.getNroMatricula());
		entidadDTO.setCuit(entidad.getCuit());
		entidadDTO.setCodigoPostal(entidad.getCodigoPostal());

		return entidadDTO;
	}

	public static TipoProductoDTO getTipoProductoDTO(TipoProducto tipoProducto) {

		TipoProductoDTO tipoProductoDTO = new TipoProductoDTO();

		tipoProductoDTO.setId(tipoProducto.getId());
		tipoProductoDTO.setNombre(tipoProducto.getNombre());

		return tipoProductoDTO;

	}

	public static TipoProductoForestalDTO getTipoProductoForestalDTO(
			TipoProductoForestal tipoProductoForestal) {

		TipoProductoForestalDTO tipoProductoForestalDTO = new TipoProductoForestalDTO();

		tipoProductoForestalDTO.setId(tipoProductoForestal.getId());
		tipoProductoForestalDTO.setNombre(tipoProductoForestal.getNombre());
		tipoProductoForestalDTO.setCantDiametros(tipoProductoForestal
				.getCantDiametros());
		tipoProductoForestalDTO.setDiam1Desde(tipoProductoForestal
				.getDiam1Desde());
		tipoProductoForestalDTO.setDiam1Hasta(tipoProductoForestal
				.getDiam1Hasta());
		tipoProductoForestalDTO.setDiam2Desde(tipoProductoForestal
				.getDiam2Desde());
		tipoProductoForestalDTO.setDiam2Hasta(tipoProductoForestal
				.getDiam2Hasta());
		tipoProductoForestalDTO.setLargoDesde(tipoProductoForestal
				.getLargoDesde());
		tipoProductoForestalDTO.setLargoHasta(tipoProductoForestal
				.getLargoHasta());
		tipoProductoForestalDTO.setEsDeExportacion(tipoProductoForestal
				.isEsDeExportacion());
		
		return tipoProductoForestalDTO;

	}

	public static UsuarioDTO getUsuarioDTO(Usuario usuario) {

		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
		usuarioDTO.setPassword(usuario.getPassword());
		usuarioDTO.setRol(ProviderDTO.getRolDTO(usuario.getRol()));
		usuarioDTO.setEntidad(ProviderDTO.getEntidadDTO(usuario.getEntidad()));
		usuarioDTO.setHabilitado(usuario.isHabilitado());

		return usuarioDTO;
	}

	public static LocalidadDTO getLocalidadDTO(Localidad localidad) {

		LocalidadDTO localidadDTO = new LocalidadDTO();

		localidadDTO.setId(localidad.getId());
		localidadDTO.setNombre(localidad.getNombre());

		return localidadDTO;
	}

	public static PeriodoDTO getPeriodoDTO(Periodo periodo) {

		PeriodoDTO periodoDTO = new PeriodoDTO();

		periodoDTO.setId(periodo.getId());
		periodoDTO.setPeriodo(periodo.getPeriodo());

		return periodoDTO;
	}

	public static AforoDTO getAforoDTO(Aforo aforo) {

		AforoDTO aforoDTO = new AforoDTO();

		aforoDTO.setEstado(aforo.getEstado());
		aforoDTO.setId(aforo.getId());
		aforoDTO.setTipoProducto(ProviderDTO.getTipoProductoForestalDTO(aforo
				.getTipoProducto()));
		aforoDTO.setTipoProductor(aforo.getTipoProductor());
		aforoDTO.setTipoProductorDesc(aforo.getTipoProductorDesc());
		aforoDTO.setValorAforo(aforo.getValorAforo());

		return aforoDTO;
	}

	public static RolDTO getRolDTO(Rol rol) {

		List<ItemMenuDTO> listaMenuesDTO = new ArrayList<ItemMenuDTO>();
		RolDTO rolDTO = new RolDTO();

		rolDTO.setId(rol.getId());
		rolDTO.setRol(rol.getRol());

		for (ItemMenu menu : rol.getMenues()) {
			listaMenuesDTO.add(ProviderDTO.getItemMenuDTO(menu));
		}
		rolDTO.setMenues(listaMenuesDTO);

		return rolDTO;
	}

	public static ItemMenuDTO getItemMenuDTO(ItemMenu menu) {
		return ProviderDTO.getItemMenuDTO(menu, null);
	}

	private static ItemMenuDTO getItemMenuDTO(ItemMenu menu, ItemMenuDTO padre) {

		if (menu != null) {
			List<ItemMenuDTO> listaMenuDTO = new ArrayList<ItemMenuDTO>();
			ItemMenuDTO menuDTO = new ItemMenuDTO();

			menuDTO.setId(menu.getId());
			menuDTO.setItem(menu.getItem());
			menuDTO.setOrden(menu.getOrden());
			menuDTO.setPadre(padre);
			menuDTO.setUrl(menu.getUrl());

			for (ItemMenu menuHijo : menu.getHijos()) {
				listaMenuDTO.add(ProviderDTO.getItemMenuDTO(menuHijo, menuDTO));
			}
			menuDTO.setHijos(listaMenuDTO);

			return menuDTO;
		} else {
			return null;
		}
	}

	// Genera una FiscalizacionDTO sin los datos de la guiaForestal.
	private static FiscalizacionDTO getFiscalizacionDTODatosGenerales(
			Fiscalizacion fiscalizacion) {

		List<MuestraDTO> listaMuestrasDTO = new ArrayList<MuestraDTO>();
		List<OperacionFiscalizacionDTO> listaOperacionDTO = new ArrayList<OperacionFiscalizacionDTO>();
		FiscalizacionDTO fiscalizacionDTO = new FiscalizacionDTO();

		fiscalizacionDTO.setCantidadMts(fiscalizacion.getCantidadMts());
		fiscalizacionDTO.setCantidadUnidades(fiscalizacion
				.getCantidadUnidades());
		fiscalizacionDTO.setFecha(Fecha.getFechaDDMMAAAASlash(Fecha
				.dateToStringDDMMAAAA(fiscalizacion.getFecha())));
		fiscalizacionDTO.setId(fiscalizacion.getId());
		fiscalizacionDTO.setOficinaAlta(ProviderDTO.getEntidadDTO(fiscalizacion
				.getOficinaAlta()));
		fiscalizacionDTO.setProductorForestal(ProviderDTO
				.getEntidadDTO(fiscalizacion.getProductorForestal()));
		fiscalizacionDTO.setIdLocalizacion(fiscalizacion.getLocalizacion()
				.getLocalizacionDTO().getId());
		fiscalizacionDTO.setLocalizacion(ProviderDTO
				.getLocalizacionDTO(fiscalizacion.getLocalizacion()));
		fiscalizacionDTO.setTipoProducto(ProviderDTO
				.getTipoProductoForestalDTO(fiscalizacion.getTipoProducto()));

		for (OperacionFiscalizacion operacion : fiscalizacion.getOperaciones()){
			listaOperacionDTO.add(ProviderDTO.getOperacionFiscalizacionDTO(
												operacion,
												fiscalizacionDTO));						
		}	
		fiscalizacionDTO.setOperaciones(listaOperacionDTO);

		fiscalizacionDTO.setPeriodoForestal(fiscalizacion.getPeriodoForestal());
		fiscalizacionDTO.setTamanioMuestra(fiscalizacion.getTamanioMuestra());

		List<Muestra> listaMuestra = fiscalizacion.getMuestra();
		for (Muestra muestra : listaMuestra) {
			listaMuestrasDTO.add(ProviderDTO.getMuestraDTO(muestra));
		}
		fiscalizacionDTO.setMuestra(listaMuestrasDTO);

		return fiscalizacionDTO;
	}

	public static OperacionFiscalizacionDTO getOperacionFiscalizacionDTO(
			OperacionFiscalizacion operacion, FiscalizacionDTO fiscalizacionDTO) {

		OperacionFiscalizacionDTO operacionDTO = new OperacionFiscalizacionDTO();
		operacionDTO.setFecha(Fecha.getFechaDDMMAAAASlash(Fecha
				.dateToStringDDMMAAAA(operacion.getFecha())));
		operacionDTO.setFiscalizacion(fiscalizacionDTO);
		operacionDTO.setId(operacion.getId());
		operacionDTO.setTipoOperacion(operacion.getTipoOperacion());
		operacionDTO.setUsuario(ProviderDTO.getUsuarioDTO(operacion
				.getUsuario()));

		return operacionDTO;
	}

	public static OperacionGuiaForestalDTO getOperacionGuiaForestalDTO(
			OperacionGuiaForestal operacion, GuiaForestalDTO guiaForestal) {

		OperacionGuiaForestalDTO operacionDTO = new OperacionGuiaForestalDTO();
		operacionDTO.setFecha(Fecha.getFechaDDMMAAAASlash(Fecha
				.dateToStringDDMMAAAA(operacion.getFecha())));
		operacionDTO.setGuiaForestal(guiaForestal);
		operacionDTO.setId(operacion.getId());
		operacionDTO.setTipoOperacion(operacion.getTipoOperacion());
		operacionDTO.setUsuario(ProviderDTO.getUsuarioDTO(operacion
				.getUsuario()));

		return operacionDTO;
	}

	public static LocalizacionDTO getLocalizacionDTO(Localizacion localizacion) {
		return localizacion.getLocalizacionDTO();
	}

	public static FiscalizacionDTO getFiscalizacionDTO(
			Fiscalizacion fiscalizacion) {

		FiscalizacionDTO fiscalizacionDTO = getFiscalizacionDTODatosGenerales(fiscalizacion);

		if (fiscalizacion.getGuiaForestal() != null) {
			fiscalizacionDTO.setGuiaForestal(ProviderDTO
					.getGuiaForestalDTODatosGenerales(fiscalizacion
							.getGuiaForestal()));
		}

		return fiscalizacionDTO;
	}

	public static MuestraDTO getMuestraDTO(Muestra muestra) {

		MuestraDTO muestraDTO = new MuestraDTO();

		muestraDTO.setDiametro1(muestra.getDiametro1());
		muestraDTO.setDiametro2(muestra.getDiametro2());
		muestraDTO.setId(muestra.getId());
		muestraDTO.setLargo(muestra.getLargo());

		return muestraDTO;
	}

	// Genera una GuiaForestalDTO sin los datos de la Fiscalización.
	private static GuiaForestalDTO getGuiaForestalDTODatosGenerales(
			GuiaForestal guiaForestal) {

		GuiaForestalDTO guiaForestalDTO = new GuiaForestalDTO();
		List<BoletaDepositoDTO> listaBoletasDTO = new ArrayList<BoletaDepositoDTO>();
		List<ValeTransporteDTO> listaValesTransporteDTO = new ArrayList<ValeTransporteDTO>();
		List<SubImporteDTO> listaSubImportesDTO = new ArrayList<SubImporteDTO>();

		guiaForestalDTO.setId(guiaForestal.getId());
		guiaForestalDTO.setDistanciaAforoMovil(guiaForestal
				.getDistanciaAforoMovil());
		guiaForestalDTO.setFecha(Fecha.getFechaDDMMAAAASlash(Fecha
				.dateToStringDDMMAAAA(guiaForestal.getFecha())));
		guiaForestalDTO.setFechaVencimiento(Fecha.getFechaDDMMAAAASlash(Fecha
				.dateToStringDDMMAAAA(guiaForestal.getFechaVencimiento())));
		guiaForestalDTO.setImporteTotal(guiaForestal.getImporteTotal());
		guiaForestalDTO.setInspFiscalizacion(guiaForestal
				.getInspFiscalizacion());
		guiaForestalDTO.setLocalidad(ProviderDTO.getLocalidadDTO(guiaForestal
				.getLocalidad()));
		guiaForestalDTO.setNroGuia(guiaForestal.getNroGuia());
		guiaForestalDTO.setObservaciones(guiaForestal.getObservaciones());

		guiaForestalDTO.setPeriodoForestal(guiaForestal.getPeriodoForestal());
		guiaForestalDTO.setProductorForestal(ProviderDTO
				.getEntidadDTO(guiaForestal.getProductorForestal()));

		if (guiaForestal.getLocalizacion() != null) {
			guiaForestalDTO.setLocalizacion(ProviderDTO
					.getLocalizacionDTO(guiaForestal.getLocalizacion()));
			guiaForestalDTO.setIdLocalizacion(String.valueOf(guiaForestal
					.getLocalizacion().getId()));
		}

		if (guiaForestal.getOperacionAlta() != null) {
			guiaForestalDTO.setOperacionAlta(ProviderDTO
					.getOperacionGuiaForestalDTO(
							guiaForestal.getOperacionAlta(), guiaForestalDTO));
		}
		if (guiaForestal.getOperacionModificacion() != null) {

			guiaForestalDTO.setOperacionModificacion(ProviderDTO
					.getOperacionGuiaForestalDTO(
							guiaForestal.getOperacionModificacion(),
							guiaForestalDTO));
		}
		if (guiaForestal.getOperacionAnulacion() != null) {

			guiaForestalDTO.setOperacionAnulacion(ProviderDTO
					.getOperacionGuiaForestalDTO(
							guiaForestal.getOperacionAnulacion(),
							guiaForestalDTO));
		}

		List<BoletaDeposito> listaBoletas = guiaForestal.getBoletasDeposito();
		for (BoletaDeposito boletaDeposito : listaBoletas) {
			listaBoletasDTO.add(ProviderDTO.getBoletaDepositoDTO(
					boletaDeposito, guiaForestalDTO));
		}
		guiaForestalDTO.setBoletasDeposito(listaBoletasDTO);

		List<ValeTransporte> listaValesTransporte = guiaForestal
				.getValesTransporte();
		for (ValeTransporte valeTransporte : listaValesTransporte) {
			listaValesTransporteDTO.add(ProviderDTO.getValeTransporteDTO(
					valeTransporte, guiaForestalDTO));
		}
		guiaForestalDTO.setValesTransporte(listaValesTransporteDTO);

		List<SubImporte> listaSubImportes = guiaForestal.getSubImportes();
		for (SubImporte subImporte : listaSubImportes) {
			listaSubImportesDTO.add(ProviderDTO.getSubImporteDTO(subImporte,
					guiaForestalDTO));
		}
		guiaForestalDTO.setSubImportes(listaSubImportesDTO);

		guiaForestalDTO.setAnulado(guiaForestal.isAnulado());

		return guiaForestalDTO;
	}

	public static GuiaForestalDTO getGuiaForestalDTO(GuiaForestal guiaForestal) {

		GuiaForestalDTO guiaForestalDTO = getGuiaForestalDTODatosGenerales(guiaForestal);

		for (Fiscalizacion fiscalizacion : guiaForestal.getFiscalizaciones()) {
			guiaForestalDTO.getFiscalizaciones().add(
					ProviderDTO
							.getFiscalizacionDTODatosGenerales(fiscalizacion));
		}

		// guiaForestalDTO.setFiscalizacion(ProviderDTO.getFiscalizacionDTODatosGenerales(guiaForestal.getFiscalizacion()));

		return guiaForestalDTO;
	}

	public static BoletaDepositoDTO getBoletaDepositoDTO(BoletaDeposito boleta,
			GuiaForestalDTO guiaForestalDTO) {

		BoletaDepositoDTO boletaDTO = new BoletaDepositoDTO();

		boletaDTO.setArea(boleta.getArea());
		boletaDTO.setConcepto(boleta.getConcepto());
		boletaDTO.setEfectivoCheque(boleta.getEfectivoCheque());
		if (boleta.getFechaPago() != null) {
			boletaDTO.setFechaPago(Fecha.getFechaDDMMAAAASlash(Fecha
					.dateToStringDDMMAAAA(boleta.getFechaPago())));
		}
		boletaDTO.setFechaVencimiento(Fecha.getFechaDDMMAAAASlash(Fecha
				.dateToStringDDMMAAAA(boleta.getFechaVencimiento())));
		boletaDTO.setGuiaForestal(guiaForestalDTO);
		boletaDTO.setIdBoleta(boleta.getId());
		boletaDTO.setMonto(boleta.getMonto());
		boletaDTO.setNumero(boleta.getNumero());

		return boletaDTO;
	}

	public static ValeTransporteDTO getValeTransporteDTO(ValeTransporte vale,
			GuiaForestalDTO guiaForestalDTO) {

		ValeTransporteDTO valeDTO = new ValeTransporteDTO();

		valeDTO.setId(vale.getId());
		valeDTO.setCantidadMts(vale.getCantidadMts());
		valeDTO.setDestino(vale.getDestino());
		valeDTO.setDominio(vale.getDominio());
		valeDTO.setEspecie(vale.getEspecie());
		if (vale.getFechaDevolucion() != null) {
			valeDTO.setFechaDevolucion(Fecha.getFechaDDMMAAAASlash(Fecha
					.dateToStringDDMMAAAA(vale.getFechaDevolucion())));
		}
		valeDTO.setFechaVencimiento(Fecha.getFechaDDMMAAAASlash(Fecha
				.dateToStringDDMMAAAA(vale.getFechaVencimiento())));
		valeDTO.setGuiaForestal(guiaForestalDTO);
		valeDTO.setMarca(vale.getMarca());
		valeDTO.setNroPiezas(vale.getNroPiezas());
		valeDTO.setNumero(vale.getNumero());
		valeDTO.setOrigen(vale.getOrigen());
		valeDTO.setProducto(vale.getProducto());
		valeDTO.setVehiculo(vale.getVehiculo());

		return valeDTO;
	}

	public static SubImporteDTO getSubImporteDTO(SubImporte subImporte,
			GuiaForestalDTO guiaDTO) {

		SubImporteDTO subImporteDTO = new SubImporteDTO();

		subImporteDTO.setCantidadMts(subImporte.getCantidadMts());
		subImporteDTO.setCantidadUnidades(subImporte.getCantidadUnidades());
		subImporteDTO.setEspecie(subImporte.getEspecie());
		subImporteDTO.setEstado(subImporte.getEstado());
		subImporteDTO.setGuiaForestal(guiaDTO);
		subImporteDTO.setId(subImporte.getId());
		// subImporteDTO.setImporte(subImporte.getImporte());
		subImporteDTO.setImporte(MathUtils.round(subImporte.getImporte(), 2));
		subImporteDTO.setTipoProducto(ProviderDTO
				.getTipoProductoForestalDTO(subImporte.getTipoProducto()));
		subImporteDTO.setValorAforos(subImporte.getValorAforos());

		return subImporteDTO;
	}

	public static ProvinciaDestinoDTO getProvinciaDestinoDTO(
			ProvinciaDestino provincia) {

		ProvinciaDestinoDTO provinciaDTO = new ProvinciaDestinoDTO();

		provinciaDTO.setId(provincia.getId());
		provinciaDTO.setNombre(provincia.getNombre());

		return provinciaDTO;
	}

	public static LocalidadDestinoDTO getLocalidadDestinoDTO(
			LocalidadDestino localidad) {

		LocalidadDestinoDTO localidadDTO = new LocalidadDestinoDTO();

		localidadDTO.setId(localidad.getId());
		localidadDTO.setNombre(localidad.getNombre());
		localidadDTO.setProvinciaDestinoDTO(ProviderDTO
				.getProvinciaDestinoDTO(localidad.getProvinciaDestino()));

		return localidadDTO;
	}

	public static TipoProductoEnCertificadoDTO getTipoProductoEnCertificadoDTO(
			TipoProductoEnCertificado tipoProducto,
			CertificadoOrigenDTO certificadoDTO) {

		TipoProductoEnCertificadoDTO tipoProductoDTO = new TipoProductoEnCertificadoDTO();

		tipoProductoDTO.setCertificadoOrigen(certificadoDTO);
		tipoProductoDTO.setId(tipoProducto.getId());
		tipoProductoDTO.setTipoProductoExportacion(ProviderDTO
				.getTipoProductoDTO(tipoProducto.getTipoProductoExportacion()));
		tipoProductoDTO.setVolumenTipoProducto(tipoProducto
				.getVolumenTipoProducto());

		return tipoProductoDTO;
	}

	public static CertificadoOrigenDTO getCertificadoOrigenDTO(
			CertificadoOrigen certificado) {

		CertificadoOrigenDTO certificadoDTO = new CertificadoOrigenDTO();

		certificadoDTO.setExportador(ProviderDTO.getEntidadDTO(certificado
				.getExportador()));
		certificadoDTO.setFecha(Fecha.getFechaDDMMAAAASlash(Fecha
				.dateToStringDDMMAAAA(certificado.getFecha())));
		certificadoDTO.setId(certificado.getId());
		certificadoDTO.setLocalidadDestino(ProviderDTO
				.getLocalidadDestinoDTO(certificado.getLocalidadDestino()));
		certificadoDTO.setNroCertificado(certificado.getNroCertificado());
		certificadoDTO.setNroFactura(certificado.getNroFactura());
		certificadoDTO.setNroRemito(certificado.getNroRemito());
		certificadoDTO.setOrigenMateriaPrima(certificado
				.getOrigenMateriaPrima());
		certificadoDTO.setPeriodoForestal(certificado.getPeriodoForestal());
		if (certificado.getLocalizacion().esAreaDeCosecha()) {
			certificadoDTO.setAreaDeCosecha((AreaDeCosechaDTO) certificado
					.getLocalizacion().getLocalizacionDTO());
		} else {
			certificadoDTO.setPmf((PMFDTO) certificado.getLocalizacion()
					.getLocalizacionDTO());
		}

		certificadoDTO.setProductor(ProviderDTO.getEntidadDTO(certificado
				.getProductor()));
		certificadoDTO.setReservaForestal(certificado.getReservaForestal());
		certificadoDTO.setUsuarioAlta(ProviderDTO.getUsuarioDTO(certificado
				.getUsuarioAlta()));
		certificadoDTO.setVolumenTotalTipoProductos(certificado
				.getVolumenTotalTipoProductos());
		certificadoDTO.setVolumenTransferido(certificado
				.getVolumenTransferido());

		List<TipoProductoEnCertificadoDTO> listaTipoProdDTO = new ArrayList<TipoProductoEnCertificadoDTO>();
		for (TipoProductoEnCertificado tipoProd : certificado
				.getTiposProductoEnCertificado()) {
			listaTipoProdDTO.add(ProviderDTO.getTipoProductoEnCertificadoDTO(
					tipoProd, certificadoDTO));
		}

		certificadoDTO.setTiposProductoEnCertificado(listaTipoProdDTO);

		return certificadoDTO;
	}

	/*
	 * public static ActaMartilladoDTO getActaMartilladoDTO(FiscalizacionForm
	 * form){
	 * 
	 * ActaMartilladoDTO acta = new ActaMartilladoDTO();
	 * acta.setNroOrden(form.getNroOrden());
	 * acta.setProductorForestal(form.getProductorForestal());
	 * acta.setPeriodoForestal(form.getPeriodoForestal());
	 * acta.setExpediente(form.getExpediente());
	 * acta.setFecha(Fecha.stringDDMMAAAAToDate(form.getFecha()));
	 * acta.setUbicacionZonal(form.getUbicacionZonal());
	 * acta.setCantidadUnidades(form.getCantidadUnidades());
	 * acta.setTipoTransporte(form.getTipoTransporte());
	 * acta.setCantidadMts(form.getCantidadMts());
	 * acta.setTamanio(form.getTamanio()); if(form.getGuiaForestal() != null &&
	 * form.getGuiaForestal().getNroGuia() != 0){
	 * acta.setGuiaForestal(form.getGuiaForestal()); }
	 * 
	 * return acta; }
	 * 
	 * public static ActaMartilladoDTO getActaMartilladoDTO(ActaMartillado
	 * acta){
	 * 
	 * ActaMartilladoDTO actaDTO = new ActaMartilladoDTO();
	 * actaDTO.setNroOrden(acta.getNroOrden());
	 * actaDTO.setProductorForestal(getEntidadDTO(acta.getProductorForestal()));
	 * actaDTO.setPeriodoForestal(acta.getPeriodoForestal());
	 * actaDTO.setExpediente(acta.getExpediente());
	 * actaDTO.setFecha(acta.getFecha());
	 * actaDTO.setUbicacionZonal(acta.getUbicacionZonal());
	 * actaDTO.setCantidadUnidades(acta.getCantidadUnidades());
	 * actaDTO.setTipoTransporte(acta.getTipoTransporte());
	 * actaDTO.setCantidadMts(acta.getCantidadMts());
	 * actaDTO.setTamanio(acta.getTamanio()); if(acta.getGuiaForestal() != null
	 * && acta.getGuiaForestal().getNroGuia() != 0){
	 * actaDTO.setGuiaForestal(getGuiaForestalDTO
	 * (acta.getGuiaForestal(),actaDTO)); }
	 * 
	 * return actaDTO; }
	 * 
	 * public static ActaMartilladoDTO getActaMartilladoDTO(ActaMartillado acta,
	 * GuiaForestalDTO guiaDTO){
	 * 
	 * ActaMartilladoDTO actaDTO = new ActaMartilladoDTO();
	 * actaDTO.setNroOrden(acta.getNroOrden());
	 * actaDTO.setProductorForestal(getEntidadDTO(acta.getProductorForestal()));
	 * actaDTO.setPeriodoForestal(acta.getPeriodoForestal());
	 * actaDTO.setExpediente(acta.getExpediente());
	 * actaDTO.setFecha(acta.getFecha());
	 * actaDTO.setUbicacionZonal(acta.getUbicacionZonal());
	 * actaDTO.setCantidadUnidades(acta.getCantidadUnidades());
	 * actaDTO.setTipoTransporte(acta.getTipoTransporte());
	 * actaDTO.setCantidadMts(acta.getCantidadMts());
	 * actaDTO.setTamanio(acta.getTamanio()); actaDTO.setGuiaForestal(guiaDTO);
	 * 
	 * return actaDTO; }
	 * 
	 * public static GuiaForestalDTO getGuiaForestalDTO(GuiaForestalForm form,
	 * Usuario usuario){
	 * 
	 * GuiaForestalDTO guia = new GuiaForestalDTO();
	 * 
	 * guia.setNroGuia(form.getNroGuia());
	 * guia.setFiscalizacion(form.getFiscalizacion());
	 * guia.setLugarCorte(form.getLugarCorte());
	 * //guia.setPermisionario(form.getPermisionario());
	 * guia.setFechaVencimiento
	 * (Fecha.stringDDMMAAAAToDate(form.getFechaVencimiento()));
	 * guia.setDistanciaAforoMovil(form.getDistanciaAforoMovil());
	 * guia.setNroExpediente(form.getNroExpediente());
	 * guia.setPeriodoForestal(form.getPeriodoForestal());
	 * guia.setDisposicionNro(form.getDisposicionNro());
	 * guia.setTipo(form.getTipo()); guia.setEstado(form.getEstado());
	 * guia.setEspecie(form.getEspecie());
	 * guia.setVolumenTotal(form.getVolumenTotal());
	 * guia.setCantidad(form.getCantidad()); guia.setImporte(form.getImporte());
	 * guia.setAforo(form.getAforo());
	 * guia.setInspFiscalizacion(form.getInspFiscalizacion());
	 * guia.setValorAforos(form.getValorAforos());
	 * guia.setObservaciones(form.getObservaciones());
	 * guia.setLocalidad(form.getLocalidad());
	 * guia.setFecha(Fecha.stringDDMMAAAAToDate(form.getFecha()));
	 * guia.setValesTransporte
	 * (normalizarValesTransporte(form.getValesTransporte()));
	 * guia.setBoletasDeposito
	 * (normalizarBoletasDeposito(form.getBoletasDeposito()));
	 * guia.setUsuario(usuario);
	 * 
	 * return guia; }
	 * 
	 * public static GuiaForestalDTO getGuiaForestalDTO(GuiaForestal guia,
	 * ActaMartilladoDTO actaDTO){
	 * 
	 * GuiaForestalDTO guiaDTO = new GuiaForestalDTO();
	 * 
	 * guiaDTO.setNroGuia(guia.getNroGuia()); guiaDTO.setFiscalizacion(actaDTO);
	 * guiaDTO.setLugarCorte(guia.getLugarCorte());
	 * //guiaDTO.setPermisionario(guia.getPermisionario().getId());
	 * guiaDTO.setFechaVencimiento(guia.getFechaVencimiento());
	 * guiaDTO.setDistanciaAforoMovil(guia.getDistanciaAforoMovil());
	 * guiaDTO.setNroExpediente(guia.getNroExpediente());
	 * guiaDTO.setPeriodoForestal(guia.getPeriodoForestal());
	 * guiaDTO.setDisposicionNro(guia.getDisposicionNro());
	 * guiaDTO.setTipo(guia.getTipo()); guiaDTO.setEstado(guia.getEstado());
	 * guiaDTO.setEspecie(guia.getEspecie());
	 * guiaDTO.setVolumenTotal(guia.getVolumenTotal());
	 * guiaDTO.setCantidad(guia.getCantidad());
	 * guiaDTO.setImporte(guia.getImporte()); guiaDTO.setAforo(guia.getAforo());
	 * guiaDTO.setInspFiscalizacion(guia.getInspFiscalizacion());
	 * guiaDTO.setValorAforos(guia.getValorAforos());
	 * guiaDTO.setObservaciones(guia.getObservaciones());
	 * guiaDTO.setLocalidad(guia.getLocalidad());
	 * guiaDTO.setFecha(guia.getFecha());
	 * guiaDTO.setValesTransporte(getValesTransporteDTO
	 * (guia.getValesTransporte(),guiaDTO));
	 * guiaDTO.setBoletasDeposito(getBoletasDesposito
	 * (guia.getBoletasDeposito(),guiaDTO));
	 * guiaDTO.setUsuario(guia.getUsuario());
	 * 
	 * return guiaDTO; }
	 * 
	 * public static GuiaForestalDTO getGuiaForestalDTO(GuiaForestal guia){
	 * 
	 * GuiaForestalDTO guiaDTO = new GuiaForestalDTO();
	 * 
	 * guiaDTO.setNroGuia(guia.getNroGuia());
	 * guiaDTO.setFiscalizacion(getActaMartilladoDTO
	 * (guia.getActaMartillado(),guiaDTO));
	 * guiaDTO.setLugarCorte(guia.getLugarCorte());
	 * //guiaDTO.setPermisionario(guia.getPermisionario().getId());
	 * guiaDTO.setFechaVencimiento(guia.getFechaVencimiento());
	 * guiaDTO.setDistanciaAforoMovil(guia.getDistanciaAforoMovil());
	 * guiaDTO.setNroExpediente(guia.getNroExpediente());
	 * guiaDTO.setPeriodoForestal(guia.getPeriodoForestal());
	 * guiaDTO.setDisposicionNro(guia.getDisposicionNro());
	 * guiaDTO.setTipo(guia.getTipo()); guiaDTO.setEstado(guia.getEstado());
	 * guiaDTO.setEspecie(guia.getEspecie());
	 * guiaDTO.setVolumenTotal(guia.getVolumenTotal());
	 * guiaDTO.setCantidad(guia.getCantidad());
	 * guiaDTO.setImporte(guia.getImporte()); guiaDTO.setAforo(guia.getAforo());
	 * guiaDTO.setInspFiscalizacion(guia.getInspFiscalizacion());
	 * guiaDTO.setValorAforos(guia.getValorAforos());
	 * guiaDTO.setObservaciones(guia.getObservaciones());
	 * guiaDTO.setLocalidad(guia.getLocalidad());
	 * guiaDTO.setFecha(guia.getFecha());
	 * guiaDTO.setValesTransporte(getValesTransporteDTO
	 * (guia.getValesTransporte(),guiaDTO));
	 * guiaDTO.setBoletasDeposito(getBoletasDesposito
	 * (guia.getBoletasDeposito(),guiaDTO));
	 * guiaDTO.setUsuario(guia.getUsuario());
	 * 
	 * return guiaDTO; }
	 * 
	 * public static List<BoletaDepositoDTO>
	 * getBoletasDesposito(List<BoletaDeposito> boletas, GuiaForestalDTO
	 * guiaDTO){
	 * 
	 * List<BoletaDepositoDTO> boletasDTO = new ArrayList<BoletaDepositoDTO>();
	 * 
	 * for (BoletaDeposito boleta : boletas) {
	 * 
	 * boletasDTO.add(getBoletaDesposito(boleta,guiaDTO)); }
	 * 
	 * return boletasDTO; }
	 * 
	 * public static BoletaDepositoDTO getBoletaDesposito(BoletaDeposito boleta,
	 * GuiaForestalDTO guiaDTO){
	 * 
	 * BoletaDepositoDTO boletaDTO = new BoletaDepositoDTO();
	 * 
	 * boletaDTO.setArea(boleta.getArea());
	 * boletaDTO.setConcepto(boleta.getConcepto());
	 * boletaDTO.setEfectivoCheque(boleta.getEfectivoCheque());
	 * boletaDTO.setFechaPago
	 * (Fecha.dateToStringDDMMAAAA(boleta.getFechaPago()));
	 * boletaDTO.setFechaVencimiento
	 * (Fecha.dateToStringDDMMAAAA(boleta.getFechaVencimiento()));
	 * boletaDTO.setMonto(boleta.getMonto());
	 * boletaDTO.setNumero(boleta.getNumero());
	 * boletaDTO.setGuiaForestal(guiaDTO);
	 * 
	 * return boletaDTO; }
	 * 
	 * public static List<ValeTransporteDTO>
	 * getValesTransporteDTO(List<ValeTransporte> vales, GuiaForestalDTO
	 * guiaDTO){
	 * 
	 * List<ValeTransporteDTO> valesDTO = new ArrayList<ValeTransporteDTO>();
	 * 
	 * for (ValeTransporte vale : vales) {
	 * 
	 * valesDTO.add(getValeTransporte(vale,guiaDTO)); }
	 * 
	 * return valesDTO; }
	 * 
	 * public static ValeTransporteDTO getValeTransporte(ValeTransporte vale,
	 * GuiaForestalDTO guiaDTO){
	 * 
	 * ValeTransporteDTO valeDTO = new ValeTransporteDTO();
	 * 
	 * valeDTO.setCantidadMts(vale.getCantidadMts());
	 * valeDTO.setDestino(vale.getDestino());
	 * valeDTO.setDominio(vale.getDominio());
	 * valeDTO.setEspecie(vale.getEspecie());
	 * valeDTO.setFecha(Fecha.dateToStringDDMMAAAA(vale.getFecha()));
	 * valeDTO.setFechaVencimiento
	 * (Fecha.dateToStringDDMMAAAA(vale.getFechaVencimiento()));
	 * valeDTO.setMarca(vale.getMarca());
	 * valeDTO.setNroPiezas(vale.getNroPiezas());
	 * valeDTO.setNumero(vale.getNumero()); valeDTO.setOrigen(vale.getOrigen());
	 * valeDTO.setProducto(vale.getProducto());
	 * valeDTO.setGuiaForestal(guiaDTO);
	 * valeDTO.setVehiculo(vale.getVehiculo());
	 * 
	 * return valeDTO; }
	 * 
	 * private static List<BoletaDepositoDTO>
	 * normalizarBoletasDeposito(List<BoletaDepositoDTO> boletasDeposito) {
	 * List<BoletaDepositoDTO> boletasAEliminar = new
	 * ArrayList<BoletaDepositoDTO>(); for (BoletaDepositoDTO boleta :
	 * boletasDeposito) {
	 * 
	 * if(boleta.getNumero() == 0){ boletasAEliminar.add(boleta); } }
	 * boletasDeposito.removeAll(boletasAEliminar);
	 * 
	 * return boletasDeposito; }
	 * 
	 * private static List<ValeTransporteDTO>
	 * normalizarValesTransporte(List<ValeTransporteDTO> valesTransporte) {
	 * 
	 * List<ValeTransporteDTO> valesAEliminar = new
	 * ArrayList<ValeTransporteDTO>(); for (ValeTransporteDTO vale :
	 * valesTransporte) {
	 * 
	 * if(vale.getNumero() == 0){ valesAEliminar.add(vale); } }
	 * valesTransporte.removeAll(valesAEliminar);
	 * 
	 * return valesTransporte; }
	 */
}
