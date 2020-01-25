// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Jugador {

	private Personaje[][] tablero;
	private Personaje elegido;
	private String nombre;

	public Jugador(String n,int f,int c) {
		nombre = new String(n);
		int fil = 2, col = 3;
		if(f > 0) fil = f;
		if(c > 0) col = c;
		tablero = new Personaje[fil][col];
		elegido = null;
	}

	
	public boolean situaPersonaje(int i,int j,Personaje p)
	throws PersonajeIncompletoException {					// PRUEBA p03458910
		boolean devuelve = false;
		
		//  si el personaje esta completo y la posicion es valida y no la ocupa otro personaje entonces se situa
		if(p != null) {
			if(p.completo()) {
				if(i >= 0  &&  j >= 0  &&  i < tablero.length  &&  j < tablero[0].length  &&  tablero[i][j] == null) {
					tablero[i][j] = p;
					devuelve = true;
				}
			}
			else
				throw new PersonajeIncompletoException(p.getNombre());
		}
			
		return devuelve;
	}

	
	public boolean comienzaJuego(int i,int j)
	throws TableroIncompletoException {			 // PRUEBA p045910
		boolean devuelve = false;
		int faltan = this.tableroIncompleto();
		
		// si el tablero esta lleno y posicion es correcta sin tener elegido entonces tomamos al elegido
		if(faltan == 0) {
			if(i >= 0  &&  j >= 0  &&  i < tablero.length  &&  j < tablero[0].length)
				if(elegido == null) {
					elegido = tablero[i][j];
					devuelve = true;
				}
		}
		else
			throw new TableroIncompletoException(faltan);
		
		return devuelve;
	}
	
	
	public boolean pregunta(Rasgo r) {	// PRUEBA P05910
		boolean devuelve = false;
		
		// si alguno de los rasgos del elegido coincide con r devuelve true
		if(r != null) {
			
			if(elegido != null  &&  elegido.consulta(r))
				devuelve = true;
			
		}
		
		return devuelve;		
	}

	
	public Rasgo consulta(int i,int j,int k) { // PRUEBA p058910
		Rasgo r = null;
		String name = null;
		
		// si las posiciones son correctas se devuelve el rasgo del personaje
		if(i >= 0  &&  j >= 0  &&  i < tablero.length  &&  j < tablero[0].length  &&  tablero[i][j] != null)
			if(k > 0  &&  k < 8) {
				name = this.nameRasgo(k);
				r = tablero[i][j].consultaRasgo(name);
			}
		
		return r;
	}

	
	public int elimina(Rasgo r)
	throws PartidaGanadaException { // PRUEBA p05910
		int eliminados = 0;
		
		if(r != null) {
			
			// primero averiguamos los que no tienen r y los eliminamos
			for(int i=0; i<tablero.length; i++)
				for(int j=0; j<tablero[0].length; j++)
					if(tablero[i][j] != null  &&  !tablero[i][j].consulta(r)) {
						eliminados++;
						tablero[i][j] = null;
					}
			
			// despues comprobamos que si queda uno y lanzamos la excepcion
			if((this.tableroIncompleto() + 1)  ==  (tablero.length * tablero[0].length))
				throw new PartidaGanadaException(nombre,this.getGanador().getNombre());	// ERROR CORREGIDO se pasa por mensaje el jugador que queda en el tablero no su elegido
		}
		
		return eliminados;
	}

	
	public void muestraTablero() { // PRUEBA p0348
		for(int i=0; i<tablero.length; i++) {
			System.out.println("fila " + i + ":");
			for(int j=0; j<tablero[0].length; j++) {
				if(tablero[i][j] != null) {
					System.out.println(tablero[i][j].toString());
				}
			}
		}
	}
	

	public String getNombre() {
		return nombre;
	}


	public Personaje getElegido() {
		return elegido;
	}
	
	
	// metodos adicionales practica 3
	
	
	public int tableroIncompleto() { // devuelve el numero de personajes que faltan para completar el tablero
		int faltan = 0;
		
		for(int i=0; i<tablero.length; i++)
			for(int j=0; j<tablero[0].length; j++)
				if(tablero[i][j] == null)
					faltan++;
		
		return faltan;		
	}
	
	
	public int getFilas() { // devuelve las filas del tablero
		return tablero.length;
	}
	
	
	public int getColumnas() { // devuelve las columnas del tablero
		return tablero[0].length;
	}
	
	
	public Personaje[][] getTablero() { // devuelve el tablero
		return tablero;
	}
	
	
	public String nameRasgo(int k) { // averiguamos el nombre del rasgo siguiendo la enumeracion de agregaRasgo
		String name = null;
		
		if(k > 0  &&  k < 8) {
			switch(k) {
				case 1: name = new String("pelo");
				break;
				case 2: name = new String("ojos");
				break;
				case 3: name = new String("gafas");
				break;
				case 4: name = new String("pendientes");
				break;
				case 5: name = new String("bigote");
				break;
				case 6: name = new String("barba");
				break;
				case 7: name = new String("sombrero");
				break;
			}
		}
		
		return name;
	}
	
	
	public Personaje getGanador() { // devuelve el personaje que falta en el tablero
		Personaje p = null;
		
		for(int i=0; i<tablero.length && p == null; i++)
			for(int j=0; j<tablero[0].length && p == null; j++)
				if(tablero[i][j] != null)
					p = tablero[i][j];
		
		return p;					
	}

}
