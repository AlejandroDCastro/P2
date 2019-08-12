/**
* @author Alicia Garrido Alenda
* Se crea una huerta y varias plantas y trifidos. Se invoca el metodo planta de la huerta con
* las plantas y trifidos. Se invoca abona de los trifidos sin parametro.
* Se muestra por pantalla el resultado de cada accion.
*/
import java.text.*;
import java.util.*;
public class p02{
  private static String mrf(double db){
     Locale lengua=new Locale("en");
     DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
     DecimalFormat formato=new DecimalFormat("0.000",chars);

     return(formato.format(db).toString());
   }

  private static ArrayList<Planta> creaSemillas(){
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano"};
    String[] planta={"ciruelo","tomatera","carica","fragaria","naranjo","mandarino","platanero"};
    ArrayList<Planta> plantas=new ArrayList<>();
    int cont=0;
    for(int i=0;i<fruta.length&&i<planta.length;i++)
     if(i%2==0)
       plantas.add(new Planta(new String(planta[i]),new String(fruta[i]),i/2));
     else
       plantas.add(new Trifido(new String(planta[i]),new String(fruta[i]),i/2));
    cont=fruta.length;
    for(int i=0;i<fruta.length&&i<planta.length;i++){
      plantas.add(new Planta(new String(planta[i]),new String(fruta[i]),cont/2));
      cont--;
    }
    cont=1;
    for(int i=fruta.length-1;i>=planta.length/2;i--){
      plantas.add(new Planta(new String(planta[i]),new String(fruta[i]),(cont*i)/2));
      cont++;
    }
    return plantas;
  }

  private static ArrayList<String> names(){
    String[] a={"ciruelo","tomatera","carica","fragaria","naranjo","mandarino","platanero","peral"};
    ArrayList<String> n=new ArrayList<>();
    for(int i=0;i<a.length;i++)
      if(i%2==0)
        n.add(a[i].toUpperCase());
      else
        n.add(a[i]);
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
  private static ArrayList<Planta> creaSemillas2(){
    String[] fruta={"fresa","ciruela","tomate","papaya","manzana","uva","cereza","nuez","coco","albaricoque","higo","pera","platano"};
    String[] planta={"fragaria","ciruelo","tomatera","carica","manzano","vid","cerezo","nogal","cocotero","albaricoquero","higuera","peral","platanero"};
    ArrayList<Planta> plantas=new ArrayList<>();
    for(int i=0;i<fruta.length&&i<planta.length;i++)
      plantas.add(new Planta(new String(planta[i]),new String(fruta[i]),2));
    return plantas;
  }
  
  private static void consultaFruto(Fruto f){
    if(f!=null){
      System.out.print("nombre: "+f.getNombre()+"; peso: "+p02.mrf(f.getPeso())+"; estado: "+f.getEstado());
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
           p02.consultaFruto(producted[i]);
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
          p02.consultaPlanta(h[i][j]);
        }
      }
    }
  }
  public static void main(String[] args){
    ArrayList<Planta> plantitas=p02.creaSemillas();
    ArrayList<String> names=p02.names();
    p02.generaRelaciones();
    Huerta finca=new Huerta(8,3);
    boolean change=false;
    for(int i=0;i<plantitas.size();i++){
      change=finca.planta(plantitas.get(i));
      System.out.println("Se ha plantado la "+plantitas.get(i).getNombre()+"? -> "+change);
    }
    System.out.println("-------- Estado de la finca antes de abonar ------------------------");
    p02.consultaHuerto(finca.getHuerto());
    int changes=0;
    for(int i=0;i<plantitas.size();i+=3){
       if(plantitas.get(i) instanceof Trifido){
        changes=((Trifido)plantitas.get(i)).abona();
        System.out.println(plantitas.get(i).getNombre()+" se ha comido -> "+changes);
       }
    }
    System.out.println("-------- Estado de la finca despues de abonar ------------------------");
    p02.consultaHuerto(finca.getHuerto());
  }
}
