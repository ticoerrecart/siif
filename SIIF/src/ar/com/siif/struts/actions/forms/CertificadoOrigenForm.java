package ar.com.siif.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siif.dto.CertificadoOrigenDTO;

public class CertificadoOrigenForm extends ActionForm {

	CertificadoOrigenDTO certificadoOrigen;

	public CertificadoOrigenForm(){
		
		certificadoOrigen = new CertificadoOrigenDTO();
		
	}
	
	public CertificadoOrigenDTO getCertificadoOrigen() {
		return certificadoOrigen;
	}

	public void setCertificadoOrigen(CertificadoOrigenDTO certificadoOrigen) {
		this.certificadoOrigen = certificadoOrigen;
	}
	
	
	
}
