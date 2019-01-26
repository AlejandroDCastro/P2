/**
* @author Alicia Garrido Alenda
* Se crean una serie de navegantes y una Dophraki. Se invoca embarca de uno de
* los navegantes con la nave y embarque de la nave con todos los navegantes,
* mostrando el resultado de la accion. Se invoca desembarque de la nave con 
* todos los navegantes y distintas posiciones.
*/
import java.text.*;
import java.util.*;

public class p04{
  private static void mrf(double db){
    Locale lengua=new Locale("en");
    DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
    DecimalFormat formato=new DecimalFormat("0.000",chars);

    System.out.print(formato.format(db));
  }
  
  public static Navegante[] creaPasaje(){
    String[] names={"Orkom","Nasguk","Izwick","Merna","Psef","Narle","Dlave","Xivya","Leyud"};
    double[] espe={12.5,15.3,23.2,10.2,1.5,6.5,13,16.2,9.1};
    Navegante[] ping=new Navegante[names.length];
    for(int i=0;i<ping.length;i++)
      ping[i]=new Navegante(names[i],espe[i]);
    return ping;
  }

  public static void main(String[] args){
    Navegante[] viajeros=p04.creaPasaje();
    Dophraki nave=new Dophraki(new String("Entreprise"),4,10.0,5,4,10.0);
    boolean entra=false;
    if(viajeros!=null && viajeros.length>0){
     if(viajeros[0]!=null){
      entra=viajeros[0].embarca(nave);
      if(entra)
           System.out.println(viajeros[0].getNombre()+" ha embarcado");
      else System.out.println(viajeros[0].getNombre()+" no ha embarcado");
      int pasaje=-10;
      for(int i=0;i<viajeros.length;i++){
        pasaje=nave.embarque(viajeros[i]);
        switch(pasaje){
            case -1: System.out.println(viajeros[i].getNombre()+" ya tenia pasaje");
                     break;
            case -2: System.out.println("En "+nave.getNombre()+" no quedan plazas libres");
                     break;
            case -3: System.out.println(viajeros[i].getNombre()+" no podia pagar");
                     break;
            default:
              System.out.println("A "+viajeros[i].getNombre()+" se le asignaria el pasaje "+pasaje+" en "+nave.getNombre());
        }
      }
      boolean sale=false;
      for(int i=0;i<viajeros.length;i++){
        sale=nave.desembarque(i,viajeros[i]);
        if(sale)
          System.out.println(viajeros[i].getNombre()+" desembarca y deja la plaza "+i);
        else
          System.out.println(viajeros[i].getNombre()+" no ocupaba la plaza "+i);
      }
      Navegante[] nav=nave.getPasaje();
      int cont=0;
      for(int i=0;i<nav.length;i++)
        if(nav[i]!=null)
         cont++;
      System.out.println("Al final quedaria en la nave "+cont+" pasajero");
     }
    }
  }
}