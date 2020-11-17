package models;

public class Aul_Periodo {
	private int idPeriodo;
	private Aul_Ciclo ciclo;
	private String codMes;
	private String descPeriodo;
	private int flagEstado;
	
	public int getIdPeriodo() {
		return idPeriodo;
	}
	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	
	public Aul_Ciclo getCiclo() {
		return ciclo;
	}
	public void setCiclo(Aul_Ciclo ciclo) {
		this.ciclo = ciclo;
	}
	public String getCodMes() {
		return codMes;
	}
	public void setCodMes(String codMes) {
		this.codMes = codMes;
	}
	public String getDescPeriodo() {
		return descPeriodo;
	}
	public void setDescPeriodo(String descPeriodo) {
		this.descPeriodo = descPeriodo;
	}
	public int getFlagEstado() {
		return flagEstado;
	}
	public void setFlagEstado(int flagEstado) {
		this.flagEstado = flagEstado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descPeriodo == null) ? 0 : descPeriodo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aul_Periodo other = (Aul_Periodo) obj;
		if (descPeriodo == null) {
			if (other.descPeriodo != null)
				return false;
		} else if (!descPeriodo.equals(other.descPeriodo))
			return false;
		return true;
	}
	
}
