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
		if (p != null  &&  p.getEstado().equals("semilla")) {
			
		}
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