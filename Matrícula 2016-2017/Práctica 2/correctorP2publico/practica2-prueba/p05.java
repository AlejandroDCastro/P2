/**
* @author Alicia Garrido Alenda
* Se crea una Galaxia. Se crean dos de sus sectores y se situan en sus
* coordenadas. Se crean distintas mercancias (algunas son skrogems) y 
* se invoca su almacena con una dophraki. Se invoca recogida de todas las 
* mercancias. Despues se almacenan estas mercancias en los sectores. 
* Se colocan los sectores en la galaxia. Se invoca setMapa de Romskip con esta 
* galaxia y setMapa de Dophraki. Se crea una Dophraki y se invoca su metodo
* embarque pasando por parametro las etiquetas de las mercancias y la coordenada
* del sector donde se encuentran. Se comprueba la bodega de la nave.
*/

import java.util.*;
public class p05{

  public static void modifica(double[] a,double v){
    if(a!=null){
      for(int i=0;i<a.length;i++)
        a[i]=v+0.3;
    }
  }
  public static void muestraCoordenada(Coordenada creada){
      if(creada!=null){
        int[] valores=creada.getCoordenadas();
        if(valores!=null){
          System.out.print("[");
          for(int i=0;i<valores.length-1;i++)
            System.out.print(valores[i]+",");
          System.out.println(valores[valores.length-1]+"]");
        }
      }
  }
  public static void compruebaSector(Sector s){
   Coordenada creada=null;
   int[] valores=null;
   
   if(s!=null){
     creada=s.getCoordenada();
     if(creada!=null){
        System.out.print("sector situado ha creado la coordenada ");
        p05.muestraCoordenada(creada);
     }
     else System.out.println("sector situado pero no ha creado su coordenada");
   }
  }

  public static void creaMercancias(Mercancia[] cargamento,double peso,double inicio,double[] original){
    int i=0;
    double iniciov=inicio;
    double[] copia=new double[original.length];
    Dophraki estacion=new Dophraki(new String("Babylon"),25,200,4,4,10.5);
    for(int k=0;k<copia.length;k++)
     copia[k]=original[k];
    for(;i<cargamento.length;i++){
      if(i%5==0)
        cargamento[i]=new Skrogem(peso,copia,3);
      else
        cargamento[i]=new Mercancia(peso,copia);
      iniciov+=0.2;
      peso+=0.5;
      p05.modifica(copia,iniciov);
      cargamento[i].almacena(estacion);
    }
    for(i=0;i<cargamento.length;i++)
      cargamento[i].recogida();
  }
  
  public static void creaSectores(Sector[] sectores, int f,int c,int p){
    for(int i=0;i<sectores.length;i++)
      sectores[i]=new Sector(20);
    int cont=0;
    for(int i=0;i<f && cont<sectores.length;i++)
     for(int j=0;j<c && cont<sectores.length;j++)
       for(int k=0;k<p && cont<sectores.length;k++){
           sectores[cont].situa(i,j,k);
           cont++;
       }
  }

  public static void muestraCoordenadas(Coordenada[] creada){
   int[] valores=null;
   
   if(creada!=null){
     for(int j=0;j<creada.length;j++){
       if(creada[j]!=null){
        valores=creada[j].getCoordenadas();
        if(valores!=null){
          System.out.print("[");
          for(int i=0;i<valores.length-1;i++)
            System.out.print(valores[i]+",");
          if(valores.length>0)
           System.out.print(valores[valores.length-1]);
          System.out.print("]");
        }
       }
     }
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
  
  public static void main(String[] args){
    double[] original=new double[4];
    double inicio=0.5,peso=0.2;
    Mercancia[] cargamento=null;
    Sector[] sectores=null;
    int f=3,c=4,p=3,cont=0;
    Galaxia remerat=null;
    boolean flag=false;
    String etq=null;
    Coordenada actual=null;

    
    p05.modifica(original,inicio);
    cargamento=new Mercancia[23];
    p05.creaMercancias(cargamento,peso,inicio,original);
    sectores=new Sector[2];
    p05.creaSectores(sectores,f,c,p);
    TreeMap<String,Sector> socios=new TreeMap<String,Sector>();
    for(int i=0;i<cargamento.length;i++){
     if(i%2==0)
      cont+=1;
     else
      cont-=1;
      flag=sectores[cont].almacena(cargamento[i]);
      etq=cargamento[i].getEtiqueta();
      socios.put(etq,sectores[cont]);
      if(flag){
        System.out.print("mercancia "+i+" "+etq+" almacenada -> "+cargamento[i].getAlmacenada()+" en ");
        actual=sectores[cont].getCoordenada();
        p05.muestraCoordenada(actual);
      }
      else System.out.println("mercancia "+i+" "+etq+" no almacenada");
    }
    remerat=new Galaxia("Remerat",f,c,p);
    for(int i=0;i<sectores.length;i++)
      if(!remerat.coloca(sectores[i]))
        System.out.println("sector "+i+" no colocado en "+remerat.getNombre());
    Romskip.setMapa(remerat);
    Dophraki.setMapa();
    Dophraki crucero=new Dophraki(new String("Cheyenne"),21,200,4,4,10.5);

    Set<String> lista=socios.keySet();
    Iterator<String> it=lista.iterator();
    while(it.hasNext()){
      etq=it.next();
      Sector presente=socios.get(etq);
      System.out.print("embarque de "+etq+" presente en ");
      if(presente!=null){
        actual=presente.getCoordenada();
        p05.muestraCoordenada(actual);
        String motivo=crucero.embarque(etq,actual);
        if(motivo!=null)
           System.out.println(motivo);
      }
    }
    Mercancia[] carga=crucero.getBodega();
    p05.muestraBodega(carga);
  }
}
