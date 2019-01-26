/**
* @author Alicia Garrido Alenda
* Se crea una Galaxia. Se crean todos sus sectores y se situan en sus
* coordenadas. Se crean distintas mercancías y se invoca su almacena con 
* distintas cadenas. Se invoca recogida de todas las mercancías. Se almacenan 
* estas  mercancías en los sectores. Se colocan los sectores en la galaxia.
* Se invoca setMapa de Romskip con esta galaxia. Se crean dos Romskip y se
* embarcan mercancías en una de ellas; se comprueba su bodega y se
* teletransporta una mercancía a la otra nave que verifica el teletransporte, 
* comprobando después ambas bodegas.
*/

import java.util.*;
public class p17{

  public static void modifica(double[] a,double v){
    if(a!=null){
      for(int i=0;i<a.length;i++)
        a[i]=v*a.length-i;
    }
  }

  public static void modifica2(double[] a){
    if(a!=null){
      double[] b=new double[a.length];
      for(int i=0;i<a.length;i++)
        b[i]=a[i];
      for(int i=a.length-1,j=0;i>=0;i--,j++)
        a[i]=b[j];
    }
  }

  public static void modifica3(double[] a){
    if(a!=null){
      double[] b=new double[a.length];
      int i=0,j=0;
      for(i=0;i<a.length;i++)
        b[i]=a[i];
      a[0]=b[3];
      a[3]=b[0];
      a[1]=b[2];
      a[2]=b[1];
    }
  }

  public static void compruebaSector(Sector s){
   Coordenada creada=null;
   int[] valores=null;
   
   if(s!=null){
     creada=s.getCoordenada();
      if(creada!=null){
        System.out.print("sector situado ha creado la coordenada ");
        valores=creada.getCoordenadas();
        if(valores!=null){
          System.out.print("[");
          for(int i=0;i<valores.length-1;i++)
            System.out.print(valores[i]+",");
          System.out.println(valores[valores.length-1]+"]");
        }
        else System.out.println("pero no se pueden consultar sus valores");
      }
      else System.out.println("sector situado pero no ha creado su coordenada");
   }
  }

  public static void creaMercancias(Mercancia[] cargamento,double peso,double inicio,double[] original){
    int i=0;
    String[] nombres={"acelgas","ajo","alcachofas","bacores","berenjenas","boniato","cerezas","coco",
                      "datiles","escarola","escorzonera","fresas","guisantes",
                      "kiwis","mandarinas","manzana","naranja","pera","rabano","remolacha","uva","valerianela","zanahorias"};
    double iniciov=inicio;
    double[] copia=new double[original.length];
    for(int k=0;k<copia.length;k++)
     copia[k]=original[k];
    int c2=0;
    for(;i<cargamento.length && i<nombres.length;i++){
      cargamento[i]=new Mercancia(peso,copia);
      iniciov+=0.2;
      peso+=0.5;
      p17.modifica(copia,iniciov);
      cargamento[i].almacena(nombres[i]);
    }
    for(i=0;i<cargamento.length;i++)
      cargamento[i].recogida();
  }
  
  public static void creaSectores(Sector[] sectores, int f,int c,int p){
    int cont=0;
    boolean flag=false;
    for(int i=0;i<sectores.length;i++)
      sectores[i]=new Sector(i+1);
    int c2=0;
    for(int i=0;i<f && cont<sectores.length;i++)
     for(int j=0;j<c && cont<sectores.length;j++)
       for(int k=0;k<p && cont<sectores.length;k++){
         if(!flag){
           boolean set=sectores[cont].situa(i,j,k);
           if(set)
             p17.compruebaSector(sectores[cont]);
           flag=true;
           cont++;
           c2++;
           if(c2<3)
            flag=false;
         }
         else{
           flag=false;
           c2=0;
         }
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
          double suma=carga[i].getDimension();
          System.out.print(carga[i].getEtiqueta()+" -> "+carga[i].getAlmacenada());//+" -> ");
          //muestraRealFormato(suma);
        }
        System.out.println();
      }  
    }
  }
  
  public static void main(String[] args){
    double[] original=new double[4];
    double inicio=0.4,peso=0.2;
    Mercancia[] cargamento=null;
    Sector[] sectores=null;
    int f=3,c=4,p=3,cont=0;
    Galaxia remerat=null;
    boolean flag=false;
    String etq=null;

    
    p17.modifica(original,inicio);
    cargamento=new Mercancia[23];
    p17.creaMercancias(cargamento,peso,inicio,original);
    sectores=new Sector[20];
    p17.creaSectores(sectores,f,c,p);
    TreeMap<String,Sector> socios=new TreeMap<String,Sector>();
    for(int i=0;i<sectores.length && cont<cargamento.length;i++){
      flag=sectores[i].almacena(cargamento[cont]);
      etq=cargamento[cont].getEtiqueta();
      socios.put(etq,sectores[i]);
      if(flag)
        System.out.println("mercancia "+cont+" "+etq+" almacenada -> "+cargamento[cont].getAlmacenada());
      else System.out.println("mercancia "+cont+" "+etq+" no almacenada");
      cont++;
    }
    for(int i=0;i<sectores.length && cont<cargamento.length;i++){
      flag=sectores[i].almacena(cargamento[cont]);
      etq=cargamento[cont].getEtiqueta();
      socios.put(etq,sectores[i]);
      if(flag)
        System.out.println("mercancia "+cont+" "+etq+" almacenada -> "+cargamento[cont].getAlmacenada());
      else System.out.println("mercancia "+cont+" "+etq+" no almacenada");
      cont++;
    }
    remerat=new Galaxia("Remerat",f,c,p);
    for(int i=0;i<sectores.length;i++)
      if(!remerat.coloca(sectores[i]))
        System.out.println("sector "+i+" no colocado en "+remerat.getNombre());
    Romskip.setMapa(remerat);
    
    Romskip crucero=new Romskip(new String("Lancer"),25,200);
    Romskip carguero=new Romskip(new String("Cygnus"),5,100);

    Set<String> lista=socios.keySet();
    Iterator<String> it=lista.iterator();
    flag=true;
    while(it.hasNext() && flag){
      etq=it.next();
      Sector presente=socios.get(etq);
      System.out.println("embarque de "+etq);
      if(presente!=null){
        Coordenada actual=presente.getCoordenada();
        String motivo=crucero.embarque(etq,actual);
        if(motivo!=null){
           System.out.println(motivo);
           flag=false;
        }
      }
    }
    Mercancia[] carga=crucero.getBodega();
    p17.muestraBodega(carga);
    flag=crucero.teletransporte(carguero,11);
    System.out.println("Se ha teletransportado la mercancia 11 de "+crucero.getNombre()+" a "+carguero.getNombre()+" -> "+flag);
    System.out.println("bodega de "+crucero.getNombre()+":");
    carga=crucero.getBodega();
    p17.muestraBodega(carga);
    System.out.println("bodega de "+carguero.getNombre()+":");
    carga=carguero.getBodega();
    p17.muestraBodega(carga);
  }
}
