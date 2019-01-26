/**
* @author Alicia Garrido Alenda
* Se invoca el metodo fechada pasandole por parametro un dia y mes validos numericamente, 
* y un dia valido en forma de cadena, mostrando por pantalla lo que devuelve el metodo.
*/
public class p03{
  public static void main(String[] args){
    int dia=20,mes=1;
    String diaS=new String("martes");
    String[] fecha=Metodos.fechada(dia,mes,diaS);
    if(fecha!=null){
     System.out.print("el "+dia+" del "+mes+" sera en ");
     int j=fecha.length-1;
     for(;j>0;j--)
      System.out.print(fecha[j]+" ");
     if(j==0)
      System.out.print("un "+fecha[0]);
     System.out.println();
    }
  }
}