/**
* @author Alicia Garrido Alenda
* Se crea un Sector y se invoca situa con unos valores. Se invoca getCoordenada
* y se invoca getCoordenadas de la Coordenada devuelta. Se invoca situa del
* Sector con otros valores y se comprueba si se ha modificado su coordenada.
*/

public class p02{

  public static void main(String[] args){
    Sector primero=null;
    Coordenada creada=null;
    int[] valores=null;
    boolean flag=false;
    
    primero=new Sector(5);
    flag=primero.situa(2,1,0);
    if(flag){
      creada=primero.getCoordenada();
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
    else
     System.out.println("sector no situado");
    flag=primero.situa(10,12,11);
    if(!flag){
      creada=primero.getCoordenada();
      System.out.print("sector ya situado ");
      if(creada!=null){
        System.out.print("en la coordenada ");
        valores=creada.getCoordenadas();
        if(valores!=null){
          System.out.print("[");
          for(int i=0;i<valores.length-1;i++)
            System.out.print(valores[i]+",");
          System.out.println(valores[valores.length-1]+"]");
        }
        else System.out.println("que no se puede consultar");
      }
      else System.out.println("pero sin su coordenada");
    }
    else System.out.println("sector resituado (que no debe)");
    
  }
}