/** 
 * @author Alex - Alicia Garrido Alenda
 * Se crea un personaje y se invoca agregaRasgo con distintos valores,
 * consultando si el personaje tiene distintos rasgos. Se muestra el
 * personaje creado por pantalla.
 */
public class p07 {

	
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
	
	
	public static void main(String[] args) {
		Personaje person = new Personaje("Diego");
		String[] names = p07.getNombres(), atr = p07.getAtributos();
		p07.creaRasgos(person, names, atr);
		System.out.println(person);
	}

}
