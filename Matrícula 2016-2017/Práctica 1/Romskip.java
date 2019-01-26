
public class Romskip {
	private String nombre;
	private Mercancia[] bodega;
	private double carga;
	private static Galaxia mapa;
	
	public Romskip(String n,int i,double x) {
		nombre=new String(n);
		if(i>0) {bodega=new Mercancia[i];}
		else {bodega=new Mercancia[3];}
		if(x>0) {carga=x;}
		else {carga=8.5;}
	}
	
	public String embarque(String s,Coordenada c) {
		String etq=null;
		Sector[][][] aux=null;
		Mercancia[] primera=null, ordena=null;
		Mercancia auxiliar=null;
		int espnulos=0, posicion=0;
		double pesocupado=0;
		
		if(bodega!=null && mapa!=null && s!=null && c!= null && mapa.getFil()>=c.fil() && mapa.getCol()>=c.col() && mapa.getDim()>=c.dim()) {
			int x=c.fil();
			int y=c.col();
			int z=c.dim();
			
			aux=mapa.getSector();
			primera=aux[x][y][z].devuMercancia();
			for(int i=0; i<primera.length; i++) {
				if(s.equalsIgnoreCase(primera[i].getEtiqueta())) {
					for(int j=0; j<bodega.length; j++) {
						if(bodega[j]==null) {
							espnulos++;
							if(espnulos==1)
								posicion=j;
						}
					if(bodega[j]!=null)
							pesocupado=pesocupado+bodega[j].getPeso();
					}
					pesocupado=pesocupado+primera[i].getPeso();
					if(espnulos==0) {etq=s.concat(": bodega de carga completa");}
					if(espnulos>0 && pesocupado>carga) {etq=s.concat(": sobrecarga");}
					if(espnulos>0 && pesocupado<=carga) {bodega[posicion]=primera[i]; espnulos--;}
					i=primera.length;
				}
			}
			posicion=0;
			ordena=new Mercancia[bodega.length-espnulos];
			for(int w=0; w<bodega.length; w++)
				if(bodega[w]!=null) {
					ordena[posicion]=bodega[w];
					posicion++;
				}
			posicion=0;
			for(int p=1; p<ordena.length; p++)
				for(int j=ordena.length-1; j>=p; j--)
					if(ordena[j]!=null && ordena[j-1]!=null && ordena[j-1].getDimension()>ordena[j].getDimension()) {
						auxiliar=ordena[j-1];
						ordena[j-1]=ordena[j];
						ordena[j]=auxiliar;						
					}
			for(int j=0; j<bodega.length; j++) {
				if(j<ordena.length) {
					bodega[j]=null;
					bodega[j]=ordena[j];
				}
				else {bodega[j]=null;}
			}
		}
		return etq;
	}
	
	public Mercancia desembarque(String s) {
		boolean coincide=false;
		Mercancia retira=null;
		
		if(s!=null) {
			for(int i=0; i<bodega.length; i++) {
				coincide=s.equalsIgnoreCase(bodega[i].getEtiqueta());
				if(coincide) {
					retira=bodega[i];
					bodega[i]=null;
					for(int j=i+1; j<bodega.length; j++)
						if(bodega[j]!=null) {
							bodega[j-1]=bodega[j];
							bodega[j]=null;
						}
				}
			}
		}
		return retira;
	}
	
	public int conteo() {
		int num=0;
		
		if(bodega!=null)
			for(int i=0; i<bodega.length; i++)
				if(bodega[i]!=null)
					num++;
		return num;
	}
	
	public double getPeso() {
		double peso=0;
		
		if(bodega!=null)
			for(int i=0; i<bodega.length; i++)
				if(bodega[i]!=null)
					peso=peso+bodega[i].getPeso();
		return peso;
	}
	
	public boolean teletransporte(Romskip r,int i) {
		boolean devuelve=false;
		
		if(r!=null) {
			for(int x=0; x<r.bodega.length; x++) {
				if(bodega[i]!=null && r.verifica(bodega[i])) {
					bodega[i]=null; devuelve=true;
					for(int j=i+1; j<bodega.length; j++)
						if(bodega[j]!=null) {
							bodega[j-1]=bodega[j];
							bodega[j]=null;
						}
					x=r.bodega.length;
				}
			}
		}
		return devuelve;
	}

	public boolean verifica(Mercancia m) {
		double pesototal=0;
		boolean almacena=false;
		Mercancia []reordena=null;
		Mercancia aux=null;
		
		if(m!=null) {
			for(int i=0; i<bodega.length; i++)
				if(bodega[i]!=null)
					pesototal=pesototal+bodega[i].getPeso();
			if(pesototal<=carga) {
				for(int i=0; i<bodega.length; i++)
					if(bodega[i]==null) {bodega[i]=m; almacena=true; i=bodega.length;}
				if(!almacena) {
					reordena=new Mercancia[bodega.length+1];
					for(int j=0; j<bodega.length; j++) {reordena[j]=bodega[j];}
					reordena[reordena.length-1]=m;
					bodega=new Mercancia[reordena.length];
					for(int j=0; j<bodega.length; j++) {bodega[j]=reordena[j];}
					almacena=true;
				}
			}
			for(int i=1; i<bodega.length; i++)
				for(int j=bodega.length-1; j>=i; j--)
					if(bodega!=null && bodega[j]!=null && bodega[j-1]!=null && bodega[j-1].getDimension()>bodega[j].getDimension()) {
						aux=bodega[j-1];
						bodega[j-1]=bodega[j];
						bodega[j]=null;
						bodega[j]=aux;
					}
		}
		return almacena;
	}
	
	public Mercancia[] getBodega() {
		return bodega;
	}
		
	public String getNombre() {
		return nombre;
	}
	
	public static String getNombreMapa() {
		return mapa.getNombre();
	}
	
	public static void setMapa(Galaxia g) {
		mapa=g;
	}
	
	public static boolean hayAgujerosNegros() {
		boolean agujeros=false;
		
		if(mapa.getAgujerosNegros()>0) {agujeros=true;}
		return agujeros;
	}
}
