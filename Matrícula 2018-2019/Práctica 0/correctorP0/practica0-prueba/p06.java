/**
 * @author Alejandro Castro Valero
 * Se invoca el método Collatz con varios números mayores que 0. Se muestra por
 * pantalla el resultado devuelto.
 */


public class p06 {

	public static void main(String[] args) {
		System.out.println(Metodos.Collatz(5));
		System.out.println(Metodos.Collatz(10));
		System.out.println(Metodos.Collatz(15));
		System.out.println(Metodos.Collatz(18));
		System.out.println(Metodos.Collatz(20));
	}

}