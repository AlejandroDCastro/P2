// DNI 20521823 CASTRO VALERO, ALEJANDRO

public class Metodos {

	// metodo que comprueba si num1 es multiplo de num2
	public static boolean compMultiplos(int num1, int num2) {

		if(num1%num2 == 0) {
			return true;
		} else {
			return false;
		}
	}


	// metodo 1
	public static int[] nomultiplos(int num, int n1, int n2) {
		int []__devuelve__ = null;
		int []__aux__ = null;
		int contador = 0;

		// si algun dato es igual o menor que cero devolvemos null
		if(num > 0  ||  n1 > 0  ||  n2 > 0) {

			// bucle para ver que numeros no son multiplos
			__aux__ = new int[num];
			for(int i=1; i<=num; i++) {
				if(!compMultiplos(num,n1)  &&  !compMultiplos(num,n2)) {
					__aux__[contador++] = i;
				}
			}

			// bucle para crear el array de no primos
			__devuelve__ = new int[contador];
			for(int i=0; i<contador; i++) {
				if(__aux__[i] != 0) {
					__devuelve__[i] = __aux__[i];
				} else {
					break;
				}
			}

		}

		return __devuelve__;
	}




	// metodo 2
	public static String[] sufijos(String s) {
		String[] __devuelve__ = new String[s.length()];
		int aux = 0;

		for(int i=s.length()-1; i<=0; i--) {
			__devuelve__[aux] = String.valueOf(s.charAt(i));
			if(aux > 0) {
				__devuelve__[aux] += __devuelve__[aux-1];
			}
			aux++;
		}

		return __devuelve__;
	}




	// metodo 3
	public static String[] prefijos(String s) {
		String[] __devuelve__ = new String[s.length()];
		int aux = 0;

		for(int i=0; i<=s.length()-1; i--) {
			__devuelve__[aux] = String.valueOf(s.charAt(i));
			if(aux > 0) {
				__devuelve__[aux] = __devuelve__[aux-1] + __devuelve__[aux];
			}
			aux++;
		}

		return __devuelve__;
	}




	// metodo 4
	public static String IMC(double altura, double peso, int edad) {
		String __devuelve__ = null;
		double __imc__ = 0.0;

		if(altura > 0.0  ||  peso > 0.0  ||  edad > 0) {

			__imc__ = peso / Math.pow(altura,2);
			if(__imc__ < 22) {
				if(edad < 45) {
					__devuelve__ = "bajo";
				} else {
					__devuelve__ = "medio";
				}
			} else {
				if(edad < 45) {
					__devuelve__ = "medio";
				} else {
					__devuelve__ = "alto";
				}
			}

		} else {

			__devuelve__ = "error en entrada";

		}

		return __devuelve__;
	}




	// metodo 5
	public static int[] comunes(int[] v1, int[] v2) {
		int[] __aux__ = new int[v1.length];
		int[] __devuelve__;
		int contador = 0;
		int aux = 0;

		// sacamos los enteros comunes
		for(int i=0; i<v1.length; i++) {
			for(int j=0; j<v2.length; j++) {
				if(v1[i] == v2[j]) {
					__aux__[contador++] = v1[i];
					break;
				}
			}
		}

		// los ordenamos
		__devuelve__ = new int[contador];
		for(int i=0; i<contador; i++) {
			__devuelve__[i] = __aux__[i];
			for(int j=i; j>0; j--) {
				if(__devuelve__[j] > __devuelve__[j-1]) {
					aux = __devuelve__[j];
					__devuelve__[j] = __devuelve__[j-1];
					__devuelve__[j-1] = aux;
				}
			}
		}

		return __devuelve__;
	}




	// metodo que devuelve el numero de elementos de la secuencia Collatz
	public static int numSecuencia(int num) {
		int __sec__ = num;
		int __devuelve__ = 0;

		while(__sec__ > 1) {
			__sec__ = (__sec__%2 == 0) ? (__sec__/2) : (__sec__*3+1);
			__devuelve__++;
		}
		__devuelve__++;

		return __devuelve__;
	}




	// metodo 6
	public static void secCollatz(int i) {

		if(i > 0) {

			for(int x=1; x<=i; x++) {
				System.out.println(x + " " + numSecuencia(x));
			}

		}
	}




	// metodo 7
	public static int[] Collatz(int i) {
		int[] __secuencia__ = null;
		int contador = 0, __num__ = i;

		if(i > 0) {

			// inicializamos el array con el valor del numero
			__secuencia__ = new int[numSecuencia(i)];
			__secuencia__[contador++] = __num__;

			// obtemos la secuencia restante
			while(__num__ > 1) {
				__num__ = (__num__%2 == 0) ? (__num__/2) : (__num__*3+1);
				__secuencia__[contador++] = __num__;
			}
			__secuencia__[contador++] = __num__;

		}

		return __secuencia__;
	}




	// metodo 8


}