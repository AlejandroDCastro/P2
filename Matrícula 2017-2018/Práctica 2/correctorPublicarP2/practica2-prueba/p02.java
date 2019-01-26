/**
* @author Alicia Garrido Alenda
* Se crea un terreno y productos que se colocan en el terreno. Se crea
* una bestia, un demoledor, un plebeyo y un oscuro. Se invoca domestica del 
* plebeyo con la bestia. Se invoca busca de la bestia con el terreno. 
* Se invoca malogra del oscuro con el plebeyo. Se invoca getBestiola del plebeyo
* y se consultan sus datos. Se invoca exorciza del demoledor.
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
  
   private static int[] creaTipos(){
     int[] tipos={1,2,3,4,5,6}; //Vegetal,Animal,Piedra,Madera,Edificado,Filosofal
     return tipos;
   }

   private static String[][] creaNombres(){
     String[][] names={
     {"brocoli", "berza", "espinaca", "lechuga romana","comote", "zanahoria", "calabaza de invierno","lentejas","frijoles secos","guisantes"},
     {"vaca","cerdo","pollo","conejo","borrego","cabra","pavo","avestruz","venado","liebre"},
     {"agata","amatista","cuarzo","alejandrita","malaquita","rodonita","pirita","kunzita","esmeralda","datolita"},
     {"caoba","teca","mindi","rattan","abaca","cerezo","fresno","haya","nogal","palisandro"},
     {"muralla","valla","cuadra","baluarte","apeadero","torre","porton","establo","pared","cercado"},
     {"rodocrosita","aventurina","actinolita","amazonita","ambligonita","blenda","cianita","cinabrio","esfarelita","galena"}
     };
     return names;
   }

   private static ArrayList<Producto> creaProductos(String[][] names,int[] tipos,double peso,double incr){
     ArrayList<Producto> creados=new ArrayList<Producto>();
     int tp=0;
     Producto creado=null;
     for(int j=0;j<names[0].length;j++){
       for(int i=0;i<names.length;i++){
         creado=new Producto(peso,tipos[tp],names[i][j]);
         creados.add(creado);
         peso+=incr;
         tp++;
         if(tp>=tipos.length) tp=0;
       }
     }
     return creados;
   }

   private static void colocaProductos(Terreno solar,ArrayList<Producto> creados){
     if(solar!=null){
       int f=0,c=0;
       for(int i=0;i<solar.getFilas()&&creados.size()>0;i++)
         for(int j=0;j<solar.getColumnas()&&creados.size()>0;j++)
             solar.coloca(creados.remove(0),i,j);
     }
   } 

   private static void muestraTipo(int tipo){
     switch(tipo){
       case 1:
         System.out.print(" V");
         break;
       case 2:
         System.out.print(" A");
         break;
       case 3:
         System.out.print(" P");
         break;
       case 4:
         System.out.print(" M");
         break;
       case 5:
         System.out.print(" E");
         break;
       case 6:
         System.out.print(" F");
         break;
       default:
         System.out.print(" x");
     }

   }

   private static void consultaTerreno(Terreno solar){
     if(solar!=null){
       for(int i=0;i<solar.getFilas();i++){
         for(int j=0;j<solar.getColumnas();j++){
           p02.muestraTipo(solar.consultaTipo(i,j));
         }
         System.out.println();
       }
     }
   }
   
   private static void veoDatos(Producto p){
     if(p!=null){
       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+p02.mrf(p.getPeso()));
     }
   }
   
   private static void muestraProductos(ArrayList<Producto> lista){
    if(lista!=null){
     System.out.println("Total de productos: "+lista.size());
     for(int j=0;j<lista.size();j++)
         p02.veoDatos(lista.get(j));
    }
   }
   
   private static void datosBestia(Bestia pet){
     if(pet!=null){
       double f=pet.getFuerza();
       Producto a=pet.getAmuleto();
       String s=pet.getNombre();
       Plebeyo d=pet.getAmo();
       if(s!=null)
         System.out.print("Nombre: "+s+"; ");
       else
         System.out.print("Sin nombre; ");
       if(d!=null)
         System.out.print("Amo: "+d.getNombre()+"; ");
       else
         System.out.print("Sin amo; ");
       System.out.print("Fuerza: "+p02.mrf(f)+"; Amuleto -> ");
       p02.veoDatos(a);
     }
   }


   public static void main(String[] args){
     int[] tipos=p02.creaTipos();
     String[][] names=p02.creaNombres();
     double pinicial=1.5;
     ArrayList<Producto> existencias=p02.creaProductos(names,tipos,pinicial,0.2);
     Plebeyo doe=new Plebeyo("Eara Duncan",'M');
     Bestia casera=new Bestia(9.5);
     Terreno campo=new Terreno(3,4);
     Oscuro saruman=new Oscuro();
     p02.colocaProductos(campo,existencias);
     System.out.println("Terreno campo:");
     p02.consultaTerreno(campo);
     boolean got=doe.domestica(casera,"PiriPiri");
     String name=casera.getNombre();
     if(name!=null)
      System.out.println(doe.getNombre()+" domestica con exito a "+name+" -> "+got);
     else
      System.out.println("algo ha fallado cuando "+doe.getNombre()+" ha intentado domesticar -> "+got);
     name=casera.busca(campo);
     if(name!=null)
       System.out.println("encontrado amuleto "+name);
     else
       System.out.println("no ha sido encontrado amuleto");
     System.out.println("Terreno campo:");
     p02.consultaTerreno(campo);
     got=saruman.malogra(doe);
     System.out.println("Saruman ha malogrado a "+doe.getNombre()+" -> "+got);
     casera=doe.getBestiola();
     if(casera instanceof Demoledor){
      System.out.print("la bestia ha sido hechizada -> ");
      p02.datosBestia(casera);
      Demoledor salvaje=(Demoledor)casera;
      casera=salvaje.exorciza();
      if(!(casera instanceof Demoledor)){
        System.out.print("la bestia ha sido liberada de su hechizo -> ");
        p02.datosBestia(casera);
      }
      
     }
  }
}   
  
