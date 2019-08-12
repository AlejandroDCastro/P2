/**
* @author Alicia Garrido Alenda
* Se crean dos huertas, un inmune, varias plantas y trifidos. Se localizan las huertas. Se pasea el inmune.
* Se invoca el metodo planta de una de las huertas con plantas y trifidos. Se invoca abona de los trifidos, sin parametros.
* Se invoca abona de la huerta con las distintas plantas.
* Se muestra por pantalla el resultado de cada accion.
*/
import java.text.*;
import java.util.*;
public class p04{
  private static String mrf(double db){
     Locale lengua=new Locale("en");
     DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
     DecimalFormat formato=new DecimalFormat("0.000",chars);

     return(formato.format(db).toString());
   }
  private static void localiza(Huerta a,Huerta b){
    b.localiza(0,38);
    a.localiza(0,39);
  }
  private static ArrayList<Planta> creaSemillas(){
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano","manzana","uva","cereza"};
    String[] planta={"ciruelo","tomatera","carica","fragaria","naranjo","mandarino","platanero","manzano","vid","cerezo"};
    ArrayList<Planta> plantas=new ArrayList<>();
    int cont=0;
    for(int i=0;i<fruta.length&&i<planta.length;i++)
     if(i%5==0)
      plantas.add(new Trifido(new String(planta[i]),new String(fruta[i]),i));
     else
      plantas.add(new Planta(new String(planta[i]),new String(fruta[i]),i));
    cont=fruta.length;
    for(int i=0;i<fruta.length&&i<planta.length;i++){
      plantas.add(new Planta(new String(planta[i]),new String(fruta[i]),cont));
      cont--;
    }
    cont=1;
    for(int i=fruta.length-1;i>=planta.length/2;i--){
      plantas.add(new Planta(new String(planta[i]),new String(fruta[i]),cont));
      cont++;
    }
    return plantas;
  }

  private static ArrayList<String> names(){
    ArrayList<String> n=new ArrayList<>();
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano","manzana","uva","cereza"};
    for(int i=0;i<fruta.length;i++)
      n.add(fruta[i].toLowerCase());
    return n;
  }  
  private static void generaRelaciones(){
    String[] fruta={"ciruela","tomate","papaya","fresa","naranja","mandarina","platano","manzana","uva","cereza"};
    double valor=0;
    for(int i=0;i<fruta.length;i++){
       valor=(fruta[i].length()*0.5)/100.0;
       Valores.add(fruta[i],valor);
    }
  }
  private static void consultaFruto(Fruto f){
    if(f!=null){
      System.out.print("nombre: "+f.getNombre()+"; peso: "+p04.mrf(f.getPeso())+"; estado: "+f.getEstado());
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
           p04.consultaFruto(producted[i]);
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
          p04.consultaPlanta(h[i][j]);
        }
      }
    }
  }
  public static void main(String[] args){
    ArrayList<Planta> plantitas=p04.creaSemillas();
    ArrayList<String> frutos=p04.names();
    p04.generaRelaciones();
    Huerta finca=new Huerta(8,4);
    Huerta campo=new Huerta(3,2);
    Persona campesino=new Inmune("Galadriel Moror");
    p04.localiza(finca,campo);
    campesino.paseo();
    boolean change=false;
    for(int i=0;i<plantitas.size();i++){
      change=finca.planta(plantitas.get(i));
      System.out.println("Se ha plantado la "+plantitas.get(i).getNombre()+"? -> "+change);
    }
    System.out.println("-------- Estado de la finca despues de plantar ------------------------");
    p04.consultaHuerto(finca.getHuerto());    
    int changes=0,veces=0;
    ArrayList<String> adultas=null;
    for(int i=0;i<plantitas.size();i++){
      if(plantitas.get(i) instanceof Trifido){
        Trifido bad=(Trifido)plantitas.get(i);
        changes=bad.abona();
        System.out.println("A "+bad.getNombre()+" le entro hambre -> "+changes);
      }
    }
    System.out.println("-------- Estado de la finca despues del ataque ------------------------");
    p04.consultaHuerto(finca.getHuerto());
    for(int i=0;i<plantitas.size();i++){
        String n=plantitas.get(i).getNombre();
        changes=finca.abona(1,n);
    }
    System.out.println("-------- Estado de las fincas despues de abonar ------------------------");
    System.out.println("ORIGEN:");
    p04.consultaHuerto(finca.getHuerto());
    System.out.println("DESTINO:");
    p04.consultaHuerto(campo.getHuerto());
  }
}
