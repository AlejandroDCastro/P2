/**
* @author Alicia Garrido Alenda
* Se crean varios obtejos de tipo Coordenada, se comparan entre ellos y se calculan
* distancias entre ellos. Se muestra por pantalla el resultado de cada accion
*/

import java.text.*;
import java.util.*;
public class p02{
  private static String mrf(double db){
     Locale lengua=new Locale("en");
     DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
     DecimalFormat formato=new DecimalFormat("0.000",chars);

     return(formato.format(db).toString());
   }
   
   private static ArrayList<Coordenada> generaPosiciones(ArrayList<Integer> p){
     ArrayList<Coordenada> v=new ArrayList<>();
     
     for(int x=181;x>=-180;x-=50){
       for(int y=-91;y<=90;y+=50){
         v.add(new Coordenada(y,x));
         if(y>=-90 && y<=90)
           p.add(y);
         else p.add(0);
         if(x>=-180 && x<=180)
           p.add(x);
         else p.add(0);
       }
     }
     return v;
   }
   
   public static void main(String[] args){
     ArrayList<Integer> posicion=new ArrayList<>();
     ArrayList<Coordenada> situa=p02.generaPosiciones(posicion);
     posicion.clear();
     ArrayList<Coordenada> situa2=p02.generaPosiciones(posicion);
     int o=0,f=posicion.size()-1;
     for(int i=0,j=situa2.size()-1;i<situa.size() && j>=0;i++,j--){
       double dist=situa.get(i).distancia(situa2.get(j));
       System.out.println("distancia entre ("+posicion.get(o)+","+posicion.get(o+1)+") y ("+posicion.get(f-1)+","+posicion.get(f)+") -> "+p02.mrf(dist));
       o+=2;
       f-=2;
     }
     for(int i=0;i<situa.size();i++){
      o=0;f=0;
      for(int j=0;j<situa2.size();j++){
        if(situa.get(i).iguales(situa2.get(j))){
          System.out.println("("+posicion.get(o)+","+posicion.get(o+1)+") y ("+posicion.get(f)+","+posicion.get(f+1)+") -> son iguales");
        }
        o+=2;f+=2;
      }
     }
   }
}