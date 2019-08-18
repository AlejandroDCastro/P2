/**
* @author Alicia Garrido
* Se crea una ficha correcta, su inversa y se muestran ambas por pantalla.
* Se intenta crear una ficha incorrecta.
*/
public class p01{
  public static void main(String []args){
    try{
      Ficha normal=new Ficha(3,4);
      Ficha inversa=normal.creaInversa();
      System.out.println("Ficha correcta: "+normal);
      System.out.println("Su inversa: "+inversa);
      Ficha incorrecta=new Ficha(5,7);
      System.out.println("Ficha incorrecta: "+incorrecta);
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}