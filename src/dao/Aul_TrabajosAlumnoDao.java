package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.NotAll;
import exceptions.NotCreated;
import exceptions.NotDeleted;
import exceptions.NotFound;
import exceptions.NotUpdated;
import interfaces.IAul_TrabajosAlumno;
import models.Aul_TrabajosAlumno;

public class Aul_TrabajosAlumnoDao implements IAul_TrabajosAlumno {


	@Override
	public void create(Aul_TrabajosAlumno t) throws NotCreated {
		
		try {
			
			try (Connection db = new ConexionSqlServer().getConexion()) {

				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_TRABAJOSALUMNO_ins_upd(?,?,?,?,?,?,?)}")) {

					ps.setInt(1, t.getIdTraAlu());
					
					ps.setInt(2, t.getIdCuenta());
					
					ps.setInt(3, t.getIdTrabajo());
					
					ps.setString(4, t.getRutaArchivo());
					
					ps.setString(5, t.getExtensionArchivo());
					
					ps.setString(6, t.getNombreArchivo());
					
					ps.setInt(7, t.getIdUsuario());
					
					if(ps.executeUpdate() == 0) {
						
						throw new NotCreated("No se pudo crear el trabajo");
						
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			
			throw new NotCreated("No se pudo crear el trabajo");
			
		} 
		
	}

	@Override
	public Aul_TrabajosAlumno read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_TrabajosAlumno t) throws NotUpdated {
		
		try {
			
			try (Connection db = new ConexionSqlServer().getConexion()) {

				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_TRABAJOSALUMNO_ins_upd_by_PROFESOR(?,?,?,?,?,?)}")) {
			
					ps.setInt(1, t.getIdTraAlu());
					
					ps.setInt(2, t.getIdCuenta());
					
					ps.setInt(3, t.getIdTrabajo());
					
					ps.setString(4, t.getNotaTrabajo());
					
					ps.setString(5, t.getComentario());
					
					ps.setInt(6, t.getIdUsuario());
					
					
					if(ps.executeUpdate() == 0) {
						
						throw new NotUpdated("No se pudo actualizar el trabajo");
						
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			
			throw new NotUpdated("No se pudo actualizar el trabajo");
			
		}
		
	}

	@Override
	public void delete(Aul_TrabajosAlumno t) throws NotDeleted {
		
		try {
			
			try (Connection db = new ConexionSqlServer().getConexion()) {

				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_TRABAJOSALUMNO_del(?,?)}")) {
			
					ps.setInt(1, t.getIdTraAlu());
					
					ps.setInt(2, t.getIdUsuario());
					
					if(ps.executeUpdate() == 0) {
						
						throw new NotDeleted("No se pudo eliminar su trabajo");
						
					}
				
				}
			
			}
			
		} catch (SQLException e) {
			
			throw new NotDeleted("No se pudo eliminar su trabajo");
			
		} 
		
	}

	@Override
	public List<Aul_TrabajosAlumno> all() throws NotAll {

		return null;

	}

	@Override
	public List<Aul_TrabajosAlumno> all(int idTrabajo) throws NotAll {

		List<Aul_TrabajosAlumno> list = null;

		try {

			try (Connection db = new ConexionSqlServer().getConexion()) {

				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_TRABAJOSALUMNO_by_IDSECCUR_get(?)}")) {
			
					ps.setInt(1, idTrabajo);
					
					try (ResultSet rs = ps.executeQuery()){
						
						if (rs.next()) {

							list = new ArrayList<>();

							Aul_TrabajosAlumno aulC = new Aul_TrabajosAlumno();

							aulC.setIdCuenta(rs.getInt(1));
							aulC.setIdTraAlu(rs.getInt(2)); 
							aulC.setAlumno(rs.getString(3));
							aulC.setUltimaModificacion(rs.getString(4));
							aulC.setLink(rs.getString(5));
							aulC.setIcono(rs.getString(6));
							aulC.setColor2(rs.getString(7));
							aulC.setNotaTrabajo(rs.getString(8));
							aulC.setComentario(rs.getString(9));
							
							list.add(aulC);

							while (rs.next()) {

								aulC = new Aul_TrabajosAlumno();

								aulC.setIdCuenta(rs.getInt(1));
								aulC.setIdTraAlu(rs.getInt(2)); 
								aulC.setAlumno(rs.getString(3));
								aulC.setUltimaModificacion(rs.getString(4));
								aulC.setLink(rs.getString(5));
								aulC.setIcono(rs.getString(6));
								aulC.setColor2(rs.getString(7));
								aulC.setNotaTrabajo(rs.getString(8));
								aulC.setComentario(rs.getString(9));
								
								list.add(aulC);

							}

						} else {

							throw new NotAll("Este trabajo no tiene alumnos asignados");

						}
						
					}
					
				}
				
			}
			
		} catch (SQLException e) {

			throw new NotAll("Este trabajo no tiene alumnos asignados");

		}

		return list;
		
	}
	
}
