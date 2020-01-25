/**
* @author Alicia Garrido Alenda
* Se invoca el metodo add de Valores con diferentes valores y cadenas.
* Se invoca getNombre de Valores para comprobar que relaciones se han creado.
* Se invoca consulta de Valores con un conjunto de cadenas, para las cuales unas veces existe una relacion creada
* y otras no. Se muestra por pantalla el resultado de cada accion.
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
       v=new double[n.length/2];
       for(int i=0,j=0;i<v.length&&j<n.length;i++,j+=2){
         v[i]=(n[j].length()*0.5)/100.0;
       }
    }
    return v;
  }
  public static void main(String[] args){
    String[] names=p01.generaCadenas();
    double[] valores=p01.generaValores(names);
    for(int i=0,j=0;i<valores.length && j<names.length ;i++,j+=2)
      Valores.add(names[j],valores[i]);
    ArrayList<String> noms=Valores.getNombres();
    System.out.println(noms);
    for(int i=0;i<names.length;i++)
     System.out.println("Valor asociado de "+names[i]+" -> "+p01.mrf(Valores.consulta(names[i])));
  }
}
