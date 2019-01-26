// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Dophraki extends Romskip {
	private Navegante[] pasaje;
	private Tripulante[] tripulacion;
	private Coordenada posicion;
	private double especia;
	private static Galaxia mapa;
	
	public Dophraki(String n,int i,double x,int j,int k,double e) {
		super(n,i,x);
		if(j>=3) pasaje=new Navegante[j];
		else pasaje=new Navegante[3];
		if(k>=1) tripulacion=new Tripulante[j];
		else tripulacion=new Tripulante[1];
		if(e>=3.5) especia=e;
		else especia=3.5;
		posicion=new Coordenada(0,0,0);
	}
	
	public String embarque(String s,Coordenada c) {
		String mensaje=null;
		Sector sector=null;
		Mercancia primera=null;
		Mercancia[] bodega=null;
		int ocupadas=0, nomaserrores=0;
		
		if(posicion!=null) {
			if(posicion.fil()!=c.fil() || posicion.col()!=c.col() || posicion.dim()!=c.dim()) {
				mensaje=new String(String.valueOf(c.fil()));
				mensaje=mensaje.concat(" "); mensaje=mensaje.concat(String.valueOf(c.col()));
				mensaje=mensaje.concat(" "); mensaje=mensaje.concat(String.valueOf(c.dim()));
				mensaje=mensaje.concat(": emplazamiento incorrecto");
			}
			if((posicion.fil()==c.fil() || posicion.col()==c.col() || posicion.dim()==c.dim()) && mapa!=null) {
				sector=mapa.getUnSector(c.fil(), c.col(), c.dim());
				primera=sector.recoge(s);
				if(sector!=null && primera==null) {
					mensaje=new String(s); mensaje=mensaje.concat(": mercancia no encontrada");}
				if(sector!=null && primera!=null) {
					bodega=super.getBodega();
					if(bodega!=null) {
						for(int i=bodega.length-1; i>=0; i--) {
							if(bodega[i]!=null) ocupadas++;
							if(bodega[i]==null && nomaserrores==0) {
								bodega[i]=primera; primera.almacena(this);
								mensaje=new String(primera.getEtiqueta()); mensaje=mensaje.concat(": mercancia embarcada");
								nomaserrores++;
							}
						}
						if(ocupadas==bodega.length) {
							mensaje=new String(s); mensaje=mensaje.concat(": bodega de carga completa");
						}
					}
				}
			}
		}
		if(mapa==null) mensaje=null;
		return mensaje;
	}
	
	public Mercancia desembarque(String s) {
		boolean coincide=false;
		Mercancia retira=null;
		Mercancia[] bodega=super.getBodega();
		
		if(s!=null && bodega!=null) {
			for(int i=0; i<bodega.length; i++) {
				coincide=s.equalsIgnoreCase(bodega[i].getEtiqueta());
				if(coincide) {
					retira=bodega[i];
					bodega[i]=null;
					i=bodega.length+1;
				}
			}
		}
		return retira;
	}
	
	public boolean teletransporte(Romskip r,int i) {
		boolean devuelve=false;
		Mercancia[] bodega=super.getBodega();
		int posicion=0;
		
		if(r!=null && i>-1) {
			if(pasaje!=null && r instanceof Dophraki) {
				posicion=((Dophraki) r).verifica(pasaje[i]);
				if(posicion>=0) {
					pasaje[i].setPasaje(posicion);
					pasaje[i]=null; devuelve=true;
				}
			}
			else {
				if(bodega!=null && r.verifica(bodega[i])) {
					if(bodega[i] instanceof Skrogem) {((Skrogem) bodega[i]).setAnull();}
					bodega[i]=null;
					devuelve=true;
				}
			}
		}
		return devuelve;
	}
	
	public boolean verifica(Mercancia m) {
		boolean devuelve=false;
		Mercancia[] bodega=super.getBodega();
		int x=0;
		
		if(bodega!=null) {
			for(int i=bodega.length-1; i>=0; i--) {
				if(bodega[i]==null && x==0) {
					bodega[i]=m;
					x++;
					devuelve=true;
					if(bodega[i] instanceof Skrogem) {((Skrogem) bodega[i]).setNave(this);}
				}
				else x++;
			}
			if(x==bodega.length) {
				Mercancia[] almacena=new Mercancia[bodega.length];
				for(int i=0; i<bodega.length; i++) {almacena[i]=bodega[i];}
				super.setBodega(almacena); bodega=super.getBodega();
				bodega[bodega.length-1]=m; devuelve=true;
				if(bodega[bodega.length-1] instanceof Skrogem) {((Skrogem) bodega[bodega.length-1]).setNave(this);}
			}
		}
		return devuelve;			
	}
	
	public String embarque(Mercancia m) {
		String etiqueta=null;
		Mercancia[] bodega=super.getBodega();
		int x=0;
		
		if(m!=null && m.getEtiqueta()!=null && bodega!=null) {
			if(m.getAlmacenada()) {
				etiqueta=new String(m.getEtiqueta()); etiqueta=etiqueta.concat(": mercancia ya almacenada");}
			else {
				for(int i=0; i<bodega.length; i++)
					if(bodega[i]==null) x++;
				if(x==0)
					etiqueta=new String(m.getEtiqueta()); etiqueta=etiqueta.concat(": bodega de carga completa");
			}
			if(etiqueta==null) {
				for(int i=bodega.length-1; i>=0; i--)
					if(bodega[i]==null) {
						bodega[i]=m;
						i=-3;
					}
				m.almacena(this);
				etiqueta=new String(m.getEtiqueta()); etiqueta=etiqueta.concat(": mercancia embarcada");
			}
		}
		return etiqueta;
	}
	
	public int embarque(Navegante n) {
		int devuelve=0, esplibre=0, poslibre=0, embarcados=0;
		double coste=0;
		
		if(pasaje!=null && n!=null) {
			for(int i=0; i<pasaje.length; i++) {
				if(pasaje[i]==null) {esplibre++; if(esplibre==1) poslibre=i;}
				else embarcados++;
			}
			if(n.getPasaje()>=0) devuelve=-1;
			else if(esplibre==0) devuelve=-2;
			if(devuelve==0) {
				if(n instanceof Tripulante)
					coste=(((double) pasaje.length/(poslibre+1))+embarcados)*0.70;
				else
					coste=((double) pasaje.length/(poslibre+1))+embarcados;
				if(n.paga(coste)<=0) devuelve=-3;
			}
			if(devuelve==0) {pasaje[poslibre]=n; especia=especia+coste; devuelve=poslibre;}
		}
		return devuelve;
	}
	
	public boolean solicitudAdmision(Tripulante n) {
		boolean devuelve=false, limite=false;
		int esplibre=0, superiores=0, subalternos=0, poslibre=0;
		
		if(tripulacion!=null && n!=null && n.getRango()!=null) {
			for(int i=0; i<tripulacion.length; i++)
				if(tripulacion[i]==null) {esplibre++; if(esplibre==1) poslibre=i;}
				else {
					if((n.getRango()).equalsIgnoreCase("superior")) superiores++;
					else subalternos++;
				}
			if((subalternos/3)>=superiores) limite=true;
			if(n.getConexion()==null && n.getPasaje()<0 && esplibre>0 && limite) {
				tripulacion[poslibre]=n;
				n.setConexion(this);
				devuelve=true;
			}
		}
		return devuelve;
	}
	
	public boolean solicitudDimision(double d,Tripulante n) {
		boolean devuelve=false;
		
		if(tripulacion!=null && n!=null) {
			for(int i=0; i<tripulacion.length; i++)
				if(tripulacion[i]==n) {
					especia=especia+d;
					tripulacion[i]=null;
					n.setConexion(this);
					devuelve=true;
					i=tripulacion.length+1;
				}
		}
		return devuelve;
	}
	
	public boolean desembarque(int i,Navegante n) {
		boolean devuelve=false;
		
		if(pasaje!=null && n!=null && i<pasaje.length && i>=0) {
			if(pasaje[i]==n) {pasaje[i]=null; devuelve=true;} }
		return devuelve;
	}
	
	public void repostaje() {
		for(int i=0; i<tripulacion.length; i++)
			if(tripulacion[i]!=null)
				especia=especia+tripulacion[i].cobra();
	}
	
	public boolean viaja(Galaxia g,Coordenada c) {
		double distancia=0, op=0;
		int diferencia=0;
		boolean devuelve=false;
		
		if(c!=null && posicion!=null && !g.esAgujero(c)) {
			op=Math.pow(((double)posicion.fil()-(double)c.fil()),2)+Math.pow(((double)posicion.col()-(double)c.col()),2)+Math.pow(((double)posicion.dim()-(double)c.dim()),2);
			distancia=Math.sqrt(op);
			if(mapa!=g) {
				if(mapa!=null) {
					diferencia=mapa.getSectores()-g.getSectores();
					diferencia=Math.abs(diferencia);
					distancia=distancia*diferencia;
				}
				else distancia=distancia*g.getSectores();
			}
			if((distancia/especia)<=especia) {
				especia=especia-(distancia/especia);
				posicion=c;
				if(mapa!=g || mapa==null) mapa=g;
				devuelve=true;
			}
		}
		return devuelve;
	}
	
	public int verifica(Navegante n) {
		int x=0, poslibre=-1;
		
		if(n!=null && pasaje!=null) {
			for(int i=pasaje.length-1; i>=0; i--) {
				if(pasaje[i]==null) {
					x++;
					if(x==1)
						poslibre=i;
				}
			}
			if(x>0) pasaje[poslibre]=n;
			else poslibre=-1;
		}
		return poslibre;
	}
	
	public double getEspecia() {
		return especia;
	}
	
	public Navegante[] getPasaje() {
		return pasaje;
	}
	
	public Tripulante[] getTripulacion() {
		return tripulacion;
	}
	
	public Coordenada getPosicion() {
		return posicion;
	}
	
	public static void setMapa() {
		Dophraki.mapa=Romskip.getMapa();
	}
	
}