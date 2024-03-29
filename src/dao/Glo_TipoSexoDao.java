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
import interfaces.IGlo_TipoSexo;
import models.Aul_Curso;
import models.Glo_TipoSexo;
import utilidades.Conexion;

public class Glo_TipoSexoDao implements IGlo_TipoSexo{

	@Override
	public void create(Glo_TipoSexo t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Glo_TipoSexo read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Glo_TipoSexo t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Glo_TipoSexo t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Glo_TipoSexo> all() throws NotAll {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		List<Glo_TipoSexo> list = null;
		
		try {
			
			ps= c.prepareStatement("{call dbo.sp_TIPOSEXO_get()}");
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				list = new ArrayList<>();
				
				Glo_TipoSexo aulC = new Glo_TipoSexo();
				
				aulC.setIdSexo(rs.getInt(1));
				
				aulC.setDescSexo(rs.getString(2));
				
				aulC.setPrefijo(rs.getString(3).charAt(0));
				
				list.add(aulC);
				
				while(rs.next()) {
					
					aulC = new Glo_TipoSexo();
					
					aulC.setIdSexo(rs.getInt(1));
					
					aulC.setDescSexo(rs.getString(2));
					
					aulC.setPrefijo(rs.getString(3).charAt(0));
					
					list.add(aulC);
					
				}
				
			}else {
			
				throw new NotAll("No se encontraron sexos");
				
			}
		
		} catch (SQLException e) {
			
			throw new NotAll("No se encontraron sexos");
			
		} finally {
			
			Conexion.cerrarConexion(conexion,ps,rs);
			
		}
		
		return list;
	}

	@Override
	public List<Glo_TipoSexo> all(int id) throws NotAll {
		return null;
	}

}
