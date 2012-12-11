package main;

import java.util.Date;
import java.util.List;

public interface ServiziContabilita {
	public boolean inserisciEntrata(Entrata entrata);
	public boolean inserisciUscita(Uscita uscita);
	
	public List<Uscita> dammiUsciteGiorno(Date data);
	public List<Uscita> dammiUsciteMese(Date data);
	public List<Uscita> dammiUsciteAnno(Date data);
	
	public double dammiUsciteAggregateGiorno(Date data);
	public double dammiUsciteAggregateMese(Date data);
	public double dammiUsciteAggregateAnno(Date data);
	
	public List<Entrata> dammiEntrateGiorno(Date data);
	public List<Entrata> dammiEntrateMese(Date data);
	public List<Entrata> dammiEntrateAnno(Date data);
	
	public double dammiEntrateAggregateGiorno(Date data);
	public double dammiEntrateAggregateMese(Date data);
	public double dammiEntrateAggregateAnno(Date data);
	
	public double dammiSaldoMese(Date data);
	public double dammiSaldoAnno(Date data);
	
	public int dammiQuanteOperazioniPerData(Date data);
}