/**
* @author Alicia Garrido
* Se invoca el metodo nomultiplos pasando por parametros datos correctos 
* mostrando por pantalla el resultado devuelto.
* Se invoca el metodo nomultiplos pasando por parametros datos incorrectos 
* mostrando por pantalla el resultado devuelto.
*/
public class p01{
  public static void main(String[] args){
     int[] ar=Metodos.nomultiplos(40,-2,5);
     if(ar!=null){
       System.out.print("{");
       for(int i=0;i<ar.length-1;i++)
         System.out.print(ar[i]+", ");
       if(ar.length>0)
         System.out.print(ar[ar.length-1]);
       System.out.print("}");
     }
     System.out.println();
     ar=Metodos.nomultiplos(40,2,5);
     if(ar!=null){
       System.out.print("{");
       for(int i=0;i<ar.length-1;i++)
         System.out.print(ar[i]+", ");
       if(ar.length>0)
         System.out.print(ar[ar.length-1]);
       System.out.print("}");
     }
     System.out.println();
  }
}