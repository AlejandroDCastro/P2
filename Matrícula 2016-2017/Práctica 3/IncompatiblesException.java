// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class IncompatiblesException extends Exception {

	public IncompatiblesException(String n) {
		super(n);
	}
	
	public IncompatiblesException(String n,String m) {
		super(n+"-"+m);
	}
	

}
