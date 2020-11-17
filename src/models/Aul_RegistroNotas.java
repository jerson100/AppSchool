package models;

public class Aul_RegistroNotas {
	
	private int idRegistroNota;
	private int idSecCur;
	private int idCuenta;
	private String alumno;
	private String periodo;
	private String nota;
	private String descNotas;
	private String idPeriodoNotas;
	
	public String getIdPeriodoNotas() {
		return idPeriodoNotas;
	}
	public void setIdPeriodoNotas(String idPeriodoNotas) {
		this.idPeriodoNotas = idPeriodoNotas;
	}
	public int getIdRegistroNota() {
		return idRegistroNota;
	}
	public void setIdRegistroNota(int idRegistroNota) {
		this.idRegistroNota = idRegistroNota;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public int getIdSecCur() {
		return idSecCur;
	}
	public void setIdSecCur(int idSecCur) {
		this.idSecCur = idSecCur;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getDescNotas() {
		return descNotas;
	}
	public void setDescNotas(String descNotas) {
		this.descNotas = descNotas;
	}
	@Override
	public String toString() {
		return "Aul_RegistroNotas [idRegistroNota=" + idRegistroNota + ", idSecCur=" + idSecCur + ", idCuenta="
				+ idCuenta + ", alumno=" + alumno + ", periodo=" + periodo + ", nota=" + nota + ", descNotas="
				+ descNotas + ", idPeriodoNotas=" + idPeriodoNotas + "]";
	}
	
}
