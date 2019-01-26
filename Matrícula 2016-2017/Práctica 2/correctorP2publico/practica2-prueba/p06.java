/**
* @author Alicia Garrido Alenda
* Se crea una Dophraki y una serie de tripulantes (de ambos rangos). Se invoca
* embarca de los tripulantes con la nave. Se crea una serie de skrogems y se
* invoca embarque(Mercancia) de la nave con ellos. Se consulta la bodega de la nave. Se
* invoca trabaja de todos los tripulantes de la nave. Se consulta de nuevo la
* bodega de la nave. Se invoca getCosecha, getTempo y getEspecia de cada
* tripulante, se invoca cobra de un tripulante de cada rango y se vuelve a invocar su
* getEspecia.
*/
import java.text.*;
import java.util.*;

public class p06{
  private static void mrf(double db){
    Locale lengua=new Locale("en");
    DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
    DecimalFormat formato=new DecimalFormat("0.000",chars);

    System.out.print(formato.format(db));
  }

  public static Tripulante[] creaTripulacion(){
    String[] names={"Salvor","Dors","Arkady","Hari","Golan","Medile","Clove","Parzo","Kluskal"};
    String[] rango={"subalterno","superior"};
    double[] espe={12.5,15.3,23.2,10.2,1.5,6.5,13,16.2,9.1}; //9
    Tripulante[] ping=new Tripulante[names.length-1];
    for(int i=0;i<ping.length;i++)
      if(i%4==0)
        ping[i]=new Tripulante(names[i],espe[i],rango[1]);
      else
        ping[i]=new Tripulante(names[i],espe[i],rango[0]);
    return ping;
  }
  
  public static void modifica(double[] a,double v){
    if(a!=null){
      for(int i=0;i<a.length;i++)
        a[i]=v+0.3;
    }
  }

  public static void creaParasitos(Skrogem[] bichos){
    double[] original=new double[3];
    double inicio=0.5,peso=0.5;
    double esp=3;
    p06.modifica(original,inicio);
    for(int i=0;i<bichos.length;i++){
      bichos[i]=new Skrogem(peso,original,esp);
      inicio+=0.2;
      peso+=0.5;
      esp++;
      p06.modifica(original,inicio);
    }
  }
  public static void muestraBodega(Mercancia[] carga){
    if(carga!=null){
      for(int i=0;i<carga.length;i++){
        System.out.print("pos. "+i+": ");
        if(carga[i]!=null){
          System.out.print(carga[i].getEtiqueta()+" -> "+carga[i].getAlmacenada());
          if(carga[i] instanceof Skrogem){
            Skrogem bug=(Skrogem)carga[i];
            if(bug.getNave()!=null)
              System.out.print(" -> parasito en la nave "+bug.getNave().getNombre());
          }
        }
        System.out.println();
      }  
    }
  }
  
  public static void muestraTripulacion(Tripulante[] cofradia){
    double e=0.0,t=0.0,c=0.0;
    for(int i=0;i<cofradia.length;i++){
     if(cofradia[i]!=null){
       e=cofradia[i].getEspecia();
       t=cofradia[i].getTempo();
       c=cofradia[i].getCosecha();
       System.out.print(cofradia[i].getNombre()+" -> especia: ");
       mrf(e);
       System.out.print("; tempo: ");
       mrf(t);
       System.out.print("; cosecha: ");
       mrf(c);
       System.out.println();
     }
    }
  }
  

  public static void main(String[] args){
    Tripulante[] cofradia=p06.creaTripulacion();
    Skrogem[] plaga=new Skrogem[24];
    double tiempo=1.5;
    Dophraki nave=new Dophraki(new String("Legacy"),25,10.0,8,8,10.0);
    boolean entra=false;
    int in=0;
    for(in=1;in<cofradia.length;in++){
        entra=cofradia[in].embarca(nave);
        System.out.println(cofradia[in].getNombre()+" con rango "+cofradia[in].getRango()+" ha embarcado en "+nave.getNombre()+"? "+entra);
    }
    entra=cofradia[0].embarca(nave);
    System.out.println(cofradia[0].getNombre()+" con rango "+cofradia[0].getRango()+" ha embarcado en "+nave.getNombre()+"? "+entra);
    p06.creaParasitos(plaga);
    for(int i=0;i<plaga.length;i++){
        String motivo=nave.embarque(plaga[i]);
        System.out.println(motivo);
    }
    System.out.println("Bodega de "+nave.getNombre()+":");
    Mercancia[] transportadas=nave.getBodega();
    p06.muestraBodega(transportadas);
    Tripulante[] tripulacion=nave.getTripulacion();
    for(int i=0;i<tripulacion.length;i++){
      if(tripulacion[i]!=null){
       int eliminados=tripulacion[i].trabaja(tiempo);
       tiempo+=0.5;
       System.out.println(tripulacion[i].getNombre()+" ha eliminado "+eliminados+" parasitos");
      }
    }
    System.out.println("Bodega de "+nave.getNombre()+" despues de trabajar:");
    transportadas=nave.getBodega();
    p06.muestraBodega(transportadas);
    p06.muestraTripulacion(tripulacion);
    boolean trobat=false;
    for(int i=0;i<tripulacion.length && !trobat;i++){
      if(tripulacion[i]!=null && tripulacion[i].getRango().equalsIgnoreCase("superior")){
        trobat=true;
        double resto=tripulacion[i].cobra();
        double e=tripulacion[i].getEspecia();
        double c=tripulacion[i].getCosecha();
        double t=tripulacion[i].getTempo();
        System.out.print(tripulacion[i].getNombre()+" con rango "+tripulacion[i].getRango()+" devuelve cuando cobra ");
        mrf(resto);
        System.out.print(" y tiene despues de cobrar ");
        mrf(e);
        System.out.print(" especia (");mrf(c);System.out.print(", ");mrf(t);System.out.println(")");
      }
    }
    trobat=false;
    for(int i=0;i<tripulacion.length && !trobat;i++){
      if(tripulacion[i]!=null && tripulacion[i].getRango().equalsIgnoreCase("subalterno")){
        trobat=true;
        double resto=tripulacion[i].cobra();
        double e=tripulacion[i].getEspecia();
        double c=tripulacion[i].getCosecha();
        double t=tripulacion[i].getTempo();
        System.out.print(tripulacion[i].getNombre()+" con rango "+tripulacion[i].getRango()+" devuelve cuando cobra ");
        mrf(resto);
        System.out.print(" y tiene despues de cobrar ");
        mrf(e);
        System.out.print(" especia (");mrf(c);System.out.print(", ");mrf(t);System.out.println(")");
      }
    }
  }
}
