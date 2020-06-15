
public class p06acv {

	public static void main(String[] args) {
		int[] m1 = {1,2,3}, m3 = null;
		int[] m2 = {2,1,4,3}, m4 = new int[0];
		int[][] matriz = null;
		
		matriz = Metodos.matriz(m1, m2);
		comprobacion(matriz);
		
		// distinto orden
		System.out.print("\n");
		matriz = Metodos.matriz(m2, m1);
		comprobacion(matriz);
		
		System.out.print("\n");
		matriz = Metodos.matriz(m1, m3);
		comprobacion(matriz);
		
		System.out.print("\n");
		matriz = Metodos.matriz(m1, m4);
		comprobacion(matriz);
		
		System.out.print("\n");
		matriz = Metodos.matriz(m3, m4);
		comprobacion(matriz);
	}
	
	
	public static void comprobacion(int [][] m) {
		if(m != null) {
			for(int i=0; i<m.length; i++) {
				for(int j=0; j<m[i].length; j++) {
					System.out.print(m[i][j] + " ");
				}
				System.out.print("\n");
			}
		}
		else
			System.out.println("ERROR: Parametros incorrectos.");
	}

}
