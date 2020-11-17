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
import interfaces.IAul_PeriodoNotas;
import models.Aul_Ciclo;
import models.Aul_DescNotas;
import models.Aul_PeriodoNotas;
import models.Aul_Periodo;

public class Aul_PeriodoNotasDao implements IAul_PeriodoNotas {

	@Override
	public void create(Aul_PeriodoNotas t) throws NotCreated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aul_PeriodoNotas read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_PeriodoNotas t) throws NotUpdated {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Aul_PeriodoNotas t) throws NotDeleted {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aul_PeriodoNotas> all() throws NotAll, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aul_PeriodoNotas> all(int id) throws NotAll {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<Aul_PeriodoNotas>> all(int idCiclo, int idSecCur) throws NotAll, SQLException {
		Map<String, List<Aul_PeriodoNotas>> map = new LinkedHashMap<String, List<Aul_PeriodoNotas>>();
		try(Connection con = new ConexionSqlServer().getConexion()){
			try(PreparedStatement pr = con.prepareStatement("{ call dbo.sp_REGISTRONOTAS_by_IDCICLO_1_get(?,?)}")){
				pr.setInt(1, idCiclo);
				pr.setInt(2, idSecCur);
				try(ResultSet rs = pr.executeQuery()){
					while(rs.next()) {
						Aul_PeriodoNotas aul = new Aul_PeriodoNotas();
						Aul_Periodo p = new Aul_Periodo();
						Aul_Ciclo ciclo = new Aul_Ciclo();
						Aul_DescNotas descNotas = new Aul_DescNotas();
						
						ciclo.setIdCiclo(rs.getInt(1));
						ciclo.setDescCiclo(rs.getString(2));
						p.setCiclo(ciclo);
						p.setDescPeriodo(rs.getString(4));
						
						descNotas.setDescNotas(rs.getString(5));
						
						aul.setPeriodo(p);
						aul.setNotas(descNotas);
						aul.setIdPeriodoNotas(rs.getInt(3));
						
						if(!map.containsKey(p.getDescPeriodo())) {
							List<Aul_PeriodoNotas> list = new ArrayList<Aul_PeriodoNotas>();
							list.add(aul);
							map.put(p.getDescPeriodo(), list);
						}else {
							map.get(p.getDescPeriodo()).add(aul);
						}
					
					}
				}
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return map;
	}
	
}
