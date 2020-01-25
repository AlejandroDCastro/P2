// DNI 20521823 CASTRO VALERO, ALEJANDRO

public class Ficha {
	
	
	private int primera;
	private int segunda;
	
	
	public Ficha(int p1, int p2) throws ObjetoNoValidoException {
		if (p1 >= 0  &&  p2 >= 0  &&  p1 < 7  &&  p2 < 7) {
			primera = p1;  segunda = p2;
		} else {
			throw new ObjetoNoValidoException(p1, p2);
		}
	}
	
	
	public int getPrimera() {
		return primera;
	}
	
	
	public int getSegunda() {
		return segunda;
	}
	
	
	public Ficha creaInversa() throws ObjetoNoValidoException {
		Ficha devuelve_ = new Ficha(segunda, primera);
		return devuelve_;
	}
	
	
	public String toString() {
		return ("[" + Integer.toString(primera) + "," + Integer.toString(segunda) + "]");
	}
	
	
	// metodo que determina si dos fichas son iguales
	public boolean equals(Ficha f) {
		if (f != null  &&  ((primera == f.getPrimera() && segunda == f.getSegunda())  ||  (primera == f.getSegunda() && segunda == f.getPrimera()))) {
			return true;
		} else {
			return false;
		}
	}
	
}
