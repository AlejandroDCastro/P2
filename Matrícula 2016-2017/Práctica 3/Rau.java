// DNI 20521823 CASTRO VALERO, ALEJANDRO
public abstract class Rau {
	boolean sexo;
	int[] energia;
	
	public Rau(boolean b,int i) {
		sexo=b;
		if(i>0) energia=new int[i];
		else energia=new int[9];
	}
	
	public boolean come(int i,int j) {
		boolean devuelve=false;
		
		if(energia!=null && i<energia.length && i>=0)
			if(j>0) {energia[i]=energia[i]+j; devuelve=true;}
		return devuelve;
	}
	
	public abstract Rau reproduce();
	
	abstract Rau reproduce(Rau r,String n) throws IncompatiblesException;
	
	public abstract int interacciona(int i);
	
	public int nivelEnergetico() {
		int devuelve=0;
		
		if(energia!=null)
			for(int i=0; i<energia.length; i++) devuelve=devuelve+energia[i];
		return devuelve;
	}
	
	public boolean getSexo() {
		return sexo;
	}
	
	public int[] getEnergia() {
		return energia;
	}
	
	public int getPosiciones() {
		return energia.length;
	}
	
	public void setEnergia() {
		for(int i=0; i<energia.length; i++) energia[i]=0;
	}
	
	public void setEnergia2(int a) {
		for(int i=0; i<a; i++) energia[i]=0;
	}

}
