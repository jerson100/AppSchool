package models;

public class Aul_PeriodoNotas {

	private int idPeriodoNotas;
	private Aul_Periodo periodo;
	private Aul_DescNotas notas;
	private int flagEstado;
	
	public int getIdPeriodoNotas() {
		return idPeriodoNotas;
	}
	public void setIdPeriodoNotas(int idPeriodoNotas) {
		this.idPeriodoNotas = idPeriodoNotas;
	}
	public Aul_Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Aul_Periodo periodo) {
		this.periodo = periodo;
	}
	public Aul_DescNotas getNotas() {
		return notas;
	}
	public void setNotas(Aul_DescNotas notas) {
		this.notas = notas;
	}
	public int getFlagEstado() {
		return flagEstado;
	}
	public void setFlagEstado(int flagEstado) {
		this.flagEstado = flagEstado;
	}
	
}
