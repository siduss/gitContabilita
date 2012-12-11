package comandi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.Entrata;
import main.GestioneContabilita;

public class ComandoVisualizzaEntrateGiorno implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		stampaEntrateGiorno(caricaEntrateGiorno(gestioneContabilita));
	}

	private void stampaEntrateGiorno(List<Entrata> entrate){
		if (entrate.size() > 0){
			for (Entrata e: entrate){
				System.out.println(e.getData() + ", " + e.getDescrizione() + ", " + e.getValore() + " €");
			}
			System.out.println("Operazione Conclusa!");
		}
		else System.out.println("Non ci sono entrate in questo giorno");
	}

	private List<Entrata> caricaEntrateGiorno(GestioneContabilita gc){
		List<Entrata> entrate = new ArrayList<Entrata>();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("* * * * * * ");
			System.out.println("* Hai scelto di visualizzare le entrate del giorno.");
			System.out.println("* NB: la data deve avere questo formato (dd-MM-yyyy)");
			System.out.println("* Esempio Operazione: 31-01-2009");
			System.out.println("* Inserisci la data:");
			String input = br.readLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data = sdf.parse(input);
			entrate = gc.dammiEntrateGiorno(data);
		}
		catch (ParseException e){
//			throw new RuntimeException("Formato data invalido");
			System.out.println("Formato della data non valido");
		}
		catch (IOException e){
		}
		return entrate;
	}
}
