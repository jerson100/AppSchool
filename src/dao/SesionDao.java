package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import exceptions.NotAll;
import exceptions.NotCreated;
import exceptions.NotDeleted;
import exceptions.NotFound;
import exceptions.NotUpdated;
import interfaces.ISesion;
import models.Sesion;
import utilidades.Conexion;

public class SesionDao implements ISesion{
	
	
	public SesionDao() {
	}
	
	@Override
	public void create(Sesion t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sesion read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Sesion t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Sesion t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Sesion> all() throws NotAll {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sesion acceder(String usuario, String contrasena) throws NotFound {
	
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		Sesion sesion = null;
		
		try {
		
			ps= c.prepareStatement("{call dbo.sp_SESION_get(?,?)}");
			
			ps.setString(1, usuario);
			
			ps.setString(2, contrasena);
		
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				sesion = new Sesion();
				sesion.setIdUsuario(rs.getInt(1));
				sesion.setUsuario(rs.getString(2));
				sesion.setNombres(rs.getString(3));
				sesion.setApPaterno(rs.getString(4));
				sesion.setApMaterno(rs.getString(5));
				sesion.setIdCuenta(rs.getInt(6));
				sesion.setIdTipoCuenta(rs.getInt(7));
				sesion.setTipoCuenta(rs.getString(8));
				sesion.setIdPerfil(rs.getInt(9));
				sesion.setPerfil(rs.getString(10));
				sesion.setGrado(rs.getString(11));
				sesion.setTutor(rs.getString(12));
			}else {
			
				throw new NotFound("El usuario o la contraseña son incorrectos");
				
			}
		
		} catch (SQLException e) {
			
			throw new NotFound("Error inesperado");
			
		} finally {
			
			Conexion.cerrarConexion(conexion,ps,rs);
			
		}
		
		return sesion;
		
	}

	@Override
	public List<Sesion> all(int id) throws NotAll {
		// TODO Auto-generated method stub
		return null;
	}

}
