package models;

public class Aul_TrabajosAlumno extends Aul_Comun{

	private int idCuenta;
	private int idTrabajo;
	private String rutaArchivo;
	private String extensionArchivo;
	private String nombreArchivo;
	private String notaTrabajo;
	private String alumno;
	private String ultimaModificacion;
	private String comentario;
	
	public int getIdCuenta() {
		return idCuenta;
	}
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	public String getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(String ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public int getIdTrabajo() {
		return idTrabajo;
	}
	public void setIdTrabajo(int idTrabajo) {
		this.idTrabajo = idTrabajo;
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
	public String getNotaTrabajo() {
		return notaTrabajo;
	}
	public void setNotaTrabajo(String notaTrabajo) {
		this.notaTrabajo = notaTrabajo;
	}
	
	
	
}
