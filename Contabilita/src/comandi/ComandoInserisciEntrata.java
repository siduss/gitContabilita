package comandi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.Entrata;
import main.GestioneContabilita;
import eccezioni.ParameterRequestInsufficientException;

public class ComandoInserisciEntrata implements Comando{
	
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		inserisciEntrata(gestioneContabilita);
	}
	
	private void inserisciEntrata(GestioneContabilita gc){
		System.out.println("* * * * * * ");
		System.out.println("* Hai scelto di inserire un'entrata.");
		System.out.println("* L'operazione deve avere questa sintassi: data,descrizione,valore");
		System.out.println("* NB: la data deve avere questo formato (dd-MM-yyyy)");
		System.out.println("* Esempio Operazione: 1-1-2008,panino da Orfeo,3.50");
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String input = in.readLine();
			Entrata e = creaEntrata(input);
			if (e.diversoDaNull() && gc.inserisciEntrata(e) == true) System.out.println("ok");
			else System.out.println("fallito");
		}
		catch (IOException e){
			System.out.println("IOException has been caught");
		}
	}

	private Entrata creaEntrata(String input){
		Entrata e = new Entrata();
		try{
			String[] split = input.split(",");
			if (split.length != 3) throw new ParameterRequestInsufficientException();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data = sdf.parse(split[0]);
			String d = split[1];
			double v = Double.parseDouble(split[2]);
			e.setData(data);
			e.setDescrizione(d);
			if (v > 0) e.setValore(v);
		}
		catch (ParseException pe){
			System.out.println("Formato della data non valido");
		}
		catch (ParameterRequestInsufficientException ee){
		}
		return e;
	}

	

}
