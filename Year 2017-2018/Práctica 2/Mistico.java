// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Mistico {
	
	private ArrayList<Producto> tributos;
	private ArrayList<String> adoradores;
	
	public Mistico() {
		tributos = new ArrayList<Producto>();
		adoradores = new ArrayList<String>();
	}
	
	
	public double culto(ArrayList<Producto> a,String s) {	// REVISADO p09 necesario p3
		double valorkilo = 0;
		boolean igual = false;
		
		if(a != null  &&  !a.isEmpty()  &&  s != null  &&  tributos != null  &&  adoradores != null) {
			
			// calculamos lo que hay que devolver y guardamos lo que nos pasan por parametro
			for(int i=0; i<a.size(); i++)
				if(a.get(i) != null) {
					valorkilo += a.get(i).valorKilo();
					tributos.add(a.get(i));
					tributos.get(tributos.size()-1).setColocado(true);	// ERROR VISUALIZADO no habias seteado colocado a true
				}
			valorkilo /= a.size();
			
			// por ultimo guardamos el nombre del que rinde culto al mistico
			for(int i=0; i<adoradores.size(); i++)
				if(adoradores.get(i) != null  &&  s.equals(adoradores.get(i))) {
					igual = true;
					break;
				}
			if(!igual)
				adoradores.add(s);
			
		}
		
		return valorkilo;
	}
	
		
	public int regenera(Terreno t,Producto p) {  // REVISADO p04
		Producto pacolocar = null;
		int colocados = 0, tribu = 0;
		
		if(t != null  &&  p != null  &&  tributos != null  &&  t.getFilas() > 0  &&  t.getColumnas() > 0) {
			
			// si no esta colocado p lo metemos en sus tributos
			if(!p.getColocado()) {
				tributos.add(p);
				p.setColocado(true);
					
				// despues creamos el producto y lo intentamos colocar
				for(int i=0; i<t.getFilas(); i++) {
					for(int j=0; j<t.getColumnas(); j++) {
						pacolocar = null;
						if(tributos.get(tribu) != null) {
							pacolocar = new Producto(tributos.get(tribu).getPeso(),tributos.get(tribu).getTipo(),tributos.get(tribu).getNombre());
							
							// y si lo hace incrementamos colocados
							if(t.coloca(pacolocar, i, j))
								colocados++;
							
							// aqui pasamos a la siguiente posicion de tributos
							tribu++;
							// si sobrepasa la longitud de arrays volvemos al principio
							if(tribu == tributos.size())
							tribu = 0;
						}
					}
				}
			}
			
		}
		
		return colocados;		
	}
	
	
	public int transforma(Terreno t,int i) {	// REVISADO
		int trans = 0;
		Producto[][] parcela = t.getParcela();
		Producto filosofal = null;
		
		if(t != null  &&  i != 5  &&  tributos != null  &&  !tributos.isEmpty()) {
			
			// primero comprobamos si tiene un producto filosofal en sus tributos
			for(int x=0; x<tributos.size(); x++)
				if(tributos.get(x) != null  &&  tributos.get(x).getTipo() == 6) {
					
					// ERROR CORREGIDO el objeto filosofal no se borra sino que permanece
					filosofal = tributos.get(x);
					break;
				}
			
			// transformamos los edificados en el tipo i
			if(filosofal != null)
				for(int x=0; x<t.getFilas(); x++)
					for(int y=0; y<t.getColumnas(); y++)
						if(t.consultaTipo(x, y) == 5)
							if(filosofal.transforma(parcela[x][y],i) == 1)
								trans++;	
		}
		
		return trans;
	}
	
	
	public ArrayList<Producto> getTributos() {
		return tributos;
	}
	
	
	public ArrayList<String> getAdoradores() {
		return adoradores;
	}
	
	
	// metodos adicionales de la practica 3
	
	
	public boolean meteProducto(Producto p) { // mete p al array de tributos al final
		if(p != null) {
			tributos.add(p);
			p.setColocado(true);
			return true;
		}
		return false;
	}
	
	
	public int cantidadProductos(int t) { // devuelve la cantidad de productos del tipo pasado por parametro
		int num = 0;
		
		if(t > 0  &&  t < 7) {
			
			for(int i=0; i<tributos.size(); i++)
				if(tributos.get(i) != null  &&  tributos.get(i).getTipo() == t)
					num++;
			
		}
		
		return num;		
	}
	
	
	public boolean meteAlPrincipio(Producto p) { // mete al principio de los tributos el producto p
		ArrayList<Producto> ricos = new ArrayList<Producto>();
		
		if(p != null) {
			p.setColocado(true);
			ricos.add(p);
			ricos.addAll(tributos);
			tributos = ricos;		// ERROR CORREGIDO no habia actualizado la variable tributos
			return true;
		}
		return false;
	}

}
