/**
* @author Alicia Garrido Alenda
* Se crea una huerta y varias plantas. Se invoca planta de la huerta con 
* las plantas. Se invoca abona de la huerta para las distintas plantas.
* Se invoca getHuerto de la huerta.
* Se muestra por pantalla el resultado de cada accion.
*/
import java.text.*;
import java.util.*;
public class p05{
  private static ArrayList<Planta> creaSemillas(){
    String[] fruta={"ciruela","tomate","Papaya","fresa","naranja","mandarina","platano"};
    String[] planta={"ciruelo","tomatera","carica","fragaria","naranjo","mandarino","platanero"};
    ArrayList<Planta> plantas=new ArrayList<>();
    for(int i=0;i<fruta.length&&i<planta.length;i++)
      plantas.add(new Planta(planta[i],fruta[i],i));
    for(int i=0;i<fruta.length/2&&i<planta.length;i++)
      plantas.add(new Planta(planta[i],fruta[i],i));
    return plantas;
  }

  public static void main(String[] args){
    ArrayList<Planta> plantitas=p05.creaSemillas();
    Huerta finca=new Huerta(4,0);
    boolean change=false;
    for(int i=0;i<plantitas.size();i++){
      change=finca.planta(plantitas.get(i));
      System.out.println("Se ha plantado la "+plantitas.get(i).getNombre()+"? -> "+change);
    }
    int changes=0;
    for(int i=0;i<plantitas.size();i++){
      changes=finca.abona(1,plantitas.get(i).getNombre());
      System.out.println("plantas que han cambiado de estado al abonar "+plantitas.get(i).getNombre()+" en la finca -> "+changes);
    }
    System.out.println("Estado de la finca");
    Planta[][] huerto=finca.getHuerto();
    for(int i=0;i<huerto.length;i++){
     for(int j=0;j<huerto[i].length;j++){
        if(huerto[i][j]==null)
          System.out.print("hueco() ");
        else
          System.out.print(huerto[i][j].getNombre()+"("+huerto[i][j].getEstado()+") ");
     }
     System.out.println();
    }
  }
}
