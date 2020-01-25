/**
* @author Alicia Garrido Alenda
* Se crean varios frutos y un vehiculo. Se transforman los distintos frutos.
* Se invoca repostaje del vehiculo.
* Se muestra por pantalla el resultado de cada accion.
*/

import java.text.*;
import java.util.*;
public class p03{
  private static String mrf(double db){
     Locale lengua=new Locale("en");
     DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
     DecimalFormat formato=new DecimalFormat("0.000",chars);

     return(formato.format(db).toString());
   }
   
  private static ArrayList<Fruto> creaFrutos(){
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano","manzana","uva","cereza","nuez","coco","albaricoque","higo","pera"};
    ArrayList<Fruto> frutas=new ArrayList<>();
    for(int i=0;i<fruta.length;i++)
      frutas.add(new Fruto(new String(fruta[i])));
    for(int i=fruta.length-1;i>=0;i--)
      frutas.add(new Fruto(new String(fruta[i])));
    return frutas;
  }

  private static ArrayList<String> namesF(){
    ArrayList<String> n=new ArrayList<>();
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano","manzana","uva","cereza","nuez","coco","albaricoque","higo","pera"};
    for(int i=0;i<fruta.length;i++)
      n.add(fruta[i].toLowerCase());
    return n;
  }  
  private static void generaRelaciones(){
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano","manzana","uva","cereza","nuez","coco","albaricoque","higo","pera"};
    double valor=0;
    for(int i=0;i<fruta.length;i++){
       valor=(fruta[i].length()*0.5)/100.0;
       Valores.add(fruta[i],valor);
    }
  }
  private static void consultaFruto(Fruto f){
    if(f!=null){
      System.out.print("nombre: "+f.getNombre()+"; peso: "+p03.mrf(f.getPeso())+"; estado: "+f.getEstado());
    }
    System.out.println();
  }
  
  private static void consultaVehiculo(Vehiculo v){
    if(v!=null){
      boolean[] comb=v.getCombustible();
      if(comb!=null && comb.length>0){
        for(int i=0;i<comb.length;i++){
          if(comb[i])
            System.out.print("| ");
          else
            System.out.print("- ");
        }
        System.out.println();
      }
    }
  }
  public static void main(String[] args){
     p03.generaRelaciones();
     ArrayList<Fruto> postres=p03.creaFrutos();
     ArrayList<Fruto> caldo=new ArrayList<>();
     int b=0;
     Vehiculo range=new Vehiculo(7);
     int veces=0,inc=1;
     while(veces<6){
       for(int i=0;i<postres.size();i+=inc)
          postres.get(i).transforma(20);
       veces++;
       inc++;
     }
     for(int i=0;i<postres.size()/2;i++)
       caldo.add(postres.get(i));
     p03.consultaVehiculo(range);
     b=range.repostaje(caldo);
     System.out.println("El combustible ha subido "+b+" barras");
     p03.consultaVehiculo(range);
     caldo.clear();
     for(int i=postres.size()/2;i<postres.size();i++)
       caldo.add(postres.get(i));
     b=range.repostaje(caldo);
     System.out.println("El combustible ha subido "+b+" barras");
     p03.consultaVehiculo(range);
     
  }
}
