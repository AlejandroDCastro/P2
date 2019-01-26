// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Galaxia {
	private String nombre;
	private Sector[][][] espacio;
	
	public Galaxia(String s,int i,int j,int k) {
		int x=0, y=0, z=0;
		
		x=i; y=j; z=k;
		nombre=new String(s);
		if(x<=0) {x=2;}
		else if(y<=0) {y=2;}
		else if(z<=0) {z=2;}
		espacio=new Sector[x][y][z];
	}
	
	public boolean coloca(Sector s) {
		int x=0, y=0, z=0;
		boolean devuelve=false;
		
		if(espacio!=null && s!=null && s.devuFil()>-1 && s.devuCol()>-1 && s.devuDim()>-1) {
			x=s.devuFil(); y=s.devuCol(); z=s.devuDim();
			if(x<espacio.length && y<espacio[0].length && z<espacio[0][0].length && espacio[x][y][z]==null) {
				espacio[x][y][z]=s;
				devuelve=true;
			}
		}
		return devuelve;
	}
	
	public Coordenada[] existencias(String s) {
		Coordenada[] c=null;
		int dimension=0, recorre=0;
		Mercancia[] m=null;
		boolean a=false;
		
		if(espacio!=null && s!=null) {
			for(int i=0; i<espacio.length; i++)
				for(int j=0; j<espacio[i].length; j++)
					for(int k=0; k<espacio[i][j].length; k++)
						if(espacio[i][j][k]!=null) {
//							m=new Mercancia[espacio[i][j][k].devuNumMercancias()];
							m=espacio[i][j][k].devuMercancia();
							for(int q=0; q<m.length; q++) {
								if(m[q]!=null && m[q].getEtiqueta()!=null) {
									a=s.equalsIgnoreCase(m[q].getEtiqueta());
									if(a)
										dimension++;
								}
							}
							m=null;
						}
			c=new Coordenada[dimension];
			for(int i=0; i<espacio.length; i++)
				for(int j=0; j<espacio[i].length; j++)
					for(int k=0; k<espacio[i][j].length; k++)
						if(espacio[i][j][k]!=null) {
//							m=new Mercancia[espacio[i][j][k].devuNumMercancias()];
//							m=espacio[i][j][k].devuMercancia();
							for(int q=0; q<m.length; q++) {
								if(m[q]!=null && m[q].getEtiqueta()!=null) {
									a=s.equalsIgnoreCase(m[q].getEtiqueta());
									if(a) {
										c[recorre]=new Coordenada(i,j,k);
										recorre++;
									}
								}
							}
							m=null;
						}
		}
		return c;
	}
	
	public String getNombre() {
		return nombre;
	}

	public int getAgujerosNegros() {
		int n=0;
		
		if(espacio!=null) {
			for(int i=0; i<espacio.length; i++)
				for(int j=0; j<espacio[i].length; j++)
					for(int k=0; k<espacio[i][j].length; k++)
						if(espacio[i][j][k]==null)
							n++;
		}
		return n;
	}
	
	public int getFil() {
		return espacio.length;
	}
	
	public int getCol() {
		return espacio[0].length;
	}
	
	public int getDim() {
		return espacio[0][0].length;
	}
	
	public Sector[][][] getSector() {
		return espacio;
	}
	
	public Sector getUnSector(int a,int b,int c) {
		return espacio[a][b][c];
	}
	
	public int getSectores() {
		int n=0;
		
		if(espacio!=null) {
			for(int i=0; i<espacio.length; i++)
				for(int j=0; j<espacio[i].length; j++)
					for(int k=0; k<espacio[i][j].length; k++)
						if(espacio[i][j][k]!=null)
							n++;
		}
		return n;
	}
	
	public boolean esAgujero(Coordenada c) {
		boolean devuelve=false;
		
		if(espacio[c.fil()][c.col()][c.dim()]==null)
			devuelve=true;
		return devuelve;
	}
}
