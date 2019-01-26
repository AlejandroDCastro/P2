/**
* @author Alicia Garrido Alenda
* Se crea un jugador y un personaje. Se invoca agregaRasgo del personaje con distintos valores, pero no con todos los rasgos.
* Se invoca situaPersonaje del jugador con el personaje y lanza la excepcion de personaje incompleto. Se agregan rasgos
* al personaje hasta que se completa. Se vuelve a invocar situaPersonaje del jugador con el personaje.
* Se muestra el tablero del jugador por pantalla. 
*/
import java.util.*;
public class p03{

   private static String[] creaNombres1(){
     String[] names={"pendientes","gafas","barba","bigote"};
     return names;
   }

   private static String[] creaAtributos1(){
     String[] names={"true","rojas",null,"false"};
     return names;
   }
   private static String[] creaNombres2(){
     String[] names={"pelo","ojos","sombrero","cejas"};
     return names;
   }

   private static String[] creaAtributos2(){
     String[] names={"negro",null,"azules","false"};
     return names;
   }

   private static void creaRasgos(Personaje p,String[] n,String[] a){
     int j=0;
     for(int i=0;i<n.length&&i<a.length;i++){
      try{
        p.agregaRasgo(n[i],a[i]);
        System.out.println("agrega "+n[i]+" a "+p.getNombre());
      }
      catch(Exception e){
        System.out.println(e);
      }
     }
   }
   
   private static void situa(Jugador g,Personaje p,int f,int c){
     try{
       System.out.println("situa "+p.getNombre()+" en tablero de "+g.getNombre()+" -> "+g.situaPersonaje(f,c,p));
     }
     catch(Exception e){
       System.out.println(e);
     }
   }

   public static void main(String[] args){
     String[] names=p03.creaNombres1();
     String[] atribs=p03.creaAtributos1();
     Jugador gamer=new Jugador(new String("Bulma"),0,0);
     Personaje milk=new Personaje(new String("Chi-Chi"));
     p03.creaRasgos(milk,names,atribs);
     p03.situa(gamer,milk,5,5);
     names=p03.creaNombres2();
     atribs=p03.creaAtributos2();
     p03.creaRasgos(milk,names,atribs);
     p03.situa(gamer,milk,0,0);
     gamer.muestraTablero();
  }
}   
  
