// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;


public class Valores {

	// Variables de instancia

	private static class Relacion {

		private String nombre;
		private double valor; // calorias por kilo

		public Relacion(String n, double v) {
			valor = (v>0) ? v : 0.5;
			nombre = (n!=null) ? n : "pitaya";
		}

		public String getNombre() {
			return nombre;
		}

		public double getValor() {
			return valor;
		}

	}
	public static ArrayList<Relacion> clasificacion = new ArrayList<Relacion>();


	// Metodos

	public static boolean add(String cad, double val) {
		Relacion nueva_ = new Relacion(cad, val);
		boolean esta_ = false;

		for (int i=0; i<clasificacion.size(); i++) {
			if (nueva_.getNombre().equalsIgnoreCase(clasificacion.get(i).getNombre())) {
				esta_ = true;
				break;
			}
		}

		if (!esta_) {
			clasificacion.add(nueva_);
			return true;
		} else {
			return false;
		}
	}


	public static double consulta(String cad) {
		double devuelve_ = -1.0;

		if(cad != null) {
			for (int i=0; i<clasificacion.size(); i++) {
				if (cad.equalsIgnoreCase(clasificacion.get(i).getNombre())) {
					devuelve_ = clasificacion.get(i).getValor();
					break;
				}
			}
		}

		return devuelve_;
	}


	public static ArrayList<String> getNombres() {
		ArrayList<String> cadenas_ = new ArrayList<String>();

		for (int i=0; i<clasificacion.size(); i++) {
			cadenas_.add(clasificacion.get(i).getNombre());
		}

		return cadenas_;
	}


}