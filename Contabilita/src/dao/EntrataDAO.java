package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.Entrata;
import metodi_utilita.ServiziDAO;

public class EntrataDAO{
	private DataSource dataSource;

	public EntrataDAO(DataSource ds){
		dataSource = ds;
	}

	public boolean inserisciEntrata(Entrata entrata){
		boolean v = false;
		PreparedStatement stmt = null;
		Connection conn = null;
		try{
			String query = "INSERT INTO operazioni (data_operazione, descrizione, entrata) VALUES (?,?,?) ";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setDate(1, new java.sql.Date(entrata.getData().getTime()));
			stmt.setString(2, entrata.getDescrizione());
			stmt.setDouble(3, entrata.getValore());
			stmt.execute();
			conn.commit();
			v = true;
		}
		catch (Exception e){
			e.printStackTrace();
			try{
				if (conn != null) conn.rollback();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
		}
		finally{
			ServiziDAO.closeStmt(stmt);
			ServiziDAO.closeConn(conn);
		}
		return v;
	}

	public double calcolaEntrateGiorno(Date data){
		double v = 0;
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT sum(entrata) AS totale FROM operazioni WHERE data_operazione=?";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setDate(1, new java.sql.Date(data.getTime()));
			rs = stmt.executeQuery();
			if (rs.next()) v = rs.getDouble("totale");
			conn.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			try{
				if (conn != null) conn.rollback();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
		}
		finally{
			ServiziDAO.closeStmt(stmt);
			ServiziDAO.closeConn(conn);
			ServiziDAO.closeRs(rs);
		}
		return v;
	}

	@SuppressWarnings("deprecation")
	public double calcolaEntrateMese(Date data){
		double v = 0;
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT sum(entrata) AS totale FROM operazioni WHERE data_operazione >= ? AND  data_operazione < ?";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			data.setDate(1);
			Date d1 = new Date(data.getTime());
			d1.setMonth(d1.getMonth() + 1);
			stmt.setDate(1, new java.sql.Date(data.getTime()));
			stmt.setDate(2, new java.sql.Date(d1.getTime()));
			rs = stmt.executeQuery();
			if (rs.next()) v = rs.getDouble("totale");
			conn.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			try{
				if (conn != null) conn.rollback();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
		}
		finally{
			ServiziDAO.closeStmt(stmt);
			ServiziDAO.closeConn(conn);
			ServiziDAO.closeRs(rs);
		}
		return v;
	}

	@SuppressWarnings("deprecation")
	public double calcolaEntrateAnno(Date data){
		double v = 0;
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT sum(entrata) AS totale FROM operazioni WHERE data_operazione >= ? AND  data_operazione < ?";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			data.setDate(1);
			data.setMonth(0);
			Date d1 = new Date(data.getTime());
			d1.setYear(d1.getYear() + 1);
			stmt.setDate(1, new java.sql.Date(data.getTime()));
			stmt.setDate(2, new java.sql.Date(d1.getTime()));
			rs = stmt.executeQuery();
			if (rs.next()) v = rs.getDouble("totale");
			conn.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			try{
				if (conn != null) conn.rollback();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
		}
		finally{
			ServiziDAO.closeStmt(stmt);
			ServiziDAO.closeConn(conn);
			ServiziDAO.closeRs(rs);
		}
		return v;
	}

	public List<Entrata> selezionaEntrateGiorno(Date data){
		List<Entrata> operazioni = new ArrayList<Entrata>();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT * FROM operazioni WHERE data_operazione=?";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setDate(1, new java.sql.Date(data.getTime()));
			rs = stmt.executeQuery();
			String descrizione;
			double entrata;
			Entrata e;
			while (rs.next()){
				descrizione = rs.getString("descrizione");
				entrata = rs.getDouble("entrata");
				data = rs.getDate("data_operazione");
				e = new Entrata();
				e.setData(data);
				e.setDescrizione(descrizione);
				e.setValore(entrata);
				if (e.getValore() > 0) operazioni.add(e);
			}
			conn.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			try{
				if (conn != null) conn.rollback();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
		}
		finally{
			ServiziDAO.closeStmt(stmt);
			ServiziDAO.closeConn(conn);
			ServiziDAO.closeRs(rs);
		}
		return operazioni;
	}

	@SuppressWarnings("deprecation")
	public List<Entrata> selezionaEntrateMese(Date data){
		List<Entrata> operazioni = new ArrayList<Entrata>();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT * FROM operazioni WHERE data_operazione >= ? AND  data_operazione < ?";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			data.setDate(1);
			Date d1 = new Date(data.getTime());
			d1.setMonth(d1.getMonth() + 1);
			stmt.setDate(1, new java.sql.Date(data.getTime()));
			stmt.setDate(2, new java.sql.Date(d1.getTime()));
			rs = stmt.executeQuery();
			String descrizione;
			double entrata;
			Entrata e;
			while (rs.next()){
				descrizione = rs.getString("descrizione");
				entrata = rs.getDouble("entrata");
				data = rs.getDate("data_operazione");
				e = new Entrata();
				e.setData(data);
				e.setDescrizione(descrizione);
				e.setValore(entrata);
				if (e.getValore() > 0) operazioni.add(e);
			}
			conn.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			try{
				if (conn != null) conn.rollback();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
		}
		finally{
			ServiziDAO.closeStmt(stmt);
			ServiziDAO.closeConn(conn);
			ServiziDAO.closeRs(rs);
		}
		return operazioni;
	}

	@SuppressWarnings("deprecation")
	public List<Entrata> selezionaEntrateAnno(Date data){
		List<Entrata> operazioni = new ArrayList<Entrata>();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT * FROM operazioni WHERE data_operazione >= ? AND  data_operazione < ?";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			data.setDate(1);
			data.setMonth(0);
			Date d1 = new Date(data.getTime());
			d1.setYear(d1.getYear() + 1);
			stmt.setDate(1, new java.sql.Date(data.getTime()));
			stmt.setDate(2, new java.sql.Date(d1.getTime()));
			rs = stmt.executeQuery();
			String descrizione;
			double entrata;
			Entrata e;
			while (rs.next()){
				descrizione = rs.getString("descrizione");
				entrata = rs.getDouble("entrata");
				data = rs.getDate("data_operazione");
				e = new Entrata();
				e.setData(data);
				e.setDescrizione(descrizione);
				e.setValore(entrata);
				if (e.getValore() > 0) operazioni.add(e);
			}
			conn.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			try{
				if (conn != null) conn.rollback();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
		}
		finally{
			ServiziDAO.closeStmt(stmt);
			ServiziDAO.closeConn(conn);
			ServiziDAO.closeRs(rs);
		}
		return operazioni;
	}

	public int calcolaQuanteOperazioniPerData(Date data){
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int valore = 0;
		try{
			String query = "SELECT COUNT(data_operazione) AS quantita_operazioni FROM operazioni WHERE data_operazione=? and entrata>0";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setDate(1, new java.sql.Date(data.getTime()));
			rs = stmt.executeQuery();
			if (rs.next()) valore = rs.getInt("quantita_operazioni");
			conn.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			try{
				if (conn != null) conn.rollback();
			}
			catch (SQLException sqle){
				sqle.printStackTrace();
			}
		}
		finally{
			ServiziDAO.closeStmt(stmt);
			ServiziDAO.closeConn(conn);
			ServiziDAO.closeRs(rs);
		}
		return valore;
	}
}
