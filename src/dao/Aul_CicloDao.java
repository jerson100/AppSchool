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
import interfaces.IAul_Ciclo;
import models.Aul_Ciclo;

public class Aul_CicloDao implements IAul_Ciclo {

	@Override
	public void create(Aul_Ciclo t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aul_Ciclo read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_Ciclo t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Aul_Ciclo t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aul_Ciclo> all() throws NotAll, SQLException {
		List<Aul_Ciclo> list = new ArrayList<Aul_Ciclo>();
		try{
			try(Connection c = new ConexionSqlServer().getConexion()){
				try(PreparedStatement pr = c.prepareStatement("{call dbo.sp_CICLO_get()}")){
					try(ResultSet rs = pr.executeQuery()){
						if(rs.next()) {
							Aul_Ciclo aul = new Aul_Ciclo();
							aul.setIdCiclo(rs.getInt(1));
							aul.setDescCiclo(rs.getString(2));
							list.add(aul);
							while(rs.next()) {
								aul = new Aul_Ciclo();
								aul.setIdCiclo(rs.getInt(1));
								aul.setDescCiclo(rs.getString(2));
								list.add(aul);
							}
						}else {
							throw new NotAll("No se encontraron ciclos");
						}
					}
				}
			}
		}catch(SQLException e) {
			throw new SQLException(e);
		}
		return list;
	}

	@Override
	public List<Aul_Ciclo> all(int id) throws NotAll {
		// TODO Auto-generated method stub
		return null;
	}

}
