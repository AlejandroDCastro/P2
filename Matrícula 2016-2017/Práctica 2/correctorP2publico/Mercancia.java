// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Mercancia {
	private double peso;
	private double[] dimension;
	private String etiqueta=null;
	private boolean almacenada=false;
	
	public Mercancia(double p,double[] d) {
		if(p>=0) {peso=p;}
		else {peso=1.5;}
		if(d!=null) {
			dimension=new double[d.length];
			for(int i=0; i<d.length; i++)
				dimension[i]=d[i];
		}
		else {dimension=new double[1]; dimension[0]=0.5;}
	}
	
	public boolean almacena(String s) {
		double suma=0;
		int x=0;
		boolean y=false;
		
		if(!almacenada) {
			if(etiqueta==null) {
				for(int i=0; i<dimension.length; i++)
					suma=suma+dimension[i];
				suma=suma*10; x=(int) suma; suma=(double) x; suma=suma/10;
				etiqueta=s.concat(String.valueOf(peso));
				etiqueta=etiqueta.concat(String.valueOf("-"));
				etiqueta=etiqueta.concat(String.valueOf(suma));
			}
			almacenada=true;
			y=true;
		}
		return y;
	}
	
	public boolean almacena(Romskip r) {
		boolean devuelve=false;
		String nombre=new String(r.getNombre());
		if(!almacenada) {
			almacenada=true;
			etiqueta=null;
			etiqueta=nombre.concat(String.valueOf(peso));
			devuelve=true;
		}
		return devuelve;
	}
	
	public boolean recogida() {
		boolean s=true;
		
		if(almacenada)
			almacenada=false;
		else
			s=false;
		return s;
	}
	
	public boolean iguales(Mercancia m) {
		boolean s=false;
		boolean pes=false;
		boolean dimen=false;
		boolean bool=etiqueta.equalsIgnoreCase(m.etiqueta);
		
		if(m!=null) {
			if(peso==m.peso)
				pes=true;
			for(int i=0; i<dimension.length; i++) {
				if(dimension[i]==m.dimension[i]) {dimen=true;}
				else {dimen=false; i=dimension.length;}
			}
			if(pes==true && dimen==true && bool==true) {s=true;}
		}
		return s;
	}
	
	public Mercancia duplicado() {
		Mercancia dupli=null;
		
		dupli=new Mercancia(this.peso,this.dimension);
		dupli.etiqueta=new String(this.etiqueta);
		dupli.almacenada=this.almacenada;
		return dupli;
	}
	
	public double getDimension() {
		double suma=0;
		
		for(int i=0; i<dimension.length; i++) {
			suma=suma+dimension[i];
		}
		return suma;
	}
	
	public boolean getAlmacenada() {
		return almacenada;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public String getEtiqueta() {
		return etiqueta;
	}

	public double[] dimension() {
		return dimension;
	}
	
	public void setAlmacenada() {
		almacenada=true;
	}
	
	public void setEtiqueta(String p) {
		etiqueta=new String(p);
	}
	
}