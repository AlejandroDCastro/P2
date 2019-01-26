// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Sector {
	private Coordenada coordenada=null;
	private Mercancia[] almacen=null;
	private int[] existencias=null;
	
	public Sector(int n) {
		if(n>0) {
			almacen=new Mercancia[n];
			existencias=new int[n];
		}
	}
	
	public boolean situa(int i,int j,int k) {
		boolean s=false;
		
		if(coordenada==null) {
			coordenada=new Coordenada(i,j,k);
			s=true;
		}
		else {s=false;}
		return s;
	}
	
	public boolean almacena(Mercancia o) {
		boolean devu=false;
		boolean pes=false;
		boolean etq=false;
		boolean dim=false;
		int y=0, u=0, a=0;
		double []dimension=o.dimension(), dimensionAlmacen=null;
		String w=String.valueOf(coordenada.fil());
		
		if(almacen!=null && o!=null && coordenada!=null && !o.getAlmacenada()) {
			for(int i=0; i<almacen.length; i++) {
				if(almacen[i]!=null) {
					u++;
					if(o.getPeso()==almacen[i].getPeso())
						pes=true;
					if(o.getEtiqueta()!=null && almacen[i].getEtiqueta()!=null && o.getEtiqueta().equalsIgnoreCase(almacen[i].getEtiqueta()))
						etq=true;
					dimensionAlmacen=almacen[i].dimension();
					for(int j=0; j<dimension.length; j++) {
						if(dimension[j]==dimensionAlmacen[j]) {dim=true;}
						else {dim=false; j=dimension.length+1;}
					}
					if(pes==false || etq==false || dim==false)
						y++;
					if(pes==true && etq==true && dim==true && a==0) {a=i;}
				}
			}
			if(y==u) {
				for(int i=0; i<almacen.length; i++)
					if(almacen[i]==null) {almacen[i]=o; existencias[a]++; i=almacen.length; devu=true;}
				if(o.getEtiqueta()==null) {
					w=w.concat(String.valueOf(coordenada.col()));
					w=w.concat(String.valueOf(coordenada.dim()));
					devu=o.almacena(w);
				}
				o.setAlmacenada();
			}
			if(y!=u) {existencias[a]++; devu=true;}
		}
		return devu;
	}
	
	public Mercancia recoge(String s) {
		boolean t=false;
		Mercancia p=null;
		
		if(almacen!=null && s!=null) {
			for(int i=0; i<almacen.length; i++) {
				if(almacen[i]!=null && almacen[i].getEtiqueta()!=null) {
					t=s.equalsIgnoreCase(almacen[i].getEtiqueta());
					if(t) {
						existencias[i]--;
						p=almacen[i];
						if(existencias[i]==0)
							almacen[i]=null;
						else
							p.recogida();
						i=almacen.length+1;
					}
				}
			}
		}
		return p;
	}
	
	public int recuento(String s) {
		boolean t=false;
		int p=0;
		
		if(almacen!=null && s!=null) {
			for(int i=0; i<almacen.length; i++) {
				if(almacen[i]!=null && almacen[i].getEtiqueta()!=null) {
					t=s.equalsIgnoreCase(almacen[i].getEtiqueta());
					if(t) {p=p+existencias[i];}
				}
			}
		}
		return p;
	}
	
	public String[] disponibilidad() {
		String[] p=new String[almacen.length];
		
		if(almacen!=null) {
			for(int i=0; i<almacen.length; i++) {
				if(almacen[i]!=null)
					p[i]=almacen[i].getEtiqueta();
			}
		}
		return p;
	}
	
	public int capacidadDisponible() {
		int a=0;
		
		if(almacen!=null)
			for(int i=0; i<almacen.length; i++)
				if(almacen[i]==null)
					a++;
		return a;
	}
	
	public Coordenada getCoordenada() {
		return coordenada;
	}
	
	public int devuFil() {
		if(coordenada!=null)
			return coordenada.fil();
		else
			return -12;
	}
	
	public int devuCol() {
		if(coordenada!=null)
			return coordenada.col();
		else
			return -12;
	}
	
	public int devuDim() {
		if(coordenada!=null)
			return coordenada.dim();
		else
			return -12;
	}
	
	public Mercancia[] devuMercancia() {
		return almacen;
	}
	
}