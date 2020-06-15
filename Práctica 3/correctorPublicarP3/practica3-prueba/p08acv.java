/**
 * @author Alex - Alicia Garrido Alenda
 * Creo un personaje e invoco agregaRasgo hasta completarlo con rasgos no validos o ya existentes.
 * Despues creamos otros rasgos e invocamos consulta del personaje con ellos invocando el
 * metodo completo de personaje. Mostramos los resultados por pantalla. Prueba p06 mejorada un poco.
 */
import java.util.ArrayList;
public class p08acv {
	
	
	private static String[] getNombres() {
		String[] names = {"barba","ojos","corbata","bigote","pelo","gafas","ojos","bigote",
				"pendientes","camiseta","barba","pelo","sombrero","sombrero","carton","juguete"};
		return names;
	}
	
	
	private static String[] getAtributos() {
		String[] atr = {null,"violetas","elegante","true","marron",null,null,"largo",
				"diamantes",null,"largaGris","calvo","rapper","true","coraza","latess"};
		return atr;
	}
	
	
	private static void creaRasgos(Personaje p,String[] n,String[] a){
		for(int i=0;i<n.length&&i<a.length;i++){
			try{
				p.agregaRasgo(n[i],a[i]);
				System.out.println(p.getNombre()+" tiene distintos rasgos? -> "+p.completo());
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
	
	
	private static ArrayList<Rasgo> getRasgos() {
		ArrayList<Rasgo> rasgos = new ArrayList<Rasgo>();
		
		rasgos.add(new Rasgo("ojos"));
		rasgos.get(0).setAtributo("violetas");
		rasgos.get(0).loTiene();
		rasgos.add(new Rasgo("ojos"));
		rasgos.get(1).setAtributo("violetas");
		rasgos.add(new Rasgo("bigote"));
		rasgos.get(2).loTiene();
		rasgos.add(new Rasgo("pelo"));
		rasgos.get(3).setAtributo("calvicie");
		rasgos.add(new Rasgo("piel"));
		rasgos.get(4).setAtributo("verde");
		rasgos.add(new Rasgo("pendientes"));
		rasgos.add(new Rasgo("ojos"));
		rasgos.add(new Rasgo("pelo"));
		
		return rasgos;
	}
	
	
	private static void consultando(ArrayList<Rasgo> r,Personaje p) {
		if(p != null  &&  r != null  &&  r.size() > 0) {
			
			for(int i=0; i<r.size(); i++) {
				System.out.println(r.get(i).toString() + " esta en " + p.getNombre() + " -> " + p.consulta(r.get(i)));
			}
			
		}
	}
	

	public static void main(String[] args) {
		Personaje giren = new Personaje("Giren");
		String[] names = p08acv.getNombres(), atr = p08acv.getAtributos();
		p08acv.creaRasgos(giren, names, atr);
		System.out.println(giren);
		ArrayList<Rasgo> rasgos = p08acv.getRasgos();
		p08acv.consultando(rasgos,giren);
	}

}
