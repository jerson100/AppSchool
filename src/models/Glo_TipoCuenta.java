package models;

public class Glo_TipoCuenta extends Glo_Comun {
	
	private String codTipoCuenta;
	private String descTipoCuenta;
	
	public String getCodTipoPersona() {
		return codTipoCuenta;
	}
	public void setCodTipoPersona(String codTipoPersona) {
		this.codTipoCuenta = codTipoPersona;
	}
	public String getTipoPersonaDescripcion() {
		return descTipoCuenta;
	}
	public void setTipoPersonaDescripcion(String tipoPersonaDescripcion) {
		this.descTipoCuenta = tipoPersonaDescripcion;
	}
	
}
