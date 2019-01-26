/**
* @author Alicia Garrido Alenda
* Se crea un objeto de tipo RauD y otro de tipo RauC usando una variable de 
* tipo Rau. Se invoca come de ambos para algunas posiciones de su array de
* energia, pero no todas correctas. Se invoca reproduce sin parametros de ambos, 
* mostrando los datos de todos los rau.
*/

public class p03{
  private static void seeEnergy(int[] e){
    if(e!=null && e.length>0){
      System.out.print("[");
      for(int i=0;i<e.length-1;i++)
        System.out.print(e[i]+",");
      System.out.println(e[e.length-1]+"]");
    }
  }
  private static void seeRau(Rau r){
   boolean sx=false;
   String sex=null;
    if(r!=null){
      sx=r.getSexo();
      if(sx)
          sex=new String("hembra");
      else
          sex=new String("macho");
      if(r instanceof RauD){
        RauD t=(RauD)r;
        System.out.print("Datos RauD: "+t.getNombre()+" "+sex);
        Rau[] hijos=t.getProgenie();
        if(hijos!=null)
         System.out.print(" cap.hijos("+hijos.length+") ");
      }else{
        RauC t=(RauC)r;
        System.out.print("Datos RauC: "+sex);
        String[] par=t.getParejas();
        if(par!=null)
         System.out.print(" cap.parejas("+par.length+") ");
      }  
      p03.seeEnergy(r.getEnergia());
    }
  }
  public static void main(String[] args){
    Rau[] mascara=new Rau[4];
    boolean sex=false,fet=false;
    int pos=0,e=6,menjare=3,l=7;
    mascara[pos]=new RauD(sex,e,new String("Neckin"));
    for(int i=0;i<l;i++){
       fet=mascara[pos].come(i,menjare);
       if(!fet)
        System.out.println("No come "+menjare+" en la posicion "+i);
       menjare+=i;
    }
    pos++;e++;menjare=4;
    mascara[pos]=new RauC(!sex,e,4);
    for(int i=-1;i<l;i++){
       fet=mascara[pos].come(i,menjare);
       if(!fet)
        System.out.println("No come "+menjare+" en la posicion "+i);
       menjare+=i;
    }
    pos++;
    mascara[pos]=mascara[0].reproduce();
    pos++;
    mascara[pos]=mascara[1].reproduce();
    for(int i=0;i<mascara.length;i++)
       p03.seeRau(mascara[i]);
  }
}