package models;

public class Aul_Horario extends Aul_Comun{

	private int idHorario;
	private int idSemana;
	private String horaIni;
	private String horaFin;
	
	public int getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}
	public int getIdSemana() {
		return idSemana;
	}
	public void setIdSemana(int idSemana) {
		this.idSemana = idSemana;
	}
	public String getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	
	
}
