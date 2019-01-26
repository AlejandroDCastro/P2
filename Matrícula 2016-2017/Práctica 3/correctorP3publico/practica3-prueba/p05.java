/**
* @author Alicia Garrido Alenda
* Se crea un objeto de tipo RauD usando una variable de tipo Rau. Se invoca 
* come para todas las posiciones de su array de energia. Se invoca nivelEnergetico. 
* Se invoca interacciona de manera que accede a todas las posiciones de su array de 
* energia. Se invoca nivelEnergetico.
*/

public class p05{
  public static void main(String[] args){
    Rau mascara=null;
    boolean sex=true,fet=false;
    int e=10,menjare=3,l=0;
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
    System.out.println("Nivel energetico del RauD ("+cadena+") -> "+mascara.nivelEnergetico());
    int pos=10;
    for(int i=0;i<l;i++){
       menjare=mascara.interacciona(pos);
       System.out.println("Interacciona de "+pos+" -> "+menjare);
       pos--;
    }
    System.out.println("Nivel energetico del RauD ("+cadena+") despues de interaccionar -> "+mascara.nivelEnergetico());

  }
}