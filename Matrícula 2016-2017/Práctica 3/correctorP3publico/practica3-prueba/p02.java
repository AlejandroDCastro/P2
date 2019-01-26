/**
* @author Alicia Garrido Alenda
* Se crea un objeto de tipo RauD y otro de tipo RauC usando una variable de 
* tipo Rau. Se invoca come de ambos para todas las posiciones de su array de
* energia. Se invoca getEnergia de ambos, mostrando por pantalla el contenido
* de ambos arrays.
*/

public class p02{
  private static void seeEnergy(int[] e){
    if(e!=null && e.length>0){
      System.out.print("[");
      for(int i=0;i<e.length-1;i++)
        System.out.print(e[i]+",");
      System.out.println(e[e.length-1]+"]");
    }
  }
  public static void main(String[] args){
    Rau mascara=null;
    boolean sex=true,fet=false;
    int e=0,menjare=3,l=0;
    String cadena=null;
    mascara=new RauD(sex,e,new String("Bullma"));
    int[] energy=mascara.getEnergia();
    if(energy!=null)
      l=energy.length;
    for(int i=0;i<l;i++){
       fet=mascara.come(i,menjare);
       if(!fet)
        System.out.println("No come "+menjare+" en la posicion "+i);
       menjare+=i;
    }
    sex=mascara.getSexo();
    if(sex)
     cadena=new String("hembra");
    else
     cadena=new String("macho");
    System.out.print("Energia del RauD ("+cadena+") -> ");
    p02.seeEnergy(mascara.getEnergia());
    e=5;
    menjare=-1;
    mascara=new RauC(!sex,e,4);
    energy=mascara.getEnergia();
    if(energy!=null)
      l=energy.length;
    for(int i=0;i<l;i++){
       fet=mascara.come(i,menjare);
       if(!fet)
        System.out.println("No come "+menjare+" en la posicion "+i);
       menjare+=i;
    }
    sex=mascara.getSexo();
    if(sex)
     cadena=new String("hembra");
    else
     cadena=new String("macho");
    System.out.print("Energia del RauC ("+cadena+") -> ");
    p02.seeEnergy(mascara.getEnergia());
  }
}