

/**
 * Se crea un terreno y se generan productos de distinto tipo en distintos
 * sitios del terreno. Se crea un habitante que recolecta distintos productos
 * del terreno y se vigoriza sin cocinar. Por pantalla se muestra el resultado
 * de cada accion realizada.
 * 
 * @author Nedyar
 */
public class p10 {
	public static void main(String[] args) {
		Terreno solarcico = new Terreno(8, 8);
		System.out.println("Se crea el solarcico\n");

		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				int k = i;
				if (i == 4)
					k = 1;
				Producto cosica = new Producto(3, k, queEEso(k));
				System.out.println("Colocamos un " + queEEso(k) + " en " + i + ", " + j);
				solarcico.coloca(cosica, i, j);
			}
		}

		System.out.println("\nWertecico del Tio Pencho:");
		for (int i = 0; i < solarcico.getFilas(); i++) {
			for (int j = 0; j < solarcico.getColumnas(); j++) {
				if (solarcico.consultaTipo(i, j) == 1)
					System.out.print("V");
				else if (solarcico.consultaTipo(i, j) == 2)
					System.out.print("A");
				else if (solarcico.consultaTipo(i, j) == 3)
					System.out.print("P");
				else if (solarcico.consultaTipo(i, j) == 4)
					System.out.print("M");
				else if (solarcico.consultaTipo(i, j) == 5)
					System.out.print("E");
				else if (solarcico.consultaTipo(i, j) == 6)
					System.out.print("F");
				else
					System.out.print(".");
			}
			System.out.println();
		}

		System.out.println();
		Habitante pencho = new Habitante("Tio Pencho", 'H');

		System.out.println(pencho.getNombre() + " se intenta comer un Conejico y se queda a: "
				+ pencho.vigoriza("Conejico") + " del maximo");
		System.out.println(pencho.getNombre() + ": \"Hostie, que no tengo naica\"");
		System.out.println();

		System.out.println(pencho.getNombre() + " sale a faenar");

		for (int i = 0; i < 10; i++) {
			if (pencho.recolecta(solarcico, i % 6 + 1))
				System.out.println(pencho.getNombre() + " recoge un " + queEEso(i % 6 + 1));
			else
				System.out.println(pencho.getNombre() + " no encuentra naica");
		}

		System.out.println("\nWertecico del Tio Pencho:");
		for (int i = 0; i < solarcico.getFilas(); i++) {
			for (int j = 0; j < solarcico.getColumnas(); j++) {
				if (solarcico.consultaTipo(i, j) == 1)
					System.out.print("V");
				else if (solarcico.consultaTipo(i, j) == 2)
					System.out.print("A");
				else if (solarcico.consultaTipo(i, j) == 3)
					System.out.print("P");
				else if (solarcico.consultaTipo(i, j) == 4)
					System.out.print("M");
				else if (solarcico.consultaTipo(i, j) == 5)
					System.out.print("E");
				else if (solarcico.consultaTipo(i, j) == 6)
					System.out.print("F");
				else
					System.out.print(".");
			}
			System.out.println();
		}
		
		System.out.println();

		System.out.println(pencho.getNombre() + " se intenta comer un Conejico y se queda a: "
				+ pencho.vigoriza("Conejico") + " del maximo");
		System.out.println(pencho.getNombre()
				+ ": \"Axo, que agustico me quedao, voy a echarme un ratico que menuda solanera esta cayendo\"");
		System.out.println(pencho.getNombre() + " se intenta comer un Tomatico y se queda a : "
				+ pencho.vigoriza("Tomatico") + " del maximo");
		System.out.println(pencho.getNombre() + " se intenta comer un Pedrolo y se queda a : "
				+ pencho.vigoriza("Pedrolo") + " del maximo");
		System.out.println(pencho.getNombre() + ": \"Hostie, pensaba que era un pistacho revenio, pero no\"");

	}

	private static String queEEso(int i) {
		String queEs = "";
		switch (i) {
		case 1:
			queEs = "Tomatico";
			break;
		case 2:
			queEs = "Conejico";
			break;
		case 3:
			queEs = "Pedrolo";
			break;
		case 4:
			queEs = "Palico";
			break;
		case 5:
			queEs = "Zulo";
			break;
		case 6:
			queEs = "Cosico";
			break;
		}
		return queEs;
	}
}
