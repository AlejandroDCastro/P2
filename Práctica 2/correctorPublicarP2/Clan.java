// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Clan {
	
	private String nombre;
	private ArrayList<Habitante> miembros;
	private ArrayList<Terreno> feudo;
	private Mistico deidad;
	private static ArrayList<Clan> clanes = new ArrayList<Clan>(); // variable adicional para tener constancia de los clanes
	
	public Clan(String n) { // REVISADO p0345
		nombre = new String(n);
		deidad = null;
		miembros = new ArrayList<Habitante>();
		feudo = new ArrayList<Terreno>();
		
		// metemos al clan los miembros de la poblacion que tienen como apellido el nombre del clan
		for(int i=0; i<Habitante.getPoblacion().size(); i++) {
			Habitante h = Habitante.getPoblacion().get(i);
			if(h != null  &&  h.perteneceClan(nombre))
				miembros.add(h);
		}
		for(int i=0; i<miembros.size(); i++) {
			Habitante h = miembros.get(i);
			if(h != null  &&  h.perteneceClan(nombre)) // ERROR CORREGIDO estaba quitando los miembros mientras redimensionaba los arrays
				Habitante.getPoblacion().remove(h);
		}
		
		// actualiza las tribus de los miembros
		for(int i=0; i<miembros.size(); i++)
			miembros.get(i).esAcogido(this);
		
		// metemos el clan creado en la variable clanes
		clanes.add(this);
	}
	
	
	public boolean conquista(Terreno t) { // REVISADO p034567810 PruebaClan
		boolean devuelve = false, igual = false;
		
		if(t != null) {
			
			// Pimero recorremos los terrenos de cada clan
			for(int i=0; i<Clan.clanes.size(); i++) {
				Clan c = Clan.clanes.get(i);
				if(c != null  &&  c.getFeudo() != null)
					for(int j=0; j<c.getFeudo().size(); j++) {
						Terreno r = c.getFeudo().get(j);
						
						// si ya hay un clan con t rompemos bucles
						if(r != null  &&  r == t) {
							igual = true;
							j = c.getFeudo().size();
							i = Clan.clanes.size();
						}
					}
			}
			
			// si no hay un clan con t entonces la metemos en el nuestro
			if(!igual) {
				feudo.add(t);
				devuelve = true;
			}
			
		}
		
		return devuelve;
	}
	
	
	public String enfrenta(Clan c) {	// REVISADO p10 PruebaClan
		String ganador = null;
		int miem = 0, miemc = 0, victorias = 0, derrotas = 0;
		
		if(c != null  &&  c != this) {
			
			// primero averiguamos cuales son los guerreros de cada clan
			ArrayList<Guerrero> ejercito = this.getGuerrerosDelClan();
			ArrayList<Guerrero> ejercitoc = c.getGuerrerosDelClan();
			
			// los enfrentamos contando el numero de victorias y derrotas de la instancia actual
			while(miem < ejercito.size()  &&  miemc < ejercitoc.size()) {
				if(ejercito.get(miem).lucha(ejercitoc.get(miemc)) == 2)
					victorias++;
				else if(ejercito.get(miem).lucha(ejercitoc.get(miemc)) == 1)
					derrotas++;
				miem++;
				miemc++;
			}
			
			// despues averiguamos cual es el clan ganador
			Clan winner = null, loser = null;
			if(victorias > derrotas  ||  (victorias == derrotas  &&  ejercito.size() > ejercitoc.size())) {
				ganador = nombre;
				winner = this;
				loser = c;
			}
			else if(victorias < derrotas  ||  (victorias == derrotas  &&  ejercito.size() < ejercitoc.size())) {
				ganador = c.getNombre();
				winner = c;
				loser = this;
			}
			
			if(winner != null  &&  loser != null) {	// ERROR CORREGIDO menudo nullpointer iba a salir aqui
				
				// por ultimo castigamos al clan perdedor quitandole su mistico y dandole el nuestro
				if(loser.getDeidad() instanceof Blanco)
					((Blanco) loser.getDeidad()).quitaClan(c);
				loser.quitaDeidad();
				loser.adoptaDeidad(winner.getDeidad());
			}
		}
		
		return ganador;
	}
	
	
	public int laboro(int i) { // REVISADO PruebaClan
		int recolectados = 0;
		
		if(miembros != null  &&  feudo != null) {
			
			// cada miembro recolectara el terreno en funcion de la posicion que ocupe el mismo en el clan
			for(int x=0; x<miembros.size() && x<feudo.size(); x++) {
				if(miembros.get(x) != null  &&  feudo.get(x) != null) {
					
					// si es un plebeyo se pasa la posicion del terreno a recolectar junto con la cantidad de producto
					if(miembros.get(x) instanceof Plebeyo) {
						Plebeyo p = (Plebeyo) miembros.get(x);
						int r = p.recolecta(x, i);
						if(r > 0)
							recolectados += r;
					}
					
					// y si es un guerrero solo la posicion del terreno
					else if(miembros.get(x) instanceof Guerrero) {
						Guerrero g = (Guerrero) miembros.get(x);
						recolectados += g.recolecta(x);
					}
				}
			}
			
		}
		
		return recolectados;
	}
	
	
	public Habitante destierra(String s) { // REVISADO p04  PruebaClan
		Habitante traidor = null;
		
		if(s != null  &&  miembros != null) {
			
			// identificamos al habitante a desterrar y lo removemos
			for(int i=0; i<miembros.size(); i++)
				if(miembros.get(i) != null  &&  s.equalsIgnoreCase(miembros.get(i).getNombre())) {
					if(miembros.get(i).esDesterrado()) 
						traidor = miembros.remove(i);
					break;
				}
			
		}
		
		return traidor;
	}
	
	
	public int acoge(Habitante h) { // REVISADO PruebaClan
		
		if(h != null) {
			
			// metemos a h al clan y le apellidamos con el nombre del clan
			miembros.add(h);
			h.cambiaClan(nombre);
			
			// si pertenecia a una tribu lo desterreamos y lo acogemos en este clan
			if(h.getTribu() != null)
				h.esDesterrado();
			h.esAcogido(this);
			
		}
		
		return miembros.size();
	}
	
	
	public boolean adoptaDeidad(Mistico n) {	// REVISADO p089 PruebaClan
		boolean devuelve = false;
		
		// si el clan no tenia antes una deidad
		if(n != null  &&  deidad == null) {
			
			// adoptamos a la que nos pasan por parametro
			deidad = n;
			devuelve = true;
			
			// si es un blanco le pasamos un mensaje de fervor
			if(deidad instanceof Blanco)
				((Blanco) deidad).fervor(this);
			
		}
		
		return devuelve;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public ArrayList<Habitante> getMiembros() {
		return miembros;
	}
	
	
	public Mistico getDeidad() {
		return deidad;
	}
	
	
	public ArrayList<Terreno> getFeudo() {
		return feudo;
	}
	
	
	// metodos adicionales de la practica 3
	
	public Producto deFeudoUnSoloProducto() { // devuelve el primer producto que encuentre en el feudo no edificado
		Producto p = null;
		
		for(int x=0; x<feudo.size(); x++) {
			Terreno r = feudo.get(x);
			for(int i=0; i<r.getFilas(); i++)
				for(int j=0; j<r.getColumnas(); j++) {
					if(r.consultaTipo(i, j) > 0  &&  r.consultaTipo(i, j) != 5) {
						p = r.recoge(i, j);
						j = r.getColumnas();
						i = r.getFilas();
						x = feudo.size();
					}
				}
		}
		
		return p;
	}
	
	
	public ArrayList<Guerrero> getGuerrerosDelClan() { // devuelve los guerreros del clan
		ArrayList<Guerrero> tontos = new ArrayList<Guerrero>();
		
		if(miembros != null  &&  miembros.size() > 0) {
			
			for(int i=0; i<miembros.size(); i++)
				if(miembros.get(i) != null  &&  miembros.get(i) instanceof Guerrero)
					tontos.add((Guerrero) miembros.get(i));
			
		}
		
		return tontos;
	}
	
	
	public void quitaDeidad() {
		deidad = null;
	}
	
	
	public ArrayList<Plebeyo> getPlebeyosDelClan() { // devuelve los plebeyos del clan
		ArrayList<Plebeyo> tontos = new ArrayList<Plebeyo>();
		
		if(miembros != null  &&  miembros.size() > 0) {
			
			for(int i=0; i<miembros.size(); i++)
				if(miembros.get(i) != null  &&  miembros.get(i) instanceof Plebeyo)
					tontos.add((Plebeyo) miembros.get(i));
			
		}
		
		return tontos;
	}

}
