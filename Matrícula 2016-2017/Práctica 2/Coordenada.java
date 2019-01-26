// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Coordenada {
	private int fila;
	private int columna;
	private int dimension;
	
	public Coordenada(int i,int j,int k) {
		fila=i;
		columna=j;
		dimension=k;
	}
	
	public int[] getCoordenadas() {
		int []val=null;
		
		val=new int[3];
		val[0]=fila;
		val[1]=columna;
		val[2]=dimension;
		return val;
	}
	
	public int fil() {
		return fila;
	}
	
	public int col() {
		return columna;
	}
	
	public int dim() {
		return dimension;
	}
}