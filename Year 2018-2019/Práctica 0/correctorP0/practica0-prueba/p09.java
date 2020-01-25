/**
 * @author Alejandro Castro Valero
 * Se invoca el método multrusa pasándole por parámetro valores correctos. Se
 * muestra por pantalla el resultado devuelto.
 */


public class p09 {

	public static void mostrar(int[] v) {
		System.out.print("{");
		for(int i=0; i<v.length-1; i++) {
			System.out.print(v[i] + ", ");
		}
		System.out.print(v[v.length-1]);
		System.out.println("}");
	}

	public static void main(String[] args) {
		System.out.println("ando: 2, ador: 40");
		p09.mostrar(Metodos.multrusa(2, 40));
		System.out.println("ando: 12, ador: 37");
		p09.mostrar(Metodos.multrusa(12, 37));
		System.out.println("ando: 7, ador: 93");
		p09.mostrar(Metodos.multrusa(7, 93));
	}

}