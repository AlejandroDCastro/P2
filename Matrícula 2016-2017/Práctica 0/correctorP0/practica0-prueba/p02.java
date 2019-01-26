/**
* @author Alicia Garrido Alenda
* Se invoca el metodo primos pasando por parametro un numero mayor que 0,
* mostrando por pantalla lo que devuelve el metodo.
*/
public class p02{
  public static void main(String[] args){
    int i=73;
    int[] primitos=Metodos.primos(i);
    if(primitos!=null){
     System.out.print("primos entre 0 y "+i+" [");
     int j=0;
     for(;j<primitos.length-1;j++)
      System.out.print(primitos[j]+",");
     if(j<primitos.length)
      System.out.print(primitos[primitos.length-1]);
     System.out.println("]");
    }
  }
}