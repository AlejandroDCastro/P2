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
		int pos_libre_x_ = -1, pos_libre_y_ = -1, fila_libre_ = -1, cont_;
		
		if (p != null  &&  p.getPlantada() == null  &&  p.getEstado().equals("semilla")) {
			
			for (int i=0; i<huerto.length; i++) {
				pos_libre_x_ = i;
				for (int j=0; j<huerto[i].length; j++) {
					if (huerto[i][j] != null) {
						if (huerto[i][j].getNombre().equals(p.getNombre())) {
							i = huerto.length;
						} else {
							pos_libre_y_ = -1;
							break;
						}
					} else {
						pos_libre_y_ = j;
						cont_ ++;
					}
				}
				
				// comprobamos si la fila esta libre o no
				if (cont_ == huerto[i].length) {
					fila_libre_ = i;
				}
				cont_ = 0;
			}
			
		}
		
		if (pos_libre_y_ > -1) {
			huerto[pos_libre_x_][pos_libre_y_] = p;
			devuelve_ = true;
		} else if (fila_libre_ > -1) {
			huerto[fila_libre_][huerto[0].length-1];
			devuelve = true;
		}
		
		return devuelve_;
	}

/*
	public ArrayList<Fruto> recolecta(String) {

	}


	public int abona(int,String) {

	}


	public String consulta(int,int) {

	}


	public Planta arranca(String,int,int) {

	}


	public void localiza(double,double) {

	}


	public Coordenada getLocalizacion() {

	}


	public Persona getCuidador() {

	}


	public ArrayList<String> getAdultas() {

	}


	public static ArrayList<Huerta> getLocalizadas() {

	}


	public void setCuidador(Persona) {

	}


	public Planta[][] getHuerto() {

	}
*/
}
