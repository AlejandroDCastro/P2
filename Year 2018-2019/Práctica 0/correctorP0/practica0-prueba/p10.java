/**
 * @author Alejandro Castro Valero
 * Se invoca el método histograma pasándole por parámetro una cadena
 * que contiene subcadenas con repeticiones. Se muestra por pantalla
 * el resultado devuelto.
 */


public class p10 {

	public static void mostrar(String[][] m) {
		if(m != null) {
			System.out.print("{ ");
			for (int i=0; i<m.length-1; i++) {
				System.out.print("{''" + m[i][0] + "'', ''" + m[i][1] + "''},");
			}
			System.out.print("{''" + m[m.length-1][0] + "'', ''" + m[m.length-1][1] + "''}");
			System.out.println(" }");
		} else {
			System.out.println("La matriz está vacia");
		}
	}

	public static void main(String[] args) {
		String cadena = "flores en el campo flores en el prado donde las amapolas abundan entre todas las flores";
		p10.mostrar(Metodos.histograma(cadena, 2));
		System.out.println("-----------------");
		p10.mostrar(Metodos.histograma(cadena, 3));
		System.out.println("-----------------");
		p10.mostrar(Metodos.histograma(cadena, 4));
	}

}