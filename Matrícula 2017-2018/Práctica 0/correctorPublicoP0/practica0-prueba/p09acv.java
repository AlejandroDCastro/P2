
public class p09acv {

	public static void main(String[] args) {
		int[] ordenado = null, vector = {1,3,3,5,5,6,6,6,9,14,26,88}, vector1 = null;
		int[] vector2 = {8,5,6,0,1,7,3,4,6,8,2,1,55};
		int num = 6;
		
		ordenado = Metodos.elimina(vector, num);
		comprobacion(ordenado);
		
		ordenado = Metodos.elimina(vector, 4);
		comprobacion(ordenado);
		
		ordenado = Metodos.elimina(vector1, num);
		comprobacion(ordenado);
		
		ordenado = Metodos.elimina(vector2, 1);
		comprobacion(ordenado);
		
	}
	
	
	public static void comprobacion(int[] m) {
		if(m != null  &&  m.length > 0) {
			for(int i=0; i<m.length; i++) {
				System.out.print(m[i] + " ");
			}
			System.out.print("\n");
		}
		else
			System.out.println("Error: Parametros incorrectos.");
	}

}
