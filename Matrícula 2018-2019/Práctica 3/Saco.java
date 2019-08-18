// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Saco {
	
	
	private ArrayList<Ficha> fichas;
	private boolean completado;
	
	
	public Saco() {
		fichas = new ArrayList<Ficha>();
		completado = false;
	}
	
	
	public boolean meteFicha(Ficha f) throws FichaRepetidaException {
		boolean devuelve_ = false;
		
		if (f != null  &&  fichas.size() < 28) {
			
			// comprobamos si la ficha esta repetida
			for (int i=0; i<fichas.size(); i++) {
				if (fichas.get(i).equals(f)) {
					throw new FichaRepetidaException(f);
				}
			}
			
			// si no salta la excepcion es porque no esta repetida y se introduce en el saco
			fichas.add(f);
			devuelve_ = true;
		}
		
		return devuelve_;
	}
	
	
	public Ficha roba() {
		if (fichas.size() > 0) {
			return fichas.remove(0);
		} else {
			return null;
		}
	}
	
	
	public boolean completado() {
		if (fichas.size() == 28) {
			completado = true;
		}
		
		return completado;
	}
	
	
	public String toString() {
		String devuelve_ = new String("{");
		
		for (int i=0; i<fichas.size(); i++) {
			devuelve_ = devuelve_.concat(fichas.get(i).toString());
			if (i < fichas.size()-1) {
				devuelve_ = devuelve_.concat(",");
			}
		}
		devuelve_ = devuelve_.concat("}");
		
		return devuelve_;
	}
	
	
	// metodo que devuelve el numero de fichas que contiene el saco
	public int getFichas() {
		return fichas.size();
	}

}
