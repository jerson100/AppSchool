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
import interfaces.IAul_ProfesorMensaje;
import models.Aul_ProfesorMensaje;

public class Aul_ProfesorMensajeDao implements IAul_ProfesorMensaje{

	@Override
	public void create(Aul_ProfesorMensaje t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aul_ProfesorMensaje read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_ProfesorMensaje t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Aul_ProfesorMensaje t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aul_ProfesorMensaje> all() throws NotAll, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aul_ProfesorMensaje> all(int idSecCur) throws NotAll {

		List<Aul_ProfesorMensaje> list = new ArrayList<Aul_ProfesorMensaje>();
		
		try {
				
			try(Connection con = new  ConexionSqlServer().getConexion()){
				
				try(PreparedStatement pr = con.prepareStatement("{call dbo.sp_PROFESORMENSAJE_by_IDSECCUR_get(?)}")){
					
					pr.setInt(1, idSecCur);
					
					try(ResultSet rs = pr.executeQuery()){
						
						if(rs.next()) {
	
							Aul_ProfesorMensaje ob = new Aul_ProfesorMensaje();
							
							ob.setIdMensajeProfesor(rs.getInt(1));
							
							ob.setDescMensaje(rs.getString(2));
							
							list.add(ob);
							
							while(rs.next()) {
							
								ob = new Aul_ProfesorMensaje();
								
								ob.setIdMensajeProfesor(rs.getInt(1));
								
								ob.setIdSecCur(rs.getInt(2));
								
								ob.setDescMensaje(rs.getString(3));
								
								ob.setIdCuenta(rs.getInt(4));
								
								ob.setNombres(rs.getString(5));
								
								ob.setApPaterno(rs.getString(6));
								
								ob.setApMaterno(rs.getString(7));
								
								list.add(ob);
								
							}
							
						}else {
						
							throw new NotAll("No se encontraron mensajes para la sección.");
							
						}
						
					}
					
				}
				
			}
			
		}catch(Exception e) {
			throw new NotAll("No se encontraron mensajes para la sección");
		}
		
		return list;
		
	}

}
