// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class RauD extends Rau {
	String nombre;
	Rau[] progenie;
	
	public RauD(boolean b,int i,String n) {
		super(b,i);
		nombre=new String(n);
		progenie=new Rau[4];
	}
	
	public int interacciona(int i) {
		int devuelve=0;
		int[] energia=super.getEnergia();
		int posicion;
		
		if(energia!=null) {
			if(i<0) i=Math.abs(i);
			posicion=i%energia.length;
			if(posicion<energia.length && posicion>=0) {
				devuelve=energia[posicion];
				energia[posicion]=0;
			}
		}
		return devuelve;
	}
	
	public Rau reproduce() {
		RauD nuevo=new RauD(super.getSexo(),super.getPosiciones(),this.getNombre());
		int[] energia=super.getEnergia();
		int[] nuevaEnergia=nuevo.getEnergia();
		
		if(energia!=null)
			for(int i=0; i<energia.length; i++) {nuevaEnergia[i]=energia[i];}
		return nuevo;
	}
	
	public Rau reproduce(Rau r,String n) throws IncompatiblesException {
		RauD nuevo=null;
		int tamaño=0, ocupada=0;
		boolean sexo=false, mami=false;
		String sex=null, tipo=null;
		Rau[] aux=new Rau[progenie.length];
		int[] energiamadre=null, energiahijo=null;
		
		if(r.getSexo()) {sex=new String("hembra"); energiamadre=r.getEnergia(); mami=false;}  // Aquí se sabe además quien es la madre.
		else {sex=new String("macho"); energia=this.getEnergia(); mami=true;}
		if(r instanceof RauC) tipo=new String("RauC");
		if(r instanceof RauD) tipo=new String("RauD");
		
		if(r.getSexo()!=this.getSexo()) {
			if(r instanceof RauD)
				if(r.getEnergia()!=null && this.getEnergia()!=null && n!=null && progenie!=null) {
					tamaño=r.getPosiciones()+this.getPosiciones();
					if(r.nivelEnergetico()>this.nivelEnergetico()) sexo=r.getSexo();
					if(r.nivelEnergetico()<this.nivelEnergetico()) sexo=this.getSexo();
					nuevo=new RauD(sexo,tamaño,n);
					
					// Cómete a tu madre.
					energiahijo=nuevo.getEnergia();
					for(int i=0; i<energiamadre.length; i++) energiahijo[i]=energiamadre[i];
					
					// Vacíala.
					if(!mami) r.setEnergia();
					else this.setEnergia();
					
					// Que rica que estaba.
					for(int i=0; i<progenie.length; i++) {
						if(progenie[i]==null) {progenie[i]=nuevo; i=progenie.length+1;}
						else ocupada++;
					}
					if(ocupada==progenie.length) {
						for(int i=0; i<progenie.length; i++) aux[i]=progenie[i];
						progenie=new Rau[aux.length+3];
						for(int i=0; i<aux.length; i++) progenie[i]=aux[i];
						progenie[progenie.length-3]=nuevo;
					}
				}
		}
		else throw new IncompatiblesException(sex,tipo);
		return nuevo;
	}
/*	
	public String[] arbolGenealogico() {
		String[] familia=new String[1];
		Rau[] nuevo=null;
		
		if(progenie!=null && nombre!=null) {
			familia[0]=new String(nombre);
			crearFamilia(this);
		}
	}
	*/
	public Rau[] getProgenie() {
		return progenie;
	}
	
	public String getNombre() {
		return nombre;
	}
	
/*	public void crearFamilia(RauD c) {
		Rau[] progeni=c.getProgenie();
		
	}
*/
}
