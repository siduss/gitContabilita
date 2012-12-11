package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import dao.DataSource;
import dao.EntrataDAO;
import dao.UscitaDAO;

public class EseguiProgramma{
	private Parser parser;
	private GestioneContabilita gestioneContabilita;

	public EseguiProgramma(){
		DataSource ds = new DataSource();
		EntrataDAO eDAO = new EntrataDAO(ds);
		UscitaDAO uDAO = new UscitaDAO(ds);
		gestioneContabilita = new GestioneContabilita(eDAO, uDAO);
		parser = new Parser();
	}

	public void esegui(){
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(reader);
		try{
			System.out.println("Cosa vuoi fare?");
			System.out.println(" (0)  Esci");
			System.out.println(" (1)  Inserisci entrata");
			System.out.println(" (2)  Inserisci uscita");
			System.out.println(" (3)  Visualizza uscite del giorno");
			System.out.println(" (4)  Visualizza uscite del mese");
			System.out.println(" (5)  Visualizza uscite dell'anno");
			System.out.println(" (6)  Visualizza uscite aggregate del giorno");
			System.out.println(" (7)  Visualizza uscite aggregate del mese");
			System.out.println(" (8)  Visualizza uscite aggregate dell'anno");
			System.out.println(" (9)  Visualizza entrate del giorno");
			System.out.println(" (10) Visualizza entrate del mese");
			System.out.println(" (11) Visualizza entrate dell'anno");
			System.out.println(" (12) Visualizza entrate aggregate del giorno");
			System.out.println(" (13) Visualizza entrate aggregate del mese");
			System.out.println(" (14) Visualizza entrate aggregate dell'anno");
			System.out.println(" (15) Visualizza saldo del mese");
			System.out.println(" (16) Visualizza saldo dell'anno");
			System.out.println(" (17) Visualizza quante operazioni per data");
			int i = Integer.parseInt(in.readLine());
			parser.parseCommand(i).esegui(gestioneContabilita);
			
		}
		catch (IOException e){
		}
		catch (NumberFormatException e){
			System.out.println("Devi inserire un numero tra le parentesi tonde");
		}
	}
	
	public static void main(String[] argc){
		EseguiProgramma programma = new EseguiProgramma();
		programma.esegui();
	}
}
