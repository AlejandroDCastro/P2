/**
* @author Alicia Garrido Alenda
* Se crean una serie de rasgos. Se invoca setAtributo de algunos de ellos, y loTiene de otros.
* Se muestran todos los rasgos creados por pantalla. 
*/
import java.util.*;
public class p01{

   private static String[] creaNombres(){
     String[] names={"pendientes","gafas","pecas","barba","bigote","pelo","ojos","gorra","trenzas","cejas"};
     return names;
   }

   private static String[] creaAtributos(){
     String[] names={null,"rojas",null,"false","poblado",null,"azules",null,"finas","false"};
     return names;
   }

   private static ArrayList<Rasgo> creaRasgos(String[] n,String[] a){
     ArrayList<Rasgo> genericos=new ArrayList<>();
     Rasgo actual=null;
     int j=0;
     for(int i=0;i<n.length;i++){
        actual=new Rasgo(n[i]);
        if(j%2==0 && j<a.length)
          actual.setAtributo(a[j]);
        else actual.loTiene();
        j++;
        genericos.add(actual);
     }
     return genericos;
   }

   public static void main(String[] args){
     String[] names=p01.creaNombres();
     String[] atribs=p01.creaAtributos();
     ArrayList<Rasgo> varios=p01.creaRasgos(names,atribs);
     System.out.println(varios);
  }
}   
  
