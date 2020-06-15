
public class p07acv {

	public static void main(String[] args) {
		String[][] matriz = null;
		
		String cad0 = "3 4#Alicia por favor no#me SUSPENDAS que soy#buena PERSONA carita sonriente:)";
		matriz = Metodos.procesa(cad0);
		comprobacion(matriz);
		
		String cad1 = "2 10#Mi comida favorita son los tallarines con atun o carbonara.#Tambien me gusta el hervido con pechuga empanada con queso.";
		matriz = Metodos.procesa(cad1);
		comprobacion(matriz);
		
		String cad2 = "2 0#hola hola#a mis seguidores";
		matriz = Metodos.procesa(cad2);
		comprobacion(matriz);
		
		String cad3 = "2 4#Santa Maria madre#de DIOS";
		matriz = Metodos.procesa(cad3);
		comprobacion(matriz);
		
		String cad4 = "4 3#Sistemas#Distribuidos es interesante#pero #es estresante";
		matriz = Metodos.procesa(cad4);
		comprobacion(matriz);
				
	}
	
	public static void comprobacion(String[][] matriz) {
		if(matriz != null  &&  matriz.length>0  &&  matriz[0].length>0) {
			for(int i=0; i<matriz.length; i++)
				for(int j=0; j<matriz[i].length; j++)
					System.out.print(matriz[i][j] + " ");
			System.out.print("\n");
		}
		else
			System.out.println("Error: Las dimensiones deben ser mayores que 0.");
	}

}
