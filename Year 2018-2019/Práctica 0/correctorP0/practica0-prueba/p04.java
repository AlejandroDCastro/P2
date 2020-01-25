/**
* @author Alicia Garrido
* Se invoca el metodo IMC pasandole por parametro datos en unas ocasiones correctos para 
* riesgo medio y en otras incorrectos. Se muestra por pantalla el resultado devuelto.
*/
public class p04{
  public static void main(String[] args){
     double altura=1.68,peso=54;
     int edad=45;
     String riesgo=Metodos.IMC(altura,peso,edad);
     if(riesgo!=null)
       System.out.println("Persona con altura="+altura+"; peso="+peso+"; edad="+edad+" tiene un riesgo --> "+riesgo);
     altura=0;peso=10.9;edad=1;
     riesgo=Metodos.IMC(altura,peso,edad);
     if(riesgo!=null)
       System.out.println("Persona con altura="+altura+"; peso="+peso+"; edad="+edad+" tiene un riesgo --> "+riesgo);
     altura=1.55;peso=70.9;edad=44;
     riesgo=Metodos.IMC(altura,peso,edad);
     if(riesgo!=null)
       System.out.println("Persona con altura="+altura+"; peso="+peso+"; edad="+edad+" tiene un riesgo --> "+riesgo);
     
  }
}