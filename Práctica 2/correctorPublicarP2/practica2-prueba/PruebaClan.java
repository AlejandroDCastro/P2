/**
 * @author Alejandro Castro Valero / Alicia Garrido Alenda
 * Prueba el metodo Clan. Se crea la poblacion de habitantes y terrenos. Se crean productos
 * que se colocan en los terrenos. Se crean dos clanes que conquistan los terrenos. Se invocan
 * los metodos laborando y adoptaDeidad de ambos clanes. Despues establecen una guerra invocando
 * el metodo enfrenta, y el perdedor gana dos miembros del clan ganador para compensar. Se
 * muestran cada una de las acciones realizadas.
 */

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class PruebaClan {

	
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
	        	 PruebaClan.muestraTipo(solar.consultaTipo(i,j));
	         }
	         System.out.println();
	       }
	     }
	   }
	   
	   private static void consultaFeudo(ArrayList<Terreno> reino){
	     if(reino!=null){
	       for(int i=0;i<reino.size();i++){
	          System.out.println("Terreno "+i+":");
	          PruebaClan.consultaTerreno(reino.get(i));
	       }
	     }
	   }

	   private static void veoDatos(Producto p){
	     if(p!=null){
	       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+PruebaClan.mrf(p.getPeso()));
	     }
	   }
	   
	   private static void muestraProductos(ArrayList<Producto> lista){
	    if(lista!=null){
	     System.out.println("Total de productos: "+lista.size());
	     for(int j=0;j<lista.size();j++)
	    	 PruebaClan.veoDatos(lista.get(j));
	    }
	   }
	   
	   private static void creaPoblacion(){
	     String nameh=new String("Alejandro"+" "+"Castro");
	     String nameg=new String("David"+" "+"Castro");
	     String namek=new String("JuanMa"+" "+"Castro");
	     String namem=new String("Isabel"+" "+"Castro");
	     String name1=new String("Florin"+" "+"Postolache");
	     String name2=new String("Ares"+" "+"Postolache");
	     String name3=new String("Javi"+" "+"Postolache");
	     String name4=new String("Cristina"+" "+"Postolache");
	     String name5=new String("Papa"+" "+"Postolache");
	     Habitante neo=null;
	     neo=new Guerrero(nameh,'H');
	     neo=new Guerrero(nameg,'H');
	     neo=new Guerrero(namek,'H');
	     neo=new Guerrero(namem,'M');
	     neo=new Guerrero(name1,'H');
	     neo=new Guerrero(name2,'H');
	     neo=new Guerrero(name3,'H');
	     neo=new Plebeyo(name4,'M');
	     neo=new Plebeyo(name5,'H');
	   }
	   
	   
	   private static ArrayList<Clan> generaClanes(){
		     ArrayList<Clan> clanes=new ArrayList<Clan>();
		     clanes.add(new Clan(new String("Castro")));
		     clanes.add(new Clan(new String("Postolache")));
		     return clanes;
		   }
	   
	   private static void consultaHabitante(Habitante h){
		     if(h!=null){
		       System.out.println("Nombre: "+h.getNombre()+"; Vigor: "+PruebaClan.mrf(h.getVigor())+"; Clan: "+h.getClan());
		       System.out.println("Cesta:");
		       PruebaClan.muestraProductos(h.getCesta());
		       if(h instanceof Guerrero){
		         Guerrero bully=(Guerrero)h;
		         System.out.println("Armamento:");
		         PruebaClan.muestraProductos(bully.getArmamento());
		       }
		     }
		   }
	
	private static void consultaAllClanes(ArrayList<Clan> clanes) {
		for(int i=0; i<2; i++) {
			System.out.println("Feudo del clan " + clanes.get(i).getNombre());
		   PruebaClan.consultaFeudo(clanes.get(i).getFeudo());
		   for(int j=0; j<clanes.get(i).getMiembros().size(); j++) {
			   PruebaClan.consultaHabitante(clanes.get(i).getMiembros().get(j));
		   }
		   System.out.print("Mistico: ");
		   if(clanes.get(i).getDeidad()!= null) {
			   System.out.print("Si, ");
			   if(clanes.get(i).getDeidad() instanceof Blanco) System.out.println("Blanco");
			   else if(clanes.get(i).getDeidad() instanceof Oscuro) System.out.println("Oscuro");
			   else System.out.println("Normal");
		   }
		   else System.out.println("No");
		}
	}
	
	private static void adoptaMistico(Clan c, Mistico m) {
		if(c.adoptaDeidad(m))
			System.out.println("SI");
		else
			System.out.println("NO");
	}
	
	   
	public static void main(String[] args) {
		PruebaClan.creaPoblacion();
		String[][] names=PruebaClan.creaNombres();
	    int[] tipos=PruebaClan.creaTipos();
	    ArrayList<Clan> clanes=PruebaClan.generaClanes();
	    ArrayList<Habitante> pueblo=null;
	    ArrayList<Terreno> conquista=PruebaClan.generaFeudo();
	    ArrayList<Producto> materia=PruebaClan.creaProductos(names,tipos,3.2,0.2);
	    PruebaClan.colocaProductos(conquista,materia);
	    if(conquista!=null){
	        for(int i=0;i<conquista.size()&&i<clanes.size();i++){
	          boolean got=clanes.get(i).conquista(conquista.get(i));
	          System.out.println("Conquistado terreno "+i+" -> "+got);
	        }   
	   }
	   ArrayList<Mistico> misticos = new ArrayList<Mistico>();
	   misticos.add(new Blanco("Klan"));
	   misticos.add(new Oscuro());
	   System.out.println("---------------------------------------------");
	   System.out.println("LOS CLANES 1: EL COMIENZO");
	   PruebaClan.consultaAllClanes(clanes);
	   System.out.println("---------------------------------------------");
	   System.out.println("LOS CLANES 2: LABORANDO");
	   System.out.println("El Clan " + clanes.get(0).getNombre() + "ha recogido en total: " + clanes.get(0).laboro(5) + " productos");
	   System.out.println("El Clan " + clanes.get(1).getNombre() + "ha recogido en total: " + clanes.get(1).laboro(10) + " productos");
	   PruebaClan.consultaAllClanes(clanes);
	   System.out.println("---------------------------------------------");
	   System.out.println("LOS CLANES 3: ADOPTANDO DEIDADES");
	   for(int i=0; i<clanes.size() && i<misticos.size(); i++) {
		   System.out.print("Los " + clanes.get(i).getNombre() + "s adoptan: ");
		   PruebaClan.adoptaMistico(clanes.get(i), misticos.get(i));
	   }
	   System.out.print("Los " + clanes.get(0).getNombre() + "s adoptan: ");
	   PruebaClan.adoptaMistico(clanes.get(0), new Mistico());
	   PruebaClan.consultaAllClanes(clanes);
	   System.out.println("---------------------------------------------");
	   System.out.println("LOS CLANES 3: CASTROS vs POSTOLACHES");
	   System.out.println("El clan " + clanes.get(0).enfrenta(clanes.get(1)) + " los ha destrozado completamente.");
	   PruebaClan.consultaAllClanes(clanes);
	   System.out.println("---------------------------------------------");
	   System.out.println("LOS CLANES 4: LO SIENTO FLOREN");
	   System.out.println("Me quedo con tu hermana y tu padre.");
	   System.out.println("Me quedo con Papa Postolache y Cristina Postolache.");
	   clanes.get(0).acoge(clanes.get(1).destierra("Papa Postolache"));
	   clanes.get(0).acoge(clanes.get(1).destierra("Cristina Postolache"));
	   PruebaClan.consultaAllClanes(clanes);
	}

}
