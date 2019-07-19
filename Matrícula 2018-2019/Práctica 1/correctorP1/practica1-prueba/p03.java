/**
* @author Alicia Garrido Alenda
* Se invoca el metodo add de Valores con diferentes valores y cadenas.
* Se crea un fruto cuyo nombre existe en la clasificacion de Valores y se transforma el fruto
* hasta que sea comestible. Se muestra por pantalla el resultado de cada accion.
*/
import java.text.*;
import java.util.*;
public class p03{
  private static String mrf(double db){
     Locale lengua=new Locale("en");
     DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
     DecimalFormat formato=new DecimalFormat("0.000",chars);

     return(formato.format(db).toString());
   }

  private static String[] generaCadenas(){
    String[] names={"ciruela","tomate","Papaya","Chirimbolinos","fresa","naranja","mandarina","platano","Uva Moscatel","cereza",
                    "mango","manzana","frambuesa","durazno","coco","uva negra","kiwi","aguacate","granada","guayas","pera",
                    "Guanabana","zarzamora","papaya","pomelo","maracuya","mora","carambola","lima","tamarindo","jitomate",
                    "higos","albaricoque","chirimoya","nectarina","pitaya","arandanos","litchi","tuna","rambutan","melon",
                    "grocella","mamey","mangosteen","uchuva","jicama","tejocote","noni","Caymito","durian","naranjilla",
                    "kiwano","yaca","kumquat","nuez","cacahuete","calabaza","judias","pimiento","zanahoria"};
    return names;
  }
  
  private static double[] generaValores(String[] n){
    double[] v=null;
    if(n!=null){
       v=new double[n.length];
       for(int i=0;i<v.length;i++){
         v[i]=(n[i].length()*0.5)/100.0;
       }
    }
    return v;
  }
  public static void main(String[] args){
    String[] names=p03.generaCadenas();
    double[] valores=p03.generaValores(names);
    Fruto prohibido=null;
    boolean secome=false;
    for(int i=0;i<valores.length && i<names.length ;i++)
      Valores.add(names[i],valores[i]);
    prohibido=new Fruto(names[names.length/2]);
    System.out.println("estado de "+prohibido.getNombre()+" -> "+prohibido.getEstado());
    while(!secome){
       secome=prohibido.transforma(1);
       System.out.println("peso de "+prohibido.getNombre()+" despues de transforma("+secome+") -> "+p03.mrf(prohibido.getPeso()));
    }
    System.out.println("estado de "+prohibido.getNombre()+" -> "+prohibido.getEstado());
    for(int i=0;i<4;i++){
       secome=prohibido.transforma(i);
       System.out.println("peso de "+prohibido.getNombre()+" despues de transforma("+secome+") -> "+p03.mrf(prohibido.getPeso()));
    }
  }
}
