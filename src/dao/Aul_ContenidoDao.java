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
import interfaces.IAul_Contenido;
import models.Aul_Contenido;
import utilidades.Conexion;

public class Aul_ContenidoDao implements IAul_Contenido {
	
	@Override
	public void create(Aul_Contenido t) throws NotCreated {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			
			ps = c.prepareStatement("{call dbo.sp_CONTENIDO_ins_upd(?,?,?,?,?,?,?,?)}");
			
			ps.setInt(1, t.getIdContenido());
			
			ps.setInt(2, t.getIdSecCurPro());
			
			ps.setString(3, t.getDescContenido());
			
			ps.setString(4, t.getLink());
			
			ps.setInt(5, t.getIdUsuario());
			
			ps.setString(6, t.getCodContenido());
			
			ps.setInt(7, t.isReplicar_todos()?1:0);
			//System.out.println("idSecCurpro: "+t.getIdSecCurPro()+" "+t.getDescContenido());
			
			ps.setInt(8, /*t.isReplicar_solo()?1:*/0);
			
			if(ps.executeUpdate() == 0) {
				
				throw new NotCreated("No se pudo crear el contenido");
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new NotCreated("Error inesperado");
			
		} finally {
			
			Conexion.cerrarConexion(conexion, ps, rs);
			
		}
		
	}

	@Override
	public Aul_Contenido read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_Contenido t) throws NotUpdated {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			
			ps = c.prepareStatement("{call dbo.sp_CONTENIDO_ins_upd(?,?,?,?,?,?,?,?)}");
			
			ps.setInt(1, t.getIdContenido());
			
			ps.setInt(2, t.getIdSecCurPro());
			
			ps.setString(3, t.getDescContenido());
			
			ps.setString(4, t.getLink());
			
			ps.setInt(5, t.getIdUsuario());
			
			ps.setString(6, t.getCodContenido());
			
			ps.setInt(7, t.isReplicar_todos()?1:0);
			
			ps.setInt(8, /*t.isReplicar_solo()?1:*/0);
			 
			if(ps.executeUpdate() == 0) {
				
				throw new NotUpdated("No se pudo crear el contenido");
				
			}
			
		} catch (SQLException e) {
	
			throw new NotUpdated("Error inesperado");
			
		} finally {
			
			Conexion.cerrarConexion(conexion, ps, rs);
			
		}
	}

	@Override
	public void delete(Aul_Contenido t) throws NotDeleted {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			
			ps= c.prepareStatement("{call dbo.sp_CONTENIDO_del(?,?,?,?)}");
			
			ps.setInt(1, t.getIdContenido());
			
			ps.setInt(2, t.getIdUsuario());
			
			ps.setInt(3, t.isReplicar_todos() ? 1 : 0);
			
			ps.setInt(4, /*t.isReplicar_solo() ? 1 : */0);
		
			if(ps.executeUpdate() == 0) {
				
				throw new NotDeleted("No se pudo eliminar el contenido");
				
			}
			
		} catch (SQLException e) {
			
			throw new NotDeleted("Error inesperado");
			
		} finally {
			
			Conexion.cerrarConexion(conexion, ps, rs);
			
		}
	}

	@Override
	public List<Aul_Contenido> all() throws NotAll {
		
		return null;
		
	}

	@Override
	public List<Aul_Contenido> all(int idSecCurPro) throws NotAll {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		List<Aul_Contenido> list = null;
		
		try {
		
			ps= c.prepareStatement("{call dbo.sp_CONTENIDO_get(?)}");
			
			ps.setInt(1, idSecCurPro);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				list = new ArrayList<>();
				
				Aul_Contenido aulC = new Aul_Contenido();
				
				aulC.setIdContenido(rs.getInt(1));
				aulC.setCodContenido(rs.getString(2));
				aulC.setDescContenido(rs.getString(3));
				aulC.setLink(rs.getString(4));
				
				list.add(aulC);
				//rs.previous();
				
				while(rs.next()) {
					
					aulC = new Aul_Contenido();
					
					aulC.setIdContenido(rs.getInt(1));
					aulC.setCodContenido(rs.getString(2));
					aulC.setDescContenido(rs.getString(3));
					aulC.setLink(rs.getString(4));
					
					list.add(aulC);
					
				}
				
			}else {
			
				throw new NotAll("El curso no tiene contenidos");
				
			}
		
		} catch (SQLException e) {
			
			throw new NotAll("Póngase en contacto con su administrador");
			
		} finally {
			
			Conexion.cerrarConexion(conexion,ps,rs);
			
		}
		
		return list;
		
	}

}