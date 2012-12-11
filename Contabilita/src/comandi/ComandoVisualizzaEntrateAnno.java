package comandi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.Entrata;
import main.GestioneContabilita;
import metodi_utilita.ServiziDate;

public class ComandoVisualizzaEntrateAnno implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		stampaEntrateAnno(caricaEntrateAnno(gestioneContabilita));
	}

	private void stampaEntrateAnno(List<Entrata> entrate){
		if (entrate.size() > 0){
			for (Entrata e: entrate)
				System.out.println(e.getData() + ", " + e.getDescrizione() + ", " + e.getValore() + " €");
		}
		else System.out.println("Non ci sono entrate in questo anno");
	}

	private List<Entrata> caricaEntrateAnno(GestioneContabilita gc){
		List<Entrata> entrate = new ArrayList<Entrata>();
		try{
			System.out.println("* * * * * * ");
			System.out.println("* Hai scelto di visualizzare le entrate dell'anno");
			System.out.println("* Inserisci l'anno:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			
			int yyyy = ServiziDate.controllaIntero(input, 4, 4);
			
			if (yyyy > 1970){
				Date data = ServiziDate.creaDataDaAnno(yyyy);
				entrate = gc.dammiEntrateAnno(data);
			}
		}
		catch (IOException e){
			System.out.println("IOException has been caught");
		}
		return entrate;
	}
}
