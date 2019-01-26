/**
* @author Alicia Garrido Alenda
* Se crea una Galaxia y 3 Sectores; se invoca situa de los sectores con distintos 
* valores. Se invoca coloca de la galaxia con estos sectores de manera que unas
* veces devuelve cierto y otras falso. Se muestra la informacion de
* cada sector, el nombre de la galaxia y el resultado de las acciones que se realizan.
*/

public class p03{
  public static void compruebaSector(Sector s){
   Coordenada creada=null;
   int[] valores=null;
   
   if(s!=null){
     creada=s.getCoordenada();
      if(creada!=null){
        System.out.print("sector situado ha creado la coordenada ");
        valores=creada.getCoordenadas();
        if(valores!=null){
          System.out.print("[");
          for(int i=0;i<valores.length-1;i++)
            System.out.print(valores[i]+",");
          if(valores.length>0)
           System.out.print(valores[valores.length-1]);
          System.out.println("]");
        }
        else System.out.println("pero no se pueden consultar sus valores");
      }
      else System.out.println("sector situado pero no ha creado su coordenada");
   }
  }
  
  public static void main(String[] args){
    Galaxia centauro=null;
    Sector alpha,omega,omicron=null;
    int[] valores=null;
    boolean flag=false;
    
    centauro=new Galaxia("Centauro",11,13,15);
    alpha=new Sector(3);
    omega=new Sector(2);
    omicron=new Sector(1);
    flag=alpha.situa(5,7,8);
    if(flag)
     p03.compruebaSector(alpha);
    else System.out.println("sector no situado en [5,7,8]");
    flag=omega.situa(7,13,14);
    if(flag)
     p03.compruebaSector(omega);
    else System.out.println("sector no situado en [7,13,14]");
    flag=omicron.situa(5,7,8);
    if(flag)
     p03.compruebaSector(omicron);
    else System.out.println("sector no situado en [5,7,8]");
    flag=centauro.coloca(omega);
    System.out.print("sector [7,13,14]");
    if(flag)
     System.out.println(" colocado en "+centauro.getNombre());
    else
     System.out.println(" no colocado en "+centauro.getNombre());
    flag=centauro.coloca(alpha);
    System.out.print("sector [5,7,8]");
    if(flag)
     System.out.println(" colocado en "+centauro.getNombre());
    else
     System.out.println(" no colocado en "+centauro.getNombre());
    flag=centauro.coloca(omicron);
    System.out.print("sector [5,7,8]");
    if(flag)
     System.out.println(" colocado en "+centauro.getNombre());
    else
     System.out.println(" no colocado en "+centauro.getNombre());
  }
}