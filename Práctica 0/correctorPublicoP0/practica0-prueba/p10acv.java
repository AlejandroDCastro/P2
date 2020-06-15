
public class p10acv {

	public static void main(String[] args) {
		boolean[] vector = {true,true,false,true,false,false,true}, vector2 = {true,false,true,false,true,false,true,true};
		boolean[] vector3 = null, vector4 = new boolean[0], vector5 = {true,true,false,false};
		int cambios = 0;
		
		cambios = Metodos.organiza(vector);
		comprobacion(cambios,vector);
		
		cambios = Metodos.organiza(vector2);
		comprobacion(cambios,vector2);
		
		cambios = Metodos.organiza(vector3);
		comprobacion(cambios,vector3);
		
		cambios = Metodos.organiza(vector4);
		comprobacion(cambios,vector4);
		
		cambios = Metodos.organiza(vector5);
		comprobacion(cambios,vector5);
	}
	
	
	public static void comprobacion(int c, boolean[] v) {
		if(v != null  &&  v.length > 0) {
			System.out.println("El numero de cambios en el array han sido " + c);
			System.out.print("Array: ");
			for(int i=0; i<v.length; i++) {
				System.out.print(v[i] + " ");
			}
			System.out.print("\n");
		}
		else
			System.out.println("Error: Parametros incorrectos.");
	}

}
