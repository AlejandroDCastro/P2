// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Oscuro extends Mistico {
	
	private ArrayList<Demoledor> caterva;
	
	public Oscuro() {
		super();
		caterva = new ArrayList<Demoledor>();
	}
	
	
	public double culto(Bestia c) { // REVISADO PruebaOscuro
		double p = 0.0;
		boolean esta = false;
		
		if(c != null) {
			
			// primero comprobamos que c no pernece a la caterva
			for(int i=0; i<caterva.size(); i++)
				if(caterva.get(i) != null  &&  caterva.get(i) == c) {
					esta = true;
					break;
				}
			
			// si no pertenece a la caterva transformamos c en demoledor y la metemos
			if(!esta) {
				Demoledor d = null;
				if(c instanceof Demoledor)
					d = (Demoledor) c;
				else
					d = c.hechiza();
				p = d.chupaFuerza(d.getFuerza() * 0.2);
				caterva.add(d);
			}
		}
		
		return p;
	}
	
	
	public boolean malogra(Habitante h) { // REVISADO p02
		
		if(h != null  &&  h instanceof Plebeyo  &&  ((Plebeyo) h).getBestiola() != null) {
			Demoledor demon = ((Plebeyo) h).getBestiola().hechiza();
			((Plebeyo) h).setBestiola(demon);
			return true;
		}
		return false;		
	}
	
	
	public int calamidades(Clan c) { // REVISADO PruebaOscuro
		int arrasados = 0;
		
		if(c != null  &&  c.getFeudo() != null  &&  c.getFeudo().size() > 0) {
			
			for(int i=0; i<c.getFeudo().size()  &&  i<caterva.size(); i++)
				if(caterva.get(i) != null  &&  caterva.get(i).arrasa(c.getFeudo().get(i)) > 0.0)
					arrasados++;
			
		}
		
		return arrasados;
	}
	
	
	public double ayuda(Producto p) {
		double negativo = 0.0;
		
		if(p != null) {
			negativo = - p.valorProducto();
			super.meteAlPrincipio(p);
		}
		
		return negativo;
	}
	
	
	public ArrayList<Demoledor> getCaterva() {
		return caterva;
	}
	

}
