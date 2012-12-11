package main;
import comandi.*;

public class Parser{
	public Comando parseCommand(int i){
		switch (i){
			case 0:
				return new ComandoFine();
			case 1:
				return new ComandoInserisciEntrata();
			case 2:
				return new ComandoInserisciUscita();
			case 3:
				return new ComandoVisualizzaUsciteGiorno();
			case 4:
				return new ComandoVisualizzaUsciteMese();
			case 5:
				return new ComandoVisualizzaUsciteAnno();
			case 6:
				return new ComandoVisualizzaUsciteAggregateGiorno();
			case 7:
				return new ComandoVisualizzaUsciteAggregateMese();
			case 8:
				return new ComandoVisualizzaUsciteAggregateAnno();
			case 9:
				return new ComandoVisualizzaEntrateGiorno();
			case 10:
				return new ComandoVisualizzaEntrateMese();
			case 11:
				return new ComandoVisualizzaEntrateAnno();
			case 12:
				return new ComandoVisualizzaEntrateAggregateGiorno();
			case 13:
				return new ComandoVisualizzaEntrateAggregateMese();
			case 14:
				return new ComandoVisualizzaEntrateAggregateAnno();
			case 15:
				return new ComandoVisualizzaSaldoMese();
			case 16:
				return new ComandoVisualizzaSaldoAnno();
			case 17:
				return new ComandoVisualizzaQuanteOperazioniPerData();
			default:
				return new ComandoInvalido();
		}
	}
}
