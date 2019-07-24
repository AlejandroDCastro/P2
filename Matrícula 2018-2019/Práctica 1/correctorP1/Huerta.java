// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Huerta {

	private Planta[][] huerto;
	private Persona cuidador;
	private Coordenada localizacion;
	private static ArrayList<Huerta> localizadas;


	public Huerta(int t1, int t2) {
		int f_, c_;

		if (t1 > 0) { f_ = t1; } else { f_ = 2; }
		if (t2 > 0) { c_ = t2; } else { c_ = 2; }
		huerto = new Planta[f_][c_];
		cuidador = null;
		localizacion = null;
		localizadas = new ArrayList<Huerta>();
	}


	public boolean planta(Planta p) {
		boolean devuelve_ = false;
		int pos_libre_x_ = -1, pos_libre_y_ = -1, fila_libre_ = -1, cont_ = 0;
		
		if (p != null  &&  p.getPlantada() == null  &&  p.getEstado().equals("semilla")) {
			
			for (int i=0; i<huerto.length; i++) {
				pos_libre_x_ = i;
				for (int j=0; j<huerto[i].length; j++) {
					if (huerto[i][j] != null) {
						if (huerto[i][j].getNombre().equals(p.getNombre())) {
							i = huerto.length-1;
						} else {
							pos_libre_y_ = -1;
							break;
						}
					} else {
						pos_libre_y_ = j;
						cont_++;
					}
				}
				
				// comprobamos si la fila esta libre o no
				if (cont_ == huerto[i].length) {
					fila_libre_ = i;
				}
				cont_ = 0;
			}
			
		}
		
		if (pos_libre_y_ > -1  ||  fila_libre_ > -1) {
			if (pos_libre_y_ > -1) {
				huerto[pos_libre_x_][pos_libre_y_] = p;
			} else if (fila_libre_ > -1) {
				huerto[fila_libre_][huerto[0].length-1] = p;
			}
			p.setPlantada(this);
			devuelve_ = true;
		}
		
		return devuelve_;
	}


	public ArrayList<Fruto> recolecta(String n) {
		ArrayList<Fruto> devuelve_ = new ArrayList<Fruto>();
		
		if (n != null) {
			
			for (int i=0; i<huerto.length; i++) {
				for (int j=0; j<huerto[i].length; j++) {
					if (huerto[i][j] != null) {
						if (huerto[i][j].getFruto().equalsIgnoreCase(n)) {
							devuelve_.addAll(huerto[i][j].recolecta());
						} else {
							break;
						}
					}
				}
				if(devuelve_.size() > 0) {
					break;
				}
			}
			
		}
		
		return devuelve_;
	}


	public int abona(int p, String n) {	// n es el nombre de la planta
		int devuelve_ = 0;
		
		if (p > 0  &&  n != null) {
			
			for (int i=0; i<huerto.length; i++) {
				for (int j=0; j<huerto[i].length; j++) {
					if (huerto[i][j] != null) {
						if (huerto[i][j].getNombre().equalsIgnoreCase(n)) {
							devuelve_ += (huerto[i][j].abona(p)) ? 1 : 0;
						} else {
							break;
						}
					}
				}
				if (devuelve_ > 0) {
					break;
				}
			}
			
		}
		
		return devuelve_;
	}


	public String consulta(int f, int c) {
		if (f > 0  &&  f < huerto.length  &&  c > 0  &&  c < huerto[0].length) {
			return huerto[f][c].getNombre();
		} else {
			return null;
		}
	}


	public Planta arranca(String n, int f, int c) {		// n es el nombre de la planta
		Planta arrancada_ = null;
		
		if (f > 0  &&  f < huerto.length  &&  c > 0  &&  c < huerto[0].length  &&  n != null) {
			if (n.equalsIgnoreCase(huerto[f][c].getNombre())) {
				arrancada_ = huerto[f][c];
				arrancada_.arranca();
				huerto[f][c] = null;
			}
		}
		
		return arrancada_;
	}


	public void localiza(double lat, double lon) {
		boolean esta_localizada_ = false;
		
		for (int i=0; i<localizadas.size(); i++) {
			if (localizadas.get(i) == this) {
				esta_localizada_ = true;
				break;
			}
		}
		
		if (!esta_localizada_) {
			localizacion = new Coordenada(lat, lon);
			localizadas.add(this);
		}
	}
	
	
	public ArrayList<String> getAdultas() {
		ArrayList<String> adultas_ = new ArrayList<String>();
		
		for (int i=0; i<huerto.length; i++) {
			for (int j=0; j<huerto[i].length; j++) {
				if (huerto[i][j] != null  &&  huerto[i][j].getEstado().equals("adulta")) {
					adultas_.add(huerto[i][j].getNombre());
					break;
				}
			}
		}
		
		return adultas_;
	}


	public Coordenada getLocalizacion() {
		return localizacion;
	}


	public Persona getCuidador() {
		return cuidador;
	}


	public static ArrayList<Huerta> getLocalizadas() {
		return localizadas;
	}


	public void setCuidador(Persona c) {
		cuidador = c;
	}


	public Planta[][] getHuerto() {
		return huerto;
	}
	
}
