// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Bestia {
	
	private double fuerza;
	private Plebeyo amo;
	private String nombre;
	private Producto amuleto;
	
	public Bestia(double p) {
		if(p > 0) fuerza = p;
		else fuerza = 10.5;
		amo = null;
		amuleto = null;
		nombre = null;
	}
	
	
	public String busca(Terreno r) { // REVISADO p0129 PruebaDemoledor PruebaFinal
		String amulet = null;
		
		if(r != null  &&  amuleto == null) { // ERROR CORREGIDO habia igualado amuleto a distinto de nulo
			
			// busca primero una piedra filosofal
			amuleto = r.getElPrimeroDe(6);
			
			// si la encuentra entonces devolvemos nombre
			if(amuleto != null)
				amulet = amuleto.getNombre();
			
		}
		
		return amulet;
	}
	
	
	public boolean domestica(Habitante h) { // REVISADO p012789
		boolean devuelve = false;
		
		if(h != null) {
			
			if(amo == null  &&  h instanceof Plebeyo  &&  ((Plebeyo) h).getBestiola() == null) { // CORREGIDO habias puesto que los dos tenian bestiola
				amo = (Plebeyo) h;
				devuelve = true;
			}
			
		}
		
		return devuelve;		
	}
	
	
	public boolean alimenta(Producto p) { // REVISADO p017
		
		if(p != null  &&  p.getTipo() == 1) {
			fuerza += p.getPeso() * 0.1;
			return true;
		}
		else
			return false;
		
	}
	
	
	public ArrayList<Producto> pasturea(Terreno r,int k) { // REVISADO p017
		ArrayList<Producto> bag = new ArrayList<Producto>();
		
		if(r != null  &&  k >= 0  &&  k < r.getFilas()) {
			
			// primero recogemos todos los productos de las filas no edificados
			for(int j=0; j<r.getColumnas(); j++)
				if(r.consultaTipo(k, j) != 5  &&  r.consultaTipo(k, j) > 0)
					bag.add(r.recoge(k, j));
			
			// si tiene amuleto se transforman los productos a vegetal
			if(amuleto != null)
				for(int i=0; i<bag.size(); i++)
					amuleto.transforma(bag.get(i), 1);
			
			// si tiene amo nos alimentamos con el primer vegetal
			if(amo != null) {
				for(int i=0; i<bag.size(); i++)
					if(this.alimenta(bag.get(i))) {
						bag.remove(i);
						break;
					}
			}
			
			// en otro caso nos alimentamos con todos y devolvemos bag vacia
			else {
				for(int i=0; i<bag.size(); i++)
					this.alimenta(bag.get(i));
				bag = new ArrayList<Producto>();	// ERROR CORREGIDO modificaba el tamanio del array mientras lo recorria
			}
			
		}
		
		return bag;
	}
	
	
	public double ayuda() {
		
		// devuelve la mitad de la fuerza decrementando
		fuerza -= fuerza * 0.5;
		return fuerza;
	}
	
	
	public Demoledor hechiza() { // REVISADO p02
		Demoledor demon = new Demoledor(fuerza);
		demon.setAmo(amo);
		demon.setAmuleto(amuleto);
		demon.setNombre(nombre);
		
		return demon;
	}
	
	
	public void setNombre(String s) {
		nombre = s;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public double getFuerza() {
		return fuerza;
	}
	
	
	public Producto getAmuleto() {
		return amuleto;
	}
	
	
	public Plebeyo getAmo() {
		return amo;
	}
	
	
	// metodos adicionales de la practica 3
	
	
	// se suma a la fuerza la cantidad f
	public void sumaFuerza(double f) {
		fuerza += f;
	}
	
	
	public void quitaAmuleto() { // ponemos a nulo el amuleto de la bestia
		amuleto = null;
	}

	
	public double chupaFuerza(double p) { // roba la fuerza de la bestia y la devuelve
		double devu = 0.0;
		
		if(p > 0) {		// ERROR CORREGIDO habia puesto que p era menor que cero
			devu = p;
			fuerza -= p;
		}
		
		return devu;
	}
	
	
	public void setAmo(Plebeyo p) { // setea al amo
		amo = p;
	}
	
	
	public void setAmuleto(Producto p) { // setea el amuleto
		amuleto = p;
	}
}
