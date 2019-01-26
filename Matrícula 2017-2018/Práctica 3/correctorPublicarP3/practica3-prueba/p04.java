/**
* @author Alicia Garrido Alenda
* Se crea un jugador y un personaje. Se invoca agregaRasgo del personaje con todos los rasgos.
* Se invoca situaPersonaje del jugador con el personaje. Se invoca comienzaJuego del jugador que lanza la excepcion de tablero incompleto.
* Se muestra el tablero del jugador por pantalla. 
*/
import java.util.*;
public class p04{

   private static String[] creaNombres(){
     String[] names={"pendientes","gafas","barba","bigote","pelo","ojos","sombrero"};
     return names;
   }

   private static String[] creaAtributos(){
     String[] names={"true","rojas",null,"false","negro",null,"azules"};
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
   
   private static void empieza(Jugador g,Personaje p,int f,int c){
     try{
       System.out.println("situa "+p.getNombre()+" en tablero de "+g.getNombre()+" -> "+g.situaPersonaje(f,c,p));
       System.out.println("empieza "+g.getNombre()+" eligiendo (5,5)  -> "+g.comienzaJuego(5,5));
     }
     catch(Exception e){
       System.out.println(e);
     }
   }

   public static void main(String[] args){
     String[] names=p04.creaNombres();
     String[] atribs=p04.creaAtributos();
     Jugador gamer=new Jugador(new String("Bulma"),1,2);
     Personaje milk=new Personaje(new String("Chi-Chi"));
     p04.creaRasgos(milk,names,atribs);
     p04.empieza(gamer,milk,0,1);
     gamer.muestraTablero();
  }
}   
  
