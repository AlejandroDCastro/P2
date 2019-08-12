// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Trifido extends Planta {
	
	
	private int[] posicion;
	
	
	public Trifido(String n1, String n2, int c) {
		super(n1, n2, c);
		posicion = new int[2];
	}
	
	
	public boolean abona(int p) {
		boolean devuelve_ = false;
		Huerta huerta_ = null, nueva_ = null;
		Fruto[] frutos_ = null;
		Persona cuidador_ = null;
		double distancia_ = -2, aux_ = 0;
		Coordenada loc_ = null, loc_aux_ = null;
		Planta[][] huerto_ = null;
		
		huerta_ = super.getPlantada();
		if (huerta_ != null) {
			
			cuidador_ = huerta_.getCuidador();
			frutos_ = super.getFrutos();
			if (cuidador_ != null  &&  !(cuidador_ instanceof Inmune)) {
				
				
				if (super.getEstado().equals("adulta")) {
					for (int i=0; i<frutos_.length; i++) {
						if (frutos_[i] != null) {
							frutos_[i].transforma(p);
						}
					}
				} else {
					super.setEstado("adulta");
				}
				
				// creamos los nuevos frutos
				for (int i=0; i<2; i++) {
					int pos_ = super.frutoLibre();
					if (pos_ > -1) {
						frutos_[pos_] = new Fruto(super.getFruto());
					}
				}
				
				// atacamos al cuidador y lo convertimos
				cuidador_ = (Zombie) cuidador_;
				devuelve_ = true;
				
				
			} else if (cuidador_ == null  ||  (cuidador_ != null && cuidador_ instanceof Inmune)) {
				if (super.tieneFrutos()) {
					
					
					// si esta localizada se desplaza a la huerta mas cercana
					loc_ = huerta_.getLocalizacion();
					if (loc_ != null) {
						
						for (int i=0; i<Huerta.getLocalizadas().size(); i++) {
							loc_aux_ = Huerta.getLocalizadas().get(i).getLocalizacion();
							cuidador_ = Huerta.getLocalizadas().get(i).getCuidador();
							aux_ = loc_.distancia(loc_aux_);
							if (distancia_ == -2  ||  (distancia_ > -1  &&  aux_ < distancia_)) {
								if (cuidador_ != null) {
									distancia_ = aux_;
									nueva_ = Huerta.getLocalizadas().get(i);
								}
							}
						}
						
					} else {	// si no esta localizada se desplaza a la primera huerta encontrada
						
						for (int i=0; i<Huerta.getLocalizadas().size(); i++) {
							cuidador_ = Huerta.getLocalizadas().get(i).getCuidador();
							if (cuidador_ != null) {
								nueva_ = Huerta.getLocalizadas().get(i);
								break;
							}
						}
						
					}
					
					// se desplaza a la nueva huerta
					if (nueva_ != null) {
						huerto_ = nueva_.getHuerto();
						for (int i=0; i<huerto_.length; i++) {
							for (int j=0; j<huerto_[i].length; j++) {
								if (huerto_[i][j] == null) {
									huerto_[i][j] = this;
									this.setPlantada(nueva_);
									posicion[0] = i;  posicion[1] = j;  // no es necesario porque no se especifica en el enunciado, pero es logico tenerlo en cuenta
									devuelve_ = true;
									break;
								}
							}
						}
					}
					
					// eliminamos sus frutos
					for (int i=0; i<frutos_.length; i++) {
						frutos_[i] = null;
					}
					
					
				}
			}
		}
		
		return devuelve_;
	}
	
	
	public ArrayList<Fruto> recolecta() {
		ArrayList<Fruto> devuelve_ = new ArrayList<Fruto>();
		Huerta local_ = null, nueva_ = null;
		double distancia_ = -1, dis_aux_ = 0;
		Coordenada localizacion_ = null, loc_aux_ = null;
		Planta ocupa_ = null;
		Planta[][] huerto_ = null;
		Fruto[] frutos_ = null;
		
		if (super.tieneFrutos()) {
			
			// el trifido intenta huir a la primera huerta mas cercana
			local_ = super.getPlantada();
			if (local_ != null  &&  local_.getLocalizacion() != null) {
				localizacion_ = local_.getLocalizacion();
					
				// averiguamos dicha huerta
				for (int i=0; i<Huerta.getLocalizadas().size(); i++) {
					loc_aux_ = Huerta.getLocalizadas().get(i).getLocalizacion();
					dis_aux_ = localizacion_.distancia(loc_aux_);
					if (distancia_ == -1  ||  (distancia_ > 0  &&  dis_aux_ < distancia_)) {
						distancia_ = dis_aux_;
						nueva_ = Huerta.getLocalizadas().get(i);
					}
				}
				
				// el trifido devuelve los frutos de la planta ordenados descendentemente por peso
				if (nueva_ != null) {
					huerto_ = nueva_.getHuerto();
					ocupa_ = huerto_[0][0];
					
					// el trifido ocupa la primera posicion del nuevo huerto
					huerto_[0][0] = this;
					super.setPlantada(nueva_);
					posicion[0] = 0;  posicion[1] = 0;
					if (ocupa_ != null  &&  ocupa_.tieneFrutos()) {
						frutos_ = ocupa_.getFrutos();
						for (int i=0; i<frutos_.length; i++) {
							if (frutos_[i] != null) {
								if (devuelve_.size() == 0) {
									devuelve_.add(frutos_[i]);
								} else {
									for (int j=0; j<devuelve_.size(); j++) {
										if (devuelve_.get(j).getPeso() < frutos_[i].getPeso()) {
											devuelve_.add(j, frutos_[i]);
											j = devuelve_.size();
										} else if (j == devuelve_.size()-1) {
											devuelve_.add(frutos_[i]);
										}
									}
								}
							}
						}
					}
				}
			} else {
				
				// se recolecta como una planta normal si no tiene huerta o no esta localizada
				devuelve_ = super.recolecta();
			}
			
			
		}
		
		return devuelve_;
	}
	
	
	public void arranca() {
		Huerta huerta_ = super.getPlantada();
		Persona cuidador_ = null;
		
		if (huerta_ != null) {
			cuidador_ = huerta_.getCuidador();
			if (cuidador_ != null  &&  cuidador_ instanceof Inmune) {
				huerta_.arranca(getNombre(), posicion[0], posicion[1]);
				posicion = new int[2];
				super.arranca();
			} else if (cuidador_ != null  &&  !(cuidador_ instanceof Zombie)) {
				cuidador_ = (Zombie) cuidador_;
			}
		}
	}
	
	
	public void setPlantada(Huerta h) {
		Planta[][] huerto_ = null;
		
		if (h != null) {
			
			huerto_ = h.getHuerto();
			for (int i=0; i<huerto_.length; i++) {
				for (int j=0; j<huerto_[i].length; j++) {
					if (huerto_[i][j] == this) {
						posicion[0] = i;
						posicion[1] = j;
						break;
					}
				}
			}

			
		}
	}
	
	
	public boolean otea() {
		boolean devuelve_ = false;
		Huerta huerta_ = null;
		Planta[][] huerto_ = null;
		
		if (super.getPlantada() == null) {
			
			// encontramos una huerta sin cuidador
			for (int i=0; i<Huerta.getLocalizadas().size(); i++) {
				if (Huerta.getLocalizadas().get(i).getCuidador() == null) {
					huerta_ = Huerta.getLocalizadas().get(i);
					break;
				}
			}
			
			// plantamos el trifido
			if (huerta_ != null) {
				huerto_ = huerta_.getHuerto();
				for (int i=0; i<huerto_.length; i++) {
					for (int j=0; j<huerto_[i].length; j++) {
						if (huerto_[i][j] == null) {
							huerto_[i][j] = this;
							super.setPlantada(huerta_);
							posicion[0] = i;  posicion[1] = j;
							super.setEstado("adulta");
							devuelve_ = true;
							break;
						}
					}
				}
			}
			
		}
		
		return devuelve_;
	}
	
	
	public int abona() {
		int devoradas_ = 0, l_ = -1;
		Planta[][] huerto_ = null;
		Fruto[] frutos_ = null;
		int f_ = -1, c_ = -1;
		
		if (super.getPlantada() != null) {
			huerto_ = super.getPlantada().getHuerto();
			frutos_ = super.getFrutos();
			for (int i=0; i<8; i++) {
				switch (i) {
					case 0:
						f_ = posicion[0] - 1;  c_ = posicion[i] - 1;
						break;
					case 1:
						f_ = posicion[0] - 1;  c_ = posicion[i];
						break;
					case 2:
						f_ = posicion[0] - 1;  c_ = posicion[i] + 1;
						break;
					case 3:
						f_ = posicion[0];  c_ = posicion[i] - 1;
						break;
					case 4:
						f_ = posicion[0];  c_ = posicion[i] + 1;
						break;
					case 5:
						f_ = posicion[0] + 1;  c_ = posicion[i] - 1;
						break;
					case 6:
						f_ = posicion[0] + 1;  c_ = posicion[i];
						break;
					case 7:
						f_ = posicion[0] + 1;  c_ = posicion[i] + 1;
						break;
				}
				if (f_ >= 0  &&  c_ >= 0  &&  f_ < huerto_.length  &&  c_ < huerto_[0].length) {
					if (huerto_[f_][c_] != null  &&  !(huerto_[f_][c_] instanceof Trifido)) {
						huerto_[f_][c_].arranca();
						huerto_[f_][c_] = null;
						if (devoradas_ == 0  &&  !super.getEstado().equals("adulta")) {
							super.setEstado("adulta");
						} else {
							if (l_ > -1) {
								frutos_[l_] = new Fruto(super.getFruto());
							}
						}
						devoradas_++;
					}
				}
			}
		}
		
		return devoradas_;
	}
	
	
	public int[] getPosicion() {
		return posicion;
	}
	
}
