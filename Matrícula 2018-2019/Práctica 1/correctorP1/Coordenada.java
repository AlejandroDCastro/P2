// DNI 20521823 CASTRO VALERO, ALEJANDRO

public class Coordenada {

	private double latitud; // norte y sur eje y
	private double longitud; // este y oeste eje x



	public Coordenada(double lat, double lon) {
		latitud = (lat>=-90 && lat<=90) ? lat : 0;
		longitud = (lon>=-180 && lon<=180) ? lon : 0;
	}


	public boolean iguales(Coordenada c) {
		if (c != null  &&  c.getLatitud() == latitud  &&  c.getLongitud() == longitud) {
			return true;
		} else {
			return false;
		}
	}


	public double distancia(Coordenada c) {
		double distancia_ = -1, resultado_;

		if (c != null) {
			resultado_ = Math.pow((latitud-c.getLatitud()), 2) + Math.pow((longitud-c.getLongitud()), 2);
			distancia_ = Math.sqrt(resultado_);
		}

		return distancia_;
	}


	public double getLatitud() {
		return latitud;
	}


	public double getLongitud() {
		return longitud;
	}
	

}
