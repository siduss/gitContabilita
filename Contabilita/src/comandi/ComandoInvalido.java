package comandi;

import main.GestioneContabilita;

public class ComandoInvalido implements Comando {
	@Override
	public void esegui(GestioneContabilita gestioneContabilita) {
		System.out.println("Devi inserire un numero tra le parentesi tonde");
	}
}