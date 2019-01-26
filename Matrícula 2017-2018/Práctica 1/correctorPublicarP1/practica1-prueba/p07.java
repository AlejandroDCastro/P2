



import java.util.ArrayList;

/**
 * Se crea una serie de productos, un terreno y un habitante. Se colocan los
 * productos en el terreno. El habitante va realizando estudio de su cesta y
 * recolectando el tipo de producto indicado mientras queden productos
 * recolectables en el terreno. Finalmente se muestra el contenido de la cesta
 * del habitante, su vigor actual y el tipo de producto que queda en cada
 * parcela del terreno.
 * 
 * @author Nedyar
 */
public class p07 {
	public static void main(String[] args) {
		ArrayList<Producto> paparajotes = creaProductos();
		Terreno solarcico = new Terreno(10, 10);

		Producto bicicleta = paparajotes.get(5);

		for (int i = 0; i < solarcico.getFilas(); i++)
			for (int j = i % 4; j < solarcico.getColumnas(); j += 3) {
				bicicleta = paparajotes.get((i * 10 + j) / 5 % 40);
				solarcico.coloca(bicicleta, i, j);
			}

		Producto pedrolo = new Producto(100, 3, "Un pedrolo");
		Habitante pencho = new Habitante("Tio Pencho", 'H');
		pencho.getCesta().add(pedrolo);
		for (int i = 0; i < 16; i++) {
			int x = pencho.estudio();
			System.out.print("Recolecta " + pencho.getNombre() + " un " + queEEso(x) + "? ");
			if (pencho.recolecta(solarcico, x))
				System.out.println("No ni na");
			else
				System.out.println("Axo que va");
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

		System.out.println("\nHUERTO EL LIMONCICO DE LA RAMONA:");
		for (int i = 0; i < solarcico.getFilas(); i++) {
			for (int j = 0; j < solarcico.getColumnas(); j++) {
				System.out.print(solarcico.consultaTipo(i, j) + " ");
			}
			System.out.println();
		}
	}

	private static ArrayList<Producto> creaProductos() {
		ArrayList<Producto> paparajotes = new ArrayList<Producto>();

		for (int i = 0; i < 100; i++) {
			String queEs = queEEso(i % 6 + 1);

			Producto zarangollo = new Producto(i * (i * 5 - 3) % 25 + i * 0.35452, i % 6 + 1, queEs + (i + 1));
			paparajotes.add(zarangollo);
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
