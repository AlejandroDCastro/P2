
public class FichaRepetidaException extends Exception {
	
	public FichaRepetidaException(Ficha f) {
		super(f.toString());
	}

}
