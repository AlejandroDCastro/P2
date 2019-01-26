/**
* @author Alicia Garrido Alenda
* Se crea una Mercancia con un array valido para sus dimensiones. Se modifican 
* los valores del array en el programa principal, y se usa para crear una segunda
* Mercancia. Se invoca getDimension de ambas mercancias. De una de las mercancias
* se invoca almacena con una cadena, recogida y almacena con otra cadena. Se muestran
* las dimensiones calculadas y la etiqueta de la mercancia almacenada.
*/
import java.text.*;
import java.util.*;

public class p01{

  private static void muestraRealFormato(double db){
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
    double[] original=new double[4];
    double inicio=0.4,dimcalc=0.0,peso=0.2;
    Mercancia[] cargamento=null;
    String cadena=new String("snurfles");
    boolean flag=false;
    
    p01.modifica(original,inicio);
    cargamento=new Mercancia[8];
    for(int i=0;i<cargamento.length;i++){
      peso=peso+i/2;
      cargamento[i]=new Mercancia(peso,original);
      inicio+=0.2;
      p01.modifica(original,inicio);
    }
    for(int i=0;i<cargamento.length;i++){
       dimcalc=cargamento[i].getDimension();
       System.out.print("Dimension calculada de la mercancia "+i+": ");
       p01.muestraRealFormato(dimcalc);
       System.out.println();
    }
    for(int i=0;i<cargamento.length;i++){
      flag=cargamento[i].almacena(cadena);
      if(flag){
        String etq=cargamento[i].getEtiqueta();
        if(etq!=null)
          System.out.println("Mercancia almacenada genera la etiqueta "+etq);
        else
          System.out.println("Mercancia almacenada no genera etiqueta");
      }
    }
    flag=cargamento[0].recogida();
    if(flag)
     System.out.println("Mi primera mercancia recogida");
    cadena=new String("frofuncos");
    flag=cargamento[0].almacena(cadena);
    if(flag){
      String etq=cargamento[0].getEtiqueta();
      if(etq!=null)
        System.out.println("Mercancia almacenada genera la etiqueta "+etq);
      else
        System.out.println("Mercancia almacenada no genera etiqueta");
    }
    
  }
}