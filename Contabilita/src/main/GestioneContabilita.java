package main;
import java.util.Date;
import java.util.List;
import dao.EntrataDAO;
import dao.UscitaDAO;

public class GestioneContabilita implements ServiziContabilita{
	private EntrataDAO entrataDAO;
	private UscitaDAO uscitaDAO;

	public GestioneContabilita(EntrataDAO entrataDAO, UscitaDAO uscitaDAO){
		this.entrataDAO = entrataDAO;
		this.uscitaDAO = uscitaDAO;
	}

	@Override
	public boolean inserisciEntrata(Entrata entrata){
		return entrataDAO.inserisciEntrata(entrata);
	}

	@Override
	public boolean inserisciUscita(Uscita uscita){
		return uscitaDAO.inserisciUscita(uscita);
	}

	@Override
	public double dammiEntrateAggregateGiorno(Date data){
		return entrataDAO.calcolaEntrateGiorno(data);
	}

	@Override
	public double dammiEntrateAggregateMese(Date data){
		return entrataDAO.calcolaEntrateMese(data);
	}

	@Override
	public double dammiEntrateAggregateAnno(Date data){
		return entrataDAO.calcolaEntrateAnno(data);
	}

	@Override
	public List<Entrata> dammiEntrateGiorno(Date data){
		return entrataDAO.selezionaEntrateGiorno(data);
	}

	@Override
	public List<Entrata> dammiEntrateMese(Date data){
		return entrataDAO.selezionaEntrateMese(data);
	}

	@Override
	public List<Entrata> dammiEntrateAnno(Date data){
		return entrataDAO.selezionaEntrateAnno(data);
	}

	@Override
	public double dammiUsciteAggregateGiorno(Date data){
		return uscitaDAO.calcolaUsciteGiorno(data);
	}

	@Override
	public double dammiUsciteAggregateMese(Date data){
		return uscitaDAO.calcolaUsciteMese(data);
	}

	@Override
	public double dammiUsciteAggregateAnno(Date data){
		return uscitaDAO.calcolaUsciteAnno(data);
	}

	@Override
	public List<Uscita> dammiUsciteGiorno(Date data){
		return uscitaDAO.selezionaUsciteGiorno(data);
	}

	@Override
	public List<Uscita> dammiUsciteMese(Date data){
		return uscitaDAO.selezionaUsciteMese(data);
	}

	@Override
	public List<Uscita> dammiUsciteAnno(Date data){
		return uscitaDAO.selezionaUsciteAnno(data);
	}

	@SuppressWarnings("deprecation")
	@Override
	public double dammiSaldoMese(Date data){
		data.setDate(1);
		double entrateMensili = entrataDAO.calcolaEntrateMese(data);
		double usciteMensili = uscitaDAO.calcolaUsciteMese(data);
		return entrateMensili - usciteMensili;
	}

	@SuppressWarnings("deprecation")
	@Override
	public double dammiSaldoAnno(Date data){
		data.setDate(1);
		data.setMonth(0);
		double entrateAnnue = entrataDAO.calcolaEntrateAnno(data);
		double usciteAnnue = uscitaDAO.calcolaUsciteAnno(data);
		return entrateAnnue - usciteAnnue;
	}

	@Override
	public int dammiQuanteOperazioniPerData(Date data){
		int e = entrataDAO.calcolaQuanteOperazioniPerData(data);
		int u = uscitaDAO.calcolaQuanteOperazioniPerData(data);
		return e + u;
	}
}
