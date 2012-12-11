package main;

import java.util.Comparator;

public class ComparatoreDate implements Comparator<Operazione>{

	@Override
	public int compare(Operazione o1, Operazione o2){
		return o1.getData().compareTo(o2.getData());
	}
}
