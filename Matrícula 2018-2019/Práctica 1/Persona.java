// DNI 20521823 CASTRO VALERO, ALEJANDRO
import java.util.ArrayList;

public class Persona {
  
        private String nombre;
        private Huerta huerta;
  
        
        public Persona(String n) {
                nombre = (n != null) ? n : "John Doe";
                huerta = null;
        }
  
  
        public boolean planta(Planta p, Huerta h) {
                boolean devuelve_ = false;
          
                if (h != null) {
                        devuelve_ = huerta.planta(p);
                }
                
                return devuelve_;
        }
  
  
        public Coordenada paseo() {
                Coordenada devuelve_ = null;
          
                if (huerta != null) {
                        devuelve_ = huerta.getLocalizacion();
                } else {
                        
                        ArrayList<Huerta> loc_ = Huerta.getLocalizadas();
                        for (int i=0; i<loc_.size(); i++) {
                                if (loc_.get(i).getCuidador() == null) {
                                        huerta = loc_.get(i);
                                        devuelve_ = huerta.getLocalizacion();
                                        huerta.setCuidador(this);
                                        break;
                                }
                        }
                  
                }
          
                return devuelve_;
        }
  
  
        public ArrayList<Planta> malasHierbas() {
                ArrayList<Planta> plantas_malas_ = new ArrayList<Planta>();
                ArrayList<String> plantas_adultas_ = null;
                Fruto[] frutos_ = null;
          
                if (huerta != null) {
                        
                        plantas_adultas_ = huerta.getAdultas();
                        for (int i=0; i<huerta.getHuerto().length; i++) {
                                for (int j=0; j<huerta.getHuerto()[i].length; j++) {
                                        if ()
                  
                }
        }
  
  
        public int abona(int , String ) {
                
        }
  
  
        public String getNombre() {
                return nombre;
        }
  
  
        public Huerta getHuerta() {
                return huerta;
        }
                  
                  
        // metodo que devuelve true si el array tiene frutos y false en otro caso
        public boolean tieneFrutos(Fruto[] f) {
                boolean devuelve_ = false;
                
                for (int i=0; i<f.length; i++) {
                        if (f[i] != null) {
                                devuelve_ = true;
                                break;
                        }
                }
                
                return devuelve_;
        }
  
}
