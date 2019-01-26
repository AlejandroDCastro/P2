/**
* @author Luisa Mico
* Se invoca el metodo fusionSR pasandole por parametro dos arrays ordenados con elementos repetidos,
* mostrando por pantalla lo que devuelve el metodo.
*/

public class p02{

	public static void main(String[] args) {
                int v[] = {1, 2, 5, 5};
                int x[] = {2, 2, 3, 6};

                System.out.print( "cad1 = " );
                for( int i=0; i<v.length; i++ )
                  System.out.print(v[i]+" ");
                System.out.println();

                System.out.print( "cad2 = " );
                for( int i=0; i<x.length; i++ )
                  System.out.print( x[i]+" " );
                System.out.println();

                System.out.print("salida = ");
                int[] w = Metodos.fusionSR( v, x );
                if(w!=null)   
                 for( int i=0; i<w.length; i++ )
                   System.out.print( w[i]+" " );
                System.out.println();
         }
}
