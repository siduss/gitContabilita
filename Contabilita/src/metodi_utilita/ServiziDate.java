package metodi_utilita;
import java.util.Calendar;
import java.util.Date;
import eccezioni.ParameterRequestInvalidException;

public class ServiziDate{
	public static Date creaDataDaMeseAnno(int mese, int anno){
		int dd = 1;
		int mm = mese - 1;
		int aaaa = anno;
		Calendar calendar = Calendar.getInstance();
		calendar.set(aaaa, mm, dd);
		Date data = calendar.getTime();
		return data;
	}

	public static Date creaDataDaAnno(int anno){
		int dd = 1;
		int mm = 0;
		int aaaa = anno;
		Calendar calendar = Calendar.getInstance();
		calendar.set(aaaa, mm, dd);
		Date data = calendar.getTime();
		return data;
	}

	public static int controllaIntero(String string, int minimo, int massimo){
		try{
			int i = string.length();
			int a = Integer.parseInt(string);
			if (i >= minimo && i <= massimo) return a;
			throw new ParameterRequestInvalidException();
		}
		catch (NumberFormatException e){
			System.out.println("Devi inserire esclusivamente numeri!");
		}
		catch (ParameterRequestInvalidException e){
		}
		return 0;
	}
}
