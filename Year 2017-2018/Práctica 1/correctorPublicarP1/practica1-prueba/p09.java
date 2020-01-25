/**
 * Se crea un terreno y se generan productos de distinto tipo en distintos
 * sitios del terreno. Se crea un habitante que recolecta piedras del terreno y
 * edifica con ellas. Se crea un mistico al que el habitante tributa. El
 * habitante vuelve a recolectar y tributar. Por pantalla se muestra el
 * resultado de cada accion realizada.
 * 
 * @author Nedyar
 */
public class p09 {
	public static void main(String[] args) {
		Terreno solarcico = new Terreno(8, 8);
		System.out.println("Se crea el solarcico\n");

		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				Producto cosica = new Producto(3, i, queEEso(i));
				System.out.println("Colocamos un " + queEEso(i) + " en " + i + ", " + j);
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
		System.out.println(pencho.getNombre() + " sale a faenar\n");

		for (int i = 0; i < 6; i++) {
			if (pencho.recolecta(solarcico, 3))
				System.out.println(pencho.getNombre() + " recoge un pedrolo");
			else
				System.out.println(pencho.getNombre() + " no encuentra naica");
		}

		System.out.println();

		System.out.println(pencho.getNombre() + ": \"Axo, pos voy hacer unos zulicos por aqui\"");

		for (int i = 0; i < 4; i++) {
			if (pencho.edifica("Zulico") != null)
				System.out.println(pencho.getClan() + " crea un zulo");
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
		Mistico ballesta = new Mistico();
		System.out.println(pencho.getNombre() + ": \"Ballesta, que me das si te paso en negro un poquico de esto?\"");
		System.out.println("Ballesta: \"Pos te doy: " + pencho.tributa(ballesta) + " euricos\"");
		System.out.println(pencho.getNombre() + ": \"Cawen dieh, que agarraico ereh pijo\"");
		System.out.println();
		System.out.println(
				pencho.getNombre() + " vuelve a salir a faenar porque el timador de Ballesta no nos da ni pa comer\n");

		for (int i = 0; i < 10; i++) {
			if (pencho.recolecta(solarcico, i % 6 + 1))
				System.out.println(pencho.getNombre() + " recoge un " + queEEso(i % 6 + 1));
			else
				System.out.println(pencho.getNombre() + " no encuentra naica");
		}

		System.out.println();
		System.out.println(pencho.getNombre() + ": \"Ballesta, que me das ahora?\"");
		System.out.println("Ballesta: \"Pos te doy: " + pencho.tributa(ballesta) + " euricos\"");
		System.out.println(pencho.getNombre() + ": \"Huevoh, sois tos unos ladrones, ijueputa\"");

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
