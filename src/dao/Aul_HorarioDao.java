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
import interfaces.IAul_Horario;
import models.Aul_Horario;
import utilidades.Conexion;

public class Aul_HorarioDao implements IAul_Horario{

	@Override
	public void create(Aul_Horario t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aul_Horario read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_Horario t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Aul_Horario t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aul_Horario> all() throws NotAll {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aul_Horario> all(int idCuenta) throws NotAll {
		
		ConectionSqlServer conexion = ConectionSqlServer.getInstance();
		
		Connection c = conexion.getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		List<Aul_Horario> list = null;
		
		try {
			ps= c.prepareStatement("{call dbo.sp_HORARIO_by_CUENTA_get(?)}");
			
			ps.setInt(1, idCuenta);
		
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				list = new ArrayList<>();
				
				Aul_Horario aulC = new Aul_Horario();
				
				aulC.setIdHorario(rs.getInt(1));
				aulC.setIdSemana(rs.getInt(2));
				aulC.setDescCurso(rs.getString(3));
				aulC.setHoraIni(rs.getString(4));
				aulC.setHoraFin(rs.getString(5));
				aulC.setMensaje(rs.getString(6));
				
				list.add(aulC);
				
				while(rs.next()) {
					
					aulC = new Aul_Horario();
					
					aulC.setIdHorario(rs.getInt(1));
					aulC.setIdSemana(rs.getInt(2));
					aulC.setDescCurso(rs.getString(3));
					aulC.setHoraIni(rs.getString(4));
					aulC.setHoraFin(rs.getString(5));
					aulC.setMensaje(rs.getString(6));
					
					list.add(aulC);
				}
				
			}else {
			
				throw new NotAll("Ud. no tiene el horario asignado.");
				
			}
		
		} catch (SQLException e) {
			
			throw new NotAll("Póngase en contacto con su administrador");
			
		} finally {
			
			Conexion.cerrarConexion(conexion,ps,rs);
			
		}
		
		return list;
	}

}
