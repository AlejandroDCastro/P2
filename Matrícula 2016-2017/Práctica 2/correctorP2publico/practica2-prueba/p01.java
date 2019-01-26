/* @author Alicia Garrido Alenda
* Se crea un Navegante y un Skrogem. Se invoca cobra del navegante sin haber 
* trabajado. Se invoca trabaja del navegante pasandole por parametro el 
* skrogem, getEspecia del skrogem y cobra, getCosecha y getEspecia del navegante. 
*/

import java.text.*;
import java.util.*;

public class p01{
  private static void mrf(double db){
    Locale lengua=new Locale("en");
    DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
    DecimalFormat formato=new DecimalFormat("0.000",chars);

    System.out.print(formato.format(db));
  }
  
  public static void modifica(double[] a,double v){
    if(a!=null){
      for(int i=0;i<a.length;i++)
        a[i]=v*a.length-i;
    }
  }
  
  public static void main(String[] args){
    double[] medidas=new double[3];
    Navegante primer=new Navegante(new String("Enantio"),5.0);
    p01.modifica(medidas,0.4);
    Skrogem parece=new Skrogem(1.5,medidas,0);
    System.out.print(primer.getNombre()+" intenta cobrar sin currar ");
    double obtenido=primer.cobra();
    p01.mrf(obtenido);
    System.out.println();
    Mercancia limpia=primer.trabaja(parece,1.5);
    if(limpia instanceof Skrogem)
      System.out.println(primer.getNombre()+" no trabaja muy bien");
    else{
      System.out.print("Especia del parasito eliminado -> ");
      p01.mrf(parece.getEspecia());
      System.out.println();
      System.out.print(primer.getNombre()+" ha cosechado ");
      p01.mrf(primer.getCosecha());
      System.out.println();
      System.out.print(primer.getNombre()+" cobra y devuelve ");
      obtenido=primer.cobra();
      p01.mrf(obtenido);
      System.out.print(" y su especia es ");
      p01.mrf(primer.getEspecia());
      System.out.println();
    }
  }
}