package comandi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.GestioneContabilita;
import main.Uscita;
import metodi_utilita.ServiziDate;
import eccezioni.ParameterRequestInsufficientException;

public class ComandoVisualizzaUsciteMese implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		stampaUsciteMese(caricaUsciteMese(gestioneContabilita));
	}

	private void stampaUsciteMese(List<Uscita> uscite){
		if (uscite.size() > 0){
			for (Uscita u: uscite){
				System.out.println(u.getData() + ", " + u.getDescrizione() + ", " + u.getValore() + " €");
			}
		}
		else System.out.println("Non ci sono uscite in questo mese");
	}

	private List<Uscita> caricaUsciteMese(GestioneContabilita gc){
		List<Uscita> uscite = new ArrayList<Uscita>();
		try{
			System.out.println("* * * * * * ");
			System.out.println("* Hai scelto di visualizzare le uscite del mese.");
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
					uscite = gc.dammiUsciteMese(data);
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