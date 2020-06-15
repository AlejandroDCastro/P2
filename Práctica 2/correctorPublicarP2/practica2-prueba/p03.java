/**
* @author Alicia Garrido Alenda
* Se crea una serie de habitantes y un clan. Se crean productos y terrenos donde se colocan los productos.
* El clan conquista los terrenos y se hace que los miembros del clan recolecten en dichos terrenos.
* Se crea un blanco y se invoca tributa de todos los miembros del clan con este mistico.
* Se invoca plegaria de todos los miembros del clan.
* Se muestra por pantalla el resultado de cada accion realizada.
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

   private static int[] creaTipos(){
     int[] tipos={1,2,3,4,5,6}; //Vegetal,Animal,Piedra,Madera,Edificado,Filosofal
     return tipos;
   }

   private static String[][] creaNombres(){
     String[][] names={
     {"brocoli", "berza", "espinaca", "lechuga romana","comote", "zanahoria", "calabaza de invierno","lentejas","frijoles secos","guisantes","ciruelas"},
     {"vaca","cerdo","pollo","conejo","borrego","cabra","pavo","avestruz","venado","liebre","codorniz"},
     {"agata","amatista","cuarzo","alejandrita","malaquita","rodonita","pirita","kunzita","esmeralda","datolita","rubi"},
     {"caoba","teca","mindi","rattan","abaca","cerezo","fresno","haya","nogal","palisandro","ebano"},
     {"muralla","valla","cuadra","baluarte","apeadero","torre","porton","establo","pared","cercado","cenobio"},
     {"rodocrosita","aventurina","actinolita","amazonita","ambligonita","blenda","cianita","cinabrio","esfarelita","galena","labradorita"}
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

   private static void colocaProductos(ArrayList<Terreno> solares,ArrayList<Producto> creados){
     if(solares!=null){
       for(int k=0;k<solares.size();k++){
         Terreno solar=solares.get(k);
         for(int i=0;i<solar.getFilas()&&creados.size()>0;i++)
           for(int j=0;j<solar.getColumnas()&&creados.size()>0;j++)
             solar.coloca(creados.remove(0),i,j);
       }
     }
   }
   
   private static ArrayList<Terreno> generaFeudo(){
     ArrayList<Terreno> finca=new ArrayList<Terreno>();
     int f=4,c=3;
     for(int i=0;i<3;i++){
       finca.add(new Terreno(f++,c++));
     }
     return finca;
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
           p03.muestraTipo(solar.consultaTipo(i,j));
         }
         System.out.println();
       }
     }
   }
   
   private static void consultaFeudo(ArrayList<Terreno> reino){
     if(reino!=null){
       for(int i=0;i<reino.size();i++){
          System.out.println("Terreno "+i+":");
          p03.consultaTerreno(reino.get(i));
       }
     }
   }

   private static void veoDatos(Producto p){
     if(p!=null){
       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+p03.mrf(p.getPeso()));
     }
   }
   
   private static void muestraProductos(ArrayList<Producto> lista){
    if(lista!=null){
     System.out.println("Total de productos: "+lista.size());
     for(int j=0;j<lista.size();j++)
         p03.veoDatos(lista.get(j));
    }
   }
   
   private static String[] creaNombresH(){
     String[] name={"Neakail","Frang","Carlton"};
     return name;
   }
   
   private static String[] creaNombresM(){
     String[] name={"Eara","Lair","Ishbel"};
     return name;
   }
   
   private static void creaPoblacion(){
     String[] men=p03.creaNombresH();
     String[] women=p03.creaNombresM();
     String clan=new String("McAllary");
     Habitante neo=null;
     int ih=0,im=0;
     for(int i=0;i<men.length&&i<women.length;i++){
       String nameh=new String(men[i]+" "+clan);
       String namem=new String(women[i]+" "+clan);
       if(i%2==0){
        neo=new Plebeyo(nameh,'H');
        neo=new Plebeyo(namem,'M');
       }
       else{
        neo=new Guerrero(nameh,'H');
        neo=new Guerrero(namem,'M');
        
       }
     }
     neo=new Plebeyo(new String("Mai McMeen"),'M');
   }
   
   
   
   private static void consultaHabitante(Habitante h){
     if(h!=null){
       System.out.println("Nombre: "+h.getNombre()+"; Vigor: "+p03.mrf(h.getVigor())+"; Clan: "+h.getClan());
       System.out.println("Cesta:");
       p03.muestraProductos(h.getCesta());
       if(h instanceof Guerrero){
         Guerrero bully=(Guerrero)h;
         System.out.println("Armamento:");
         p03.muestraProductos(bully.getArmamento());
       }
     }
   }
   private static void recolecta(Clan mc){
    if(mc!=null){
      ArrayList<Habitante> gente=mc.getMiembros();
      Guerrero bully=null;
      Plebeyo servile=null;
      int f=0,can=0,ter=0,i=0;
      if(gente!=null){
        System.out.println("Habitantes del clan "+mc.getNombre());
        for(;i<gente.size();i++){
          Habitante actual=gente.get(i);
          if(actual instanceof Plebeyo){
            servile=(Plebeyo)actual;
            can=servile.recolecta(ter,4);
          }
          else if(actual instanceof Guerrero){
            bully=(Guerrero)actual;
            can=bully.recolecta(ter);
          }
          System.out.println("Productos recolectados por "+actual.getNombre()+": "+can);
          if(can<=0)
            break;
          ter++;
        }
        ter=0;
        i--;
        for(;i<gente.size();i++){
          Habitante actual=gente.get(i);
          if(actual instanceof Plebeyo){
            servile=(Plebeyo)actual;
            can=servile.recolecta(ter,4);
          }
          else if(actual instanceof Guerrero){
            bully=(Guerrero)actual;
            can=bully.recolecta(ter);
          }
          System.out.println("Productos recolectados por "+actual.getNombre()+": "+can);
          ter++;
        }
      }
    }
   }

   private static void tributa(Clan mc,Blanco magic){
    if(mc!=null && magic!=null){
      ArrayList<Habitante> gente=mc.getMiembros();
      if(gente!=null){
        for(int i=0;i<gente.size();i++){
          Habitante actual=gente.get(i);
          double incr=actual.tributa(magic);
          System.out.println(actual.getNombre()+" incrementa su vigor al tributar en "+p03.mrf(incr));
        }
      }
    }
   }

   private static void plegaria(Clan mc){
    if(mc!=null){
      ArrayList<Habitante> gente=mc.getMiembros();
      if(gente!=null){
        boolean incr=false;
        for(int i=0;i<gente.size();i++){
          if(gente.get(i) instanceof Plebeyo)
            incr=((Plebeyo)gente.get(i)).plegaria();
          else if(gente.get(i) instanceof Guerrero)
            incr=((Guerrero)gente.get(i)).plegaria();
            System.out.println(gente.get(i).getNombre()+" incrementa su vigor con plegaria -> "+incr);
        }
      }
    }
   }
   
   public static void main(String[] args){
     p03.creaPoblacion();
     Clan unico=new Clan(new String("McAllary"));
     String[][] names=p03.creaNombres();
     Blanco magician=new Blanco("Anu");
     int[] tipos=p03.creaTipos();
     ArrayList<Habitante> pueblo=Habitante.getPoblacion();
     ArrayList<Terreno> conquista=p03.generaFeudo();
     ArrayList<Producto> materia=p03.creaProductos(names,tipos,3.2,0.2);
     p03.colocaProductos(conquista,materia);
     if(conquista!=null){
       for(int i=0;i<conquista.size();i++){
         boolean got=unico.conquista(conquista.get(i));
         System.out.println("Conquistado terreno "+i+" -> "+got);
       }   
     }
     conquista=unico.getFeudo();
     System.out.println("------------ Estado inicial del feudo --------------");
     p03.consultaFeudo(conquista);
     if(pueblo!=null){
       System.out.println("Habitantes sin clan actualmente:");
       for(int i=0;i<pueblo.size();i++){
         Habitante actual=pueblo.get(i);
         p03.consultaHabitante(actual);
       }
     }
     System.out.println("------------ Recolectando en el feudo --------------------");     
     p03.recolecta(unico);
     pueblo=unico.getMiembros();
     if(pueblo!=null){
       System.out.println("Estado de los habitantes del clan despues de la recolecta:");
       for(int i=0;i<pueblo.size();i++)
         p03.consultaHabitante(pueblo.get(i));
       System.out.println("------------ Estado final del feudo --------------");         
       p03.consultaFeudo(conquista);
     }
     System.out.println("------------ Tributa sin deidad en el clan --------------------");     
     p03.tributa(unico,magician);
     System.out.println("------------ Plegaria sin deidad en el clan --------------------");     
     p03.plegaria(unico);
   }
}
