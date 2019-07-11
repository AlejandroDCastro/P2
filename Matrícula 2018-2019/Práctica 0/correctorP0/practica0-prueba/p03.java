/**
* @author Alicia Garrido
* Se invoca el metodo prefijos con una cadena que contiene caracteres
* en mayusculas y minusculas. Se muestra por pantalla el resultado devuelto.
*/
public class p03{
  public static void main(String[] args){
     String[] ar=Metodos.prefijos(new String("OctopussY"));
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