package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.Uscita;
import metodi_utilita.ServiziDAO;

public class UscitaDAO{
	private DataSource dataSource;

	public UscitaDAO(DataSource ds){
		dataSource = ds;
	}

	public boolean inserisciUscita(Uscita uscita){
		boolean valore = false;
		PreparedStatement stmt = null;
		Connection conn = null;
		try{
			String query = "INSERT INTO operazioni (data_operazione, descrizione, uscita) VALUES (?,?,?) ";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setDate(1, new java.sql.Date(uscita.getData().getTime()));
			stmt.setString(2, uscita.getDescrizione());
			stmt.setDouble(3, uscita.getValore());
			stmt.execute();
			conn.commit();
			valore = true;
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
		return valore;
	}

	public double calcolaUsciteGiorno(Date data){
		double v = 0;
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT sum(uscita) AS totale FROM operazioni WHERE data_operazione=?";
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
	public double calcolaUsciteMese(Date data){
		double v = 0;
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT sum(uscita) AS totale FROM operazioni WHERE data_operazione >= ? AND  data_operazione < ?";
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
	public double calcolaUsciteAnno(Date data){
		double v = 0;
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT sum(uscita) AS totale FROM operazioni WHERE data_operazione >= ? AND  data_operazione < ?";
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

	public List<Uscita> selezionaUsciteGiorno(Date data){
		List<Uscita> operazioni = new ArrayList<Uscita>();
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
			double uscita;
			Uscita u;
			while (rs.next()){
				descrizione = rs.getString("descrizione");
				uscita = rs.getDouble("uscita");
				data = rs.getDate("data_operazione");
				u = new Uscita();
				u.setData(data);
				u.setDescrizione(descrizione);
				u.setValore(uscita);
				if (u.getValore() > 0) operazioni.add(u);
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
	public List<Uscita> selezionaUsciteMese(Date data){
		List<Uscita> operazioni = new ArrayList<Uscita>();
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
			double uscita;
			Uscita u;
			while (rs.next()){
				descrizione = rs.getString("descrizione");
				uscita = rs.getDouble("uscita");
				data = rs.getDate("data_operazione");
				u = new Uscita();
				u.setData(data);
				u.setDescrizione(descrizione);
				u.setValore(uscita);
				if (u.getValore() > 0) operazioni.add(u);
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
	public List<Uscita> selezionaUsciteAnno(Date data){
		List<Uscita> operazioni = new ArrayList<Uscita>();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			String query = "SELECT * FROM operazioni WHERE data_operazione >= ? AND  data_operazione < ?";
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(query);
			data.setMonth(0);
			data.setDate(1);
			Date d1 = new Date(data.getTime());
			d1.setYear(d1.getYear() + 1);
			stmt.setDate(1, new java.sql.Date(data.getTime()));
			stmt.setDate(2, new java.sql.Date(d1.getTime()));
			rs = stmt.executeQuery();
			String descrizione;
			double uscita;
			Uscita u;
			while (rs.next()){
				descrizione = rs.getString("descrizione");
				uscita = rs.getDouble("uscita");
				data = rs.getDate("data_operazione");
				u = new Uscita();
				u.setData(data);
				u.setDescrizione(descrizione);
				u.setValore(uscita);
				if (u.getValore() > 0) operazioni.add(u);
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
			String query = "SELECT COUNT(data_operazione) AS quantita_operazioni FROM operazioni WHERE data_operazione=? and uscita>0";
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
