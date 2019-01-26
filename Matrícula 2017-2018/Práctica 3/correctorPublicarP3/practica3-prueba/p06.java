/**
 * @authors Alex - Alicia Garrido Allenda
 * Se crea un personaje y se invoca agregaRasgo con distintos valores de manera
 * que algunas veces se lanza la excepcion RasgoPreexistente, consultando si el
 * personaje esta completo. Se muestra el personaje creado por pantalla.
 */
public class p06 {
	
	
	private static String[] getNombres() {
		String[] names = {"barba","ojos","bigote","pelo","gafas","ojos","bigote",
				"pendientes","barba","pelo","sombrero","sombrero"};
		return names;
	}
	
	
	private static String[] getAtributos() {
		String[] atr = {null,"violetas","true","marron",null,null,"largo",
				"diamantes","largaGris","calvo","rapper","true"};
		return atr;
	}
	
	
	private static void creaRasgos(Personaje p,String[] n,String[] a){
		for(int i=0;i<n.length&&i<a.length;i++){
			try{
				p.agregaRasgo(n[i],a[i]);
				System.out.println(p.getNombre()+" esta completo? -> "+p.completo());
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
	

	public static void main(String[] args) {
		Personaje person = new Personaje("Diego");
		String[] names = p06.getNombres(), atr = p06.getAtributos();
		p06.creaRasgos(person, names, atr);
		System.out.println(person);
	}

}
