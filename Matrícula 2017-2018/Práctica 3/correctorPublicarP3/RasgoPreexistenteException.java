
public class RasgoPreexistenteException extends Exception {

	public RasgoPreexistenteException(String s1,String s2) {
		super(new String("(" + s1 + "," + s2 + ")"));
	}
	
}
