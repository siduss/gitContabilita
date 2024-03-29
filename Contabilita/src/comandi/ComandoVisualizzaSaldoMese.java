package comandi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import main.GestioneContabilita;
import metodi_utilita.ServiziDate;
import eccezioni.ParameterRequestInsufficientException;

public class ComandoVisualizzaSaldoMese implements Comando{
	@Override
	public void esegui(GestioneContabilita gestioneContabilita){
		double saldoMese = calcolaSaldoMese(gestioneContabilita);
		if (saldoMese > 0) System.out.println("Il saldo equivale a : +"+saldoMese);
		else System.out.println("Il saldo equivale a: "+saldoMese);
	}

	private double calcolaSaldoMese(GestioneContabilita gc){
		double saldoMese = 0;
		try{
			System.out.println("* * * * * * ");
			System.out.println("* Hai scelto di visualizzare il saldo mese.");
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
					saldoMese = gc.dammiSaldoMese(data);
				}
			}
		}
		catch (ParameterRequestInsufficientException e){
		}
		catch (IOException e){
		}
		return saldoMese;
	}
}
