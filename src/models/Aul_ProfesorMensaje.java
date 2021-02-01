package models;

import java.util.Date;

public class Aul_ProfesorMensaje extends Glo_Persona{

	private int idMensajeProfesor;
	private int idSecCur;
	private String descMensaje;
	private int idUsuario_c;
	private Date fecha_c;
	private int idUsuario_m;
	private Date fecha_m;
	private int idUsuario_e;
	private Date fecha_e;
	private boolean flagEstado;
	
	public int getIdMensajeProfesor() {
		return idMensajeProfesor;
	}
	
	public void setIdMensajeProfesor(int idMensajeProfesor) {
		this.idMensajeProfesor = idMensajeProfesor;
	}
	public int getIdSecCur() {
		return idSecCur;
	}
	public void setIdSecCur(int idSecCur) {
		this.idSecCur = idSecCur;
	}
	public String getDescMensaje() {
		return descMensaje;
	}
	public void setDescMensaje(String descMensaje) {
		this.descMensaje = descMensaje;
	}
	public int getIdUsuario_c() {
		return idUsuario_c;
	}
	public void setIdUsuario_c(int idUsuario_c) {
		this.idUsuario_c = idUsuario_c;
	}
	public Date getFecha_c() {
		return fecha_c;
	}
	public void setFecha_c(Date fecha_c) {
		this.fecha_c = fecha_c;
	}
	public int getIdUsuario_m() {
		return idUsuario_m;
	}
	public void setIdUsuario_m(int idUsuario_m) {
		this.idUsuario_m = idUsuario_m;
	}
	public Date getFecha_m() {
		return fecha_m;
	}
	public void setFecha_m(Date fecha_m) {
		this.fecha_m = fecha_m;
	}
	public int getIdUsuario_e() {
		return idUsuario_e;
	}
	public void setIdUsuario_e(int idUsuario_e) {
		this.idUsuario_e = idUsuario_e;
	}
	public Date getFecha_e() {
		return fecha_e;
	}
	public void setFecha_e(Date fecha_e) {
		this.fecha_e = fecha_e;
	}
	public boolean isFlagEstado() {
		return flagEstado;
	}
	public void setFlagEstado(boolean flagEstado) {
		this.flagEstado = flagEstado;
	}
	
}
