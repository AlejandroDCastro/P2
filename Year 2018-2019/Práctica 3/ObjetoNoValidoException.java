
public class ObjetoNoValidoException extends Exception {
	
	public ObjetoNoValidoException(int i, int j) {
		super("[" + Integer.toString(i) + "," + Integer.toString(j) + "]");
	}
	
	
	public ObjetoNoValidoException(String s) {
		super(s);
	}

}
