/**
* @author Alicia Garrido Alenda
* Se crean dos guerreros y sus dos clanes. Se crean productos y terrenos donde se colocan los productos.
* Cada clan conquista unos terrenos y se hace que los miembros del clan recolecten en dichos terrenos.
* Se destierra a uno de los guerreros de su clan y se hace que ambos guerreros luchen.
* Se muestra por pantalla el resultado de cada accion realizada.
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
     for(int i=0;i<2;i++){
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
           p04.muestraTipo(solar.consultaTipo(i,j));
         }
         System.out.println();
       }
     }
   }
   
   private static void consultaFeudo(ArrayList<Terreno> reino){
     if(reino!=null){
       for(int i=0;i<reino.size();i++){
          System.out.println("Terreno "+i+":");
          p04.consultaTerreno(reino.get(i));
       }
     }
   }

   private static void veoDatos(Producto p){
     if(p!=null){
       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+p04.mrf(p.getPeso()));
     }
   }
   
   private static void muestraProductos(ArrayList<Producto> lista){
    if(lista!=null){
     System.out.println("Total de productos: "+lista.size());
     for(int j=0;j<lista.size();j++)
         p04.veoDatos(lista.get(j));
    }
   }
   
   private static void creaPoblacion(){
     String nameh=new String("Neakail"+" "+"McAllary");
     String namem=new String("Ishbel"+" "+"Duncan");
     Habitante neo=null;
     neo=new Guerrero(nameh,'H');
     neo=new Guerrero(namem,'M');
   }
   
   
   
   private static void consultaHabitante(Habitante h){
     if(h!=null){
       System.out.println("Nombre: "+h.getNombre()+"; Vigor: "+p04.mrf(h.getVigor())+"; Clan: "+h.getClan());
       System.out.println("Cesta:");
       p04.muestraProductos(h.getCesta());
       if(h instanceof Guerrero){
         Guerrero bully=(Guerrero)h;
         System.out.println("Armamento:");
         p04.muestraProductos(bully.getArmamento());
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
      }
    }
   }
   
   private static ArrayList<Clan> generaClanes(){
     ArrayList<Clan> clanes=new ArrayList<Clan>();
     clanes.add(new Clan(new String("McAllary")));
     clanes.add(new Clan(new String("Duncan")));
     return clanes;
   }
   public static void main(String[] args){
     p04.creaPoblacion();
     String[][] names=p04.creaNombres();
     int[] tipos=p04.creaTipos();
     ArrayList<Clan> clanes=p04.generaClanes();
     ArrayList<Habitante> pueblo=null;
     ArrayList<Terreno> conquista=p04.generaFeudo();
     ArrayList<Producto> materia=p04.creaProductos(names,tipos,3.2,0.2);
     p04.colocaProductos(conquista,materia);
     if(conquista!=null){
       for(int i=0;i<conquista.size()&&i<clanes.size();i++){
         boolean got=clanes.get(i).conquista(conquista.get(i));
         System.out.println("Conquistado terreno "+i+" -> "+got);
       }   
     }
     for(int i=0;i<clanes.size();i++){
       conquista=clanes.get(i).getFeudo();
       System.out.println("------ Estado inicial del feudo de "+clanes.get(i).getNombre()+" -------------");
       p04.consultaFeudo(conquista);
       System.out.println("------------ Recolectando en el feudo --------------------");
       p04.recolecta(clanes.get(i));
       pueblo=clanes.get(i).getMiembros();
       if(pueblo!=null){
         System.out.println("Estado de los habitantes del clan "+clanes.get(i).getNombre()+" despues de la recolecta:");
         for(int j=0;j<pueblo.size();j++)
           p04.consultaHabitante(pueblo.get(j));
       }
     }
     String elegido=new String("Ishbel"+" "+"Duncan");
     Habitante desterrado=clanes.get(1).destierra(elegido);
     if(desterrado!=null && clanes.get(0).getMiembros()!=null && clanes.get(0).getMiembros().size()>0){
      Habitante solito=clanes.get(0).getMiembros().get(0);
      if(solito instanceof Guerrero && desterrado instanceof Guerrero){
         System.out.println("--------- Lucha de guerreros ("+solito.getNombre()+" vs "+desterrado.getNombre()+") ---------------");         
         Guerrero bully=(Guerrero)solito;
         Guerrero exiled=(Guerrero)desterrado;
         int rdo=exiled.lucha(bully);
         if(rdo==1)
           System.out.println("El ganador es "+bully.getNombre());
         else if(rdo==2)
           System.out.println("El ganador es "+exiled.getNombre());
         else System.out.println("Empate tecnico");
      }
      p04.consultaHabitante(solito);
      p04.consultaHabitante(desterrado);
     }
   }
}
