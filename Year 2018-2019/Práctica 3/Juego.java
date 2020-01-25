// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.io.*;
import java.util.ArrayList;

public class Juego {
	
	
	
	// metodo que devuelve los jugadores que participan el el juego
	private static ArrayList<Jugador> creaJugadores(int n, ArrayList<String> nombres) throws ObjetoNoValidoException {
		ArrayList<Jugador> jugadores_aux_ = new ArrayList<Jugador>();
		int num_;
		
		if (nombres != null) {
			num_ = (n > 1  &&  n < 5) ? n : 2;
			for (int i=0; i<num_; i++) {
				if (i < nombres.size()) {
					jugadores_aux_.add(new Jugador(nombres.get(i)));
				} else {
					jugadores_aux_.add(new Jugador(""));
				}
			}
		}
		
		return jugadores_aux_;
	}
	
	

	public static void main(String[] args) {
		FileReader entrada_ = null;
		FileWriter descriptor_ = null;
		BufferedReader buffer_lectura_ = null;
		int num_jugadores_aux_ = 0, contador_accion_ = 0, turno_ = 0;
		ArrayList<String> nombres_jugadores_aux_ = new ArrayList<String>();
		ArrayList<Jugador> jugadores_aux_ = new ArrayList<Jugador>(), jugadores_; 
		String cadena_ = null;
		Saco saco_ = null;
		String[] numeros_ = null;
		Ficha ficha_nueva_ = null, colocada_ = null;
		boolean mete_ficha_ = false, partida_acabada_ = false;
		Tablero tablero_ = null;
		
		try {
			
			
			// abrimos los ficheros de lectura y escritura
			entrada_ = new FileReader(args[0]);
			buffer_lectura_ = new BufferedReader(entrada_);
			descriptor_ = new FileWriter(args[1]);
			
			// leemos la primera linea
			cadena_ = buffer_lectura_.readLine();
			
			// creamos bucle para leer toda la informacion
			while (cadena_ != null) {
				
				switch (contador_accion_) {
				
					// leemos el numero de jugadores que participan
					case 0:
						num_jugadores_aux_ = Integer.parseInt(cadena_);
						contador_accion_++;
						break;
						
					// guardamos los nombres y creamos jugadores
					case 1:
						if (cadena_.equals("saco")) {
							jugadores_aux_ = creaJugadores(num_jugadores_aux_, nombres_jugadores_aux_);
							saco_ = new Saco();
							contador_accion_++;
						} else {
							nombres_jugadores_aux_.add(cadena_);
						}
						break;
						
					// leemos la informacion para crear fichas y meterlas en el saco
					case 2:
						numeros_ = cadena_.split(" ");
						ficha_nueva_ = new Ficha(Integer.parseInt(numeros_[0]), Integer.parseInt(numeros_[1]));
						try {
							mete_ficha_ = saco_.meteFicha(ficha_nueva_);
							descriptor_.write("mete " + ficha_nueva_.toString() + " " + mete_ficha_ + '\n');
						} catch (FichaRepetidaException err) {
							descriptor_.write("FichaRepetidaException: " + err.getMessage() + '\n');
						}
						
				}
				
				// leemos la siguiente linea del fichero
				cadena_ = buffer_lectura_.readLine();
			}
			
			// despues leer el fichero si no se ha completado el saco el juego acaba
			tablero_ = new Tablero();
			if (!saco_.completado()) {
				throw new SacoIncompletoException(saco_.getFichas());
			}
			
			// cada jugador roba siete fichas
			for (int i=0; i<7*jugadores_aux_.size(); i++) {
				descriptor_.write(jugadores_aux_.get(turno_).getNombre() + " roba " + jugadores_aux_.get(turno_).roba(saco_) + '\n');
				turno_ = (turno_ < jugadores_aux_.size()-1) ? turno_+1 : 0;
			}
			
			// organizamos el turno de cada jugador en funcion del valor maximo de sus fichas
			jugadores_ = new ArrayList<Jugador>();
			for (int i=0; i<jugadores_aux_.size(); i++) {
				if (jugadores_.size() == 0) {
					jugadores_.add(jugadores_aux_.get(i));
				} else {
					int tamanyo_ = jugadores_.size();
					for (int j=0; j<jugadores_.size(); j++) {
						if (jugadores_.get(j).valorMaximo() < jugadores_aux_.get(i).valorMaximo()) {
							jugadores_.add(j, jugadores_aux_.get(i));
						} else if (j == jugadores_.size()-1) {
							jugadores_.add(jugadores_aux_.get(i));
						}
						
						// salimos del bucle
						if (tamanyo_ != jugadores_.size()) {
							break;
						}
					}
				}
			}
			
			// empieza la partida con dichos jugadores
			String jugada_ = null;
			turno_ = 0;
			while (true) {
				try {
					colocada_ = jugadores_.get(turno_).juega(tablero_);
					jugada_ = (colocada_ != null) ? colocada_.toString() : null;
					descriptor_.write(jugadores_.get(turno_).getNombre() + " juega " + jugada_ + '\n');
					if (jugada_ == null) {
						descriptor_.write(jugadores_.get(turno_).getNombre() + " roba " + jugadores_.get(turno_).roba(saco_) + '\n');
					}
					turno_ = (turno_ == jugadores_.size()-1) ? 0 : turno_+1;
				} catch (JugadaIncorrectaException err) {
					err.printStackTrace();
				}
			}
			
			
		} catch (ObjetoNoValidoException err) {
			
			try {
				descriptor_.write("ObjetoNoValidoException: " + err.getMessage() + '\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (SacoIncompletoException err) {
			
			try {
				descriptor_.write("SacoIncompletoException: " + err.getMessage() + '\n');
				descriptor_.write(saco_.toString() + '\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (PartidaGanadaException err) {
			
			try {
				descriptor_.write("PartidaGanadaException: " + err.getMessage() + '\n');
				partida_acabada_ = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (CierreException err) {
					
			try {
				descriptor_.write("CierreException: " + err.getMessage() + '\n');
				partida_acabada_ = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (IOException err) {
			
			err.printStackTrace();
			
		} finally {
			
			// si la partida se ha podido terminar mostramos estado del tablero y los jugadores
			if (partida_acabada_) {
				try {
					descriptor_.write(tablero_.toString() + '\n');
					for (int i=0; i<jugadores_aux_.size(); i++) {
						descriptor_.write(jugadores_aux_.get(i).toString() + '\n');
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			// por ultimo cerramos ficheros para guardar los cambios
			if (buffer_lectura_ != null) {
				try { buffer_lectura_.close(); }
				catch (IOException e) {	e.printStackTrace(); }
			}
			if (entrada_ != null) {
				try { entrada_.close(); }
				catch (IOException e) {	e.printStackTrace(); }
			}
			if (descriptor_ != null) {
				try { descriptor_.close(); }
				catch (IOException e) {	e.printStackTrace(); }
			}
			
		}
	}

}
