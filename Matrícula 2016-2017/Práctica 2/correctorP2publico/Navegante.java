// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Navegante {
	private String nombre;
	private double especia;
	private double tempo;
	private double cosecha;
	private int pasaje;
	
	public Navegante(String n,double d) {
		nombre=new String(n);
		if(d>0) especia=d;
		else especia=7.5;
		tempo=0; cosecha=0; pasaje=-1;
	}
	
	public Mercancia trabaja(Mercancia m,double t) {
		Mercancia desparasitado=null;
		
		if(m!=null) {
			if(t>0) {
				tempo=tempo+t;
				if(m instanceof Skrogem) {
					cosecha=cosecha+((Skrogem) m).getEspecia();
					((Skrogem) m).setEspecia();
					desparasitado=new Mercancia(m.getPeso(),m.dimension());
				}
			}
				else desparasitado=m;
		}
		return desparasitado;
	}
	
	public double cobra() {
		double sueldo=0, resto=0;
		
		if(cosecha>0) {
			sueldo=(especia/cosecha)*tempo;
			especia=especia+sueldo; tempo=0;
			resto=cosecha-sueldo; cosecha=0;
		}
		return resto;
	}
	
	public boolean embarca(Dophraki n) {
		boolean devuelve=false;
		int posicion=0;
		
		if(n!=null) {
			if(pasaje<0) {
				posicion=n.embarque(this);
				if(posicion>=0) {pasaje=posicion; devuelve=true;}
			}
		}
		return devuelve;
	}
	
	public boolean desembarca(Dophraki n) {
		boolean devuelve=false;
		
		if(n!=null) {
			if(n.desembarque(pasaje,this)) {pasaje=-1; devuelve=true;}
		}
		return devuelve;
	}
	
	public double paga(double d) {
		double devuelve=0;
		if(d>0 && especia>=d) {especia=especia-d; devuelve=especia;}
		else if(especia<d) devuelve=0;
		else if(d<=0) devuelve=-1;
		return devuelve;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getEspecia() {
		return especia;
	}
	
	public double getTempo() {
		return tempo;
	}
	
	public double getCosecha() {
		return cosecha;
	}
	
	public int getPasaje() {
		return pasaje;
	}
	
	public void setdouble(double sueldo) {
		especia=especia+sueldo; tempo=0; cosecha=0;
	}
	
	public void setEspecia1() {
		especia=especia*0.75;
	}
	
	public void setEspecia2() {
		especia=0;
	}
	
	public void setPasaje(int p) {
		pasaje=p;
	}

}
