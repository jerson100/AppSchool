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
import interfaces.IAul_Curso;
import models.Aul_Curso;

public class Aul_CursoDao implements IAul_Curso{

	@Override
	public void create(Aul_Curso t) throws NotCreated {
		
	}

	@Override
	public Aul_Curso read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_Curso t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Aul_Curso t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aul_Curso> all() throws NotAll {
		
		List<Aul_Curso> list = null;
		
		try {
			
			try (Connection db = new ConexionSqlServer().getConexion()) {

				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_CURSO_get()}")) {
			
					try (ResultSet rs = ps.executeQuery()){
					
						if(rs.next()) {
							
							list = new ArrayList<>();
							
							Aul_Curso aulC = new Aul_Curso();
							
							aulC.setIdCurso(rs.getInt(1));
							aulC.setDescCurso(rs.getString(2));
							list.add(aulC);
							
							while(rs.next()) {
								
								aulC = new Aul_Curso();
								
								aulC.setIdSecCurPro(rs.getInt(1));
								aulC.setDescCurso(rs.getString(2));
								aulC.setProfesor(rs.getString(3));
								aulC.setIcono(rs.getString(4));
								
								list.add(aulC);
							}
							
						}else {
						
							throw new NotAll("Ud. no tiene cursos asignados");
							
						}
						
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			
			throw new NotAll("Póngase en contacto con su administrador");
			
		} 
		
		return list;
		
	}

	@Override
	public List<Aul_Curso> all(int idTipoCuenta, int idCuenta, int idSecGraNiv) throws NotAll {
		
		List<Aul_Curso> list = null;
		
		try {
			
			try (Connection db = new ConexionSqlServer().getConexion()) {

				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_CURSO_by_IDCUENTA_get(?,?,?)}")) {
			
					ps.setInt(1, idTipoCuenta);
					
					ps.setInt(2, idCuenta);
					
					ps.setInt(3,idSecGraNiv);
					
					try (ResultSet rs = ps.executeQuery()){
					
						if(rs.next()) {
							
							list = new ArrayList<>();
							
							Aul_Curso aulC = new Aul_Curso();
							
							aulC.setIdSecCurPro(rs.getInt(1));
							aulC.setDescCurso(rs.getString(2));
							aulC.setProfesor(rs.getString(3));
							aulC.setIcono(rs.getString(4));
							list.add(aulC);
							
							while(rs.next()) {
								
								aulC = new Aul_Curso();
								
								aulC.setIdSecCurPro(rs.getInt(1));
								aulC.setDescCurso(rs.getString(2));
								aulC.setProfesor(rs.getString(3));
								aulC.setIcono(rs.getString(4));
								
								list.add(aulC);
							}
							
						}else {
						
							throw new NotAll("Ud. no tiene cursos asignados");
							
						}
						
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			
			throw new NotAll("Póngase en contacto con su administrador");
			
		} 
		
		return list;
		
	}

	@Override
	public List<Aul_Curso> all(int id) throws NotAll {
		return null;
	}

}
