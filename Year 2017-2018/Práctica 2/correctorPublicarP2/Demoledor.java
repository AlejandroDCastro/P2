// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Demoledor extends Bestia {
	
	public Demoledor(double p) {
		super(p);
	}
	
	
	public boolean domestica(Habitante h) {	// REVISADO PruebaDemoledor
		int robados = 0;
		
		// si tiene amo le quitamos la cesta
		if(super.getAmo() != null) {
			robados = this.getAmo().quitaCesta();
			
			// despues abandonamos al amo
			super.getAmo().setBestiola(null);	// ERROR CORREGIDO no habia setteado la bestiola del amo
			this.setAmo(null);
		}
		
		// si no tiene amo violamos al que nos pasan vaciando su cesta
		else {
			if(h != null  &&  h instanceof Plebeyo)
				robados = h.quitaCesta();
		}
		
		// con los productos robados incrementamos fuerza
		this.sumaFuerza(robados);
		
		if(robados > 0)
			return true;
		else
			return false;
	}
	
	
	public boolean alimenta(Producto p) {	// REVISADO PruebaDemoledor
		
		// si p es de tipo animal o sea 2 incremento su fuerza asi
		if(p != null  &&  p.getTipo() == 2) {
			super.sumaFuerza(p.getPeso() * 0.15);
			return true;
		}
		else
			return false;
		
	}
	
	
	public ArrayList<Producto> pasturea(Terreno r,int k) {	// REVISADO PruebaDemoledor
		ArrayList<Producto> bag = new ArrayList<Producto>();
		ArrayList<Producto> devuelve = new ArrayList<Producto>();
		
		if(r != null  &&  k >= 0  &&  k < r.getFilas()) {
		
			// primero recogemos productos
			for(int j=0; j<r.getColumnas(); j++) {
				if(r.consultaTipo(k, j) != 5  &&  r.consultaTipo(k, j) > 0)
					bag.add(r.recoge(k, j));
				
				// destruyendo los edificados
				else if(r.consultaTipo(k, j) == 5)
					r.destruye(k, j);
			}
			
			// si tiene amuleto los convertimos a tipo animal
			if(this.getAmuleto() != null)
				for(int i=0; i<bag.size(); i++)
					this.getAmuleto().transforma(bag.get(i), 2);
			
			// nos alimentamos de los productos de tipo animal
			for(int i=0; i<bag.size(); i++)
				if(!this.alimenta(bag.get(i)))		// ERROR CORREGIDO modificaba el tamanio del array mientras lo recorria
					devuelve.add(bag.get(i));
			
		}
		
		return devuelve;
	}
	
	
	public double ayuda() {
		return (- super.getFuerza() * 0.5);
	}
	
	
	public double arrasa(Terreno r) { // REVISADO PruebaOscuro
		double peso = 0.0;
		
		if(r != null) {
			
			// primero buscamos por el terreno r
			for(int i=0; i<r.getFilas(); i++)
				for(int j=0; j<r.getColumnas(); j++) {
					
					// si encontramos una parcela de tipo edificado teniendo fuerza positiva
					if(r.consultaTipo(i, j) == 5  &&  super.getFuerza() > 0)
						
						// destruimos recogiendo peso de edificados
						peso += r.destruye(i, j);
				}
			
			// decrementamos un cuarto de la fuerza por la busqueda
			super.sumaFuerza(- super.getFuerza() * 0.25);
			
		}
		
		return peso;		
	}
	
	
	public Bestia exorciza() {	// REVISADO p02
		Bestia bestiaja = new Bestia(this.getFuerza()); 
		bestiaja.setNombre(this.getNombre());
		bestiaja.setAmo(this.getAmo());
		bestiaja.setAmuleto(this.getAmuleto());
		
		return bestiaja;
	}

}
