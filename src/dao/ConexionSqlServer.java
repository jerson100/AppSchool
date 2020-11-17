package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSqlServer {

	private static final String HOST = /*"LAPTOP-LEO1602"*/"DESKTOP-18U8BGE";
    private static final String USER = "sa";
    private static final String PASS = "123";
    private static final String PATH = "jdbc:sqlserver://"+HOST+":1433;databasename=AULAVIRTUAL";
	
	public Connection getConexion() throws SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        //Connection connection = DriverManager.getConnection("jdbc:sqlserver://uribeschool.database.windows.net:1433;database=AULAVIRTUAL;user=uribeschool;password=PERU1602$d;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");        
		try {
			Connection connection = DriverManager.getConnection(ConexionSqlServer.PATH,ConexionSqlServer.USER,ConexionSqlServer.PASS);
			return connection;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
