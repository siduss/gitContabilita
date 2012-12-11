package comandi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import main.GestioneContabilita;
import metodi_utilita.ServiziDate;

public class ComandoVisualizzaEntrateAggregateAnno implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		double valore = calcolaValoreEntrateAnno(gestioneContabilita);
		if (valore != 0){
			System.out.println("Il valore delle entrate equivale: " + valore + " €");
			System.out.println("ok");
		}
		System.out.println("fallito");
	}

	private double calcolaValoreEntrateAnno(GestioneContabilita gc){
		System.out.println("* * * * * * ");
		System.out.println("* Inserisci l'anno:");
		double valore = 0;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			
			int yyyy = ServiziDate.controllaIntero(input, 4, 4);
			
			if (yyyy > 1970){
				Date data = ServiziDate.creaDataDaAnno(yyyy);
				valore = gc.dammiEntrateAggregateAnno(data);
			}
		}
		catch (IOException e){
			System.out.println("IOException has been caught");
		}
		return valore;
	}
}
