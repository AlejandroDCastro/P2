/**
* @author Alicia Garrido Alenda
* Se invoca el metodo extrae pasandole por parametro una cadena con distintas cadenas separadas por ;
* de las cuales algunas contienen numeros y otras no, mostrando por pantalla lo que devuelve el metodo
*/
public class p04{
  public static void main(String[] args){
    String s=new String("martes-20;enero;3789-KYJ;parte amistoso;jerifonte;kh77;somnolencia;9192048893029");
    String[] cadenas=Metodos.extrae(s);
    if(cadenas!=null){
     System.out.print("cadenas sin numeros [");
     int j=0;
     for(;j<cadenas.length-1;j++)
      System.out.print(cadenas[j]+", ");
     if(j<cadenas.length)
      System.out.print(cadenas[cadenas.length-1]);
     System.out.println("]");
    }
  }
}