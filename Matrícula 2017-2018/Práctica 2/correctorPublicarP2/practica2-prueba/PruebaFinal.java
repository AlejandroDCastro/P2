/**
 * @author Alejandro Castro Valero / Alicia Garrido Alenda
 * Prueba los metodos 'vasallaje', 'alimenta' y 'tutela' de la clase Plebeyo, y el metodo 'acoge'
 * de la clase Guerrero. Para ello, crea unos habitantes y dos clanes. Se crean productos que
 * se colocan en dos cuatro terrenos que se crean, dos terrenos por clan. Se invoca vasallaje con uno
 * de los clanes, para todo su feudo. Se crea una bestia que domestica un plebeyo del otro clan,
 * y se invoca alimenta con el segundo terreno de su feudo. Se invoca el metodo acoge, con el primer guerrero del clan
 * usado al inicio, sobre todos los miembros de su clan. Se muestran los resultados de cada accion realizada.
 */

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class PruebaFinal {

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
	           PruebaFinal.muestraTipo(solar.consultaTipo(i,j));
	         }
	         System.out.println();
	       }
	     }
	   }
	   
	   private static void veoDatos(Producto p){
	     if(p!=null){
	       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+PruebaFinal.mrf(p.getPeso()));
	     }
	   }
	   
	   private static void muestraProductos(ArrayList<Producto> lista){
	    if(lista!=null){
	     System.out.println("Total de productos: "+lista.size());
	     for(int j=0;j<lista.size();j++)
	         PruebaFinal.veoDatos(lista.get(j));
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
	       System.out.print("Fuerza: "+PruebaFinal.mrf(f)+"; Amuleto -> ");
	       PruebaFinal.veoDatos(a);
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
		     neo=new Plebeyo(namem,'M');
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
	   
	   private static void consultaAllClanes(ArrayList<Clan> clanes) {
			for(int i=0; i<2; i++) {
				System.out.println("Feudo del clan " + clanes.get(i).getNombre());
			   PruebaFinal.consultaFeudo(clanes.get(i).getFeudo());
			   for(int j=0; j<clanes.get(i).getMiembros().size(); j++) {
				   PruebaFinal.consultaHabitante(clanes.get(i).getMiembros().get(j));
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
	   
	   
	   private static void consultaFeudo(ArrayList<Terreno> reino){
		     if(reino!=null){
		       for(int i=0;i<reino.size();i++){
		          System.out.println("Terreno "+i+":");
		          PruebaFinal.consultaTerreno(reino.get(i));
		       }
		     }
		   }
	   
	   
	   private static void consultaHabitante(Habitante h){
		     if(h!=null){
		       System.out.println("Nombre: "+h.getNombre()+"; Vigor: "+PruebaFinal.mrf(h.getVigor())+"; Clan: "+h.getClan());
		       System.out.println("Cesta:");
		       PruebaFinal.muestraProductos(h.getCesta());
		       if(h instanceof Guerrero){
		         Guerrero bully=(Guerrero)h;
		         System.out.println("Armamento:");
		         PruebaFinal.muestraProductos(bully.getArmamento());
		       }
		     }
		   }
	   
	
	   private static ArrayList<Terreno> generaFeudo(){
		     ArrayList<Terreno> finca=new ArrayList<Terreno>();
		     int f=5,c=4;
		     for(int i=0;i<2;i++){
		       finca.add(new Terreno(f++,c++));
		     }
		     return finca;
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
	   
	   private static void conquistaFeudos(ArrayList<Clan> clanes, ArrayList<Terreno> conquista, ArrayList<Terreno> conquista2) {
		   if(conquista != null) {
				for(int x=0; x<clanes.size(); x++)
					for(int i=0; i<conquista.size(); i++) {
						System.out.print("Clan " + clanes.get(x).getNombre() + " conquista: ");
						if(clanes.get(x).conquista(conquista.get(i)))
							System.out.println("SI");
						else
							System.out.println("NO");
					}
			}
			if(conquista2 != null)
				for(int i=0; i<conquista.size(); i++) {
					System.out.print("Clan " + clanes.get(1).getNombre() + " conquista: ");
					if(clanes.get(1).conquista(conquista2.get(i)))
						System.out.println("SI");
					else
						System.out.println("NO");
				}
	   }
	   
	   
	public static void main(String[] args) {
		PruebaFinal.creaPoblacion();
		String[][] names = PruebaFinal.creaNombres();
		int[] tipos = PruebaFinal.creaTipos();
		ArrayList<Clan> clanes = PruebaFinal.generaClanes();
		ArrayList<Terreno> conquista = PruebaFinal.generaFeudo();
		ArrayList<Producto> materia = PruebaFinal.creaProductos(names, tipos, 2.03, 0.15);
		ArrayList<Producto> materia2 = PruebaFinal.creaProductos(names, tipos, 2.03, 0.15);
		PruebaFinal.colocaProductos(conquista, materia);
		ArrayList<Terreno> conquista2 = PruebaFinal.generaFeudo();
		PruebaFinal.colocaProductos(conquista2, materia2);
		System.out.println("CONQUISTA TERRENOS LOS DOS CLANES");
		PruebaFinal.conquistaFeudos(clanes, conquista, conquista2);
		System.out.println("--------------------------------------");
		System.out.println("MUESTRA ESTADO INICIAL DE CLANES");
		PruebaFinal.consultaAllClanes(clanes);
		System.out.println("--------------------------------------");
		System.out.println("VASALLAJE CON EL CLAN POSTOLACHE");
		System.out.println("Vasalla el miembro: " + clanes.get(1).getMiembros().get(3).getNombre());
		ArrayList<Producto> cris = ((Plebeyo)clanes.get(1).getMiembros().get(3)).vasallaje(clanes.get(1).getFeudo().get(0));
		PruebaFinal.muestraProductos(cris);
		System.out.println("Vasalla el miembro: " + clanes.get(1).getMiembros().get(4).getNombre());
		ArrayList<Producto> dad = ((Plebeyo)clanes.get(1).getMiembros().get(4)).vasallaje(clanes.get(1).getFeudo().get(1));
		PruebaFinal.muestraProductos(dad);
		PruebaFinal.consultaAllClanes(clanes);
		System.out.println("--------------------------------------");
		System.out.println("ISABEL CASTRO ALIMENTA A SU BESTIOLA");
		Bestia leblanc = new Bestia(5.0);
		Plebeyo isa = null;
		if(clanes.get(0).getMiembros().get(3) instanceof Plebeyo) {
			isa = (Plebeyo) clanes.get(0).getMiembros().get(3);
			if(isa.domestica(leblanc, "Leblanc")) {
				System.out.println("Isabel ha domesticado a " + leblanc.getNombre() + ".");
				PruebaFinal.datosBestia(leblanc);
			}
		}
		if(isa != null) {
			int n = isa.alimenta(1);
			if(n == 1) System.out.println("\nHa alimentado a su bestia con el terreno.");
			else if(n == 2) System.out.println("\nHa alimentado a su bestia con su cesta.");
			else if(n == 2) System.out.println("\nNo ha alimentado a su bestia.");
			PruebaFinal.consultaHabitante(isa);
			System.out.println("Terreno pastureado: ");
			PruebaFinal.consultaFeudo(isa.getTribu().getFeudo());
			PruebaFinal.datosBestia(leblanc);
		}
		System.out.println("\n--------------------------------------");
		System.out.println("FLOREN TUTELA A TODO SU CLAN");
		Guerrero floren = (Guerrero) clanes.get(1).getMiembros().get(0);
		for(int i=1; i<clanes.get(1).getMiembros().size(); i++) {
			System.out.print("Floren tutela " + i + ": ");
			if(floren.acoge(clanes.get(1).getMiembros().get(i))) {
				System.out.println("SI");
				System.out.println(((Plebeyo)clanes.get(1).getMiembros().get(i)).getPatrono().getNombre() + " - " + floren.getSirviente().getNombre());
			}
			else {
				System.out.println("NO");
				System.out.println("--------");
			}
		}
		PruebaFinal.consultaAllClanes(clanes);
	}

}
