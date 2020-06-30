package models;

public class Aul_Contenido extends Aul_Comun{

	private int idContenido;
	/*private int idSecCurPro;*/
	private String descContenido;
	private String codContenido;
	
	public int getIdContenido() {
		return idContenido;
	}

	public String getCodContenido() {
		return codContenido;
	}

	public void setCodContenido(String codContenido) {
		this.codContenido = codContenido;
	}

	public void setIdContenido(int idContenido) {
		this.idContenido = idContenido;
	}
	public String getDescContenido() {
		return descContenido;
	}
	public void setDescContenido(String descContenido) {
		this.descContenido = descContenido;
	}
}
