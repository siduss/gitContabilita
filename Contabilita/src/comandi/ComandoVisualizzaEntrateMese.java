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
import eccezioni.ParameterRequestInsufficientException;

public class ComandoVisualizzaEntrateMese implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		stampaEntrateMese(caricaEntrateMese(gestioneContabilita));
	}

	private void stampaEntrateMese(List<Entrata> entrate){
		if (entrate.size() > 0){
			for (Entrata e: entrate){
				System.out.println(e.getData() + ", " + e.getDescrizione() + ", " + e.getValore() + " €");
			}
		}
		else System.out.println("Non ci sono entrate in questo mese");
	}

	private List<Entrata> caricaEntrateMese(GestioneContabilita gc){
		List<Entrata> uscite = new ArrayList<Entrata>();
		try{
			System.out.println("* * * * * * ");
			System.out.println("* Hai scelto di visualizzare le entrate del mese.");
			System.out.println("* Il comando ha questa sintassi: mm,yyyy");
			System.out.println("* Esempio: 5,2008");
			System.out.println("* Inserisci il mese e l'anno:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			String[] split = input.split(",");
			if (split.length != 2) throw new ParameterRequestInsufficientException();
			
			int mm = ServiziDate.controllaIntero(split[0], 1, 2);
			
			if (mm > 0){
				int yyyy = ServiziDate.controllaIntero(split[1], 4, 4);
				if (yyyy > 1970){
					Date data = ServiziDate.creaDataDaMeseAnno(mm, yyyy);
					uscite = gc.dammiEntrateMese(data);
				}
			}
		}
		
		catch (ParameterRequestInsufficientException e){
		}
		catch (IOException e){
		}
		return uscite;
	}
}
