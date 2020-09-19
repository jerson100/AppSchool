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
import interfaces.IAul_Clases;
import models.Aul_Clases;

public class Aul_ClasesDao implements IAul_Clases {

	@Override
	public void create(Aul_Clases t) throws NotCreated {

		try {

			try (Connection db = new ConexionSqlServer().getConexion()) {

				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_CLASES_ins_upd(?,?,?,?,?,?,?,?,?,?)}")) {

					ps.setInt(1, t.getIdClass());

					ps.setInt(2, t.getIdSecCur());

					ps.setString(3, t.getCodClase());

					ps.setString(4, t.getDescClase());

					ps.setString(5, t.getLink());

					ps.setString(6, t.getFechaClase());

					ps.setString(7, t.getHoraClase());

					ps.setInt(8, t.getIdUsuario());

					ps.setInt(9, t.isReplicar_todos() ? 1 : 0);

					ps.setInt(10, /* t.isReplicar_solo()?1: */0);

					if (ps.executeUpdate() == 0) {

						throw new NotCreated("No se pudo crear la clase");

					}

				}

			}

		} catch (SQLException e) {

			throw new NotCreated("No se pudo crear la clase");

		}

	}

	@Override
	public Aul_Clases read(int id) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Aul_Clases t) throws NotUpdated {
		try {
			try (Connection db = new ConexionSqlServer().getConexion()) {
				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_CLASES_ins_upd(?,?,?,?,?,?,?,?,?,?)}")) {
					ps.setInt(1, t.getIdClass());

					ps.setInt(2, t.getIdSecCur());

					ps.setString(3, t.getCodClase());

					ps.setString(4, t.getDescClase());

					ps.setString(5, t.getLink());

					ps.setString(6, t.getFechaClase());

					ps.setString(7, t.getHoraClase());

					ps.setInt(8, t.getIdUsuario());

					ps.setInt(9, t.isReplicar_todos() ? 1 : 0);

					ps.setInt(10, /* t.isReplicar_solo()?1: */0);

					if (ps.executeUpdate() == 0) {
						throw new NotUpdated("No se pudo actualizar la clase");
					}
				}
			}

		} catch (SQLException e) {
			throw new NotUpdated("No se pudo actualizar la clase");
		}

	}

	@Override
	public void delete(Aul_Clases t) throws NotDeleted {
		try {
			try (Connection db = new ConexionSqlServer().getConexion()) {
				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_CLASES_del(?,?,?,?)}")) {
					ps.setInt(1, t.getIdClass());

					ps.setInt(2, t.getIdUsuario());

					ps.setInt(3, t.isReplicar_todos() ? 1 : 0);

					ps.setInt(4, /* t.isReplicar_solo() ? 1 : */0);

					if (ps.executeUpdate() == 0) {
						throw new NotDeleted("No se pudo eliminar la clase");
					}
				}
			}
		} catch (SQLException e) {
			throw new NotDeleted("No se pudo eliminar la clase");
		}
	}

	@Override
	public List<Aul_Clases> all() throws NotAll {

		return null;

	}

	@Override
	public List<Aul_Clases> all(int idSecCur) throws NotAll {

		List<Aul_Clases> list = null;
		
		try {

			try (Connection db = new ConexionSqlServer().getConexion()) {
				
				try (PreparedStatement ps = db.prepareStatement("{call dbo.sp_CLASES_get(?)}")) {
					
					ps.setInt(1, idSecCur);
					
					try(ResultSet rs = ps.executeQuery()){
						
						if (rs.next()) {

							list = new ArrayList<>();

							Aul_Clases aulC = new Aul_Clases();

							aulC.setIdClass(rs.getInt(1));
							aulC.setCodClase(rs.getString(2));
							aulC.setDescClase(rs.getString(3));
							aulC.setLink(rs.getString(4));
							aulC.setFechaClaseS(rs.getString(5));
							aulC.setHoraClaseS(rs.getString(6));
							aulC.setFechaClase(rs.getString(7));
							aulC.setHoraClase(rs.getString(8));

							list.add(aulC);

							while (rs.next()) {

								aulC = new Aul_Clases();

								aulC.setIdClass(rs.getInt(1));
								aulC.setCodClase(rs.getString(2));
								aulC.setDescClase(rs.getString(3));
								aulC.setLink(rs.getString(4));
								aulC.setFechaClaseS(rs.getString(5));
								aulC.setHoraClaseS(rs.getString(6));
								aulC.setFechaClase(rs.getString(7));
								aulC.setHoraClase(rs.getString(8));

								list.add(aulC);

							}

						} else {

							throw new NotAll("Este contenido no tiene clases asignadas");

						}
						
					}

				}
				
			}

		} catch (SQLException e) {

			throw new NotAll("Este contenido no tiene clases asignadas");

		}

		return list;
		
	}

}
