/**
* @author Alicia Garrido
* Se crea un tablero, se consulta su inicio y final. Se crean varias fichas y se colocan en el tablero,
* de manera que con alguna se lanza la excepcion de JugadaIncorrecta.
* Se muestra por pantalla los objetos y el resultado de cada accion
*/
import java.util.*;
public class p02{
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
      creada=p02.creaFicha(i,i+1);
      if(creada!=null)
        fichaje.add(creada);
    }
    fichaje.add(p02.creaFicha(2,2));
    return fichaje;
  }
  public static void main(String []args){
    Tablero nuevo=new Tablero();
    ArrayList<Ficha> piezas=p02.creaFichas();
    System.out.println("Tablero vacio: inicio -> "+nuevo.getInicio()+"; fin -> "+nuevo.getFin());
    for(int i=0;i<piezas.size();i++){
      try{
        System.out.println("Coloca "+piezas.get(i)+"? -> "+nuevo.coloca(piezas.get(i),1));
        System.out.println("Tablero: inicio -> "+nuevo.getInicio()+"; fin -> "+nuevo.getFin());
      }
      catch(Exception e){
        System.out.println(e);
      }
    }
    System.out.println(nuevo);
  }
}