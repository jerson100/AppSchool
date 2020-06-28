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
import interfaces.IGlo_TipoArchivo;
import models.Glo_TipoArchivo;

public class Glo_TipoArchivoDao implements IGlo_TipoArchivo {

	@Override
	public void create(Glo_TipoArchivo t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Glo_TipoArchivo read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Glo_TipoArchivo t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Glo_TipoArchivo t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Glo_TipoArchivo> all() throws NotAll {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Glo_TipoArchivo> all(int id) throws NotAll {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validarExtension(Glo_TipoArchivo tipoArchivo) {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();

		Connection c = conexion.getConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;
		
		try {
			
			ps = c.prepareStatement("{call dbo.sp_TIPOARCHIVO_by_extension_get(?,?)}");
	
			ps.setString(1, tipoArchivo.getExtension());
	
			ps.setLong(2, tipoArchivo.getTamagnoMax());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

}
