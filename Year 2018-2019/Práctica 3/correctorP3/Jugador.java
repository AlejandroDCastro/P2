// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Jugador {
	
	
	private ArrayList<Ficha> propias;
	private String nombre;
	
	
	public Jugador(String n) throws ObjetoNoValidoException {
		propias = new ArrayList<Ficha>();
		if (n != null  &&  !n.equals("")) {
			nombre = new String(n);
		} else {
			throw new ObjetoNoValidoException("No name");
		}
	}
	
	
	public boolean roba(Saco s) {
		boolean devuelve_ = false;
		Ficha nueva_ = null;
		
		if (s != null) {
			nueva_ = s.roba();
			if (nueva_ != null) {
				propias.add(nueva_);
				devuelve_ = true;
			}
		}
		
		return devuelve_;
	}
	
	
	public Ficha elige(Tablero t) {
		Ficha ficha_elegida_ = null;
		int inicio_, final_, primera_, segunda_, suma_aux_, suma_total_ = -1, indice_ = -1;
		
		if (t != null) {
			
			inicio_ = t.getInicio();
			final_ = t.getFin();
			
			// comprobamos si tiene fichas el tablero
			if (inicio_ > -1  &&  final_ > -1) {
				for (int i=0; i<propias.size(); i++) {
					primera_ = propias.get(i).getPrimera();
					segunda_ = propias.get(i).getSegunda();
					if (primera_ == inicio_  ||  segunda_ == inicio_  ||  primera_ == final_  ||  segunda_ == final_) {
						
						// se elige la ficha que tenga mayor numero en la suma de sus dos partes
						suma_aux_ = primera_ + segunda_;
						if (suma_aux_ > suma_total_) {
							suma_total_ = suma_aux_;
							indice_ = i;
						}
					}
				}
			} else {
				
				// si el tablero no tiene fichas se elige la de mayor valor total de las ultimas que haya robado
				for (int i=0; i<propias.size(); i++) {
					suma_aux_ = propias.get(i).getPrimera() + propias.get(i).getSegunda();
					if (suma_aux_ >= suma_total_) {
						suma_total_ = suma_aux_;
						indice_ = i;
					}
				}
			}
			
			// si encuentra la ficha adecuada la metemos
			if (indice_ > -1) {
				ficha_elegida_ = propias.remove(indice_);
			}
			
		}
		
		return ficha_elegida_;
	}
	
	
	public Ficha juega(Tablero t) throws PartidaGanadaException, CierreException, JugadaIncorrectaException, ObjetoNoValidoException {
		Ficha ficha_elegida_ = null;
		int primera_, segunda_, colocada_aux_ = -2, colocada_ = -2, contador_ = 0;
		
		if (t != null) {
			
			ficha_elegida_ = this.elige(t);
			if (ficha_elegida_ != null) {
				
				if (t.getSecuencia().size() > 0) {
				
					// comprobamos donde se puede colocar la ficha en el tablero
					while (contador_ < 2) {
						primera_ = ficha_elegida_.getPrimera();
						segunda_ = ficha_elegida_.getSegunda();
						if (primera_ == t.getFin()  &&  segunda_ != t.getInicio()) {
							colocada_aux_ = 1;
						} else if (primera_ != t.getFin()  &&  segunda_ == t.getInicio()) {
							colocada_aux_ = -1;
						} else if (primera_ == t.getFin()  &&  segunda_ == t.getInicio()) {
							colocada_aux_ = 0;
						} else {
							ficha_elegida_ = ficha_elegida_.creaInversa();
						}
						if (colocada_aux_ != -2) {
							break;
						}
						contador_++;
					}
					
					// comprobamos si produce o no situacion de cierre y asignamos variables
					if (colocada_aux_ == 0) {
						if (t.anticipaCierre(ficha_elegida_)) {
							colocada_ = 1;
						} else {
							colocada_ = 0;
						}
					} else if (colocada_aux_ == -1) {
						colocada_ = 0;
					} else if (colocada_aux_ == 1) {
						colocada_ = 1;
					}
					
					// colocamos la ficha en el tablero
					if (colocada_ != -2  &&  t.coloca(ficha_elegida_, colocada_)) {
						if (propias.size() == 0) {
							throw new PartidaGanadaException(nombre);
						}
					} else {
						ficha_elegida_ = null;
					}
					
				} else {
					
					t.coloca(ficha_elegida_, 0);
					
				}
			}
			
		}
		
		return ficha_elegida_;
	}
	
	
	public int valorMaximo() {
		int valor_maximo_ = -1, valor_aux_;
		
		for (int i=0; i<propias.size(); i++) {
			valor_aux_ = propias.get(i).getPrimera() + propias.get(i).getSegunda();
			if (valor_aux_ > valor_maximo_) {
				valor_maximo_ = valor_aux_;
			}
		}
		
		return valor_maximo_;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public String toString() {
		String devuelve_ = new String(nombre + "{");
		
		for (int i=0; i<propias.size(); i++) {
			devuelve_ = devuelve_.concat(propias.get(i).toString());
			if (i < propias.size()-1) {
				devuelve_ = devuelve_.concat(",");
			}
		}
		devuelve_ = devuelve_.concat("}");
		
		return devuelve_;
	}

}
