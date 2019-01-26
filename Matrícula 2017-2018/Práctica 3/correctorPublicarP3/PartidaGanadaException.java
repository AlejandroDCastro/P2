
public class PartidaGanadaException extends Exception {

	public PartidaGanadaException(String s1,String s2) {
		super(new String("(" + s1 + "," + s2 + ")"));
	}
	
}
