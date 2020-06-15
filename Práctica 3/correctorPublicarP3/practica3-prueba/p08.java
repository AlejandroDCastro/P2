/**
 * @authors Alex - Alicia Garrido Alenda
 * Se crea un jugador y varios personajes. Se invoca agregaRasgo de los personajes
 * con todos los rasgos. Se invoca situaPersonaje del jugador con los personajes.
 * Se invoca consulta del jugador con distintos datos. Se muestra el tablero del
 * jugador por pantalla.
 */
import java.util.ArrayList;
public class p08 {
	
	private static String[] getNombresPersonajes() {
		String[] names = {"Alicia","Rosana","Paco Macia","Leandro","Borja","Armando"};
		return names;
	}
	
	
	private static String[] getNombresRasgos() {
		String[] names = {"barba","ojos","bigote","pelo","gafas","pendientes","sombrero"};
		return names;
	}
	
	
	private static String[] getAtributos1() {
		String[] atr = {"tru","azules","true","largo","falsee","true","rapper"};
		return atr;
	}
	
	
	private static String[] getAtributos2() {
		String[] atr = {"larga",null,"false","moreno","sol","calvo","true"};
		return atr;
	}
	
	
	private static void creaRasgos(Personaje p,String[] n,String[] a){
		for(int i=0;i<n.length&&i<a.length;i++){
			try{
				p.agregaRasgo(n[i],a[i]);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}

	
	private static void situandoPersonajes(Jugador jug, ArrayList<Personaje> p) {
		int x=0;
		for(int i=0; i<3; i++)
			for(int j=0; j<2; j++) {
				try {
					jug.situaPersonaje(i, j, p.get(x++));
				} catch(Exception e) {
					System.out.println(e);
				}
			}
	}
	
	
	private static void consultaRasgos(Jugador jug) {
		int a = 7;
		int k = 1;
		if(jug != null) {
			for(int i=0; i<3; i++)
				for(int j=0; j<2; j++) {
					Rasgo r = jug.consulta(i, j, a--);
					System.out.println("Consulta " + k++ + ": " + r);
					if(a < 1) a = 7;
				}
		}
	}
	
	
	public static void main(String[] args) {
		Jugador player = new Jugador("Alejandro",3,2);
		ArrayList<Personaje> people = new ArrayList<Personaje>();
		String[] nper = p08.getNombresPersonajes();
		String[] nrasgo = p08.getNombresRasgos();
		String[] atr1 = p08.getAtributos1(), atr2 = p08.getAtributos2();
		for(int i=0; i<nper.length; i++) {
			Personaje p = new Personaje(nper[i]);
			if(i%2 == 0)
				p08.creaRasgos(p,nrasgo,atr1);
			else
				p08.creaRasgos(p,nrasgo,atr2);
			people.add(p);
		}
		p08.situandoPersonajes(player, people);
		p08.consultaRasgos(player);
		player.muestraTablero();
	}

}
