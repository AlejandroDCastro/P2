// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;
public class Personaje {
	
	private ArrayList<Rasgo> rasgos;
	private String nombre;
	
	public Personaje(String s) {
		nombre = s;
		rasgos = new ArrayList<Rasgo>();
	}
	
	
	// ERROR CORREGIDO EN TODA LA CLASE no hay equalsIgnoreCase todo son equals
	
	
	public void agregaRasgo(String s1,String s2) throws
	RasgoNoValidoException, RasgoPreexistenteException { // PRUEBA p0234678910
		boolean tenencia = false;
		String atributo = null;
		
		// si no coincide con alguno de los siete rasgos existentes
		if(!this.nombreRasgo(s1))
			throw new RasgoNoValidoException(s1);
		
		// en caso contrario obtenemos el atributo y la tenencia en funcion del nombre del rasgo s1
		else {
			tenencia = this.averiguaTenencia(s1, s2);
			atributo = this.averiguaAtributo(s1, s2);
			for(int i=0; i<rasgos.size(); i++) {
				if(s1.equals(rasgos.get(i).getNombre())) {
					if(s1.equals("pelo")  ||  s1.equals("ojos"))
						throw new RasgoPreexistenteException(s1,rasgos.get(i).getAtributo());					// ERROR CORREGIDO se pasaba el atributo y tenencia del que ya estaba
					else
						throw new RasgoPreexistenteException(s1,Boolean.toString(rasgos.get(i).getTenencia()));
				}
			}
		}
		
		// por ultimo si no se lanza excepcion metemos el rasgo al array
		Rasgo r = new Rasgo(s1);
		r.setAtributo(atributo);
		if(tenencia) r.loTiene();
		rasgos.add(r);
		
	}
	
	
	public boolean consulta(Rasgo r) {	// PRUEBA P05910
		boolean devuelve = false;

		if(r != null) {

			// si todas sus caracteristicas son iguales se devuelve cierto
			for(int i=0; i<rasgos.size(); i++) {
				if(rasgos.get(i) != null  &&  rasgos.get(i).getNombre().equals(r.getNombre()))
					if((rasgos.get(i).getAtributo() == null  &&  r.getAtributo() == null)  ||  
							(rasgos.get(i).getAtributo() != null  &&  rasgos.get(i).getAtributo().equals(r.getAtributo()))  ||  // ERROR CORREGIDO no contemplaba que el primer String del equals fuese null
							(r.getAtributo() != null  &&  r.getAtributo().equals(rasgos.get(i).getAtributo())))
						if(rasgos.get(i).getTenencia() == r.getTenencia()) {
							devuelve = true;
							break;
						}
			}

		}

		return devuelve;
	}
	
	
	public boolean completo() { // PRUEBA p0267
		if(rasgos.size() == 7)
			return true;
		else
			return false;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public ArrayList<Rasgo> getRasgos() {
		return rasgos;
	}
	
	
	public String toString() {
		String cadena = null;
		
		if(nombre != null) {
			
			cadena = new String(nombre + "[");
			for(int i=0; i<rasgos.size(); i++) {
				if(i == (rasgos.size()-1))
					cadena = cadena.concat(rasgos.get(i).toString() + "]");
				else
					cadena = cadena.concat(rasgos.get(i).toString() + ",");
			}
			
		}
		
		return cadena;
	}
	
	
	// metodos adicionales practica 3
	
	
	public boolean nombreRasgo(String s) { // dice si la cadena s es alguno de los posibles rasgos
		boolean devuelve = false;
		
		if(s != null) {
			
			if(s.equals("pelo")  ||  s.equals("ojos")  ||  s.equals("gafas")  ||  s.equals("pendientes")  ||  s.equals("bigote")  ||
					s.equals("barba")  ||  s.equals("sombrero")) {
				devuelve = true;
			}
			else
				devuelve = false;
			
		}
		
		return devuelve;
	}
	
	
	public boolean averiguaTenencia(String s1, String s2) { // averigua la tenencia a partir del rasgo para crear uno nuevo
		boolean t = false;
		
		// si s1 es pelo u ojos la tenencia siempre sera true
		if(s1.equals("pelo")  ||  s1.equals("ojos"))
			t = true;
		else if(s2 != null) {
			if(s2.equals("true"))
				t = true;
		}
		
		return t;
	}
	
	
	public String averiguaAtributo(String s1, String s2) { // averigua el atributo a partir del rasgo para crear uno nuevo
		String atr = null;
		
		// pelo y ojos tienen valores por defecto pero los demas siempre son null
		if(s1.equals("pelo")) {
			if(s2 != null)
				atr = new String(s2);
			else
				atr = new String("calvo");
		} else if(s1.equals("ojos")) {
			if(s2 != null)
				atr = new String(s2);
			else
				atr = new String("verde");
		}
		
		return atr;
	}
	
	
	public Rasgo consultaRasgo(String s) { // devuelve el rasgo cuyo nombre coincide con la cadena s
		Rasgo r = null;
		
		if(s != null) {
			for(int i=0; i<rasgos.size(); i++) {
				if(s.equals(rasgos.get(i).getNombre())) {
					r = rasgos.get(i);
					break;
				}
			}
		}
		
		return r;
	}
	
}
