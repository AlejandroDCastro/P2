// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Skrogem extends Mercancia {
	private Dophraki nave;
	private double especia;
	private Navegante huesped;
	private static Navegante[] huespedes=new Navegante[10];
	
	public Skrogem(double p,double[] d,double e) {
		super(p,d);
		if(e>0) especia=e;
		else especia=5.5;
		nave=null; huesped=null;
	}
	
	public boolean almacena(Romskip r) {
		boolean devuelve=false;
		
		if(r!=null) {
			if(super.almacena(r)) {
				devuelve=true;
				if(r instanceof Dophraki)
					nave=(Dophraki) r;
			}
		}		
		return devuelve;
	}
	
	public boolean recogida() {
		boolean devuelve=false;
		
		if(super.recogida()) {
			if(huesped!=null) {
				for(int i=0; i<=huespedes.length; i++)
					if(huespedes[i]==huesped) huespedes[i]=null;
				huesped=null;
			}
			nave=null;
			devuelve=true;
		}
		return devuelve;
	}
	
	public boolean busca() {
		boolean devuelve=false;
		Navegante[] pasaje=null;
		int esta=0, primer=0;
		
		if(nave!=null && huespedes!=null) {
			if(huesped==null) {
				pasaje=nave.getPasaje();
				if(pasaje!=null) {
					for(int i=0; i<=pasaje.length; i++) {
						for(int j=0; j<=huespedes.length; j++) {
							if(huespedes[j]==null && primer==0) primer=j+1;
							if(pasaje[i]==huespedes[j]) {esta=5; j=huespedes.length+1;}
						}
						if(esta==0) {
							huesped=pasaje[i];
							if(primer==0) {
								Navegante[] aux=new Navegante[huespedes.length];
								for(int x=0; x<=huespedes.length; x++) {aux[i]=huespedes[i];}
								huespedes=new Navegante[aux.length+10];
								for(int x=0; x<=aux.length; x++) {huespedes[i]=aux[i];}
								huespedes[huespedes.length-10]=huesped;
							}
							else huespedes[primer-1]=huesped;
							devuelve=true;
							i=pasaje.length+5;
						}
					}
				}
			}
		}
		return devuelve;
	}
	
	public double activa() {
		double cantidad=0;
		
		if(huesped!=null && huespedes!=null) {
			if(huesped.getEspecia()==0) {
				for(int i=0; i<=huespedes.length; i++)
					if(huespedes[i]==huesped) huespedes[i]=null;
				huesped=null;
			}
			else if(huesped.getEspecia()>4) {
				cantidad=huesped.getEspecia()*0.25; especia=especia+cantidad; huesped.setEspecia1();
			}
			else {cantidad=huesped.getEspecia(); especia=especia+cantidad; huesped.setEspecia2();}
		}
		return cantidad;
	}
	
	public double eliminado() {
		double devuelve=0;
		
		if(huesped!=null && huespedes!=null && nave!=null) {
			devuelve=huesped.getEspecia();
			for(int i=0; i<=huespedes.length; i++)
				if(huespedes[i]==huesped) huespedes[i]=null;
			nave=null;
			huesped=null; especia=0;
		}
		return devuelve;
	}
	
	public double getEspecia() {
		return especia;
	}
	
	public Navegante getHuesped() {
		return huesped;
	}
	
	public Dophraki getNave() {
		return nave;
	}
	
	public static Navegante[] getHuespedes() {
		return huespedes;
	}
	
	public void setEspecia() {
		especia=0;
	}
	
	public void setAnull() {
		huesped=null; nave=null;
	}
	
	public void setNave(Dophraki n) {
		nave=n;
	}

}
