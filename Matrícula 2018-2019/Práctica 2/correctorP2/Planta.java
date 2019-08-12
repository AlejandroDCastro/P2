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
				cambio_ = true;
			} else if (estado.equals("germinado")) {
				estado = "brote";
				cambio_ = true;
			} else if(estado.equals("brote")) {
				estado = "adulta";
				cambio_ = true;
			} else {

				for (int i=0; i<frutos.length; i++) {
					if (frutos[i] == null  &&  !sembrada_) {
						frutos[i] = new Fruto(fruto);
						sembrada_ = true;
						cambio_ = true;
					} else if(frutos[i] != null) {
						if (frutos[i].transforma(p)) {
							cambio_ = true;
						}
						if (frutos[i].getPeso() > p) {
							frutos[i] = null;
							cambio_ = true;
						}
					}
				}

			}
		}

		return cambio_;
	}


	public ArrayList<Fruto> recolecta() {
		ArrayList<Fruto> comestibles_ = new ArrayList<Fruto>();

		for (int i=0; i<frutos.length; i++) {
			if (frutos[i].getEstado().equals("comestible")) {
				if (comestibles_.size() == 0) {
					comestibles_.add(frutos[i]);
				} else {
					for (int j=0; j<comestibles_.size(); j++) {
						if (comestibles_.get(j).getPeso() > frutos[i].getPeso()) {
							comestibles_.add(j, frutos[i]);
							break;
						} else if (j == comestibles_.size()-1) {
							comestibles_.add(frutos[i]);
						}
					}
				}
				frutos[i] = null;
			}
		}

		return comestibles_;
	}


	public void arranca() {
		plantada = null;
	}


	public String getNombre() {
		return nombre;
	}


	public String getEstado() {
		return estado;
	}
	
	
	public void setEstado(String s) {
		estado = new String(s);
	}


	public Huerta getPlantada() {
		return plantada;
	}


	public void setPlantada(Huerta p) {
		plantada = p;
	}


	public Fruto[] getFrutos() {
		return frutos;
	}
	
	
	public void setFrutos(Fruto[] f) {
		frutos = f;
	}


	public String getFruto() {
		return fruto;
	}
	
	
	// metodo que devuelve true si el array tiene frutos y false en otro caso
    public boolean tieneFrutos() {
            boolean devuelve_ = false;
            
            for (int i=0; i<frutos.length; i++) {
                    if (frutos[i] != null) {
                            devuelve_ = true;
                            break;
                    }
            }
            
            return devuelve_;
    }
        
        
    // metodo que devuelve la primera posicion libre de el array de frutos
    public int frutoLibre() {
    	int devuelve_ = -1;
    	
    	for (int i=0; i<frutos.length; i++) {
    		if (frutos[i] == null) {
    			devuelve_ = i;
    			break;
    		}
    	}
    	
    	return devuelve_;
    }



}
