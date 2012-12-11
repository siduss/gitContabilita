package comandi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.GestioneContabilita;
import main.Uscita;
import eccezioni.ParameterRequestInsufficientException;

public class ComandoInserisciUscita implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		 inserisciUscita(gestioneContabilita);
	}

	private void inserisciUscita(GestioneContabilita gc){
		System.out.println("* * * * * * ");
		System.out.println("* Hai scelto di inserire un'uscita.");
		System.out.println("* L'operazione deve avere questa sintassi: data,descrizione,valore");
		System.out.println("* NB: la data deve avere questo formato (dd-MM-yyyy)");
		System.out.println("* Esempio Operazione: 1-1-2008,Venduto immobile,50000.0");
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String input = in.readLine();
			Uscita u = creaUscita(input);
			if (u.diversoDaNull() && gc.inserisciUscita(u) == true) System.out.println("ok");
			else System.out.println("fallito");
		}
		catch (IOException e){
			System.out.println("IOException has been caught");
		}
	}

	private Uscita creaUscita(String input){
		Uscita u = new Uscita();
		try{
			String[] split = input.split(",");
			if (split.length != 3) throw new ParameterRequestInsufficientException();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data = sdf.parse(split[0]);
			String d = split[1];
			double v = Double.parseDouble(split[2]);
			u.setData(data);
			u.setDescrizione(d);
			if (v > 0) u.setValore(v);
		}
		catch (ParseException pe){ // il parse sul simpleDateFormat lancia questa eccezione
			System.out.println("Formato della data non valido");
//			System.out.println("Hai inserito un formato per la data non valido");
		}
		catch (ParameterRequestInsufficientException ee){
		}
		return u;
	}
}
