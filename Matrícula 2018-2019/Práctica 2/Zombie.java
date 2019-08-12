import java.util.ArrayList;

public class Zombie extends Persona {
	
	
	public Zombie(Persona p) {
		super(p.getNombre());
		super.setHuerta(p.getHuerta());
	}
	
	
	public boolean planta(Planta p, Huerta h) {
		boolean devuelve_ = false;
		
		if (p != null  &&  h != null) {
			
			if (h != super.getHuerta()) {
				if (p instanceof Trifido) {
					devuelve_ = h.planta(p);
				} else {
					devuelve_ = h.planta((Trifido) p);
				}
			}
			
		}
		
		return devuelve_;
	}
	
	
	public Coordenada paseo() {
		if (super.getHuerta() != null) {
			return super.getHuerta().getLocalizacion();
		} else {
			return null;
		}
	}
	
	
	public ArrayList<Planta> malasHierbas() {
		ArrayList<Planta> devuelve_ = new ArrayList<Planta>();
		Huerta huerta_ = super.getHuerta();
		Planta[][] huerto_ = null;
		
		if (huerta_ != null) {
			huerto_ = huerta_.getHuerto();
			for (int i=0; i<huerto_.length; i++) {
				for (int j=0; j<huerto_[i].length; j++) {
					if (huerto_[i][j] != null  &&  huerto_[i][j].getEstado().equals("adulta")  &&  !(huerto_[i][j] instanceof Trifido)) {
						devuelve_.add(huerta_.arranca(huerto_[i][j].getNombre(), i, j));
					}
				}
			}
		}
		
		return devuelve_;
	}
	
	
	public int abona(int p, String n) {
		int plantas_abonadas_ = 0;
		Huerta huerta_ = super.getHuerta();
		
		if (huerta_ != null) {
			plantas_abonadas_ = huerta_.abonaTrifidos(p);
		}
		
		return plantas_abonadas_;
	}
	
	
	public int abona() {
		int plantas_devoradas_ = 0;
		Planta[][] huerto_ = null;
		
		if (super.getHuerta() != null) {
			huerto_ = super.getHuerta().getHuerto();
			for (int i=0; i<huerto_.length; i++) {
				for (int j=0; j<huerto_[i].length; j++) {
					if (huerto_[i][j] != null  &&  huerto_[i][j] instanceof Trifido) {
						plantas_devoradas_ += ((Trifido) huerto_[i][j]).abona();
					}
				}
			}
		}
		
		return plantas_devoradas_;
	}
	

}
