// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Blanco extends Mistico {
	
	private ArrayList<Clan> acolitos;
	private String nombre;
	
	public Blanco(String n) {
		super();
		nombre = n;
		acolitos = new ArrayList<Clan>();
	}
	
	
	public boolean explora(String c) {
		boolean devuelve = false;
		Producto p = null;
		
		if(c != null  &&  acolitos != null) {
			
			// recorremos los terrenos de sus acolitos buscando un producto tipo 6 igual a c
			for(int i=0; i<acolitos.size(); i++) {
				if(acolitos.get(i) != null)
					for(int j=0; j<acolitos.get(i).getFeudo().size(); j++) {
						Terreno t = acolitos.get(i).getFeudo().get(j);
						
						// cuando lo encuentra
						if(t != null)
							p = t.getIgualA(c);
						
						// no seguimos buscando porque lo metemos en tributos
						if(p != null) {
							if(super.meteAlPrincipio(p)) {	// ERROR CORREGIDO esta metiendo el producto al final no al principio
								devuelve = true;
								j = acolitos.get(i).getFeudo().size();
								i = acolitos.size();
							}
						}
					}
			}

		}
		
		return devuelve;
	}
	
	
	public int exorcismo(int i) {
		int exorcismos = 0;
				
		if(i >= 0  &&  i < acolitos.size()  &&  acolitos.get(i) != null) {
			
			// si el blanco tiene 2 piedras filosofales entonces puede exorcizar
			if(super.cantidadProductos(6) > 2) {
				ArrayList<Plebeyo> normales = acolitos.get(i).getPlebeyosDelClan();
				for(int x=0; x<normales.size(); x++) {
					Plebeyo p = normales.get(x);
					
					// si la bestiola es un demoledor se exorciza pasandosela al plebeyo para reconozca a la bestia de nuevo
					if(p != null  &&  p.getBestiola() != null  &&  p.getBestiola() instanceof Demoledor) {
						Demoledor d = (Demoledor) p.getBestiola();
						Bestia b = d.exorciza();
						p.setBestiola(b);	// ERROR CORREGIDO estaba usando domestica pero es con setters
						b.setAmo(p);
						exorcismos++;
					}
				}
			}
		}
		
		return exorcismos;
	}
	
	
	public double ayuda(Producto p) {
		double peso = 0.0;
		
		if(p != null  &&  p.getTipo() == 6)
			if(super.meteProducto(p))
				peso = p.getPeso();
		
		return peso;
	}
	
	
	public void fervor(Clan c) {	// REVISADO p089
		boolean esta = false;
		
		if(c != null  &&  c.getDeidad() == null) {
			
			for(int i=0; i<acolitos.size(); i++) {	// ERROR CORREGIDO no habia comprobado si estaba en acolitos
				if(acolitos.get(i) == c) {
					esta = true;
					break;
				}
			}
			if(!esta)
				acolitos.add(c);
			
		}
		
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public ArrayList<Clan> getAcolitos() {
		return acolitos;
	}
	
	
	// metodos adicionales practica 3
	
	
	public void quitaClan(Clan c) { // quita el clan c de entre sus acolitos
		
		if(c != null) {
			
			for(int i=0; i<acolitos.size(); i++)
				if(acolitos.get(i) != null  &&  acolitos.get(i) == c) {
					acolitos.remove(i);
					break;
				}
		}
		
	}

}
