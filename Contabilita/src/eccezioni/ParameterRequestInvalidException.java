package eccezioni;

@SuppressWarnings("serial")
public class ParameterRequestInvalidException extends Exception{
	public ParameterRequestInvalidException(){
		System.out.println("I parametri inseriti sono invalidi");
	}
}
