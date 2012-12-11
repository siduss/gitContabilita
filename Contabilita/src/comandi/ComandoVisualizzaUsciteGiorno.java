package comandi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.GestioneContabilita;
import main.Uscita;

public class ComandoVisualizzaUsciteGiorno implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		stampaUsciteGiorno(caricaUsciteGiorno(gestioneContabilita));
	}

	private void stampaUsciteGiorno(List<Uscita> uscite){
		if (uscite.size() > 0){
			for (Uscita u: uscite){
				System.out.println(u.getData() + ", " + u.getDescrizione() + ", " + u.getValore() + " €");
			}
			System.out.println("Operazione Conclusa!");
		}
		else System.out.println("Non ci sono uscite in questo giorno");
	}

	private List<Uscita> caricaUsciteGiorno(GestioneContabilita gc){
		List<Uscita> uscite = new ArrayList<Uscita>();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("* * * * * * ");
			System.out.println("* Hai scelto di visualizzare le uscite del giorno.");
			System.out.println("* NB: la data deve avere questo formato (dd-MM-yyyy)");
			System.out.println("* Esempio Operazione: 31-01-2009");
			System.out.println("* Inserisci la data:");
			String input = br.readLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data = sdf.parse(input);
			uscite = gc.dammiUsciteGiorno(data);
		}
		catch (ParseException e){
//			throw new RuntimeException("Formato data invalido");
			System.out.println("Formato della data non valido");
		}
		catch (IOException e){
		}
		return uscite;
	}
}
