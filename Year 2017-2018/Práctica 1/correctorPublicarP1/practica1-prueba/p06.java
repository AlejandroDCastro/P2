



import java.util.ArrayList;

/**
 * Se crea una serie de productos, un terreno y un habitante. Se intenta colocar
 * productos en el terreno y que el habitante recolecte, de forma que unas veces
 * se consigue y otras no. Finalmente se muestra el contenido de la cesta del
 * habitante y su vigor actual.
 * 
 * @author Nedyar
 */
public class p06 {
	public static void main(String[] args) {
		ArrayList<Producto> paparajotes = creaProductos();
		Terreno solarcico = new Terreno(10, 10);

		Producto bicicleta = paparajotes.get(5);

		System.out.println();

		for (int i = 0; i < solarcico.getFilas(); i++)
			for (int j = i % 4; j < solarcico.getColumnas(); j += 3) {
				bicicleta = paparajotes.get((i * 10 + j) / 5 % 40);
				System.out.print("Colocamos " + bicicleta.getNombre() + " en " + i + ", " + j + "? ");
				solarcico = p06.intentaColocar(solarcico, bicicleta, i, j);
			}

		int x = -1, y = 4;
		bicicleta = paparajotes.get(39);
		System.out.print("Colocamos " + bicicleta.getNombre() + " en " + x + ", " + y + "? ");
		solarcico = p06.intentaColocar(solarcico, bicicleta, x, y);
		x = 1;
		y = 40;
		System.out.print("Colocamos " + bicicleta.getNombre() + " en " + x + ", " + y + "? ");
		solarcico = p06.intentaColocar(solarcico, bicicleta, x, y);
		x = 4;
		y = 0;
		System.out.print("Colocamos " + bicicleta.getNombre() + " en " + x + ", " + y + "? ");
		solarcico = p06.intentaColocar(solarcico, bicicleta, x, y);

		System.out.println();

		for (Producto limoncico : paparajotes) {
			System.out.print("+Axo, Paco, esta el " + limoncico.getNombre() + " sembrao? ");
			if (limoncico.getColocado())
				System.out.println("-Caro, pijo");
			else
				System.out.println("-Huevoh!");
		}

		System.out.println();
		Habitante pencho = new Habitante("Tio Pencho", 'H');
		for (int i = 0; i < 16; i++) {
			x = i % 6 + 1;
			System.out.print("Recolecta " + pencho.getNombre() + " un " + queEEso(x) + "? ");
			if (pencho.recolecta(solarcico, x))
				System.out.print("No ni na");
			else
				System.out.print("Axo que va");

			System.out.println(" - " + pencho.getVigor());
		}

		System.out.print("\n+" + pencho.getNombre() + ", que te has traio de la wertecica? \n-Pos ");
		for (int i = 0; i < pencho.getCesta().size(); i++) {
			Producto lechuga = pencho.getCesta().get(i);
			System.out.print("un " + lechuga.getNombre());
			if (i < pencho.getCesta().size() - 2)
				System.out.print(", ");
			if (i == pencho.getCesta().size() - 2)
				System.out.print(" y ");
		}
		System.out.println("\nEstoy to muerto axo, me queda na mas " + pencho.getVigor() + " de vigorcico");
	}

	private static Terreno intentaColocar(Terreno solarcico, Producto bicicleta, int x, int y) {
		if (solarcico.coloca(bicicleta, x, y))
			System.out.println("Pos caro");
		else
			System.out.println("Axo pos no");

		return solarcico;
	}

	private static ArrayList<Producto> creaProductos() {
		ArrayList<Producto> paparajotes = new ArrayList<Producto>();

		for (int i = 0; i < 40; i++) {
			String queEs = queEEso(i % 6 + 1);

			Producto zarangollo = new Producto(i * (i * 5 - 3) % 25 + i * 0.35452, i % 6 + 1, queEs + (i + 1));
			paparajotes.add(zarangollo);

			System.out.println("Mira que " + queEs + " de " + zarangollo.getPeso() + " Kilos ma bonico");
		}

		return paparajotes;
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
