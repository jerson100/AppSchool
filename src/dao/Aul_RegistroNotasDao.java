package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import exceptions.NotAll;
import exceptions.NotCreated;
import exceptions.NotDeleted;
import exceptions.NotFound;
import exceptions.NotUpdated;
import interfaces.IAul_RegistrarNotas;
import models.Aul_RegistroNotas;

public class Aul_RegistroNotasDao implements IAul_RegistrarNotas{

	@Override
	public void create(Aul_RegistroNotas t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aul_RegistroNotas read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_RegistroNotas t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Aul_RegistroNotas t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aul_RegistroNotas> all() throws NotAll, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aul_RegistroNotas> all(int id) throws NotAll {
		return null;
	}
	
	@Override
	public void create(Aul_RegistroNotas[] notas) throws NotCreated, SQLException{
		try(Connection  con = new ConexionSqlServer().getConexion()){
			try {
				con.setAutoCommit(false);
				for(Aul_RegistroNotas aul : notas) {
					//notas == null
					try(PreparedStatement pr = con.prepareStatement("{call dbo.sp_REGISTRONOTAS_ins_upd(?,?,?,?,?)}")){
						pr.setInt(1, aul.getIdRegistroNota());
						pr.setInt(2, aul.getIdCuenta());
						pr.setInt(3, aul.getIdSecCur());
						pr.setString(4, aul.getIdPeriodoNotas());
						pr.setString(5, aul.getNota());
						pr.executeUpdate();
					}
				}
				con.commit();
			}catch(SQLException e) {
				con.rollback();//restart
				throw new NotCreated("No se pudo crear las notas.");
			}		
		}catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	@Override
	public List<Aul_RegistroNotas> allList(int idSecCurPro, int idPeriodoNotas) throws NotAll, SQLException {
		List<Aul_RegistroNotas> map  = new ArrayList<Aul_RegistroNotas>();
		try {
			try(Connection con = new  ConexionSqlServer().getConexion()){
				try(PreparedStatement pr  = con.prepareStatement("{call dbo.sp_REGISTRONOTAS_by_IDSECCUR_and_NOTAS_get(?,?)}")){
					pr.setInt(1, 5);
					pr.setInt(2, 30);
					try(ResultSet rs= pr.executeQuery()){
						while(rs.next()) {
							Aul_RegistroNotas aul = new Aul_RegistroNotas();
							aul.setIdRegistroNota(rs.getInt(1));
							aul.setIdCuenta(rs.getInt(2));
							aul.setAlumno(rs.getString(3));
							aul.setNota(rs.getString(4));
							map.add(aul);
						}
					}
				}
			}
		}catch (SQLException e) {
			throw new SQLException(e);
		}
		return map;
	}
	
	@Override
	public Map<String, List<Aul_RegistroNotas>>  all(int idCiclo, int SecCurPro) throws NotAll, SQLException {
		Map<String, List<Aul_RegistroNotas>> map  = new LinkedHashMap<String, List<Aul_RegistroNotas>>();
		try {
			try(Connection con = new  ConexionSqlServer().getConexion()){
				try(PreparedStatement pr  = con.prepareStatement("{call dbo.sp_REGISTRONOTAS_by_IDCICLO_2_get(?,?)}")){
					pr.setInt(1, 1);
					pr.setInt(2, 5);
					try(ResultSet rs= pr.executeQuery()){
							while(rs.next()) {
								Aul_RegistroNotas aul = new Aul_RegistroNotas();
								aul.setIdSecCur(rs.getInt(1));
								aul.setIdCuenta(rs.getInt(2));
								aul.setAlumno(rs.getString(3));
								aul.setPeriodo(rs.getString(4));
								aul.setDescNotas(rs.getString(5));
								aul.setNota(rs.getString(6));
								
								/*List<String> notes = new ArrayList<String>();
								int count = 8;
								while(true) {
									try {
										notes.add(rs.getString(count++));
									}catch(Exception e) {
										break;
									}
								}*/
								
								
								if(!map.containsKey(aul.getAlumno())) {
									List<Aul_RegistroNotas> notes = new ArrayList<Aul_RegistroNotas>();
									notes.add(aul);
									map.put(aul.getAlumno(), notes);
								}else {
									map.get(aul.getAlumno()).add(aul);
								}
							
						}
					}
				}
			}			
		}catch (SQLException e) {
			throw new SQLException(e);
		}
		return map;
	}

}
