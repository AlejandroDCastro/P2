// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Vehiculo {
	
	
	private Persona ocupante;
	private boolean[] combustible;
	
	
	public Vehiculo(int p) {
		ocupante = null;
		if (p > 2) {
			combustible = new boolean[p];
		} else {
			combustible = new boolean[2];
		}
	}
	
	
	public boolean traslada(Coordenada c) {
		boolean devuelve_ = false, encontrada_ = false;
		Coordenada c_actual_ = null;
		Huerta huerta_ = null, destino_ = null;
		double d_ = 0;
		int distancia_ = 0;
		
		if (ocupante != null  &&  ocupante.getHuerta() != null  &&  c != null) {
			huerta_ = ocupante.getHuerta();
			c_actual_ = huerta_.getLocalizacion();
			if (c_actual_ != null) {
				
				// el vehiculo traslada al ocupante a la huerta de destino si es posible
				for (int i=0; i<Huerta.getLocalizadas().size(); i++) {
					destino_ = Huerta.getLocalizadas().get(i);
					if (destino_.getLocalizacion().iguales(c)) {
						encontrada_ = true;
						if (destino_ != huerta_) {
							ocupante.setHuerta(destino_);
							devuelve_ = true;
							break;
						}
					}
				}
				
				// si la coordenada no se corresponde con ninguna huerta se traslada la propia
				if (!encontrada_) {
					huerta_.localiza(c.getLatitud(), c.getLongitud());
					devuelve_ = true;
				}
				
				// averiguamos la distancia entre coordenadas y consumimos combustible
				if (devuelve_) {
					d_ = c_actual_.distancia(c);
					distancia_ = (int) d_;
					if (distancia_ > 0  &&  this.combustibleDisponible() >= distancia_) {
						for (int i=combustible.length-1; i>=0 && distancia_>0; i--) {
							if (combustible[i]) {
								combustible[i] = false;
								distancia_--;
							}
						}
					}
				}
				
			}
		}
		
		return devuelve_;
	}
	
	
	public int repostaje(ArrayList<Fruto> r) {
		int barras_ = 0, aux_ = 0;
		double valor_ = 0;
		
		if (r != null  &&  r.size() > 0) {
			
			// eleboramos combustible con los frutos
			for (int i=0; i<r.size(); i++) {
				valor_ += r.get(i).valorCalorico();
			}
			aux_ = (int) valor_;
			barras_ = (int) valor_;
			
			// reponemos el deposito con la parte entera
			for (int i=0; i<combustible.length && aux_>0; i++) {
				if (!combustible[i]) {
					combustible[i] = true;
					aux_--;
				}
			}
			
		}
		
		return barras_;
	}
	
	
	public boolean sube(Persona p) {
		if (ocupante == null  &&  p != null  &&  p instanceof Inmune  &&  ((Inmune) p).getVehiculo() == null) {
			ocupante = p;
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean baja() {
		if (ocupante != null) {
			ocupante = null;
			return true;
		} else {
			return false;
		}
	}
	
	
	public Persona getOcupante() {
		return ocupante;
	}
	
	
	public boolean[] getCombustible() {
		return combustible;
	}
	
	
	// metodo que devuelve las barras de combustible que le quedan al vehiculo
	public int combustibleDisponible() {
		int devuelve_ = 0;
		
		for (int i=0; i<combustible.length; i++) {
			if (combustible[i]) {
				devuelve_++;
			}
		}
		
		return devuelve_;
	}

}
