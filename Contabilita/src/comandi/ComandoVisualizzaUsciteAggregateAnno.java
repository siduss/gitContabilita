package comandi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import main.GestioneContabilita;
import metodi_utilita.ServiziDate;

public class ComandoVisualizzaUsciteAggregateAnno implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		double valore = calcolaValoreUsciteAnno(gestioneContabilita);
		if (valore != 0){
			System.out.println("Il valore delle uscite equivale: " + valore + " €");
			System.out.println("Operazione completata!");
		}
	}

	private double calcolaValoreUsciteAnno(GestioneContabilita gc){
		System.out.println("* * * * * * ");
		System.out.println("* Inserisci l'anno:");
		double valore = 0;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			
			int yyyy = ServiziDate.controllaIntero(input, 4, 4);
			
			if (yyyy > 1970){
				Date data = ServiziDate.creaDataDaAnno(yyyy);
				valore = gc.dammiUsciteAggregateAnno(data);
			}
		}
		catch (IOException e){
			System.out.println("IOException has been caught");
		}
		return valore;
	}
}
