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
import models.Aul_Clases;
import models.Aul_TrabajosAlumno;
import utilidades.Conexion;

public class Aul_TrabajosAlumnoDao implements IAul_TrabajosAlumno {


	@Override
	public void create(Aul_TrabajosAlumno t) throws NotCreated {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			
			ps = c.prepareStatement("{call dbo.sp_TRABAJOSALUMNO_ins_upd(?,?,?,?,?,?,?)}");
			
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
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new NotCreated("No se pudo crear el trabajo");
			
		} finally {
			
			Conexion.cerrarConexion(conexion, ps, rs);
			
		}
	}

	@Override
	public Aul_TrabajosAlumno read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_TrabajosAlumno t) throws NotUpdated {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			/*
			ps = c.prepareStatement("{call dbo.sp_TRABAJOSALUMNO_ins_upd(?,?,?,?,?,?,?)}");
			
			ps.setInt(1, t.getIdTraAlu());
			
			ps.setInt(2, t.getIdCuenta());
			
			ps.setInt(3, t.getIdTrabajo());
			
			ps.setString(4, t.getRutaArchivo());
			
			ps.setString(5, t.getExtensionArchivo());
			
			ps.setString(6, t.getNombreArchivo());
			
			ps.setInt(7, t.getIdUsuario());*/
			
			ps = c.prepareStatement("{call dbo.sp_TRABAJOSALUMNO_ins_upd_by_PROFESOR(?,?,?,?,?,?)}");
			
			ps.setInt(1, t.getIdTraAlu());
			
			ps.setInt(2, t.getIdCuenta());
			
			ps.setInt(3, t.getIdTrabajo());
			
			ps.setString(4, t.getNotaTrabajo());
			
			ps.setString(5, t.getComentario());
			
			ps.setInt(6, t.getIdUsuario());
			
			
			if(ps.executeUpdate() == 0) {
				
				throw new NotUpdated("No se pudo actualizar el trabajo");
				
			}
			
				
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new NotUpdated("No se pudo actualizar el trabajo");
			
		} finally {
			
			Conexion.cerrarConexion(conexion, ps, rs);
			
		}
		
	}

	@Override
	public void delete(Aul_TrabajosAlumno t) throws NotDeleted {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			
			ps= c.prepareStatement("{call dbo.sp_TRABAJOSALUMNO_del(?,?)}");
			
			ps.setInt(1, t.getIdTraAlu());
			
			ps.setInt(2, t.getIdUsuario());
			
			if(ps.executeUpdate() == 0) {
				
				throw new NotDeleted("No se pudo eliminar su trabajo");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotDeleted("No se pudo eliminar su trabajo");
			
		} finally {
			
			Conexion.cerrarConexion(conexion, ps, rs);
			
		}
		
	}

	@Override
	public List<Aul_TrabajosAlumno> all() throws NotAll {

		return null;

	}

	@Override
	public List<Aul_TrabajosAlumno> all(int idTrabajo) throws NotAll {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();

		Connection c = conexion.getConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;

		List<Aul_TrabajosAlumno> list = null;

		try {

			ps = c.prepareStatement("{call dbo.sp_TRABAJOSALUMNO_by_IDSECCUR_get(?)}");

			ps.setInt(1, idTrabajo);

			rs = ps.executeQuery();

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

		} catch (SQLException e) {

			e.printStackTrace();

			throw new NotAll("Este trabajo no tiene alumnos asignados");

		} finally {

			Conexion.cerrarConexion(conexion, ps, rs);

		}

		return list;
	}

	/*
	public List<Aul_TrabajosAlumno> all(int idSecCur, int idCuenta) throws NotAll {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();

		Connection c = conexion.getConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;

		List<Aul_Trabajo> list = null;

		try {

			ps = c.prepareStatement("{call dbo.sp_TRABAJOS_get(?,?)}");

			ps.setInt(1, idSecCur);
			
			ps.setInt(2, idCuenta);

			rs = ps.executeQuery();

			if (rs.next()) {

				list = new ArrayList<>();

				Aul_Trabajo aulC = new Aul_Trabajo();

				aulC.setIdTrabajo(rs.getInt(1));
				aulC.setIdTraAlu(rs.getInt(2));
				aulC.setCodTrabajo(rs.getString(3));
				aulC.setDescTrabajo(rs.getString(4));
				aulC.setFechaIniS(rs.getString(5));
				aulC.setFechaFinS(rs.getString(6));
				aulC.setFechaIni(rs.getString(7));
				aulC.setFechaFin(rs.getString(8));
				aulC.setFlagLimite(rs.getByte(9)==1);
				aulC.setDiasLimite((short)rs.getInt(10));
				aulC.setRutaArchivo(rs.getString(11));
				aulC.setNombreArchivo(rs.getString(12));
				aulC.setIcono(rs.getString(13));
				aulC.setColor(rs.getString(14));
				aulC.setNotas(rs.getString(15));
				aulC.setFlagLimite1(rs.getInt(16));
				aulC.setFlagLimite2(rs.getInt(17));
				aulC.setImg(rs.getString(13));
				aulC.setLink(rs.getString(18));
				aulC.setLink2(rs.getString(19));
				aulC.setIcono2(rs.getString(20));
				aulC.setColor2(rs.getString(21));
				aulC.setComentario(rs.getString(22));
				aulC.setNombreArchivo2(rs.getString(23));
				aulC.setRutaArchivo2(rs.getString(24));
				System.out.println(rs.getString(23));
				list.add(aulC);

				while (rs.next()) {

					aulC = new Aul_Trabajo();

					aulC.setIdTrabajo(rs.getInt(1));
					aulC.setIdTraAlu(rs.getInt(2));
					aulC.setCodTrabajo(rs.getString(3));
					aulC.setDescTrabajo(rs.getString(4));
					aulC.setFechaIniS(rs.getString(5));
					aulC.setFechaFinS(rs.getString(6));
					aulC.setFechaIni(rs.getString(7));
					aulC.setFechaFin(rs.getString(8));
					aulC.setFlagLimite(rs.getByte(9)==1);
					aulC.setDiasLimite((short)rs.getInt(10));
					aulC.setRutaArchivo(rs.getString(11));
					aulC.setNombreArchivo(rs.getString(12));
					aulC.setIcono(rs.getString(13));
					aulC.setColor(rs.getString(14));
					aulC.setNotas(rs.getString(15));
					aulC.setFlagLimite1(rs.getInt(16));
					aulC.setFlagLimite2(rs.getInt(17));
					aulC.setImg(rs.getString(13));
					aulC.setLink(rs.getString(18));
					aulC.setLink2(rs.getString(19));
					aulC.setIcono2(rs.getString(20));
					aulC.setColor2(rs.getString(21));
					aulC.setComentario(rs.getString(22));
					aulC.setNombreArchivo2(rs.getString(23));
					aulC.setRutaArchivo2(rs.getString(24));
					
					list.add(aulC);

				}

			} else {

				throw new NotAll("Este contenido no tiene trabajos");

			}

		} catch (SQLException e) {

			e.printStackTrace();

			throw new NotAll("Este contenido no tiene trabajos");

		} finally {

			Conexion.cerrarConexion(conexion, ps, rs);

		}

		return list;
		
	}*/
	
}
