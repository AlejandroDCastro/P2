/**
* @author Alicia Garrido
* Se crea un saco y se crean varias fichas. Se meten fichas en el saco
* hasta que se lanza la excepcion de FichaRepetida.
* Se muestra por pantalla los objetos y el resultado de cada accion
*/
import java.util.*;
public class p03{
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
      creada=p03.creaFicha(i,i+1);
      if(creada!=null)
        fichaje.add(creada);
    }
    for(int i=0;i<6;i++){
      creada=p03.creaFicha(i+1,i);
      if(creada!=null)
        fichaje.add(creada);
    }
    return fichaje;
  }
  public static void main(String []args){
    Saco nuevo=new Saco();
    ArrayList<Ficha> piezas=p03.creaFichas();
    try{
      for(int i=0;i<piezas.size();i++){
          System.out.println("Mete "+piezas.get(i)+"? -> "+nuevo.meteFicha(piezas.get(i)));
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
    System.out.println(nuevo);
  }
}