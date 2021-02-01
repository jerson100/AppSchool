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
import interfaces.IAul_SeccionAlumno;
import models.Aul_Ciclo;
import models.Aul_SeccionAlumno;

public class Aul_SeccionAlumnoDao implements IAul_SeccionAlumno{

	@Override
	public void create(Aul_SeccionAlumno t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aul_SeccionAlumno read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_SeccionAlumno t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Aul_SeccionAlumno t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aul_SeccionAlumno> all() throws NotAll, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aul_SeccionAlumno> all(int idSecGraNiv) throws NotAll {
		List<Aul_SeccionAlumno> list = new ArrayList<Aul_SeccionAlumno>();
		try{
			try(Connection c = new ConexionSqlServer().getConexion()){
				try(PreparedStatement pr = c.prepareStatement("{call dbo.sp_ALUMNO_by_IDSECGRANIV_get(?)}")){
					pr.setInt(1, idSecGraNiv);
					try(ResultSet rs = pr.executeQuery()){
						if(rs.next()) {
							Aul_SeccionAlumno aul = new Aul_SeccionAlumno();
							aul.setAlumno(rs.getString(2));
							list.add(aul);
							while(rs.next()) {
								aul = new Aul_SeccionAlumno();
								aul.setAlumno(rs.getString(2));
								list.add(aul);
							}
						}else {
							throw new NotAll("No se encontraron alumnos");
						}
					}
				}
			}
		}catch(SQLException e) {
			throw new NotAll("No se encontraron alumnos");
		}
		return list;
	}

}
