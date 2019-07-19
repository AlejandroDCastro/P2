// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Planta {

	private String estado;
	private String nombre;
	private String fruto;
	private Huerta plantada;
	private Fruto[] frutos;


	public Planta(String n1, String n2, int c) {
		nombre = (n1!=null && !n1.equals("")) ? n1 : "vegetal";
		fruto = (n2!=null && !n2.equals("")) ? n2 : "pitaya";
		if (c < 1) {
			frutos = new Fruto[1];
		} else {
			frutos = new Fruto[c];
		}
		estado = new String("semilla");
		plantada = null;
	}


	public boolean abona(int p) {
		boolean sembrada_ = false, cambio_ = false;

		if (plantada != null) {
			if (estado.equals("semilla")) {
				estado = "germinado";
			} else if (estado.equals("germinado")) {
				estado = "brote";
			} else if(estado.equals("brote")) {
				estado = "adulta";
			} else {

				for (int i=0; i<frutos.length; i++) {
					if (frutos[i] == null  &&  !sembrada_) {
						frutos.add(new Fruto(fruto));
						sembrada_ = true;
					} else if(frutos[i] != null) {
						frutos[i].transforma(p);
						if (frutos[i].getPeso() > p) {
							frutos[i] = null;
						}
					}
				}

			}
		}
	}

/*
	public ArrayList<Fruto> recolecta() {

	}


	public String getNombre() {

	}


	public void arranca() {

	}


	public String getEstado() {

	}


	public Huerta getPlantada() {

	}


	public void setPlantada(Huerta) {

	}


	public Fruto[] getFrutos() {

	}


	public String getFruto() {

	}

*/
}