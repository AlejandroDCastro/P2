

/**
 * Se crea un terreno y se genera productos de tipo piedra en distintos sitios
 * del terreno. Se crea un habitante que recolecta piedras del terreno y edifica
 * con ellas. Se colocan en los margenes del terreno las edificaciones
 * realizadas. Por pantalla se muestra el resultado de cada accion realizada.
 * 
 * @author Nedyar
 */
public class p08 {
	public static void main(String[] args) {
		Terreno solarcico = new Terreno(6, 6);
		System.out.println("Se crea el solarcico\n");

		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				System.out.println("Colocamos pedrolo en " + i + ", " + j);
				Producto pedrolo = new Producto(6.5, 3, "Pedrolo");
				solarcico.coloca(pedrolo, i, j);
			}
		}

		System.out.println("\nWertecico del Tio Pencho:");
		for (int i = 0; i < solarcico.getFilas(); i++) {
			for (int j = 0; j < solarcico.getColumnas(); j++) {
				if (solarcico.consultaTipo(i, j) == 5)
					System.out.print("X");
				else if (solarcico.consultaTipo(i, j) == 3)
					System.out.print("P");
				else
					System.out.print(".");
			}
			System.out.println();
		}

		System.out.println();
		Habitante pencho = new Habitante("Tio Pencho", 'H');
		System.out.println(pencho.getNombre() + " sale a faenar\n");

		for (int i = 0; i < 20; i++) {
			if (pencho.recolecta(solarcico, 3))
				System.out.println(pencho.getNombre() + " recoge un pedrolo");
			else
				System.out.println(pencho.getNombre() + " no encuentra naica");
		}

		System.out.println();

		System.out.println(pencho.getNombre() + ": \"Axo, pos voy hacer una cerquica pal werto\"");

		
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++) {
				if (i == 5 || (i == 0 && j == 5) || j == 0 || j == 5) {
					Producto valla = pencho.edifica("X");
					if (valla != null) {
						solarcico.coloca(valla, i, j);
						System.out.println(pencho.getClan() + " crea una valla y la pone en " + i + ", " + j);
					}
				}
			}
		

		System.out.println("\nWertecico del Tio Pencho:");
		for (int i = 0; i < solarcico.getFilas(); i++) {
			for (int j = 0; j < solarcico.getColumnas(); j++) {
				if (solarcico.consultaTipo(i, j) == 5)
					System.out.print("X");
				else if (solarcico.consultaTipo(i, j) == 3)
					System.out.print("P");
				else
					System.out.print(".");
			}
			System.out.println();
		}
		
		System.out.println(pencho.getNombre() + ": \"Que bonica ma quedao, pijo\"");

	}
}
