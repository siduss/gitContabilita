package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import eccezioni.DataSourceException;

public class DataSource{
	public Connection getConnection() throws DataSourceException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/contabilita", "root", "admin");
			conn.setAutoCommit(false);
			return conn;
		}
		catch (Exception e){
			throw new DataSourceException(e);
		}
	}
}
