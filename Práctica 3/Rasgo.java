// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Rasgo {

	private String nombre;
	private String atributo;
	private boolean tenencia;
	
	public Rasgo(String s) {
		nombre = s;
		atributo = null;
		tenencia = false;
	}
	
	
	public void setAtributo(String s) { // PRUEBA p01
		atributo = s;
	}
	
	
	public void loTiene() { // PRUEBA p01
		tenencia = true;
	}
	
	
	public String toString() { // PRUEBA p01
		String cadena = null;
		
		if(nombre != null) {
			if(atributo != null)
				cadena = new String(nombre + "(" + atributo + ")");
			else
				cadena = new String(nombre + "(" + Boolean.toString(tenencia) + ")");
		}
		
		return cadena;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public String getAtributo() {
		return atributo;
	}
	
	
	public boolean getTenencia() {
		return tenencia;
	}
	
}
