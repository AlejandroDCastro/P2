/**
* @author Luisa Mico
* Se invoca el metodo kposicion pasandole por parametro un array sin elementos
* repetidos y varios valores para k, mostrando por pantalla lo que devuelve 
* el metodo en cada caso
*/

public class p05{

	public static void main(String[] args) {
                int w[] = {5, 6, 1, 2, 13, 8, 3, 4, 7, 11, 12};

                System.out.print( "w = ");
                for( int i=0; i<w.length; i++ )
                  System.out.print(w[i]+" ");
                System.out.println();
 
                System.out.println("2-posicion de w = "+Metodos.kposicion( w, 2 ));
                System.out.println("7-posicion de w = "+Metodos.kposicion( w, 7 ));
                System.out.println("9-posicion de w = "+Metodos.kposicion( w, 9 ));
         }
}
