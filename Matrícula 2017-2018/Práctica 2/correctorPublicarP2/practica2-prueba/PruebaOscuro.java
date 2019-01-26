/**
 * @author Alejandro Castro Valero / Alicia Garrido Alenda
 * Prueba la clase Oscuro, principalmente los metodos 'culto' y 'calamidades'. El metodo culto se realiza
 * de cinco formas distintas: la primera, para rellenar la caterva del oscuro; la segunda, realiza culto
 * con una bestia sin amo; la tercera, con una bestia con amo; la cuarta, con un objeto bestia sin amo que
 * contine un demoledor, y la quinta, con un culto con demoledor. Para el metodo calamidades se crea un clan con su
 * feudo al completo mostrando la situacion del oscuro y del clan antes y despues de la calamidad. Se muestran cada una
 * de las acciones realizadas.
 */

import java.text.*;
import java.util.*;
public class PruebaOscuro {
	
	
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
	        	 PruebaOscuro.muestraTipo(solar.consultaTipo(i,j));
	         }
	         System.out.println();
	       }
	     }
	   }
	   
	   private static void consultaFeudo(ArrayList<Terreno> reino){
	     if(reino!=null){
	       for(int i=0;i<reino.size();i++){
	          System.out.println("Terreno "+i+":");
	          PruebaOscuro.consultaTerreno(reino.get(i));
	       }
	     }
	   }

	   private static void veoDatos(Producto p){
	     if(p!=null){
	       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+PruebaOscuro.mrf(p.getPeso()));
	     }
	   }
	   
	   private static void muestraProductos(ArrayList<Producto> lista){
	    if(lista!=null){
	     System.out.println("Total de productos: "+lista.size());
	     for(int j=0;j<lista.size();j++)
	    	 PruebaOscuro.veoDatos(lista.get(j));
	    }
	   }
	   
	   private static void creaPoblacion(){
	     String nameh=new String("Neakail"+" "+"McAllary");
	     String namem=new String("Isabel"+" "+"Duncan");
	     String nameg=new String("Nabil"+" "+"McAllary");
	     String namek=new String("Youness"+" "+"McAllary");
	     Habitante neo=null;
	     neo=new Guerrero(nameh,'H');
	     neo=new Guerrero(namem,'M');
	     neo=new Plebeyo(nameg,'H');
	     neo=new Plebeyo(namek,'H');
	   }
	   
	   
	   private static Clan generaClan(){
	     Clan clan= new Clan(new String("McAllary"));
	     return clan;
	   }
	   
	   private static void consultaHabitante(Habitante h){
		     if(h!=null){
		       System.out.println("Nombre: "+h.getNombre()+"; Vigor: "+PruebaOscuro.mrf(h.getVigor())+"; Clan: "+h.getClan());
		       System.out.println("Cesta:");
		       PruebaOscuro.muestraProductos(h.getCesta());
		       if(h instanceof Guerrero){
		         Guerrero bully=(Guerrero)h;
		         System.out.println("Armamento:");
		         PruebaOscuro.muestraProductos(bully.getArmamento());
		       }
		     }
		   }
	
	private static String[] daNombres() { // nombres de los campeones
		String[] campeones = {"Arhi","Azir","Zed","Leblanc","Oriana","Sejuani","Garen","Irelia","Zoe","Kayn","Tristana","Jhin","Darius"};
		
		return campeones;
	}
	
	
	private static void datosBestia(Bestia b) {
		System.out.println("---------- Muestra Datos de la Bestia ---------");
		if(b != null) {
			
			// Nombre
			System.out.print("Nombre: ");
			if(b.getNombre() != null)
				System.out.println(b.getNombre());
			else
				System.out.println("Desconocido");
			
			// Amo
			System.out.print("Amo: ");
			if(b.getAmo() != null)
				System.out.println(b.getAmo().getNombre());
			else
				System.out.println("Desconocido");
			
			// fuerza
			System.out.println("Fuerza: " + b.getFuerza());
			
			// demoledor
			System.out.print("Demoledor: ");
			if(b instanceof Demoledor)
				System.out.println("SI");
			else
				System.out.println("NO");
		}
		else
			System.out.println("No hay bestia hermano.");
	}
		
	
	private static void creaCaterva(Oscuro chogah) {
		int x = 10;
		
		for(int i=0; i<5; i++)
			chogah.culto(new Demoledor(x++));
	}
	
	
	private static void datosOscuro(Oscuro chogah) {	// muestra los datos del oscuro
		System.out.println("---------- Muestra Datos del Oscuro ---------");
		if(chogah != null  &&  chogah.getCaterva() != null) {
			if(chogah.getCaterva().size() == 0)
				System.out.println("El Cho'gah mata de un bocao y no se le acerca ni Trynda.");
			else if(chogah.getCaterva().size() > 0) {
				System.out.println("Cho'gah le faltan items y necesita la ayuda de su equipo.");
				for(int i=0; i<chogah.getCaterva().size(); i++) {
					System.out.print("Campeon" + (i+1) + ": ");
					if(chogah.getCaterva().get(i).getNombre() != null) {
						System.out.println(chogah.getCaterva().get(i).getNombre());
							System.out.println("Fuerza: " + chogah.getCaterva().get(i).getFuerza());
					}
					else
						System.out.println("Disconnected");
				}
			}
		}
		else
			System.out.println("Cho'gah est� afk...");
	}
	

	public static void main(String[] args) {
		int champ = 0;
		
		System.out.println("OSCURO version League of Legends");
		
		// Creamos los elementos necesarios
		Oscuro chogah = new Oscuro();		// creamos oscuro
		PruebaOscuro.datosOscuro(chogah); 	// mostramos datos del oscuro
		System.out.println("(Solo se ha creado el oscuro)");
		
		PruebaOscuro.creaCaterva(chogah);
		
		PruebaOscuro.datosOscuro(chogah); 	// mostramos datos del oscuro
		System.out.println("(Le hemos rellenado la caterva)");
		String[] nombres = PruebaOscuro.daNombres();		
		for(int i=0; i<chogah.getCaterva().size(); i++) {
			chogah.getCaterva().get(i).setNombre(nombres[champ++]);
		}
		PruebaOscuro.datosOscuro(chogah); 	// mostramos datos del oscuro
		
		System.out.println("-------------------- METODO CULTO -----------------------");
		
		System.out.println("CULTO con BESTIA SIN AMO");
		System.out.println("ANTES DE CULTO");
		Bestia bestiaja = new Bestia(10);		// creamos la bestia
		bestiaja.setNombre(nombres[champ++]);		// le damos nombre
		PruebaOscuro.datosBestia(bestiaja);		// mostramos la bestia
		double fuerza = chogah.culto(bestiaja);	// invocamos a culto
		System.out.println("DESPUES DE CULTO");
		System.out.println("FUERZA DEVUELTA -> " + fuerza);
		PruebaOscuro.datosOscuro(chogah);
		bestiaja = chogah.getCaterva().get(chogah.getCaterva().size()-1);
		PruebaOscuro.datosBestia(bestiaja);
		
		System.out.println("CULTO con BESTIA CON AMO");
		System.out.println("ANTES DE CULTO");
		bestiaja = new Bestia(10);					// creamos la bestia
		Plebeyo ama = new Plebeyo("Caitlyn",'M'); 	// creamos el amo de la bestia
		ama.domestica(bestiaja, nombres[champ++]); 	// la ama lo domestica
		PruebaOscuro.datosBestia(bestiaja);			// mostramos la bestia
		fuerza = chogah.culto(bestiaja);			// invocamos a culto
		System.out.println("DESPUES DE CULTO");
		System.out.println("FUERZA DEVUELTA -> " + fuerza);
		PruebaOscuro.datosOscuro(chogah);
		bestiaja = chogah.getCaterva().get(chogah.getCaterva().size()-1);
		PruebaOscuro.datosBestia(bestiaja);

		System.out.println("CULTO con BESTIA QUE CONTIENE DEMOLEDOR");
		System.out.println("ANTES DE CULTO");
		bestiaja = new Demoledor(10);					// creamos la bestia
		bestiaja.setNombre(nombres[champ++]);		// le damos nombre
		PruebaOscuro.datosBestia(bestiaja);			// mostramos la bestia
		fuerza = chogah.culto(bestiaja);			// invocamos a culto
		System.out.println("DESPUES DE CULTO");
		System.out.println("FUERZA DEVUELTA -> " + fuerza);
		PruebaOscuro.datosOscuro(chogah);
		bestiaja = chogah.getCaterva().get(chogah.getCaterva().size()-1);
		PruebaOscuro.datosBestia(bestiaja);
		
		System.out.println("CULTO con DEMOLEDOR");
		System.out.println("ANTES DE CULTO");
		Demoledor perro = new Demoledor(10);					// creamos la bestia
		perro.setNombre(nombres[champ++]);		// le damos nombre
		PruebaOscuro.datosBestia(perro);			// mostramos la bestia
		fuerza = chogah.culto(perro);			// invocamos a culto
		System.out.println("DESPUES DE CULTO");
		System.out.println("FUERZA DEVUELTA -> " + fuerza);
		PruebaOscuro.datosOscuro(chogah);
		perro = chogah.getCaterva().get(chogah.getCaterva().size()-1);
		PruebaOscuro.datosBestia(perro);
		
		System.out.println("-------------------- METODO CALAMIDADES -----------------------");
		PruebaOscuro.creaPoblacion();
		String[][] names=PruebaOscuro.creaNombres();
	    int[] tipos=PruebaOscuro.creaTipos();
		Clan cru = PruebaOscuro.generaClan();
		ArrayList<Habitante> miembros = cru.getMiembros();
		ArrayList<Terreno> tierras = PruebaOscuro.generaFeudo();
	    ArrayList<Producto> materia=PruebaOscuro.creaProductos(names,tipos,3.2,0.2);
	    PruebaOscuro.colocaProductos(tierras, materia);
	    if(tierras != null)
	    	for(int i=0; i<tierras.size(); i++)
	    		cru.conquista(tierras.get(i));
	    System.out.println("FEUDO");
	    PruebaOscuro.consultaFeudo(tierras);
	    System.out.println("MIEMBRO");
	    for(int a=0; a<miembros.size(); a++)
	    	PruebaOscuro.consultaHabitante(miembros.get(a));
	    System.out.println("Arrasa con todo, Chogah(calamidades):");
	    int arrasados = chogah.calamidades(cru);
	    System.out.println("Terrenos del Feudo arrasados: " + arrasados);
	    System.out.println("FEUDO");
	    PruebaOscuro.consultaFeudo(tierras);
	    PruebaOscuro.datosOscuro(chogah);
	}
	

}
