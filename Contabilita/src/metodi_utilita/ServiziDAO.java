package metodi_utilita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiziDAO{
	public static void closeRs(ResultSet resultSet){
		try{
			if (resultSet != null) resultSet.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static void closeConn(Connection connection){
		try{
			if (connection != null) connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static void closeStmt(PreparedStatement preparedStatement){
		try{
			if (preparedStatement != null) preparedStatement.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
}
