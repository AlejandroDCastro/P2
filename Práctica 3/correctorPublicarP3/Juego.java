// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.io.*;
import java.util.ArrayList;
public class Juego {
	
	
	private static void colocaPersonajes(Jugador j, ArrayList<Personaje> per, ArrayList<Integer> pos, FileWriter d) { // coloca en el tablero del jugador los personajes en sus posiciones
		if(j != null  &&  per != null  &&  per.size() > 0  &&  pos != null  &&  pos.size() > 0) {
		
			// intentamos situar todos los personajes en sus posiciones
			for(int i=0; i<per.size() && i<pos.size(); i++) {
				try {
					j.situaPersonaje((pos.get(i)/10), (pos.get(i)%10), per.get(i));
					
				// si existe alguna excepcion escribimos en el fichero
				} catch(PersonajeIncompletoException e) {
					try {
						String cadena = new String("PersonajeIncompletoException: " + e.getMessage());
						d.write(cadena);
						d.write('\n');
					} catch(IOException err) {
						err.printStackTrace();
					}
				}
			}
			
		}
	}
	
	
	public static void meteRasgos(Personaje p, ArrayList<String> rasgos, FileWriter f) { // agregamos rasgos al personaje
		if(p != null  &&  rasgos != null  &&  rasgos.size() > 0  &&  f != null) {
			
			for(int i=0; i<rasgos.size(); i++) {
				try {
					String[] rasgo = rasgos.get(i).split(" ");
					if(rasgo.length > 1) {
						p.agregaRasgo(rasgo[0], rasgo[1]);
					}
				} catch(RasgoNoValidoException err) {
					try {
						String cadena = new String("RasgoNoValidoException: " + err.getMessage());
						f.write(cadena);
						f.write("\n");
					} catch(IOException e) {
						e.printStackTrace();
					}
				} catch(RasgoPreexistenteException err) {
					try {
						String cadena = new String("RasgoPreexistenteException: " + err.getMessage());
						f.write(cadena);
						f.write("\n");
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	
	private static void escribeTablero(Personaje[][] t, FileWriter d) // escribimos el tablero en el fichero de escritura
	throws IOException {
		
		if(t != null) {
			for(int i=0; i<t.length; i++) {
				d.write("fila " + Integer.toString(i) + ":");
				d.write('\n');
				for(int j=0; j<t[0].length; j++) {
					if(t[i][j] != null) {
						d.write(t[i][j].toString());
						d.write('\n');
					}
				}
			}
		}
			
	}
	
	
	private static int getPosicion(String s) { // devolvemos una posicion como un integer
		int pos = 0;
		
		if(s != null) {
			String[] d = s.split(" ");
			pos = (Integer.parseInt(d[0]) * 10) + Integer.parseInt(d[1]);
		}
		
		return pos;
	}
	
	
	private static ArrayList<Jugador> creaJugadores(ArrayList<String> a) { // creamos los dos jugadores del juego
		ArrayList<Jugador> j = null;
		
		if(a != null  &&  a.size() == 3) {
			j = new ArrayList<Jugador>();
			int f, c;
			String[] tam = a.get(2).split(" ");
			f = Integer.parseInt(tam[0]);
			c = Integer.parseInt(tam[1]);
			j.add(new Jugador(a.get(0),f,c));
			j.add(new Jugador(a.get(1),f,c));
		}
		
		return j;
	}
	
	
	private static ArrayList<Integer> getJugada(String s) { // devuelve la jugada de los jugadores en un array
		ArrayList<Integer> jugada = new ArrayList<Integer>();
		
		if(s != null) {
			String[] pos = s.split(" ");
			if(pos.length == 3) {
				jugada.add((Integer.parseInt(pos[0])*10) + Integer.parseInt(pos[1]));
				jugada.add(Integer.parseInt(pos[2]));
			}
		}
		
		return jugada;
	}
	
	
	public static void main(String[] args) {
		
		if(args.length > 1) {
			
			// las declaramos fuera del try para que el ambito sea mas amplio
			FileReader entrada = null;
			FileWriter descriptor = null;
			BufferedReader bufr = null;
			Jugador jugador1 = null, jugador2 = null;	// jugadores
			boolean excLanzada = false;
			
			// con este entero sabremos la accion que estamos llevando a cabo
			int accion = 0;
			try {
				
				// obtenemos el fichero y lo leemos por lineas
				entrada = new FileReader(args[0]);
				bufr = new BufferedReader(entrada);
				
				// abrimos el segundo para la escritura
				descriptor = new FileWriter(args[1]);
				
				String cadena = null;
				cadena = bufr.readLine();
				
				// variables que nos serviran durante todo el programa
				ArrayList<String> datos = new ArrayList<String>(); // guardar nombres jugadores
				ArrayList<Jugador> jugadores = null; // guardar jugadores
				ArrayList<String> rasgos = null; // rasgos de cada personaje
				String name = null;	// nombre de los personajes
				ArrayList<Integer> pos1 = null, pos2 = null; // posiciones de los personajes del jugador1 y jugador2
				ArrayList<Personaje> equipo1 = null, equipo2 = null; // personajes completos del jugador1 y 2
				int turno = 1; // turno indica la accion que se lleva a cabo
				boolean esPosicion = false;	// indicador que indica si cadena es posicion de un personaje
				ArrayList<Integer> posEleg = new ArrayList<Integer>(); // posiciones de los elegidos
				
				// hacemos un bucle para que se detecte el fin de fichero
				while(cadena != null) {
					
					switch(accion) {
					
						// caso 0 CREAMOS LOS JUGADORES
						case 0: {
							
							//
							if(datos.size() < 3)
								datos.add(cadena);
							else {
								jugadores = Juego.creaJugadores(datos);
								jugador1 = jugadores.get(0);
								jugador2 = jugadores.get(1);
								
								// nos preparamos para crear sus personajes
								equipo1 = new ArrayList<Personaje>();
								equipo2 = new ArrayList<Personaje>();
								pos1 = new ArrayList<Integer>();
								pos2 = new ArrayList<Integer>();
								rasgos = new ArrayList<String>();
								accion++;
							}
						}
						break;
						
						// caso 1 CREAMOS PERSONAJES CON SUS NOMBRES RASGOS Y POSICIONES EN EL TABLERO
						case 1: {
							
							
							if(cadena.equals("partida")) {
								if(name != null)
									if(turno == 2)
										Juego.meteRasgos(equipo2.get(equipo2.size()-1),rasgos,descriptor);
								Juego.colocaPersonajes(jugador1, equipo1, pos1, descriptor);
								Juego.colocaPersonajes(jugador2, equipo2, pos2, descriptor);
								accion++;
							}
							
							
							else if(cadena.equals("personajes")) {
								if(name != null)
									if(turno == 1)
										Juego.meteRasgos(equipo1.get(equipo1.size()-1),rasgos,descriptor);
								turno++;
								name = null;
								rasgos = new ArrayList<String>();
							}
							
							
							else if(cadena.equals("personaje")) {
								
								if(name != null) {
									if(turno == 1) {
										Juego.meteRasgos(equipo1.get(equipo1.size()-1),rasgos,descriptor);
									}
									else if(turno == 2) {
										Juego.meteRasgos(equipo2.get(equipo2.size()-1),rasgos,descriptor);
									}
									name = null;
									rasgos = new ArrayList<String>();
								}
								esPosicion = true;
							}
							
							
							else if(esPosicion) {
								if(turno == 1)
									pos1.add(Juego.getPosicion(cadena));
								else if(turno == 2) {
									pos2.add(Juego.getPosicion(cadena));
								}
								esPosicion = false;
							}
							
							
							else if(name == null) {
								name = new String(cadena);
								if(turno == 1) {
									equipo1.add(new Personaje(name));
								}
								else if(turno == 2) {
									equipo2.add(new Personaje(name));
								}
							}
							
							
							else if(name != null) {
								rasgos.add(cadena);
							}
						}
						break;
						
						// caso 2 EMPEZAMOS PARTIDA ALMACENANDO LOS ELEGIDOS DE CADA JUGADOR
						case 2: {
							posEleg.add(Juego.getPosicion(cadena));
							cadena = bufr.readLine();
							posEleg.add(Juego.getPosicion(cadena));
							
							// si los dos pueden comenzar juego entonces pasamos al siguiente caso o accion
							if(jugador1.comienzaJuego((posEleg.get(0)/10),(posEleg.get(0)%10))  &&  jugador2.comienzaJuego((posEleg.get(1)/10),(posEleg.get(1)%10))) {
								accion++;
								turno = 1;
							}
						}
						break;
						
						// caso 3 RECOGEMOS Y CONCLUIMOS JUGADAS
						case 3: {
							
							// obtenemos las jugadas
							pos1 = Juego.getJugada(cadena);
							Jugador j1 = null, j2 = null;
							
							// averiguamos quien es el pregunta y quien consulta
							if(turno == 1) {
								j1 = jugador1;
								j2 = jugador2;
								turno = 2;
							} else if(turno == 2) {
								j1 = jugador2;
								j2 = jugador1;
								turno = 1;
							}
							
							// realizamos dichas acciones
							Rasgo r = j1.consulta(pos1.get(0)/10, pos1.get(0)%10, pos1.get(1));
							if(r != null) {	
								descriptor.write(r.toString());
								descriptor.write('\n');
								boolean cierto = j2.pregunta(r);
								descriptor.write(Boolean.toString(cierto));
								descriptor.write('\n');
								if(cierto)
									j1.elimina(r);
							}
						}
						break;
					}
					if(accion < 4)
						cadena = bufr.readLine();
					else
						cadena = null;
					
				}
			
			// tratamos las excepciones
			// si se produce esta excepcion el juego debe terminar
			} catch(TableroIncompletoException err) {
				try {
					excLanzada = true;
					String excepcion = new String("TableroIncompletoException: " + err.getMessage());
					descriptor.write(excepcion);
					descriptor.write('\n');
					Juego.escribeTablero(jugador1.getTablero(), descriptor);
					descriptor.write('\n');
					Juego.escribeTablero(jugador2.getTablero(), descriptor);
					
				} catch(IOException e) {
					e.printStackTrace();
				}
			} catch (IOException err) {
				err.printStackTrace();
			} catch(PartidaGanadaException err) {
				try {
					String ganada = new String("PartidaGanadaException: " + err.getMessage());
					descriptor.write(ganada);
					descriptor.write('\n');
				} catch(IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					
					// finalmente escribimos los tableros de ambos jugadores
					if(!excLanzada) {
						Juego.escribeTablero(jugador1.getTablero(), descriptor);
						descriptor.write('\n');
						Juego.escribeTablero(jugador2.getTablero(), descriptor);
					}
					
					// y cerramos ficheros
					if(bufr != null) {
						try { bufr.close(); }
						catch(IOException e) { e.printStackTrace(); }
					}
					if(entrada != null) {
						try { entrada.close(); }
						catch(IOException e) { e.printStackTrace(); }
					}
					if(descriptor != null) {
						try { descriptor.close(); }
						catch(IOException e) { e.printStackTrace(); }
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}
