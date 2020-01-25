// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Tablero {
	
	
	private ArrayList<Ficha> fichas;
	
	
	public Tablero() {
		fichas = new ArrayList<Ficha>();
	}
	
	
	public boolean coloca(Ficha f, int p) throws JugadaIncorrectaException, CierreException {
		boolean devuelve_ = false;
		Ficha adyacente_ = null;
		
		if (f != null) {
			
			// primero intentamos colocar la ficha en el tablero
			if (fichas.size() > 0) {
				if (p == 0) {
					adyacente_ = fichas.get(0);
					if (f.getSegunda() == adyacente_.getPrimera()) {
						fichas.add(0, f);
					} else {
						throw new JugadaIncorrectaException(f, adyacente_);
					}
				} else {
					adyacente_ = fichas.get(fichas.size()-1);
					if (f.getPrimera() == adyacente_.getSegunda()) {
						fichas.add(f);
					} else {
						throw new JugadaIncorrectaException(f, adyacente_);
					}
				}
			} else {
				fichas.add(f);
			}
			devuelve_ = true;
				
			// despues comprobamos si se ha producido el cierre
			if (this.situacionCierre()) {
				throw new CierreException(fichas.get(0).getPrimera());
			}
			
		}
		
		return devuelve_;
	}
	
	
	public ArrayList<Integer> getSecuencia() {
		ArrayList<Integer> devuelve_ = new ArrayList<Integer>();
		
		for (int i=0; i<fichas.size(); i++) {
			devuelve_.add(fichas.get(i).getPrimera());
			devuelve_.add(fichas.get(i).getSegunda());
		}
		
		return devuelve_;
	}
	
	
	public int getInicio() {
		if (fichas.size() > 0) {
			return fichas.get(0).getPrimera();
		} else {
			return -1;
		}
	}
	
	
	public int getFin() {
		if (fichas.size() > 0) {
			return fichas.get(fichas.size()-1).getSegunda();
		} else {
			return -1;
		}
	}
	
	
	public String toString() {
		String devuelve_ = new String("{");
		
		for (int i=0; i<fichas.size(); i++) {
			devuelve_ = devuelve_.concat(fichas.get(i).toString());
			if (i < fichas.size()-1) {
				devuelve_ = devuelve_.concat(",");
			}
		}
		devuelve_ = devuelve_.concat("}");
		
		return devuelve_;
	}
	
	
	// metodo que devuelve true si se produce la situacion de cierre
	public boolean situacionCierre() {
		boolean devuelve_ = false;
		int cont_fichas_ = 0, num_cierre_ = -1;
		
		if (fichas.size() > 0  &&  fichas.get(0).getPrimera() == fichas.get(fichas.size()-1).getSegunda()) {
			num_cierre_ = fichas.get(0).getPrimera();
			for (int i=0; i<fichas.size(); i++) {
				if (num_cierre_ == fichas.get(i).getPrimera()  ||  num_cierre_ == fichas.get(i).getSegunda()) {
					cont_fichas_++;
				}
			}
			if (cont_fichas_ == 7) {
				devuelve_ = true;
			}
		}
		
		return devuelve_;
	}
	
	
	// metodo que coloca una ficha y devuelve true si se produce cierre
	public boolean anticipaCierre(Ficha f) {
		boolean devuelve_ = false;
		
		if (f != null) {
			fichas.add(0, f);
			devuelve_ = this.situacionCierre();
			fichas.remove(0);
		}
		
		return devuelve_;
	}

}
