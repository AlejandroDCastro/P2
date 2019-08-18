/**
* @author Alicia Garrido
* Se crea un jugador no valido, un jugador valido, un saco, un tablero y varias fichas. Se meten 
* fichas en el saco, el jugador roba varias y juega una de ellas en el tablero.
* Se muestra por pantalla los objetos y el resultado de cada accion
*/
import java.util.*;
public class p04{
  private static Ficha creaFicha(int i,int j){
    Ficha nueva=null;
      try{
        nueva=new Ficha(i,j);
      }
      catch(Exception e){
        System.out.println(e);
      }
     return nueva;
  }
  private static ArrayList<Ficha> creaFichas(){
    ArrayList<Ficha> fichaje=new ArrayList<>();
    Ficha creada=null;
    for(int i=0;i<6;i++){
      creada=p04.creaFicha(i,i+1);
      if(creada!=null)
        fichaje.add(creada);
    }
    return fichaje;
  }
  public static void main(String []args){
    Saco nuevo=new Saco();
    Tablero mesa=new Tablero();
    ArrayList<Ficha> piezas=p04.creaFichas();
    ArrayList<Jugador> players=new ArrayList<>();
    try{
      players.add(new Jugador("Eragorn"));
      players.add(new Jugador(""));
    }
    catch(Exception e){
      System.out.println(e);
    }
    try{
      for(int i=0;i<piezas.size();i++){
          System.out.println("Mete "+piezas.get(i)+"? -> "+nuevo.meteFicha(piezas.get(i)));
      }
      if(players.size()>0){
        for(int i=0;i<4;i++){
          System.out.println(players.get(0).getNombre()+" roba? -> "+players.get(0).roba(nuevo));
          System.out.println("El saco -> "+nuevo);
        }
        System.out.println(players.get(0).getNombre()+" juega? -> "+players.get(0).juega(mesa));
        System.out.println("Tablero -> "+mesa);
        System.out.println("Player -> "+players.get(0));
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}