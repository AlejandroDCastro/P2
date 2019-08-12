// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Inmune extends Persona {
	
	
	private Vehiculo vehiculo;
	
	
	public Inmune(String n) {
		super(n);
		vehiculo = null;
	}
	
	
	public boolean planta(Planta p, Huerta h) {
		boolean devuelve_ = false;
		Planta normal_ = null;
		
		if (p != null  &&  h != null) {
			if (p instanceof Trifido) {
				normal_ = new Planta(p.getNombre(), p.getFruto(), 1);
				normal_.setEstado(p.getEstado());
				normal_.setPlantada(p.getPlantada());
				normal_.setFrutos(p.getFrutos());
				devuelve_ = super.planta(normal_, h);
			} else {
				devuelve_ = super.planta(p, h);
			}
		}
		
		return devuelve_;
	}
	
	
	public ArrayList<Planta> malasHierbas() {
		ArrayList<Planta> trifidos_ = new ArrayList<Planta>();
		Huerta huerta_ = null;
		Planta[][] huerto_ = null;
		
		huerta_ = super.getHuerta();
		if (huerta_ != null) {
			
			huerto_ = huerta_.getHuerto();
			for (int i=0; i<huerto_.length; i++) {
				for (int j=0; j<huerto_[i].length; j++) {
					if (huerto_[i][j] instanceof Trifido) {
						trifidos_.add(huerta_.arranca(huerto_[i][j].getNombre(), i, j));
					}
				}
			}
			
		}
		
		return trifidos_;
	}
	
	
	public int abona(int p, String n) {
		int plantas_cambiadas_ = 0;
		Huerta huerta_ = super.getHuerta();
		
		if (huerta_ != null  &&  p > 0) {
			plantas_cambiadas_ = huerta_.abonaFrutos(p, n);
		}
		
		return plantas_cambiadas_;
	}
	
	
	public boolean apropia(Vehiculo v) {
		if (vehiculo == null  &&  v != null  &&  v.getOcupante() == null) {
			v.sube(this);
			vehiculo = v;
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean abandona() {
		if (vehiculo != null) {
			vehiculo.baja();
			return true;
		} else {
			return false;
		}
	}
	
	
	public ArrayList<String> repostaje() {
		ArrayList<String> devuelve_ = new ArrayList<String>();
		ArrayList<Fruto> comestibles_ = new ArrayList<Fruto>();
		Huerta huerta_ = super.getHuerta();
		Planta[][] huerto_ = null;
		
		// recogemos los frutos de toda la huerta que cuida y repostamos su vehiculo
		if (huerta_ != null) {
			huerto_ = huerta_.getHuerto();
			for (int i=0; i<huerto_.length; i++) {
				for (int j=0; j<huerto_[i].length; j++) {
					if (huerto_[i][j] != null) {
						comestibles_.addAll(huerto_[i][j].recolecta());
					}
				}
			}
		}
		vehiculo.repostaje(comestibles_);
		
		// devolvemos sus nombres ordenados lexocograficamente
		for (int i=0; i<comestibles_.size(); i++) {
			if (devuelve_.size() == 0) {
				devuelve_.add(comestibles_.get(i).getNombre());
			} else {
				for (int j=0; j<devuelve_.size(); j++) {
					if (devuelve_.get(j).compareTo(comestibles_.get(i).getNombre()) > 0) {
						devuelve_.add(j, comestibles_.get(i).getNombre());
					} else if (j == devuelve_.size()-1) {
						devuelve_.add(comestibles_.get(i).getNombre());
					}
				}
			}
		}
		
		return devuelve_;
	}
	
	
	public int paseo(Coordenada c) {
		int trifidos_erradicados_ = 0;
		Huerta huerta_ = super.getHuerta(), primera_ = null, nueva_ = null;
		Persona cuidador_ = null;
		
		if (c != null) {
			
			if (huerta_ != null  &&  vehiculo != null) {
				
				// si es posible el traslado erradicamos trifidos de la primera huerta
				if (vehiculo.traslada(c)) {
					primera_ = super.getHuerta();
					trifidos_erradicados_ += (huerta_ != primera_) ? this.malasHierbas().size() : 0;
					cuidador_ = primera_.getCuidador();
					cuidador_ = (cuidador_ instanceof Zombie) ? null : cuidador_;
					
					// despues erradicamos las de las demas huertas para la misma coordenada si las hay
					for (int i=0; i<Huerta.getLocalizadas().size(); i++) {
						nueva_ = Huerta.getLocalizadas().get(i);
						if (nueva_.getLocalizacion().iguales(c)  &&  nueva_ != primera_) {
							super.setHuerta(nueva_);
							trifidos_erradicados_ += this.malasHierbas().size();
							cuidador_ = nueva_.getCuidador();
							cuidador_ = (cuidador_ instanceof Zombie) ? null : cuidador_;
						} 
					}
					
					// el inmune vuelve a su huerta
					super.setHuerta(huerta_);
				}
			}
			
		}
		
		return trifidos_erradicados_;
	}
	
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
	
}
