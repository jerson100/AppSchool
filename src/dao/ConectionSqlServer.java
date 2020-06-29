package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionSqlServer {

	private static ConectionSqlServer instance;
	private Connection connection;
    private static final String HOST = "EDUARDO-PC";
    private static final String USER = "sa";
    private static final String PASS = "123";
    private static final String PATH = "jdbc:sqlserver://"+HOST+":1433;databasename=AULAVIRTUAL";
    
    private ConectionSqlServer() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        //this.connection = DriverManager.getConnection("jdbc:sqlserver://uribeschool.database.windows.net:1433;database=AULAVIRTUAL;user=uribeschool;password=PERU1602$d;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");        
        this.connection = DriverManager.getConnection(PATH,USER,PASS);
    }


    public Connection getConnection() {
        return this.connection;
    }

    public static ConectionSqlServer getInstance() {
        try{
            if(ConectionSqlServer.instance == null){
            	ConectionSqlServer.instance = new ConectionSqlServer();
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return ConectionSqlServer.instance;
    }

    public void CloseConnection() {
        try {
            if(this.connection!=null && !this.connection.isClosed()){
                this.connection.close();
                this.connection = null;
                ConectionSqlServer.instance = null;
            }
        } catch (SQLException ex) {}
    }
      
}
