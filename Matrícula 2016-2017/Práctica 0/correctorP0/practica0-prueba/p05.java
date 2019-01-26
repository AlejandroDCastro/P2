/**
* @author Alicia Garrido Alenda
* Se invoca el metodo hexadecimal pasandole por parametro un numero mayor que 0, y se muestra
* por pantalla el resultado que devuelve.
*/
public class p05{
  public static void main(String[] args){
    int i=3975;
    char[] hex=Metodos.hexadecimal(i);
    if(hex!=null){
     System.out.print(i+"en hexadecimal es ");
     int j=0;
     for(;j<hex.length;j++)
      System.out.print(hex[j]);
     System.out.println();
    }
  }
}