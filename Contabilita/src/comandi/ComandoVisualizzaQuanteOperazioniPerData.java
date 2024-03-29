package comandi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.GestioneContabilita;

public class ComandoVisualizzaQuanteOperazioniPerData implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		int operazioni = calcolaQuanteOperazioniPerData(gestioneContabilita);
		System.out.println(operazioni);
	}
	
	private int calcolaQuanteOperazioniPerData(GestioneContabilita gc){
		System.out.println("* * * * * * ");
		System.out.println("* NB: il formato della data deve essere dd-MM-yyyy");
		System.out.println("* Inserisci la data:");
		int valore = 0;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date data = sdf.parse(input);
			valore = gc.dammiQuanteOperazioniPerData(data);
		}
		catch (ParseException e){
//			throw new RuntimeException("Formato data invalido");
			System.out.println("Formato della data non valido");
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return valore;
	}
}
