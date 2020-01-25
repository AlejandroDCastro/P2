/**
* @author Alicia Garrido Alenda
* Se crea una huerta y una planta. Se invoca abona de la planta. Se invoca planta de la huerta con 
* la planta. Se invoca abona de la planta.
* Se muestra por pantalla el resultado de cada accion.
*/
public class p04{

  public static void main(String[] args){
    String name=new String("ACTINIDIA");
    Huerta finca=new Huerta(0,3);
    Planta mata=new Planta(name.toLowerCase(),"kiwi",5);
    boolean change=false;
    change=mata.abona(2);
    System.out.println("Se abona la "+mata.getNombre()+" -> "+change+" => estado actual -> "+mata.getEstado());
    change=finca.planta(mata);
    System.out.println("Se ha plantado la "+mata.getNombre()+"? -> "+change);
    change=mata.abona(2);
    System.out.println("Se abona la "+mata.getNombre()+" -> "+change+" => estado actual -> "+mata.getEstado());
  }
}
