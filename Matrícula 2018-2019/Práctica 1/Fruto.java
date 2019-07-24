// DNI 20521823 CASTRO VALERO, ALEJANDRO

public class Fruto {

	private boolean estado;
	private double peso;
	private String nombre;


	public Fruto(String n) {
		if (Valores.consulta(n) > 0.0) {
			nombre = new String(n);
		} else {
			nombre = new String("pitaya");
			Valores.add(nombre, 0);
		}
		peso = 0;
		estado = false;
	}


	public boolean transforma(int p) {
		if (p > 0) { peso += 0.2*p; }
		if (peso >= 0.3  &&  !estado) {
			estado = true;
			return true;
		} else {
			return false;
		}
	}


	public double valorCalorico() {
		return peso*Valores.consulta(nombre);
	}


	public String getNombre() {
		return nombre;
	}


	public String getEstado() {
		if (estado) {
			return "comestible";
		} else {
			return "inmaduro";
		}
	}


	public double getPeso() {
		return peso;
	}


}
