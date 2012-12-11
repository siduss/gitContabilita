package comandi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.GestioneContabilita;

public class ComandoVisualizzaUsciteAggregateGiorno implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		double valore = calcolaValoreUsciteGiorno(gestioneContabilita);
		if (valore != 0){
			System.out.println("Il valore delle uscite equivale: " + valore + " €");
			System.out.println("Operazione completata!");
		}
	}

	private double calcolaValoreUsciteGiorno(GestioneContabilita gc){
		System.out.println("* * * * * * ");
		System.out.println("* NB: il formato della data deve essere dd-MM-yyyy");
		System.out.println("* Inserisci la data:");
		double valore = 0;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data;
			data = sdf.parse(input);
			valore = gc.dammiUsciteAggregateGiorno(data);
		}
		catch (ParseException e){
			System.out.println("Formato della data non valido");
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return valore;
	}
}
