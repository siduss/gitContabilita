package comandi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import main.GestioneContabilita;
import metodi_utilita.ServiziDate;

public class ComandoVisualizzaSaldoAnno implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		double saldoAnno = calcolaSaldoAnno(gestioneContabilita);
		if (saldoAnno > 0) System.out.println("Il saldo equivale a : +"+saldoAnno);
		else System.out.println("Il saldo equivale a: "+saldoAnno);
	}
	
	private double calcolaSaldoAnno(GestioneContabilita gc){
		double saldoAnno = 0;
		try{
			System.out.println("* * * * * * ");
			System.out.println("* Hai scelto di visualizzare le entrate dell'anno");
			System.out.println("* Inserisci l'anno:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			
			int yyyy = ServiziDate.controllaIntero(input, 4, 4);
			
			if (yyyy > 1970){
				Date data = ServiziDate.creaDataDaAnno(yyyy);
				saldoAnno = gc.dammiSaldoAnno(data);
			}
		}
		catch (IOException e){
		}
		return saldoAnno;
	}
}
