/** @author Alejandro Castro Valero / Alicia Garrido Alenda
 * Prueba el metodo Demoledor version 2. Se crean dos terrenos super grandes y productos que se 
 * colocan en los terrenos. Se crean dos demoledores y un plebeyo. Se invoca domestica del plebeyo
 * con una de las bestias. Se invoca pasturea, busca, getFuerza, getNombre, getAmo y getAmuleto de cada
 * bestia. Se muestra por pantalla el resultado de cada accion.
 */

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class PruebaDemoledorV2 {
	
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

	   private static void colocaProductos(Terreno solar,Terreno campo,ArrayList<Producto> creados){
	     if(solar!=null){
	       int f=0,c=0;
	       for(int i=0;i<solar.getFilas()&&creados.size()>0;i++)
	         for(int j=0;j<solar.getColumnas()&&creados.size()>0;j++)
	             solar.coloca(creados.remove(0),i,j);
	     }
	     if(campo!=null){
	       for(int i=0;i<campo.getFilas()&&creados.size()>0;i++)
	         for(int j=0;j<campo.getColumnas()&&creados.size()>0;j++)
	           campo.coloca(creados.remove(0),i,j);
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
	           PruebaDemoledorV2.muestraTipo(solar.consultaTipo(i,j));
	         }
	         System.out.println();
	       }
	     }
	   }
	   
	   private static void veoDatos(Producto p){
	     if(p!=null){
	       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+PruebaDemoledorV2.mrf(p.getPeso()));
	     }
	   }
	   
	   private static void muestraProductos(ArrayList<Producto> lista){
	    if(lista!=null){
	     System.out.println("Total de productos: "+lista.size());
	     for(int j=0;j<lista.size();j++)
	         PruebaDemoledorV2.veoDatos(lista.get(j));
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
	       System.out.print("Fuerza: "+PruebaDemoledorV2.mrf(f)+"; Amuleto -> ");
	       PruebaDemoledorV2.veoDatos(a);
	     }
	   }


	   public static void main(String[] args){
	     int[] tipos=PruebaDemoledorV2.creaTipos();
	     String[][] names=PruebaDemoledorV2.creaNombres();
	     double pinicial=1.5;
	     ArrayList<Producto> existencias=PruebaDemoledorV2.creaProductos(names,tipos,pinicial,0.2);
	     Plebeyo doe=new Plebeyo("Eara Duncan",'M');
	     Bestia salvaje=new Demoledor(7);
	     Bestia casera=new Demoledor(12);
	     Terreno solar=new Terreno(6,5);
	     Terreno campo=new Terreno(7,7);
	     PruebaDemoledorV2.colocaProductos(solar,campo,existencias);
	     System.out.println("Terreno solar:");
	     PruebaDemoledorV2.consultaTerreno(solar);
	     System.out.println("Terreno campo:");
	     PruebaDemoledorV2.consultaTerreno(campo);
	     boolean got=doe.domestica(casera,"PiriPiri");
	     String name=casera.getNombre();
	     if(name!=null)
	      System.out.println(doe.getNombre()+" domestica con exito a "+name+" -> "+got);
	     else
	      System.out.println("algo ha fallado cuando "+doe.getNombre()+" ha intentado domesticar -> "+got);
	     ArrayList<Producto> ret=casera.pasturea(campo,2);
	     ArrayList<Producto> reto=salvaje.pasturea(solar,0);
	     System.out.println("------- Pasturea sin amuleto ------------");
	     name=casera.getNombre();
	     if(name!=null)
	      System.out.println("Productos devueltos por pasturea de "+name+" en campo ("+PruebaDemoledorV2.mrf(casera.getFuerza())+")");
	     else
	      System.out.println("Productos devueltos por pasturea de sin nombre en campo("+PruebaDemoledorV2.mrf(casera.getFuerza())+")");
	     PruebaDemoledorV2.muestraProductos(ret);
	     name=salvaje.getNombre();
	     if(name!=null)
	      System.out.println("Productos devueltos por pasturea de "+name+" en solar ("+PruebaDemoledorV2.mrf(salvaje.getFuerza())+")");
	     else
	      System.out.println("Productos devueltos por pasturea de sin nombre en solar ("+PruebaDemoledorV2.mrf(salvaje.getFuerza())+")");
	     PruebaDemoledorV2.muestraProductos(reto);
	     System.out.println("Terreno solar:");
	     PruebaDemoledorV2.consultaTerreno(solar);
	     System.out.println("Terreno campo:");
	     PruebaDemoledorV2.consultaTerreno(campo);
	     name=casera.busca(campo);
	     if(name!=null)
	       System.out.println("encontrado amuleto "+name);
	     else
	       System.out.println("no ha sido encontrado amuleto");
	     name=salvaje.busca(solar);
	     if(name!=null)
	       System.out.println("encontrado amuleto "+name);
	     else
	       System.out.println("no ha sido encontrado amuleto");
	     ret=casera.pasturea(campo,1);
	     reto=salvaje.pasturea(solar,1);
	     System.out.println("------- Pasturea con amuleto ------------");
	     name=casera.getNombre();
	     if(name!=null)
	      System.out.println("Productos devueltos por pasturea de "+name+" en campo ("+PruebaDemoledorV2.mrf(casera.getFuerza())+")");
	     else
	      System.out.println("Productos devueltos por pasturea de sin nombre en campo ("+PruebaDemoledorV2.mrf(casera.getFuerza())+")");
	     PruebaDemoledorV2.muestraProductos(ret);
	     name=salvaje.getNombre();
	     if(name!=null)
	      System.out.println("Productos devueltos por pasturea de "+name+" en solar ("+PruebaDemoledorV2.mrf(salvaje.getFuerza())+")");
	     else
	      System.out.println("Productos devueltos por pasturea de sin nombre en solar ("+PruebaDemoledorV2.mrf(salvaje.getFuerza())+")");
	     PruebaDemoledorV2.muestraProductos(reto);
	     System.out.println("Terreno solar:");
	     PruebaDemoledorV2.consultaTerreno(solar);
	     System.out.println("Terreno campo:");
	     PruebaDemoledorV2.consultaTerreno(campo);
	     PruebaDemoledorV2.datosBestia(casera);
	     PruebaDemoledorV2.datosBestia(salvaje);
	  }

}
