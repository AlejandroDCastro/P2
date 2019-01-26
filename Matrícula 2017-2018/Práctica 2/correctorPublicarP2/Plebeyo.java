// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Plebeyo extends Habitante {
	
	private Bestia bestiola;
	private Guerrero patrono;
	private Clan tribu;
	
	public Plebeyo(String n,char c) {
		super(n,c);
		bestiola = null;
		patrono = null;
		tribu = null;
	}
	
	
	public ArrayList<String> trueque(Habitante h) { // REVISADO p05
		ArrayList<String> intercambiados = null;

		if(h != null) {
			
			// 1 si ni el habitante h ni el plebeyo tienen clan se invoca trueque de habitante
            if(tribu == null  &&  h.getTribu() == null) {
				intercambiados = super.trueque(h);
            }
			
			// 2 si uno pertenece a un clan pero otro no entonces se hace trueque con plebeyo
            else if((tribu == null  &&  h.getTribu() != null)  ||  (tribu != null  &&  h.getTribu() == null)) {
				
				// si tiene distintos tipos de productos en su cesta hace truequePlebeyo con el primer elemento
				if(super.productosDelTipoMayor() < super.getCesta().size())
                    intercambiados = this.truequePlebeyo(h,0);
            }
                
            // 3 si ambos tienen un clan se realizan una de las siguientes dos acciones
            else if(tribu != null  &&  h.getTribu() != null) {
                
                // si estan en el mismo clan y ambos son plebeyos se intercambian bestiolas
                if(tribu.getNombre().equalsIgnoreCase(h.getTribu().getNombre())  &&  h instanceof Plebeyo) {
                    intercambiados = new ArrayList<String>();
                    if(bestiola != null  &&  bestiola.getNombre() != null)
                        intercambiados.add(bestiola.getNombre());
                    else
                        intercambiados.add("Ignoto");
                    bestiola = ((Plebeyo) h).cambioBestiolas(bestiola);
                    if(bestiola != null)
                    	bestiola.setAmo(this);
                    if(bestiola != null  &&  bestiola.getNombre() != null)
                        intercambiados.add(bestiola.getNombre());
                    else
                        intercambiados.add("Ignoto");
                }
                
                // si no estan en el mismo clan o h es un guerrero se llama a truequePlebeyo con el ultimo producto de su cesta
                else if(tribu != h.getTribu()  ||  h instanceof Guerrero) {
                	
                	// si tiene distintos tipos de productos en su cesta hace truequePlebeyo con el ultimo producto
    				if(super.productosDelTipoMayor() < super.getCesta().size())
    					intercambiados = this.truequePlebeyo(h,super.getCesta().size()-1);
                }
            }
            
		}
		
		return intercambiados;
	}
	
	
	public double tributa(Mistico m) { // REVISADO p038
		double vigor = 0.0, incremento = 0.0;
		ArrayList<Producto> bag = new ArrayList<Producto>();
		Producto p = null;
		
		// si la tribu no dispone de terrenos el plebeyo no podra tributar
		if(m != null) {
			
			// si m es el de su tribu pues recoge el primer producto no 5 del feudo e invoca a culto
			if(tribu != null  &&  m == tribu.getDeidad()  &&  tribu.getFeudo() != null  &&  tribu.getFeudo().size() > 0) {
				p = tribu.deFeudoUnSoloProducto();
				if(p != null) {
					bag.add(p);
					vigor = m.culto(bag, super.getNombre());
				}
			}
			else if(tribu == null  ||  (tribu != null  &&  m != tribu.getDeidad())) {	// ERROR CORREGIDO si no tiene tribu tambien entra
				
				// si es blanco hago culto con el primer producto de mi cesta
				if(m instanceof Blanco  &&  super.getCesta().size() > 0) {	// ERROR CORREGIDO no comprobe si habia algo en la cesta
					bag.add(super.getPrimeroCesta());
					vigor = ((Blanco) m).culto(bag, super.getNombre());
				}
				
				// si es negro pues hasta luego mascota
				else if(m instanceof Oscuro) {
					Bestia sacrificio = bestiola;
					vigor = ((Oscuro) m).culto(sacrificio);
					bestiola = null;
				}
			}
			
			// por ultimo incrementamos el vigor y devolvemos la cantidad incrementada
			incremento = super.incrementaVigor(vigor);
			
		}
		
		return incremento;
	}
	
	
	public boolean plegaria() { // REVISADO p039
		boolean devuelve = false;
		double incrementado = 0.0, vigor = 0.0;
		Producto amuleto = null;
		
		if(tribu != null  &&  tribu.getDeidad() != null) {
			
			// ofrecemos para la ayuda del mistico el amuleto de la bestiola
			amuleto = bestiola.getAmuleto();
			bestiola.quitaAmuleto();
			
			Mistico m = tribu.getDeidad();
			
			// depende de si el mistico de la tribu es blanco o negro la ayuda es diferente
			if(m instanceof Blanco)
				vigor = ((Blanco) m).ayuda(amuleto);
			else if(m instanceof Oscuro)
				vigor = ((Oscuro) m).ayuda(amuleto);
			
			// si incrementa el vigor devuelve cierto
			incrementado = super.incrementaVigor(vigor);
			if(incrementado > 0) devuelve = true;
			
		}
		
		return devuelve;
	}
	
	
	public boolean domestica(Bestia b,String s) { // REVISADO p0125789
		boolean devuelve = false;
		
		if(b != null  &&  s != null) {
			
			if(b.domestica(this)) {
				bestiola = b;
				bestiola.setNombre(s);
				devuelve = true;
			}
			
		}
		
		return devuelve;
	}
	

	
	public int recolecta(int i,int j) { // REVISADO p0345678910
		int recolectados = -1, suma = 0;
		
		// primero vemos si tiene tribu y si tiene feudo y parametros son correctos
		if(tribu != null  &&  tribu.getFeudo() != null  &&  i >= 0  &&  i < tribu.getFeudo().size()  &&  tribu.getFeudo().get(i) != null  &&  j > 0) {
			
			// este metodo se hace solo si es mayor que cero
			if(super.getVigor() > 0) {
				
				Terreno r = tribu.getFeudo().get(i);
				for(int x=0; x<r.getFilas(); x++)
					for(int y=0; y<r.getColumnas(); y++)
						
						// si el producto no es nulo o edificado se recogen productos de r y se almacenan en cesta
						if(r.consultaTipo(x, y) > 0  &&  r.consultaTipo(x, y) != 5) {
							super.almacena(r.recoge(x, y));
							suma++;
							if(suma == j) {
								y = r.getColumnas();
								x = r.getFilas();
							}
						}
				
				// si se han recogido productos se decrementa el vigor un cinco por ciento
				if(suma > 0) {
					super.incrementaVigor(- super.getVigor() * 0.05);
					recolectados = suma;
				}
				
				// si no se recolectan productos se devuelve menos uno
				else
					recolectados = -1;
			}
			
		}
		
		return recolectados;		
	}
	
	
	public ArrayList<Producto> vasallaje(Terreno t) {	// REVISADO PruebaFinal
		ArrayList<Producto> bag = new ArrayList<Producto>();
		
		if(t != null  &&  super.getVigor() > 0) {
			
			// recogemos todos los productos del terreno que no sean de tipo edificado
			for(int i=0; i<t.getFilas(); i++)
				for(int j=0; j<t.getColumnas(); j++)
					if(t.consultaTipo(i, j) > 0  &&  t.consultaTipo(i, j) != 5) {
						bag.add(t.recoge(i, j));
						bag.get(bag.size()-1).setColocado(false);
					}
			
			// y decrementamos vigor en un diez por ciento
			super.incrementaVigor(- super.getVigor() * 0.1);
			
		}
		
		return bag;
	}
	
	
	public int alimenta(int i) { // REVISADO p07 PruebaFinal
		int devuelve = 0;
		ArrayList<Producto> bag = new ArrayList<Producto>();
		
		// si tiene tribu y las coordenadas son correctas
		if(tribu != null  &&  tribu.getFeudo() != null  &&  i >= 0  &&  i < tribu.getFeudo().size()) {
			Terreno t = tribu.getFeudo().get(i);
			
			// pasturea el terreno al completo alimentando la mascota y llenando su cesta
			for(int x=0; x<t.getFilas(); x++) {
				bag = bestiola.pasturea(t, x);
				
				for(int y=0; y<bag.size(); y++)
					super.almacena(bag.get(y));
			}
			devuelve = 1;
		}
		
		// si no pertenece a una tribu y las coordenadas son correctas alimenta bestia con producto de su cesta
		else if(tribu == null  &&  i >= 0  &&  i < super.getCesta().size()) {
			Producto p = super.sacaProductoDeCesta(i);
			bestiola.alimenta(p);	// ERROR CORREGIDO habia puesto un if a esta sentencia
			devuelve = 2;
		}
		
		return devuelve;
	}
	
	
	public boolean tutela(Guerrero g) { // REVISADO p06810 PruebaFinal
		boolean devuelve = false;
		
		if(g != null) {
			
			if(patrono == null  &&  g.getSirviente() == null) {
				patrono = g;
				devuelve = true;
			}
			
		}
		
		return devuelve;		
	}
	
	
	public String libera() { // REVISADO p04
		String s = new String("");
		
		if(patrono != null) {
			s = patrono.getNombre();
			patrono = null;
		}
		
		return s;		
	}
	
	
	public Bestia getBestiola() {
		return bestiola;
	}
	
	
	public Guerrero getPatrono() {
		return patrono;
	}
		
	
	public boolean esAcogido(Clan c) {
		boolean devuelve = false;
		
		if(c != null  &&  super.perteneceClan(c.getNombre())  &&  tribu == null) {
			tribu = c;
			devuelve = true;
		}
		
		return devuelve;
	}
	
	
	public boolean esDesterrado() {
		boolean devuelve = false;
		
		if(tribu != null) {
			tribu = null;
			devuelve = true;
		}
		
		return devuelve;
	}
	
	
	public void cambiaClan(String s) {
		super.setClan(s);
	}
	
	
	public Clan getTribu() {
		return tribu;
	}
    
    
    // metodos adicionales practica 3
    
    
    public Bestia cambioBestiolas(Bestia b) { // se intercambian bestiolas dos plebeyos
        Bestia devuelve = null;
        
        devuelve = bestiola;
        bestiola = b;
        if(bestiola != null)
        	bestiola.setAmo(this);	// ERROR CORREGIDO no habia setteado el amo de este plebeyo
        
        return devuelve;
    }
	
	
    public void setBestiola(Bestia b) { // REVISADO p02
    	bestiola = b;
    }
    
    
    public void setTribu(Clan c) {
		if(c != null)
			tribu = c;
	}

}
