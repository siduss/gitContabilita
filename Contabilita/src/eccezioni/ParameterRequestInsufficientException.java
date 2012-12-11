package eccezioni;

@SuppressWarnings("serial")
public class ParameterRequestInsufficientException extends Exception {
	public ParameterRequestInsufficientException() {
		System.out.println("Devi inserire i parametri richiesti");
	}
}
