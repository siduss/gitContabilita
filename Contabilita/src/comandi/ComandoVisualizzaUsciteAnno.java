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

public class ComandoVisualizzaUsciteAnno implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		stampaUsciteAnno(caricaUsciteAnno(gestioneContabilita));
	}

	private void stampaUsciteAnno(List<Uscita> uscite){
		if (uscite.size() > 0){
			for (Uscita u: uscite){
				System.out.println(u.getData() + ", " + u.getDescrizione() + ", " + u.getValore() + " €");
			}
		}
		else System.out.println("Non ci sono uscite in questo anno");
	}

	private List<Uscita> caricaUsciteAnno(GestioneContabilita gc){
		List<Uscita> uscite = new ArrayList<Uscita>();
		try{
			System.out.println("* * * * * * ");
			System.out.println("* Hai scelto di visualizzare le uscite dell'anno");
			System.out.println("* Inserisci l'anno:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			
			int yyyy = ServiziDate.controllaIntero(input, 4, 4);
			
			if (yyyy > 1970){
				Date data = ServiziDate.creaDataDaAnno(yyyy);
				uscite = gc.dammiUsciteAnno(data);
			}
		}
		catch (IOException e){
			System.out.println("IOException has been caught");
		}
		return uscite;
	}
}
