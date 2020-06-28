package models;

import java.sql.Blob;
import java.util.Date;

public class Glo_Persona extends Glo_Comun {

	/*private int idPersona;*/
	private String nombres;
	private String apPaterno;
	private String apMaterno;
	private String nombres2;
	private String documento;
	private int idSexo;
	private Date fecNacimiento;
	private Blob imagen;
	private String email;
	private String telefono;
	private String celular;
	
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getNombres2() {
		return nombres2;
	}
	public void setNombres2(String nombres2) {
		this.nombres2 = nombres2;
	}
	public int getIdSexo() {
		return idSexo;
	}
	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}
	public Date getFecNacimiento() {
		return fecNacimiento;
	}
	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}
	public Blob getImagen() {
		return imagen;
	}
	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
}
