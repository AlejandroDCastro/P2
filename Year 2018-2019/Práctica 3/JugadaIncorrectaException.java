
public class JugadaIncorrectaException extends Exception {
	
	public JugadaIncorrectaException(Ficha f1, Ficha f2) {
		super(f1.toString() + "!=" + f2.toString());
	}

}
