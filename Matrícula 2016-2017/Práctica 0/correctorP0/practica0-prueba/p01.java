/**
* @author Alicia Garrido Alenda
* Se invoca el metodo divisores pasandole por parametro un numero mayor que 0,
* mostrando por pantalla lo que devuelve el metodo.
*/

public class p01{
  public static void main(String[] args){
    int i=213;
    int[] divisor=Metodos.divisores(i);
    if(divisor!=null){
     System.out.print("divisores de "+i+" [");
     int j=0;
     for(;j<divisor.length-1;j++)
      System.out.print(divisor[j]+",");
     if(j<divisor.length)
      System.out.print(divisor[divisor.length-1]);
     System.out.println("]");
    }
  }
}