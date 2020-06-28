package utilidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConectionSqlServer;

public class Conexion {

	public static void cerrarConexion(ConectionSqlServer conn, PreparedStatement ps, ResultSet rs) {
		
		if(conn!=null) {
			
			conn.CloseConnection();
			
		}
		
		if(rs!=null) {
			try {
				if(!rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(ps!=null) {
			
			try {
				if(!ps.isClosed()) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
