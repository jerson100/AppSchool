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
import interfaces.IAul_SeccionGradoNivel;
import models.Aul_SeccionGradoNivel;

public class Aul_SeccionGradoNivelDao implements IAul_SeccionGradoNivel{

	@Override
	public void create(Aul_SeccionGradoNivel t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aul_SeccionGradoNivel read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_SeccionGradoNivel t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Aul_SeccionGradoNivel t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aul_SeccionGradoNivel> all() throws NotAll {
		
		List<Aul_SeccionGradoNivel> list = null;
		
		try {
		
			try (Connection db = new ConexionSqlServer().getConexion()) {

				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_SECCIONGRADONIVEL_get()}")) {
			
					try (ResultSet rs = ps.executeQuery()){
						
						if(rs.next()) {
							
							list = new ArrayList<>();
							
							Aul_SeccionGradoNivel aulC = null;
							
							aulC = new Aul_SeccionGradoNivel();
							
							aulC.setIdSecGraNiv(rs.getInt(1));
							aulC.setMensaje(rs.getString(2));
							
							list.add(aulC);
							
							while(rs.next()) {
								
								aulC = new Aul_SeccionGradoNivel();
								
								aulC.setIdSecGraNiv(rs.getInt(1));
								aulC.setMensaje(rs.getString(2));
								
								list.add(aulC);
								
							}
							
						}else {
						
							throw new NotAll("No se encontraron secciones - grado - nivel");
							
						}
						
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			
			throw new NotAll("P�ngase en contacto con su administrador");
			
		} 
		
		return list;
		
	}

	@Override
	public List<Aul_SeccionGradoNivel> all(int id) throws NotAll {
		return null;
	}

}
