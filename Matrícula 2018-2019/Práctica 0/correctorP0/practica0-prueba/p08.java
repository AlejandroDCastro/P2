/**
 * @author Alejandro Castro Valero
 * Se invoca el método caballo con posiciones iniciales próximas al centro
 * del tablero.
 */


public class p08 {

	public static void main(String[] args) {
		Metodos.caballo(4, 4);
		System.out.println("-----------");
		Metodos.caballo(3, 4);
		System.out.println("-----------");
		Metodos.caballo(4, 3);
		System.out.println("-----------");
		Metodos.caballo(4, 5);
		System.out.println("-----------");
		Metodos.caballo(5, 4);
		System.out.println("-----------");
		Metodos.caballo(2, 8);
		System.out.println("-----------");
		Metodos.caballo(1, 9);
	}

}