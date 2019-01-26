// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Guerrero extends Habitante {
	
	private Plebeyo sirviente;
	private ArrayList<Producto> armamento;
	private Clan tribu;
	
	public Guerrero(String n,char c) {
		super(n, c);
		sirviente = null;
		armamento = new ArrayList<Producto>();
		tribu = null;
	}
	
	
	public ArrayList<String> trueque(Habitante h) { // REVISADO p05
		ArrayList<String> productos = null;
				
		if(h != null  &&  h instanceof Guerrero  &&  armamento.size() > 0) {
			
			// realizamos el intercambio con el primer producto del armamento
			Producto sobra = this.getUnoArmamento(0);
			if(sobra != null) {
				Producto necesito = ((Guerrero) h).intercambio(sobra);	// ERROR CORREGIDO el mensaje se lo pasaba a this y no a h
				
				// si el producto devuelto no es nulo lo almacenamos al final del armamento
				if(necesito != null) {
					necesito.setColocado(true);
					armamento.add(necesito);
					productos = new ArrayList<String>();
					productos.add(necesito.getNombre());
					productos.add(sobra.getNombre());
				}
			}
			
		}
		
		return productos;
	}
	
	
	public double tributa(Mistico m) { // REVISADO p038
		double vigor = 0.0, incrementado = 0.0;
		
		if(m != null  &&  tribu != null) {
			
			// si m es el de su tribu entonces tributa como habitante
			if(m == tribu.getDeidad()) {
				super.tributa(m);
			}
			
			// si no es asi tributa como guerrero
			else {
				
				// si no hay como minimo dos armas no se puede hacer culto
				if(armamento.size() > 1) {
					
					// cogemos las dos armas mas poderosas o mayor valorProducto
					ArrayList<Producto> ofrenda = new ArrayList<Producto>();
					
					// las armas estan ordenadas de mayor a menor por dicho valor
					ofrenda.add(this.getArmaOP());
					ofrenda.add(this.getArmaOP());
					
					// si tenemos dos armas hacemos culto e incrementamos vigor
					if(ofrenda.size() == 2) {
						vigor = m.culto(ofrenda, super.getNombre());
						incrementado = super.incrementaVigor(vigor);
					}
				}
			}
			
		}
		
		return incrementado;		
	}
	
	
	public boolean plegaria() { // REVISADO p039
		double vigor = 0.0, incrementado = 0.0;
		boolean devuelve = false;
		
		// si tiene tribu y su mistico es blanco
		if(tribu != null  &&  tribu.getDeidad() != null  &&  tribu.getDeidad() instanceof Blanco) {
			
			// le pedimos ayuda con el primer producto filosofal de la cesta
			Producto p = super.getPrimerFilosofal();
			Blanco b = (Blanco) tribu.getDeidad();
			if(p != null) {
				vigor = b.ayuda(p);
				
				// incrementando el vigor con lo que nos devuelve el mistico
				incrementado = super.incrementaVigor(vigor);
				if(incrementado > 0.0)
					devuelve = true;
			}
		}
		
		return devuelve;
	}
	
	
	public Producto intercambio(Producto p) {	// REVISADO p05
		Producto devuelve = null;
		
		if(p != null) {
			
			if(p.getTipo() == 4  ||  p.getTipo() == 3) {
			
				// sacamos una madera o piedra del armamento para devolver
				if(p.getTipo() == 4)
					devuelve = this.getArmTipo(3);
				else if(p.getTipo() == 3)
					devuelve = this.getArmTipo(4);
				
				// y metemos el producto que nos dan
				p.setColocado(true);
				armamento.add(p);
			}
			
		}
		
		return devuelve;
	}
	
	
	public boolean acoge(Habitante h) { // REVISADO p06810 PruebaFinal
		boolean devuelve = false;
		
		if(h != null) {
			
			if(h instanceof Plebeyo  &&  ((Plebeyo) h).getPatrono() == null)
				if(((Plebeyo) h).tutela(this)) {
					sirviente = (Plebeyo) h;
					devuelve = true;
				}
			
		}
		
		return devuelve;
	}
	
	
	public int recolecta(int i) { // REVISADO p034568910
		int nuevos = 0;
		
		// comprobamos si la posicion en el feudo de i es correcta
		if(tribu != null  &&  tribu.getFeudo() != null  &&  i >= 0  &&  i < tribu.getFeudo().size()  &&  tribu.getFeudo().get(i) != null) {
			
			// recogemos de ese terreno la madera y piedra para guardar en armamento
			Terreno t = tribu.getFeudo().get(i);
			for(int x=0; x<t.getFilas(); x++)
				for(int y=0; y<t.getColumnas(); y++)
					if(t.consultaTipo(x, y) == 3  ||  t.consultaTipo(x, y) == 4) {
						armamento.add(t.recoge(x, y));
						armamento.get(armamento.size()-1).setColocado(true);
						nuevos++;
					}
			
			// despues ordenamos al sirviente que recoja productos de t para meterlos al principio de la cesta
			if(sirviente != null) {
				ArrayList<Producto> bag = sirviente.vasallaje(t);
				nuevos += bag.size();
				super.alPrincipioDeCesta(bag);
			}
		}
		
		return nuevos;
	}
	
	
	public int lucha(Habitante h) { // REVISADO p04
		int ganador = 0;
		Guerrero gana = null, pierde = null;
		ArrayList<Producto> premio = null;
		
		// si h es guerrero y no pertenecen al mismo clan o h no tiene clan entonces pelean
		if(h != null  &&  h instanceof Guerrero  &&  (((Guerrero) h).getTribu() == null  ||  ((Guerrero) h).getTribu() != tribu)) {
			double poder = 0.0, poderh = 0.0;
			
			// calculamos los poderes de ambos guerreros
			poder = super.getVigor() * (this.numeroProductos(4) * 0.4  +  this.numeroProductos(3) * 0.6);
			poderh = ((Guerrero) h).getVigor() * ( ((Guerrero) h).numeroProductos(4) * 0.4  +  ((Guerrero) h).numeroProductos(3) * 0.6 );
			
			// averiguamos quien gana y pierde para no hacer dos veces lo mismo en el siguiente if
			if(poder > poderh) {
				gana = this;
				pierde = (Guerrero) h;
				ganador = 2;
			}
			else if(poder < poderh) {
				gana = (Guerrero) h;
				pierde = this;
				ganador = 1;
			}
			
			// el ganador se queda con el armamento del perdedor y el perdedor libera a su sirviente
			if(poder != poderh) {
				premio = pierde.getArmamento();
				pierde.vaciaArmamento();
				gana.premioGanador(premio);
				if(pierde.getSirviente() != null) {
					pierde.getSirviente().libera();
					pierde.setSirviente(null);
				}
			}
		}
		
		return ganador;
	}
	
	
	public Plebeyo getSirviente() {
		return sirviente;
	}
	
	
	public ArrayList<Producto> getArmamento() {
		return armamento;
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
	
	
	public Producto getUnoArmamento(int i) { // devuelve el producto del armamento que ocupa la posicion indicada por parametro
		Producto p = null;
		
		if(i >= 0  &&  i < armamento.size()  &&  armamento.get(i) != null) {
			p = armamento.remove(i);
			p.setColocado(false);
		}
		
		return p;
	}
	
	
	public Producto getArmaOP() { // devuelve el arma mas poderosa o de mayor valor
		double valor = 0.0;
		Producto p = null;
		int pos = -1;
		
		for(int i=0; i<armamento.size(); i++) {
			if(armamento.get(i) != null  &&  armamento.get(i).valorProducto() > valor) {
				valor = armamento.get(i).valorProducto();
				pos = i;
			}
		}
		if(pos > -1) {
			p = armamento.remove(pos);
			p.setColocado(false);
		}
		
		return p;
	}
	
	
	public Producto getArmTipo(int t) { // saca el primer arma del tipo pasado por parametro
		Producto p = null;
		
		if(t > 0  &&  t < 7 ) {
			
			for(int i=0; i<armamento.size(); i++) {
				if(armamento.get(i) != null  && armamento.get(i).getTipo() == t) {
					p = armamento.remove(i);
					p.setColocado(false);
					break;
				}
			}
			
		}
		
		return p;
	}
	
	
	public int numeroProductos(int t) { // devuelve el numero de armas del tipo pasado por parametro
		int n = 0;
		
		if(t > 0  &&  t < 7)
			for(int i=0; i<armamento.size(); i++)
				if(armamento.get(i).getTipo() == t)
					n++;
		
		return n;
	}
	
	
	public void premioGanador(ArrayList<Producto> b) { // el guerrero ganador mete al final de su armamento el del perdedor
		
		if(b != null  &&  b.size() > 0)
			armamento.addAll(b);
		
	}
	
	
	public void setSirviente(Plebeyo p) { // settea el sirviente a nulo se usa para liberar
		sirviente = p;
	}
	
	
	public void setTribu(Clan c) {
		if(c != null)
			tribu = c;
	}

	
	public void vaciaArmamento() { // metodo que vacia la cesta del guerrero tras la derrota contra otro
		armamento = new ArrayList<Producto>();
	}
}
