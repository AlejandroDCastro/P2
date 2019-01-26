/**
* @author Alicia Garrido Alenda
* Se crean dos mercancias con las mismas dimensiones y mismo peso. Se crea un 
* Sector, se situa en unas coordenadas y se almacenan ambas mercancias en este sector.
* Se invoca getEtiqueta y getAlmacenada de las mercancias. Se recoge una de ellas y 
* se vuelve a almacenar en el mismo sector y se invoca a recuento del Sector con la 
* etiqueta de esa mercancia. Se vuelve a recoger una de las mercancias e invocar getAlmacenada.
*/

public class p05{

  public static void modifica(double[] a,double v){
    if(a!=null){
      for(int i=0;i<a.length;i++)
        a[i]=v*a.length-i;
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
  public static void compruebaAlmacenamiento(Mercancia m,String etq){
      if(etq!=null)
        System.out.println("Mercancia almacenada genera la etiqueta "+etq);
      else
        System.out.println("Mercancia almacenada no genera etiqueta");
      System.out.println("La mercancia esta almacenada -> "+m.getAlmacenada());
  }
  
  public static void main(String[] args){
    double[] original=new double[4];
    double inicio=0.5;
    Mercancia primera=null,segunda=null,recogida=null;
    Sector inicial=null;
    Coordenada creada=null;
    boolean flag=false;
    String etq=null;
    
    p05.modifica(original,inicio);
    primera=new Mercancia(3.7,original);
    segunda=new Mercancia(3.7,original);
    inicial=new Sector(5);
    flag=inicial.situa(3,2,4);
    if(flag)
     p05.compruebaSector(inicial);
    else
     System.out.println("sector no situado");
    flag=inicial.almacena(primera);
    if(flag){
      etq=primera.getEtiqueta();
      p05.compruebaAlmacenamiento(primera,etq);
    }
    flag=inicial.almacena(segunda);
    if(flag){
      etq=segunda.getEtiqueta();
      p05.compruebaAlmacenamiento(segunda,etq);
    }
    recogida=inicial.recoge(etq);
    if(recogida!=null && etq!=null){
      System.out.println("La mercancia "+etq+" recogida esta almacenada -> "+recogida.getAlmacenada());
      flag=inicial.almacena(recogida);
      if(flag)
       System.out.println("Se vuelve a almacenar la mercancia "+etq);
      System.out.println("Cantidad de unidades de "+etq+" -> "+inicial.recuento(etq));
    }
    recogida=inicial.recoge(etq);
    if(recogida!=null && etq!=null){
      System.out.println("La mercancia "+etq+" recogida esta almacenada -> "+recogida.getAlmacenada());
    }
  }
}