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
                Planta[][] huerto_ = null;
          
                if (huerta != null) {
                        
                        huerto_ = huerta.getHuerto();
                        for (int i=0; i<huerto_.length; i++) {
                                for (int j=0; j<huerto_[i].length; j++) {
                                        if (huerto_[i][j] != null) {
                                                if (huerto_[i][j].getEstado().equals("adulta")  &&  !huerto_[i][j].tieneFrutos()) {
                                                        plantas_malas_.add(huerto_[i][j].getNombre(), i, j);
                                                }
                                        }
                                }
                        }
                  
                }
          
                return plantas_malas_;
        }
  
  
        public int abona(int p , String n) {
                int plantas_cambiadas_ = 0;
          
                if (huerta != null) {
                        plantas_cambiadas_ = huerta.abona(p, n);
                }
                
                return plantas_cambiadas_;
        }
  
  
        public String getNombre() {
                return nombre;
        }
  
  
        public Huerta getHuerta() {
                return huerta;
        }
  
}
