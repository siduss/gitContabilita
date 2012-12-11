package comandi;

import main.GestioneContabilita;


public class ComandoFine implements Comando {
	@Override
	public void esegui(GestioneContabilita gestioneContabilita) {
		System.out.println("Programma terminato!");
		System.exit(0);
	}

}
