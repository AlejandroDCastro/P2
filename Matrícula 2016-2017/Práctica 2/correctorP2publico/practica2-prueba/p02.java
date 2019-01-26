/**
* @author Alicia Garrido Alenda
* Se crean una serie de navegantes y una Dophraki. Se invoca embarca de todos
* los navegantes con la nave mostrando el resultado de la accion. Se invoca 
* getNombre, getEspecia y getPasaje de Navegante, y getNombre y getEspecia de Dophraki. 
*/
import java.text.*;
import java.util.*;

public class p02{
  private static void mrf(double db){
    Locale lengua=new Locale("en");
    DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
    DecimalFormat formato=new DecimalFormat("0.000",chars);

    System.out.print(formato.format(db));
  }
  
  public static Navegante[] creaPasaje(){
    String[] names={"Orkom","Nasguk","Izwick","Merna","Psef","Narle","Dlave","Xivya","Leyud"};
    double[] espe={12.5,15.3,23.2,10.2,9.5,6.5,13,16.2,9.1};
    Navegante[] ping=new Navegante[names.length];
    for(int i=0;i<ping.length;i++)
      ping[i]=new Navegante(names[i],espe[i]);
    return ping;
  }

  public static void main(String[] args){
    Navegante[] viajeros=p02.creaPasaje();
    Dophraki nave=new Dophraki(new String("Entreprise"),4,10.0,10,4,10.0);
    boolean entra=false;
    if(viajeros!=null){
      for(int i=0;i<viajeros.length;i++){
         if (viajeros[i]!=null){
           entra=viajeros[i].embarca(nave);
           if(entra)
             System.out.println(viajeros[i].getNombre()+" ha embarcado");
           else System.out.println(viajeros[i].getNombre()+" no ha embarcado");
         }
      }
      for(int i=0;i<viajeros.length;i++){
        System.out.println(viajeros[i].getNombre()+" tiene el pasaje "+viajeros[i].getPasaje()+" en "+nave.getNombre());
        System.out.print("A "+viajeros[i].getNombre()+" le queda ");
        p02.mrf(viajeros[i].getEspecia());
        System.out.println(" de especia");
      }
      System.out.print("En "+nave.getNombre()+" hay ahora ");
      p02.mrf(nave.getEspecia());
      System.out.println(" de especia");
    }
  }
}