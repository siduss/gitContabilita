package eccezioni;

@SuppressWarnings("serial")
public class DataSourceException extends Exception {
	public DataSourceException(Exception e){
		super(e);
	}

}