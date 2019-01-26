/**
* @author Alicia Garrido Alenda
* Se crean una serie de navegantes, skrogems y una Dophraki. Se invoca embarca de todos
* los navegantes con la nave y se invoca embarque de la nave con los skrogems.
* Se invoca busca de los skrogems y se invoca getHuespedes de Skrogem.
*/
import java.text.*;
import java.util.*;

public class p03{
  private static void mrf(double db){
    Locale lengua=new Locale("en");
    DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
    DecimalFormat formato=new DecimalFormat("0.000",chars);

    System.out.print(formato.format(db));
  }

  public static void modifica(double[] a,double v){
    if(a!=null){
      for(int i=0;i<a.length;i++)
        a[i]=v+0.5;
    }
  }
  
  public static Navegante[] creaPasaje(){
    String[] names={"Orkom","Nasguk","Izwick","Merna","Psef","Narle","Dlave","Xivya","Leyud"};
    double[] espe={12.5,15.3,23.2,10.2,9.5,26.5,13,16.2,19.1};
    Navegante[] ping=new Navegante[names.length];
    for(int i=0;i<ping.length;i++)
      ping[i]=new Navegante(names[i],espe[i]);
    return ping;
  }
  
  public static void creaSkrogem(Skrogem[] cargamento,double peso,double inicio,double[] original){
    int i=0;
    double iniciov=inicio;
    double[] copia=new double[original.length];
    for(int k=0;k<copia.length;k++)
     copia[k]=original[k];
    for(;i<cargamento.length;i++){
      cargamento[i]=new Skrogem(peso,copia,3);
      iniciov+=0.2;
      peso+=0.5;
      p03.modifica(copia,iniciov);
    }
  }
  

  public static void main(String[] args){
    double[] original=new double[3];
    double inicio=0.5,peso=0.2;
    Navegante[] viajeros=p03.creaPasaje();
    Skrogem[] trastos=new Skrogem[12];
    Dophraki nave=new Dophraki(new String("Heighliners"),12,10.0,10,4,10.0);
    p03.creaSkrogem(trastos,peso,inicio,original);
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
    }
    for(int i=0;i<trastos.length;i++){
      String motivo=nave.embarque(trastos[i]);
      if(motivo!=null)
        System.out.println(motivo);
    }
    for(int i=0;i<trastos.length;i++){
        entra=trastos[i].busca();
        System.out.println(trastos[i].getEtiqueta()+" tiene huesped? "+entra);
    }
    Navegante[] colonizados=Skrogem.getHuespedes();
    if(colonizados!=null){
     for(int i=0;i<colonizados.length;i++){
      System.out.print("Huesped "+i+": ");
      if(colonizados[i]!=null)
         System.out.print(colonizados[i].getNombre());
      System.out.println();
     }
    }
  }
}