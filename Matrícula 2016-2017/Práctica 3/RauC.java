// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class RauC extends Rau {
	String[] parejas;
	
	public RauC(boolean b,int i,int j) {
		super(b,i);
		if(j>3) parejas=new String[j];
		else parejas=new String[3];
	}
	
	public int interacciona(int i) {
		int devuelve=0, posicion=0;
		int[] energia=super.getEnergia();
		
		if(energia!=null) {
			if(i>0) {
				posicion=i%energia.length;
				if(posicion<energia.length && posicion>=0) {
					if(energia[posicion]>0) devuelve=(-1)*energia[posicion];
					else devuelve=energia[posicion];
					energia[posicion]=0;
				}
			}
		}
		return devuelve;
	}
	
	public Rau reproduce() {
		RauC nuevo=new RauC(super.getSexo(),super.getPosiciones(),parejas.length);
		int[] energia=super.getEnergia();
		int[] nuevaEnergia=nuevo.getEnergia();
		
		if(energia!=null)
			for(int i=0; i<energia.length; i++) {nuevaEnergia[i]=energia[i];}
		return nuevo;
	}
	
	public Rau reproduce(Rau r,String n) throws IncompatiblesException {
		// Primero.
		RauD hijoD=null;
		int ocupadas=0, posiciones=0;
		String[] aux=new String[parejas.length];
		int[] energiahijo=null, energiapapa=null;
		boolean papa=false;
		String sexo=null, tipo=null;
		// Segundo.
		RauC hijoC=null;
		int tamañoenergia=0, tamañoparejas=0;
		boolean sex=false;
		int[] energiamama=null;
		Rau devuelve=null;
		
		if(r.getSexo()!=this.getSexo()) {
			
			// Aquí se sabe quien es el papa. Y los parámetros de la excepción a lanzar, en dicho caso.
			if(!r.getSexo()) {papa=true;  energiapapa=r.getEnergia();  sexo=new String("macho");}
			else {papa=false;  energiapapa=this.getEnergia();  sexo=new String("hembra");}
			
			if(r instanceof RauD && parejas!=null) {
				
				
				// Aquí averiguamos el tipo del Rau pasado por parámetro.
				if(r instanceof RauC) tipo=new String("RauC");
				else tipo=new String("RauD");
				
				// Añadimos al array de parejas o lo modificamos.
				for(int i=0; i<parejas.length; i++) {
					if(parejas[i]==null) {parejas[i]=((RauD) r).getNombre(); i=parejas.length+2;}
					else ocupadas++;
				}
				if(ocupadas==parejas.length) {
					for(int i=0; i<parejas.length; i++) aux[i]=parejas[i];
					parejas=new String[aux.length+3];
					for(int i=0; i<aux.length; i++) parejas[i]=aux[i];
					parejas[parejas.length-3]=((RauD) r).getNombre();
				}
				if(r.getSexo()) {
					if(energiapapa!=null && n!=null) {
						hijoD=new RauD(r.getSexo(),r.getPosiciones(),n);
						
						// Comer papa. Averiguar posiciones que me como.
						energiahijo=hijoD.getEnergia();
						for(int i=0; i<energiahijo.length; i++) {
							energiahijo[i]=energiapapa[i];
							posiciones++;
						}
						
						// Vaciar papi.
						if(papa) r.setEnergia2(posiciones);
						else this.setEnergia2(posiciones);
						
						devuelve=hijoD;
					}
				}
				else throw new IncompatiblesException(sexo,tipo);
			}
			
			if(r instanceof RauC && parejas!=null) {
				
				// Tamaño energía. Y array de la mama.
				if(papa) {tamañoenergia=r.getPosiciones();  energiamama=this.getEnergia();}
				else {tamañoenergia=this.getPosiciones();  energiamama=r.getEnergia();}
				// Sexo
				if(r.nivelEnergetico()<this.nivelEnergetico()) sex=r.getSexo();
				if(r.nivelEnergetico()>this.nivelEnergetico()) sex=this.getSexo();
				if(r.nivelEnergetico()==this.nivelEnergetico()) sex=true;
				// Tamaño parejas
				if(papa) tamañoparejas=this.getTamaño();
				else tamañoparejas=((RauC) r).getTamaño();
				// Créalo.
				hijoC=new RauC(sex,tamañoenergia,tamañoparejas);
				
				// Comer mama.
				energiahijo=hijoC.getEnergia();
				for(int i=0; i<energiahijo.length; i++) 
					energiahijo[i]=energiamama[i];
				// Vacíala.
				if(papa) this.setEnergia();
				else r.setEnergia();
				
				devuelve=hijoC;
			}
		}
		return devuelve;
	}
	
	public String[] getParejas() {
		return parejas;
	}
	
	public int getTamaño() {
		return parejas.length;
	}

}
