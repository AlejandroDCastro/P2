/**
* @author Alicia Garrido Alenda
* Se crean tres RauD de distinto sexo. Se invoca come de todos para todas las 
* posiciones de su array de energia. Se invoca reproduce de uno de ellos pasando 
* por parametro otro RauD de manera que se reproducen. Se invoca reproduce de 
* uno de ellos pasando por parametro otro RauD de manera que no se reproducen. 
* Se muestran los datos de todos los rau.
*/

public class p04{
  private static void seeEnergy(int[] e){
    if(e!=null && e.length>0){
      System.out.print("[");
      for(int i=0;i<e.length-1;i++)
        System.out.print(e[i]+",");
      System.out.println(e[e.length-1]+"]");
    }
  }
  private static void seeRauD(RauD r){
   boolean sx=false;
   String sex=null;
   Rau[] hijos=null;
    if(r!=null){
      sx=r.getSexo();
      if(sx)
          sex=new String("hembra");
      else
          sex=new String("macho");
      System.out.print("Datos : "+r.getNombre()+" "+sex);
      hijos=r.getProgenie();
      if(hijos!=null){
        int n=0;
        System.out.print(" cap.hijos("+hijos.length+" -> ");
        for(int i=0;i<hijos.length;i++)
          if(hijos[i]!=null)
           n++;
        System.out.print(n+") ");
      }
      p04.seeEnergy(r.getEnergia());
    }
  }
  public static void main(String[] args){
    RauD[] family=new RauD[4];
    boolean sex=false,fet=false;
    int pos=0,e=4,menjare=3;
    family[pos]=new RauD(sex,e,new String("Neckin"));
    for(int i=0;i<e;i++){
       fet=family[pos].come(i,menjare);
       if(!fet)
        System.out.println("No come "+menjare+" en la posicion "+i);
       menjare+=i;
    }
    pos++;e=2;
    family[pos]=new RauD(!sex,e,new String("Bullma"));
    for(int i=0;i<e;i++){
       fet=family[pos].come(i,menjare);
       if(!fet)
        System.out.println("No come "+menjare+" en la posicion "+i);
       menjare+=i;
    }
    pos++;
    e=6;
    family[pos]=new RauD(!sex,e,new String("Vamp"));
    for(int i=0;i<e;i++){
       fet=family[pos].come(i,menjare);
       if(!fet)
        System.out.println("No come "+menjare+" en la posicion "+i);
       menjare+=i;
    }
    pos++;
    try{
      Rau hijo=family[0].reproduce(family[1],new String("Peba"));
      if(hijo instanceof RauD)
       family[pos]=(RauD) hijo;
    }catch(IncompatiblesException ex){
      System.out.println(ex.getClass().getName()+": "+ex.getMessage());
    }
    try{
      Rau hijo=family[2].reproduce(family[1],new String("Mira"));
      if(hijo instanceof RauD)
       family[pos]=(RauD) hijo;
   }catch(IncompatiblesException ex){
     System.out.println(ex.getClass().getName()+": "+ex.getMessage());
   }
    for(int i=0;i<family.length;i++)
       p04.seeRauD(family[i]);
  }
}