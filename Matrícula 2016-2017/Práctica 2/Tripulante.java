// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Tripulante extends Navegante {
	private Dophraki conexion;
	private String rango;
	
	public Tripulante(String n,double p,String r) {
		super(n,p);
		if(r.equalsIgnoreCase("superior")) rango=new String("superior");
		else rango=new String("subalterno");
		conexion=null;
	}
	
	public double cobra() {
		double sueldo=0, resto=0;
		
		if(rango!=null) {
			if(rango.equalsIgnoreCase("superior")) {
				sueldo=((super.getEspecia()/super.getCosecha())*super.getTempo())*1.35;
			}
			else sueldo=((super.getEspecia()/super.getCosecha())*super.getTempo())*1.20;
			resto=super.getCosecha()-sueldo;
			super.setdouble(sueldo);
		}
		return resto;
	}
	
	public boolean embarca(Dophraki n) {
		boolean devuelve=false;
		
		if(n!=null) {
			if(conexion==null && super.getPasaje()<0) {
				if(n.solicitudAdmision(this)) devuelve=true;
				else {if(n.embarque(this)>=0) devuelve=true;}
			}
		}
		return devuelve;
	}
	
	public int trabaja(double t) {
		int parasitos=0;
		Mercancia[] bodega=conexion.getBodega();
		Mercancia desparasitada=null;
		
		if(bodega!=null) {
			if(t>0) {
				for(int i=0; i<bodega.length; i++)
					if(bodega[i] instanceof Skrogem && parasitos<=3) {
						desparasitada=super.trabaja(bodega[i],t);
						bodega[i]=desparasitada;
						parasitos++;
					}
			}
		}
		return parasitos;
	}
	
	public boolean desembarca() {
		double resto=0;
		boolean devuelve=false;
		
		if(conexion!=null) {
			resto=this.cobra();
			if(conexion.solicitudDimision(resto, this)) devuelve=true;
		}
		return devuelve;
	}
	
	public boolean enrolado() {
		boolean devuelve=false;
		
		if(conexion!=null) devuelve=true;
		return devuelve;
	}
	
	public void setConexion(Dophraki d) {
		conexion=d;
	}
	
	public String getRango() {
		return rango;
	}
	
	public Dophraki getConexion() {
		return conexion;
	}
}
