// DNI 20521823  CASTRO VALERO, ALEJANDRO
public class Metodos {
	
	public static int[] divisores(int i) {
		int []retorno=null;
		int n=0, m=0;
		
		for(int x=1; x<=i; x++)
			if(i%x==0)
				n++;
		retorno=new int[n];
		for(int y=i; y>=1; y--)
			if(i%y==0) {
				retorno[m]=y;
				m++;
			}
		return retorno;
	}
	
	
	public static int[] primos(int i) {
		int []enteros=null;
		int []retorno=null;
		int j=0, k=0;
		
		enteros=new int[i];
		for(int x=0; x<i; x++)
			enteros[x]=x+1;
		for(int y=0; y<i; y++) {
			j=0;
			for(int w=1; w<=enteros[y]; w++) {
				if(enteros[y]>1 && enteros[y]%w==0)
					j++;
				if(j==2 && w==enteros[y]) {
					enteros[k]=y+1;
					k++;
				}
			}
		}
		retorno=new int[k];
		for(int z=0; z<k; z++)
			retorno[z]=enteros[z];
		return retorno;
	}
	
	
	public static String[] extrae(String s) {
		String []sinum=null;
		char []car=null;
		String []nonum=null;
		int k=0, q=0, t=0;
		
		sinum=s.split(";");
		for(int x=0; x<sinum.length; x++) {
			k=0;
			car=sinum[x].toCharArray();
				for(int y=0; y<car.length; y++)
					if(car[y]<48 || car[y]>57)
						k++;
				if(k==car.length)
					q++;
		}
		nonum=new String[q];
		for(int x=0; x<sinum.length; x++) {
			k=0;
			car=sinum[x].toCharArray();
				for(int y=0; y<car.length; y++)
					if(car[y]<48 || car[y]>57)
						k++;
				if(k==car.length) {
					nonum[t]=sinum[x];
					t++;
				}
		}
		return nonum;
	}


	
	public static char[] hexadecimal(int i) {
		int []conver=null;
		char []hexa=null;
		int q=0, t=0, z=i;
		
		if(i>0) {
			if(i>=16) {
				while(i>=16) {
					i=i/16;
					t++;
				}
				t=t+1;
				conver=new int[t];
				for(int x=0; x<t; x++)
					if(z>=16) {
						conver[x]=z%16;
						z=z/16;
					}
				conver[t-1]=z;
				hexa=new char[conver.length];
				for(int x=conver.length-1; x>=0; x--) {
					switch (conver[x]) {
					case 0: hexa[q]=48; break;
					case 1: hexa[q]=49; break;
					case 2: hexa[q]=50; break;
					case 3: hexa[q]=51; break;
					case 4: hexa[q]=52; break;
					case 5: hexa[q]=53; break;
					case 6: hexa[q]=54; break;
					case 7: hexa[q]=55; break;
					case 8: hexa[q]=56; break;
					case 9: hexa[q]=57; break;
					case 10: hexa[q]=97; break;
					case 11: hexa[q]=98; break;
					case 12: hexa[q]=99; break;
					case 13: hexa[q]=100; break;
					case 14: hexa[q]=101; break;
					case 15: hexa[q]=102; break;
					}
					q++;
				}
			}
			else {
				hexa=new char[1];
				switch (i) {
				case 0: hexa[0]=48; break;
				case 1: hexa[0]=49; break;
				case 2: hexa[0]=50; break;
				case 3: hexa[0]=51; break;
				case 4: hexa[0]=52; break;
				case 5: hexa[0]=53; break;
				case 6: hexa[0]=54; break;
				case 7: hexa[0]=55; break;
				case 8: hexa[0]=56; break;
				case 9: hexa[0]=57; break;
				case 10: hexa[0]=97; break;
				case 11: hexa[0]=98; break;
				case 12: hexa[0]=99; break;
				case 13: hexa[0]=100; break;
				case 14: hexa[0]=101; break;
				case 15: hexa[0]=102; break;
				}
			}
		}
		return hexa;
	}
	
	
	public static String[] fechada(int i,int j,String s) {
		String []fecha=null;
		char car=0;
		char car2=0;
		int k=0, q=1;
		
		fecha=new String[2];
		if(j<=12 && j>0) {
			switch (j) {
			case 1:
				fecha[1]="enero";
				break;
			case 2:
				fecha[1]="febrero";
				break;
			case 3:
				fecha[1]="marzo";
				break;
			case 4:
				fecha[1]="abril";
				break;
			case 5:
				fecha[1]="mayo";
				break;
			case 6:
				fecha[1]="junio";
				break;
			case 7:
				fecha[1]="julio";
				break;
			case 8:
				fecha[1]="agosto";
				break;
			case 9:
				fecha[1]="septiembre";
				break;
			case 10:
				fecha[1]="octubre";
				break;
			case 11:
				fecha[1]="noviembre";
				break;
			case 12:
				fecha[1]="diciembre";
				break;
			}
			car=s.charAt(0);
			car2=s.charAt(1);
			switch(car) {
			case 'l':
			case 'L':
				k=1;
				break;
			case 'm':
			case 'M':
				if(car2=='a' || car2=='A')
					k=2;
				else
					k=3;
				break;
			case 'j':
			case 'J':
				k=4;
				break;
			case 'v':
			case 'V':
				k=5;
				break;
			case 's':
			case 'S':
				k=6;
				break;
			case 'd':
			case 'D':
				k=7;
				break;
			}
			switch(j) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if(i>0 && i<=31)
					while(i>7)
						i=i-7;
				else fecha=null;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				if(i>0 && i<31)
					while(i>7)
						i=i-7;
				else fecha=null;
			case 2:
				if(i>0 && i<=28)
					while(i>7)
						i=i-7;
				else fecha=null;
				break;
			}
			if(fecha!=null) {
				while(i>q) {
					q++;
					if(k==7)
						k=1;
					else
						k++;
				}
				switch(k) {
				case 1: fecha[0]="lunes"; break;
				case 2: fecha[0]="martes"; break;
				case 3: fecha[0]="miercoles"; break;
				case 4: fecha[0]="jueves"; break;
				case 5: fecha[0]="viernes"; break;
				case 6: fecha[0]="sabado"; break;
				case 7: fecha[0]="domingo"; break;
				}
			}
		}
		else
			fecha=null;
		return fecha;
	}
	
	
	public static char[] impares(String s1){
		char []cad=null;
		char []retorno=null;
		int x=0;
		
		cad=new char[s1.length()];
		for(int i=0; i<s1.length(); i++) {
			if(i!=0 && i==1)
				cad[x]=s1.charAt(i);
				x++;
			if(i!=0 && i%2!=0)
				cad[x]=s1.charAt(i);
				x++;
		}
		retorno=new char[x];
		for(int y=0; y<x; y++)
			retorno[y]=cad[y];
		return retorno;
	}
	
	
	public static boolean opuesta(int[][] a,int[][] b) {
		boolean n=true;
		
		if(a!=null && b!=null)
			if(a.length==b.length && a.length>0)
				if(a[0].length==b[0].length && a[0].length>0)
					for(int i=0; i<a.length; i++)
						for(int j=0; j<a[i].length; j++) {
							if(a[i][j]+b[i][j]==0)
								n=true;
							else {
								n=false;
								j=a[i].length;
								i=a.length;
							}
						}
				else
					n=false;
			else
				n=false;
		else
			n=false;
		return n;
	}
	
	
	public static int[] ordena(double[] v) {
		int []retorno;
		double []array;
		double aux=0;
		int x=0, q=0;
		
		for(int i=0; i<v.length; i++)
			if(v[i]>0)
				x++;
		array=new double[x];
		for(int i=0; i<v.length; i++)
			if(v[i]>0) {
				array[q]=v[i];
				q++;
		}
		for(int i=1; i<x; i++)
			for(int y=x-1; y>=i; y--)
				if(array[y-1]>array[y]) {
					aux=array[y-1];
					array[y-1]=array[y];
					array[y]=aux;
				}
		retorno=new int[x];
		for(int i=0; i<x; i++)
			retorno[i]=(int) array[i];
		return retorno;
	}
	
	
	public static int suma(int n) {
		int suma=0;
		int num=0, q=0;
		
		while(q<n) {
			num++;
			if(num==1 || num%2!=0) {
				suma=suma+num;
				q++;
			}
		}
		return suma;
	}
	
	
	public static double euclidea(int[] x,int[] y) {
		double distancia=0;
		int op=0;
		double cuad=0, suma=0;
		
		if(x!=null && y!=null)
			if(x.length==y.length && x.length>0) {
				for(int i=0; i<x.length; i++) {
					op=x[i]-y[i];
					cuad=Math.pow(op, 2);
					suma=suma+cuad;
					op=0;
					cuad=0;
				}
				distancia=Math.sqrt(suma);
			}
			else
				distancia=-1;
		else
			distancia=-1;
		return distancia;
	}

}
