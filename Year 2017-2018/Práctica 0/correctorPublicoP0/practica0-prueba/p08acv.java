
public class p08acv {

	public static void main(String[] args) {
		int cadmen = -1;
		String cadena = "patata";
		
		String[] vector0 = {"PATATA","ganzmia","PoRtADA","ANImalada","paTATA","cogollo","wakamole","PEZ","QUESO"};
		cadmen = Metodos.borra(vector0,cadena);
		comprobacion(vector0,cadmen,cadena);
		
		String[] vector1 = {null,null,null,null,null};
		cadmen = Metodos.borra(vector1,cadena);
		comprobacion(vector1,cadmen,cadena);
		
		String[] vector2 = new String[0];
		cadmen = Metodos.borra(vector2,cadena);
		comprobacion(vector2,cadmen,cadena);
		
		String[] vector3 = {null,"ganzmia",null,"ANImalada","paTATA",null,"wakamole","PEZ","QUESO"};
		cadmen = Metodos.borra(vector3,cadena);
		comprobacion(vector3,cadmen,cadena);
		
		String cadena1 = null;
		String[] vector4 = {null,"ganzmiaa",null,"ANImalada","paTATA",null,"wakamole","PEZ","QUESO"};
		cadmen = Metodos.borra(vector4,cadena1);
		comprobacion(vector4,cadmen,cadena1);
		
		String[] vector5 = null;
		cadmen = Metodos.borra(vector5,cadena);
		comprobacion(vector5,cadmen,cadena);
		
	}
	
	
	public static void comprobacion(String[] vector, int cadmen, String cadena) {
		if (vector != null  &&  cadena != null) {
			System.out.println("Las cadenas menores a " + cadena + " son " + cadmen);
			System.out.print("Cadena: ");
			
			for(int i=0; i<vector.length; i++)
				System.out.print(vector[i] + " ");
			System.out.print("\n");
		}
		else
			System.out.println("Error: Los parametros pasados al metodo son incorrectos.");
		
		cadmen = -1;
	}

}
