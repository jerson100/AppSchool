package models;

public class Aul_TrabajosAlumno extends Aul_Comun{

	private int idCuenta;
	private int idTrabajo;
	private String rutaArchivo;
	private String extensionArchivo;
	private String nombreArchivo;
	private String notaTrabajo;
	
	public int getIdCuenta() {
		return idCuenta;
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
