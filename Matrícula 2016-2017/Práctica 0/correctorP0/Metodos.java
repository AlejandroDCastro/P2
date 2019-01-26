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
	
	
	
}
