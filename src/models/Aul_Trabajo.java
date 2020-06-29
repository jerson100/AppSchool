package models;

public class Aul_Trabajo extends Aul_Comun{

	private int idTrabajo;
	private String codTrabajo;
	private String descTrabajo;
	private String fechaIni;
	private String fechaFin;
	private String fechaIniS;
	private String fechaFinS;
	private String rutaArchivo;
	private String extensionArchivo;
	private String nombreArchivo;
	private boolean flagLimite;
	private int flagLimite2;
	private int flagLimite1;
	private short diasLimite;
	private String img;
	private String color;
	private String notas;
	private String link;
	private String color2;
	private String icono2;
	
	public String getColor2() {
		return color2;
	}
	public void setColor2(String color2) {
		this.color2 = color2;
	}
	public String getIcono2() {
		return icono2;
	}
	public void setIcono2(String icono2) {
		this.icono2 = icono2;
	}
	public String getLink2() {
		return link2;
	}
	public void setLink2(String link2) {
		this.link2 = link2;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	private String link2;
	private String comentario;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getFlagLimite1() {
		return flagLimite1;
	}
	public void setFlagLimite1(int flagLimite1) {
		this.flagLimite1 = flagLimite1;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getFlagLimite2() {
		return flagLimite2;
	}
	public int isFlagLimite2() {
		return flagLimite2;
	}
	public void setFlagLimite2(int flagLimite2) {
		this.flagLimite2 = flagLimite2;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getIdTrabajo() {
		return idTrabajo;
	}
	public void setIdTrabajo(int idTrabajo) {
		this.idTrabajo = idTrabajo;
	}
	
	public String getCodTrabajo() {
		return codTrabajo;
	}
	public void setCodTrabajo(String codTrabajo) {
		this.codTrabajo = codTrabajo;
	}
	public String getDescTrabajo() {
		return descTrabajo;
	}
	public void setDescTrabajo(String descTrabajo) {
		this.descTrabajo = descTrabajo;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFechaIniS() {
		return fechaIniS;
	}
	public void setFechaIniS(String fechaIniS) {
		this.fechaIniS = fechaIniS;
	}
	public String getFechaFinS() {
		return fechaFinS;
	}
	public void setFechaFinS(String fechaFinS) {
		this.fechaFinS = fechaFinS;
	}
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public boolean isFlagLimite() {
		return flagLimite;
	}
	public void setFlagLimite(boolean flagLimite) {
		this.flagLimite = flagLimite;
	}
	public short getDiasLimite() {
		return diasLimite;
	}
	public void setDiasLimite(short diasLimite) {
		this.diasLimite = diasLimite;
	}
	
}
