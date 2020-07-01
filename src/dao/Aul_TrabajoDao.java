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
import interfaces.IAul_Trabajo;
import models.Aul_Clases;
import models.Aul_Trabajo;
import utilidades.Conexion;

public class Aul_TrabajoDao implements IAul_Trabajo {

	@Override
	public void create(Aul_Trabajo t) throws NotCreated {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			
			ps = c.prepareStatement("{call dbo.sp_TRABAJOS_ins_upd(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			ps.setInt(1, t.getIdTrabajo());
			
			ps.setInt(2, t.getIdSecCur());
			
			ps.setString(3, t.getCodTrabajo());
			
			ps.setString(4, t.getDescTrabajo());
			
			ps.setString(5, t.getFechaIni()+":00");
			
			ps.setString(6, t.getFechaFin()+":00");
			
			ps.setString(7, t.getRutaArchivo());
			
			ps.setString(8, t.getExtensionArchivo());
			
			ps.setString(9, t.getNombreArchivo());
			
			ps.setInt(10, t.isFlagLimite()?1:0);
			
			ps.setInt(11, t.getDiasLimite());
			
			ps.setInt(12, t.getIdUsuario());
			
			ps.setInt(13, t.isReplicar_todos()?1:0);
			
			ps.setInt(14, /*t.isReplicar_solo()?1:*/0);
			
			if(ps.executeUpdate() == 0) {
				
				throw new NotCreated("No se pudo crear el trabajo");
				
			}
			
		} catch (SQLException e) {
			
			throw new NotCreated("No se pudo crear el trabajo");
			
		} finally {
			
			Conexion.cerrarConexion(conexion, ps, rs);
			
		}
	}

	@Override
	public Aul_Trabajo read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_Trabajo t) throws NotUpdated {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			
			ps = c.prepareStatement("{call dbo.sp_TRABAJOS_ins_upd(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			ps.setInt(1, t.getIdTrabajo());
			
			ps.setInt(2, t.getIdSecCur());
			
			ps.setString(3, t.getCodTrabajo());
			
			ps.setString(4, t.getDescTrabajo());
			
			ps.setString(5, t.getFechaIni()+":00");
			
			ps.setString(6, t.getFechaFin()+":00");
			
			ps.setString(7, t.getRutaArchivo());
			
			ps.setString(8, t.getExtensionArchivo());
			
			ps.setString(9, t.getNombreArchivo());
			
			ps.setInt(10, t.isFlagLimite()?1:0);
			
			ps.setInt(11, t.getDiasLimite());
			
			ps.setInt(12, t.getIdUsuario());
			
			ps.setInt(13, t.isReplicar_todos()?1:0);
			
			ps.setInt(14, /*t.isReplicar_solo()?1:*/0);
			
			if(ps.executeUpdate() == 0) {
				
				throw new NotUpdated("No se pudo actualizar el trabajo");
				
			}
				
		} catch (SQLException e) {
			
			throw new NotUpdated("No se pudo actualizar el trabajo");
			
		} finally {
			
			Conexion.cerrarConexion(conexion, ps, rs);
			
		}
		
	}

	@Override
	public void delete(Aul_Trabajo t) throws NotDeleted {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			
			ps= c.prepareStatement("{call dbo.sp_TRABAJOS_del(?,?,?,?)}");
			
			ps.setInt(1, t.getIdTrabajo());
			
			ps.setInt(2, t.getIdUsuario());
			
			ps.setInt(3, t.isReplicar_todos() ? 1 : 0);
			
			ps.setInt(4, /*t.isReplicar_solo() ? 1 : */0);
		
			if(ps.executeUpdate() == 0) {
				
				throw new NotDeleted("No se pudo eliminar el trabajo");
				
			}
			
		} catch (SQLException e) {
			
			throw new NotDeleted("No se pudo eliminar el trabajo");
			
		} finally {
			
			Conexion.cerrarConexion(conexion, ps, rs);
			
		}
		
	}

	@Override
	public List<Aul_Trabajo> all() throws NotAll {

		return null;

	}

	@Override
	public List<Aul_Trabajo> all(int idSecCur) throws NotAll {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();

		Connection c = conexion.getConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;

		List<Aul_Clases> list = null;

		try {

			ps = c.prepareStatement("{call dbo.sp_CLASES_get(?)}");

			ps.setInt(1, idSecCur);

			rs = ps.executeQuery();

			if (rs.next()) {

				list = new ArrayList<>();

				Aul_Clases aulC = new Aul_Clases();

				aulC.setIdClass(rs.getInt(1));
				aulC.setCodClase(rs.getString(2));
				aulC.setDescClase(rs.getString(3));
				aulC.setLink(rs.getString(4));
				aulC.setFechaClaseS(rs.getString(5));
				aulC.setHoraClaseS(rs.getString(6));
				aulC.setFechaClase(rs.getString(7));
				aulC.setHoraClase(rs.getString(8));

				list.add(aulC);

				while (rs.next()) {

					aulC = new Aul_Clases();

					aulC.setIdClass(rs.getInt(1));
					aulC.setCodClase(rs.getString(2));
					aulC.setDescClase(rs.getString(3));
					aulC.setLink(rs.getString(4));
					aulC.setFechaClaseS(rs.getString(5));
					aulC.setHoraClaseS(rs.getString(6));
					aulC.setFechaClase(rs.getString(7));
					aulC.setHoraClase(rs.getString(8));
					
					list.add(aulC);

				}

			} else {

				throw new NotAll("Este contenido no tiene clases asignadas");

			}

		} catch (SQLException e) {
			
			throw new NotAll("Este contenido no tiene clases asignadas");

		} finally {

			Conexion.cerrarConexion(conexion, ps, rs);

		}

		return null;
	}

	@Override
	public List<Aul_Trabajo> all(int idSecCur, int idCuenta) throws NotAll {
		
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
				aulC.setRutaArchivo2(rs.getString(23));
				System.out.println("ruta: "+rs.getString(23));
				aulC.setNombreArchivo2(rs.getString(24));
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
					aulC.setRutaArchivo2(rs.getString(23));
					aulC.setNombreArchivo2(rs.getString(24));
					
					list.add(aulC);

				}

			} else {

				throw new NotAll("Este contenido no tiene trabajos");

			}

		} catch (SQLException e) {
			
			throw new NotAll("Este contenido no tiene trabajos");

		} finally {

			Conexion.cerrarConexion(conexion, ps, rs);

		}

		return list;
		
	}
	
}
