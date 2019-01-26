// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Terreno {

	private Producto[][] parcelas;
	
	public Terreno(int i,int j) {
		int a = 0, b = 0;
		
		// filas
		if(i > 0) a = i;
		else a = 3;
		// columnas
		if(j > 0) b = j;
		else b = 2;
		
		parcelas = new Producto[a][b];
	}
	
	
	public boolean genera(int i,int j,double d,int k,String s) {  // REVISADO p03910
		boolean devuelve = false;
		
		if(i >= 0  &&  j >=0  &&  i < parcelas.length  &&  j < parcelas[0].length)
			
			//	los parametros son correctos
			if(parcelas[i][j] == null  &&  k != 5) {
				
				// se pueden meter dentro de parcela
				parcelas[i][j] = new Producto(d,k,s);
				parcelas[i][j].setColocado(true);
				devuelve = true;
			}
		
		return devuelve;
	}
	
	
	public Producto recoge(int i,int j) {  // REVISADO p05 necesario p3
		Producto r = null;
		
		// comprobar dimensiones
		if(i >= 0  &&  j >=0  &&  i<parcelas.length  &&  j<parcelas[0].length) {
			
			// comprobar valores
			if(parcelas[i][j] != null  &&  parcelas[i][j].getTipo() != 5  &&  parcelas[i][j].getTipo() < 7  &&  parcelas[i][j].getTipo() > 0) {
				
				r = parcelas[i][j];
				parcelas[i][j].setColocado(false);
				parcelas[i][j] = null;
				
			}
		}
		
		return r;
	}
	
		
	public double destruye(int i,int j) { // necesario p3
		double peso = 0.0;
		
		// comprobar dimensiones
		if(parcelas != null  &&  i >= 0  &&  j >=0  &&  i<parcelas.length  &&  j<parcelas[0].length) {
			
			// comprobar valores
			if(parcelas[i][j] != null  &&  parcelas[i][j].getTipo() == 5) {
				
				peso = parcelas[i][j].getPeso();
				parcelas[i][j] = null;
				
			}
		}
		
		return peso;
	}
	

	public boolean coloca(Producto p, int i,int j) {  //  REVISADO p02346 necesario p3
		boolean devuelve = false;
		
		// comprobacion no nulo y dimensiones
		if(parcelas != null  &&  p != null  &&  i >= 0  &&  j >= 0  &&  i < parcelas.length  &&  j < parcelas[0].length) {
			
			// comprobacion de colocacion
			if(parcelas[i][j] == null  &&  !p.getColocado()) {
				parcelas[i][j] = p;
				parcelas[i][j].setColocado(true);
				devuelve = true;
			}
			
		}
		
		return devuelve;
	}
	
	
	public int consultaTipo(int i,int j) {  // REVISADO p034
		int tipo = -1;
		
		// comprobar dimensiones
		if(i >= 0  &&  j >= 0  &&  i < parcelas.length  &&  j < parcelas[0].length) {
			
			// comprobar valores
			if(parcelas[i][j] != null  &&  parcelas[i][j].getTipo() > 0  &&  parcelas[i][j].getTipo() < 7) {
				tipo = parcelas[i][j].getTipo();
			}
		}
		
		return tipo;
	}
	
	
	public double consultaPeso(int i,int j) {  // REVISADO p03
		double peso = -1.0;
		
		// comprobar dimensiones
		if(i >= 0  &&  j >= 0  &&  i < parcelas.length  &&  j < parcelas[0].length) {
				
			// comprobar valores
			if(parcelas[i][j] != null  &&  parcelas[i][j].getPeso() > 0.0) {
				peso = parcelas[i][j].getPeso();
			}
		}
		
		return peso;
		
	}
	
	
	public int existencias(int i) {  // REVISADO p02
		int n = 0;
		
		// comprobacion inicial basica
		if(parcelas != null  &&  parcelas.length > 0  &&  parcelas[0].length > 0  &&  i > 0  &&  i < 7) {
			
			for(int x=0; x<parcelas.length; x++)
				for(int y=0; y<parcelas[x].length; y++)
					if(parcelas[x][y] != null  &&  parcelas[x][y].getTipo() == i)
						n++;
			
		}
		
		return n;
	}
	
	
	public ArrayList<Integer> calculaDemanda() {  // REVISADO p02
		int[] tipos = new int[6];
		double denomina = 0.0;
		ArrayList<Integer> demanda = new ArrayList<Integer>();
		
		if(parcelas != null  &&  parcelas.length > 0  &&  parcelas[0].length > 0) {
			
			// calculamos las existencias de cada uno y el denominador
			for(int i=0; i<tipos.length; i++) {
				tipos[i] = this.existencias(i+1);
				denomina += Math.pow(tipos[i],2);
			}
			denomina = Math.sqrt(denomina);
			
			// por ultimo calculamos la demanda de cada producto metiendola en el arraylist
			for(int i=0; i<tipos.length; i++)
				
				// en el array devuelto solo apareceran los tipos presentes en el terreno ajustando su tamaño
				if(tipos[i] > 0)
					demanda.add((int) ((tipos[i]/denomina)*100));
			
		}
		
		return demanda;
	}
	
	
	public int getFilas() { //  necesario p3
		return parcelas.length;
	}
	
	
	public int getColumnas() { //  necesario p3
		return parcelas[0].length;
	}
	
	
	public Producto[][] getParcela() {
		return parcelas;
	}
	
	
	// metodos adicionales practica 3
	
	public Producto getElPrimeroDe(int n) { // REVISADO p01
		Producto devuelve = null;
		
		if(n > 0  &&  n < 7) {
			
			for(int i=0; i<this.getFilas(); i++)
				for(int j=0; j<this.getColumnas(); j++) {
					if(parcelas[i][j] != null  &&  this.consultaTipo(i,j) == n) {
						devuelve = this.recoge(i, j);
						i = this.getFilas();
						j = this.getColumnas();
					}
				}
				
		}
		
		return devuelve;
	}
	
	
	public Producto getIgualA(String s) { // devuelve el primer producto del terreno cuyo nombre coincida con el pasado por parametro y de tipo 6
		Producto p = null;
		
		if(s != null) {
			
			for(int i=0; i<parcelas.length; i++)
				for(int j=0; j<parcelas[0].length; j++)
					if(parcelas[i][j] != null  &&  parcelas[i][j].getNombre().equalsIgnoreCase(s)  &&  this.consultaTipo(i, j) == 6) {
						p = this.recoge(i, j);
						j = parcelas[0].length;
						i = parcelas.length;
					}
			
		}
		
		return p;		
	}

}
