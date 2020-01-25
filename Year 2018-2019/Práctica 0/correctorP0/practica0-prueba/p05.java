/**
* @author Alicia Garrido
* Se invoca el metodo comunes pasandole por parametro dos arrays con el mismo numero de 
* elementos, ordenados, en los que hay elementos comunes. Se muestra por pantalla el resultado devuelto.
*/
public class p05{
  public static void main(String[] args){
    int[] v1={1,3,4,5,18,30,67,902};
    int[] v2={3,5,7,8,67,92,902};
    int[] ar=Metodos.comunes(v1,v2);
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