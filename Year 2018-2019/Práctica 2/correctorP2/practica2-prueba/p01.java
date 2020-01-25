/**
* @author Alicia Garrido Alenda
* Se crean varias huertas, plantas, trifidos y un inmune. Se localizan las distintas huertas en
* distintas coordenadas. Se invoca paseo del inmune.
* Se invocan el metodo planta del inmune con las distintas plantas y abona.
* Se muestra por pantalla el resultado de cada accion.
*/

import java.text.*;
import java.util.*;
public class p01{
  private static String mrf(double db){
     Locale lengua=new Locale("en");
     DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
     DecimalFormat formato=new DecimalFormat("0.000",chars);

     return(formato.format(db).toString());
   }
   
   private static ArrayList<Coordenada> generaPosiciones(ArrayList<Double> p){
     ArrayList<Coordenada> v=new ArrayList<>();
     
     for(double x=12.5;x<80;x+=15.5){
       for(double y=1;y<=90;y+=12.5){
         v.add(new Coordenada(y,x));
         if(y>=-90 && y<=90)
           p.add(y);
         else p.add(0.0);
         if(x>=-180 && x<=180)
           p.add(x);
         else p.add(0.0);
       }
     }
     return v;
   }
   
   private static ArrayList<Huerta> generaFincas(){
     ArrayList<Huerta> h=new ArrayList<>();
     for(int i=6;i>=3;i--)
       for(int j=4;j>=2;j--)
         h.add(new Huerta(i,j));
     return h;
   }

  private static ArrayList<Planta> creaSemillas(){
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano","manzana","uva","cereza","nuez","coco","albaricoque","higo","pera"};
    String[] planta={"ciruelo","tomatera","carica","fragaria","naranjo","mandarino","platanero","manzano","vid","cerezo","nogal","cocotero","albaricoquero","higuera","peral"};
    ArrayList<Planta> plantas=new ArrayList<>();
    for(int i=0;i<fruta.length&&i<planta.length;i++)
      plantas.add(new Planta(new String(planta[i]),new String(fruta[i]),3));
    for(int i=0;i<fruta.length&&i<planta.length;i++){
      plantas.add(new Trifido(new String(planta[i]),new String(fruta[i]),3));
    }
    for(int i=fruta.length-1;i>=0;i--){
      plantas.add(new Planta(new String(planta[i]),new String(fruta[i]),3));
    }
    for(int i=0;i<fruta.length&&i<planta.length;i++)
      plantas.add(new Trifido(new String(planta[i]),new String(fruta[i]),2));
    return plantas;
  }

  private static ArrayList<String> namesF(){
    ArrayList<String> n=new ArrayList<>();
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano","manzana","uva","cereza","nuez","coco","albaricoque","higo","pera"};
    for(int i=0;i<fruta.length;i++)
      n.add(fruta[i].toLowerCase());
    return n;
  }  
  private static ArrayList<String> namesP(){
    ArrayList<String> n=new ArrayList<>();
    String[] fruta={"ciruelo","tomatera","carica","fragaria","naranjo","mandarino","platanero","manzano","vid","cerezo","nogal","cocotero","albaricoquero","higuera","peral"};
    for(int i=0;i<fruta.length;i++)
      n.add(fruta[i].toLowerCase());
    return n;
  }  
  private static void generaRelaciones(){
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano","manzana","uva","cereza","nuez","coco","albaricoque","higo","pera"};
    double valor=0;
    for(int i=0;i<fruta.length;i++){
       valor=(fruta[i].length()*0.5)/100.0;
       Valores.add(fruta[i],valor);
    }
  }
  private static void consultaFruto(Fruto f){
    if(f!=null){
      System.out.print("nombre: "+f.getNombre()+"; peso: "+p01.mrf(f.getPeso())+"; estado: "+f.getEstado());
    }
    System.out.println();
  }
  private static void consultaPlanta(Planta p){
    if(p!=null){
      System.out.println("Trifido: "+(p instanceof Trifido));
      System.out.println("Nombre: "+p.getNombre());
      System.out.println("Estado: "+p.getEstado());
      System.out.println("Nombre de sus frutos: "+p.getFruto());
      Fruto[] producted=p.getFrutos();
      if(producted!=null){
        System.out.println("Num. max: "+producted.length);
        for(int i=0;i<producted.length;i++){
           System.out.print("pos. "+i+" -> ");
           p01.consultaFruto(producted[i]);
        }
      }
    }
  }
  private static void consultaHuerto(Planta[][] h){
    System.out.println("Estado de la finca");
    if(h!=null){
      for(int i=0;i<h.length;i++){
        for(int j=0;j<h[i].length;j++){
          System.out.println("["+i+","+j+"]");
          p01.consultaPlanta(h[i][j]);
        }
      }
    }
  }
   
   public static void main(String[] args){
     p01.generaRelaciones();
     ArrayList<Double> posicion=new ArrayList<>();
     ArrayList<Coordenada> situa=p01.generaPosiciones(posicion);
     ArrayList<Planta> plantitas=p01.creaSemillas();
     ArrayList<Huerta> fincas=p01.generaFincas();
     ArrayList<String> np=p01.namesF();
     ArrayList<String> plantadas=new ArrayList<>();
     Persona campesino=new Inmune("Galadriel Moror");
     int o=0;
     for(int i=0;o<posicion.size()&&i<fincas.size();i++){
       fincas.get(i).localiza(posicion.get(o),posicion.get(o+1));
       o+=2;
     }
     Coordenada hallada=campesino.paseo();
     o=0;
     for(int i=0;i<situa.size();i++){
       if(situa.get(i).iguales(hallada)){
        System.out.println(campesino.getNombre()+" se queda al cuidado de la huerta localizada en "+posicion.get(o)+","+posicion.get(o+1));
       }
       o+=2;
     }
     for(int i=0;i<plantitas.size();i++){
       Planta actual=plantitas.get(i);
       boolean plantada=false;
       boolean tri=false;
          tri=actual instanceof Trifido;   
          plantada=campesino.planta(actual,campesino.getHuerta());
          System.out.println(campesino.getNombre()+" ha plantado "+actual.getNombre()+" -> "+plantada+"; era trifido? -> "+tri);
          if(plantada)
            plantadas.add(actual.getFruto());
     }
     
     int abonadas=0;
     for(int k=0;k<3;k++){
       for(int i=0;i<np.size();i++){
         String actual=np.get(i);
         abonadas=campesino.abona(1,actual);
         System.out.println("Han cambiado "+abonadas+" despues del abono de "+actual);
       }
     }
     System.out.println("---------- Estado huerta despues de abona (1) -------------");
     p01.consultaHuerto(campesino.getHuerta().getHuerto());
     
     for(int i=0;i<plantadas.size();i+=2){
       String actual=plantadas.get(i);
       abonadas=campesino.abona(2,actual);
       System.out.println("Han cambiado "+abonadas+" despues del abono de "+actual);
     }
     System.out.println("---------- Estado huerta despues de abona (2) -------------");
     p01.consultaHuerto(campesino.getHuerta().getHuerto());
   }
}
