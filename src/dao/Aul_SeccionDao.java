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
import interfaces.IAul_Seccion;
import models.Aul_Seccion;
import utilidades.Conexion;

public class Aul_SeccionDao implements IAul_Seccion{

	@Override
	public void create(Aul_Seccion t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aul_Seccion read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_Seccion t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Aul_Seccion t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aul_Seccion> all() throws NotAll {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aul_Seccion> all(int idCuenta) throws NotAll {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		List<Aul_Seccion> list = null;
		
		try {
		
			ps= c.prepareStatement("{call dbo.sp_SECCION_by_PROFESOR_get(?)}");
			
			ps.setInt(1, idCuenta);
		
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				list = new ArrayList<>();
				
				Aul_Seccion aulC = null;
				
				aulC = new Aul_Seccion();
				
				aulC.setIdSecGraNiv(rs.getInt(1));
				aulC.setGrado(rs.getString(2));
				aulC.setNivel(rs.getString(3));
				
				list.add(aulC);
				
				while(rs.next()) {
					
					aulC = new Aul_Seccion();
					
					aulC.setIdSecGraNiv(rs.getInt(1));
					aulC.setGrado(rs.getString(2));
					aulC.setNivel(rs.getString(3));
					
					list.add(aulC);
					
				}
				
			}else {
			
				throw new NotAll("Ud. no Niveles a cargo");
				
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new NotAll("Póngase en contacto con su administrador");
			
		} finally {
			
			Conexion.cerrarConexion(conexion,ps,rs);
			
		}
		
		return list;
		
	}

}
