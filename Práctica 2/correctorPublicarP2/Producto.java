// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Producto {

	private int tipo;
	private double peso;
	private boolean colocado;
	private String nombre;
	
	public Producto(double p,int i, String s) {
		// peso
		if(p > 0) peso = p;
		else peso = 1.0;
		// tipo
		if(i > 0  &&  i < 7) tipo = i;
		else tipo = 6;
		// nombre
		if(s != null  &&  s.length() > 0) nombre = new String(s);
		else nombre = new String("Ordinario");
		// colocado
		colocado = false;
	}
	
	
	public double valorKilo() {  // REVISADO p01
		double valor = 0.0;
		
		if(nombre != null  &&  nombre.length() > 0) {
			
			for(int i=0; i<nombre.length(); i++)
				valor += nombre.charAt(i) - 97;
			valor /= nombre.length();
			
		}
		
		return valor;
	}
	
	
	public double valorProducto() {		// REVISADO p01
		return this.valorKilo() * peso;
	}
	
	
	public int transforma(Producto p,int i) {		// REVISADO p01
		int devuelve = -1;
		
		if(p != null  &&  tipo == 6  &&  p.getTipo() != i  &&  i > 0  &&  i < 7) {
			p.setTipo(i);
			devuelve = 1;
		}
		
		return devuelve;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public boolean getColocado() {
		return colocado;
	}
	
	
	public void setColocado(boolean b) {
		colocado = b;
	}
	
	
	public double getPeso() {
		return peso;
	}
	
	
	public int getTipo() {
		return tipo;
	}
	
	// metodo adicional
	public void setTipo(int t) {
		tipo = t;
	}

}
