package main;
import java.util.Date;

public abstract class Operazione {
	private Date data;
	private String descrizione;
	private double valore;

	public Operazione() {
		
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public void setValore(double valore) {
		this.valore = valore;
	}

	public Date getData() {
		return data;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public double getValore() {
		return valore;
	}
	
	public boolean diversoDaNull(){
		return this.getData() != null && this.getDescrizione() != null && this.getValore() != 0;
	}
}