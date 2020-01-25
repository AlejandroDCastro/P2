/**
 * @author Alejandro Castro Valero
 * Se invoca el método Collatz con varios números mayores que 0. Se muestra por
 * pantalla el resultado devuelto.
 */


public class p06 {

	public static void mostrar(int[] v) {
		System.out.print("{");
		for(int i=0; i<v.length-1; i++) {
			System.out.print(v[i] + ", ");
		}
		System.out.print(v[v.length-1]);
		System.out.println("}");
	} 

	public static void main(String[] args) {
		p06.mostrar(Metodos.Collatz(5));
		p06.mostrar(Metodos.Collatz(10));
		p06.mostrar(Metodos.Collatz(15));
		p06.mostrar(Metodos.Collatz(18));
		p06.mostrar(Metodos.Collatz(20));
	}

}