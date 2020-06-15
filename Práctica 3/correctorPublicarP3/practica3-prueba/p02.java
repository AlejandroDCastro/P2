/**
* @author Alicia Garrido Alenda
* Se crea un personaje y se invoca agregaRasgo con distintos valores de manera que a veces se lanza la excepcion
* RasgoNoValido, consultando si el personaje  esta completo.
* Se muestra el personaje creado por pantalla. 
*/
import java.util.*;
public class p02{

   private static String[] creaNombres(){
     String[] names={"pendientes","gafas","pecas","barba","bigote","pelo","ojos","gorra","trenzas","sombrero","cejas"};
     return names;
   }

   private static String[] creaAtributos(){
     String[] names={null,"rojas",null,"false","poblado",null,"azules",null,"finas","true","false"};
     return names;
   }

   private static void creaRasgos(Personaje p,String[] n,String[] a){
     int j=0;
     for(int i=0;i<n.length&&i<a.length;i++){
      try{
        p.agregaRasgo(n[i],a[i]);
        System.out.println(p.getNombre()+" esta completo? -> "+p.completo());
      }
      catch(Exception e){
        System.out.println(e);
      }
     }
   }

   public static void main(String[] args){
     String[] names=p02.creaNombres();
     String[] atribs=p02.creaAtributos();
     Personaje krilin=new Personaje(new String("Krilin"));
     p02.creaRasgos(krilin,names,atribs);
     System.out.println(krilin);
  }
}   
  
