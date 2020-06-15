/**
* @author Alicia Garrido Alenda
* Se crea una serie de habitantes y un clan. Se crean productos y terrenos donde se colocan los productos.
* El clan conquista los terrenos y se hace que los miembros del clan recolecten en dichos terrenos, y si
* son guerreros que recolecten además como habitante en otro.
* Se crea una serie de bestias y algunas son domesticadas por algunos plebeyos.
* Se invoca trueque de los miembros del clan de unos con otros.
* Se muestra por pantalla el resultado de cada accion realizada.
*/
import java.text.*;
import java.util.*;
public class p05{
   
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
           p05.muestraTipo(solar.consultaTipo(i,j));
         }
         System.out.println();
       }
     }
   }
   
   private static void consultaFeudo(ArrayList<Terreno> reino){
     if(reino!=null){
       for(int i=0;i<reino.size();i++){
          System.out.println("Terreno "+i+":");
          p05.consultaTerreno(reino.get(i));
       }
     }
   }

   private static void veoDatos(Producto p){
     if(p!=null){
       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+p05.mrf(p.getPeso()));
     }
   }
   
   private static void muestraProductos(ArrayList<Producto> lista){
    if(lista!=null){
     System.out.println("Total de productos: "+lista.size());
     for(int j=0;j<lista.size();j++)
         p05.veoDatos(lista.get(j));
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
     String[] men=p05.creaNombresH();
     String[] women=p05.creaNombresM();
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
   }
   
   
   
   private static void consultaHabitante(Habitante h){
     if(h!=null){
       System.out.println("Nombre: "+h.getNombre()+"; Vigor: "+p05.mrf(h.getVigor())+"; Clan: "+h.getClan());
       System.out.println("Cesta:");
       p05.muestraProductos(h.getCesta());
       if(h instanceof Guerrero){
         Guerrero bully=(Guerrero)h;
         System.out.println("Armamento:");
         p05.muestraProductos(bully.getArmamento());
       }
       else if(h instanceof Plebeyo){
         Plebeyo servile=(Plebeyo)h;
         Bestia pet=servile.getBestiola();
         if(pet!=null && pet.getNombre()!=null){
          System.out.print("Bestiola: "+pet.getNombre());
          Plebeyo amo=pet.getAmo();
          String name=new String("sin amo");
          if(amo!=null)
            name=amo.getNombre();
          System.out.println(" -> Amo: "+name);
         }
       }
     }
   }

   private static void recolecta(Habitante h, Producto o){
     Terreno eral=new Terreno(1,1);
     eral.coloca(o,0,0);
     h.recolecta(eral,o.getTipo());
   }

   private static void recolecta(Habitante h){
     Producto[] tipos=new Producto[5];
     tipos[0]=new Producto(0.1,1,new String("chiribias"));
     tipos[1]=new Producto(0.1,2,new String("calamares"));
     tipos[2]=new Producto(0.1,3,new String("carbon"));
     tipos[3]=new Producto(0.1,4,new String("rododendro"));
     tipos[4]=new Producto(0.1,6,new String("esperiac"));
     for(int i=0;i<tipos.length;i++)
       p05.recolecta(h,tipos[i]);
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
            p05.recolecta(actual);
          }
          System.out.println("Productos recolectados por "+actual.getNombre()+": "+can);
          ter++;
          if(ter>=3)
            ter=0;
        }
      }
    }
   }

   private static void domestica(Clan mc,ArrayList<Bestia> manada){
    if(mc!=null){
      ArrayList<Habitante> gente=mc.getMiembros();
      String[] name={"Chibi","Nefertaria","Bunny","Okiko","Luna"};
      Plebeyo servile=null;
      int f=0,i=0;
      if(gente!=null && manada!=null){
        for(;i<gente.size();i++){
          Habitante actual=gente.get(i);
          if(actual instanceof Plebeyo){
            servile=(Plebeyo)actual;
            if(f<manada.size()){
             boolean got=servile.domestica(manada.get(f),name[f]);
             System.out.println(servile.getNombre()+" domestica a "+name[f]+" -> "+got);
             f++;
            }
          }
        }
      }
    }
   }
   
   private static ArrayList<Bestia> creaManada(){
     ArrayList<Bestia> manada=new ArrayList<>();
     for(int i=0;i<3;i++)
       manada.add(new Bestia(i+3));
     return manada;
   }
   
   private static void trueque(ArrayList<Habitante> pueblo){
     for(int i=0;i<pueblo.size()-1;i++){
       Habitante actual=pueblo.get(i);
       Habitante sig=pueblo.get(i+1);
       ArrayList<String> intercambio=actual.trueque(sig);
       if(intercambio!=null && intercambio.size()>0)
           System.out.println(actual.getNombre()+" hace trueque con "+sig.getNombre()+" -> "+intercambio);
     }
   }

   
   
   public static void main(String[] args){
     p05.creaPoblacion();
     Clan unico=new Clan(new String("McAllary"));
     String[][] names=p05.creaNombres();
     int[] tipos=p05.creaTipos();
     ArrayList<Habitante> pueblo=null;
     ArrayList<Terreno> conquista=p05.generaFeudo();
     ArrayList<Producto> materia=p05.creaProductos(names,tipos,3.2,0.2);
     ArrayList<Bestia> manada=p05.creaManada();
     p05.colocaProductos(conquista,materia);
     if(conquista!=null){
       for(int i=0;i<conquista.size();i++){
         boolean got=unico.conquista(conquista.get(i));
         System.out.println("Conquistado terreno "+i+" -> "+got);
       }   
     }
     conquista=unico.getFeudo();
     System.out.println("------------ Estado inicial del feudo --------------");     
     p05.consultaFeudo(conquista);
     System.out.println("------------ Recolectando en el feudo --------------------");     
     p05.recolecta(unico);
     System.out.println("------------ Plebeyos domesticando bestias ---------------");     
     p05.domestica(unico,manada);
     pueblo=unico.getMiembros();
     if(pueblo!=null){
       System.out.println("Estado de los habitantes del clan despues de recolecta y domestica:");
       for(int i=0;i<pueblo.size();i++)
         p05.consultaHabitante(pueblo.get(i));
       System.out.println("------------ Realizando trueques --------------------");
       p05.trueque(pueblo);
       for(int i=0;i<pueblo.size();i++)
         p05.consultaHabitante(pueblo.get(i));
     }
   }
}
