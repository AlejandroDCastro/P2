/**
* @author Alicia Garrido Alenda
* Se crea una Romskip, una Coordenada y una cadena; se invoca embarque de la 
* Romskip con esta cadena y esta coordenada sin invocar previamente a setMapa.
* Se muestra el resultado de la accion.
*/

public class p04{

  public static void main(String[] args){
    Romskip nave=null;
    Coordenada posicion=null;
    String mercancia=new String("poison");
    String motivo=new String("");
    int cantidad=-1;
    
    nave=new Romskip("Nostromo",10,237.5);
    posicion=new Coordenada(0,0,0);
    motivo=nave.embarque(mercancia,posicion);
    if(motivo!=null)
     System.out.println("No se ha realizado el embarque en "+nave.getNombre()+" por "+motivo);
    else{
     cantidad=nave.conteo();
     if(cantidad==0)
       System.out.println("No se ha realizado el embarque en "+nave.getNombre()+" porque no esta en una galaxia");
     else
       System.out.println("Sorprendente!!!");
    }
  }
}