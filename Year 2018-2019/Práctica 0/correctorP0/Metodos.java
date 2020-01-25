// DNI 20521823 CASTRO VALERO, ALEJANDRO

public class Metodos {

	// metodo que comprueba si num1 es multiplo de num2
	public static boolean compMultiplos(int num1, int num2) {
		if(num1 < num2  ||  num1%num2 != 0) {
			return false;
		} else {
			return true;
		}
	}


	// metodo 1
	public static int[] nomultiplos(int num, int n1, int n2) {
		int []devuelve_ = null;
		int []aux_ = null;
		int contador_ = 0;

		// si algun dato es igual o menor que cero devolvemos null
		if(num > 0  &&  n1 > 0  &&  n2 > 0) {

			// bucle para ver que numeros no son multiplos
			aux_ = new int[num];
			for(int i=1; i<=num; i++) {
				if(!compMultiplos(i,n1)  &&  !compMultiplos(i,n2)) {
					aux_[contador_++] = i;
				}
			}

			// bucle para crear el array de no primos
			devuelve_ = new int[contador_];
			for(int i=0; i<contador_; i++) {
				if(aux_[i] != 0) {
					devuelve_[i] = aux_[i];
				} else {
					break;
				}
			}

		}

		return devuelve_;
	}




	// metodo 2
	public static String[] sufijos(String s) {
		String[] devuelve_ = new String[s.length()];
		int aux = 0;

		for(int i=s.length()-1; i>=0; i--) {
			devuelve_[aux] = String.valueOf(s.charAt(i));
			if(aux > 0) {
				devuelve_[aux] += devuelve_[aux-1];
			}
			aux++;
		}

		return devuelve_;
	}




	// metodo 3
	public static String[] prefijos(String s) {
		String[] devuelve_ = new String[s.length()];
		int aux = 0;

		for(int i=0; i<=s.length()-1; i++) {
			devuelve_[aux] = String.valueOf(s.charAt(i));
			if(aux > 0) {
				devuelve_[aux] = devuelve_[aux-1] + devuelve_[aux];
			}
			aux++;
		}

		return devuelve_;
	}




	// metodo 4
	public static String IMC(double altura, double peso, int edad) {
		String devuelve_ = null;
		double imc_ = 0.0;

		if(altura > 0.0  &&  peso > 0.0  &&  edad > 0) {

			imc_ = peso / Math.pow(altura,2);
			if(imc_ < 22) {
				if(edad < 45) {
					devuelve_ = "bajo";
				} else {
					devuelve_ = "medio";
				}
			} else {
				if(edad < 45) {
					devuelve_ = "medio";
				} else {
					devuelve_ = "alto";
				}
			}

		} else {

			devuelve_ = "error en entrada";

		}

		return devuelve_;
	}




	// metodo 5
	public static int[] comunes(int[] v1, int[] v2) {
		int[] aux_ = new int[v1.length];
		int[] devuelve_ = null;
		int contador_ = 0;
		int aux = 0;

		// sacamos los enteros comunes
		for(int i=0; i<v1.length; i++) {
			for(int j=0; j<v2.length; j++) {
				if(v1[i] == v2[j]) {
					aux_[contador_++] = v1[i];
					break;
				}
			}
		}

		// los ordenamos
		devuelve_ = new int[contador_];
		for(int i=0; i<contador_; i++) {
			devuelve_[i] = aux_[i];
			for(int j=i; j>0; j--) {
				if(devuelve_[j] < devuelve_[j-1]) {
					aux = devuelve_[j];
					devuelve_[j] = devuelve_[j-1];
					devuelve_[j-1] = aux;
				}
			}
		}

		return devuelve_;
	}




	// metodo que devuelve el numero de elementos de la secuencia Collatz
	public static int numSecuencia(int num) {
		int sec_ = num;
		int devuelve_ = 1;


		while(sec_ > 1) {
			sec_ = (sec_%2 == 0) ? (sec_/2) : (sec_*3+1);
			devuelve_++;
		}

		return devuelve_;
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
		int[] secuencia_ = null;
		int contador_ = 0, num_ = i;

		if(i > 0) {

			// inicializamos el array con el valor del numero
			secuencia_ = new int[numSecuencia(i)];
			secuencia_[contador_++] = num_;

			// obtemos la secuencia restante
			while(num_ > 1) {
				num_ = (num_%2 == 0) ? (num_/2) : (num_*3+1);
				secuencia_[contador_++] = num_;
			}

		}

		return secuencia_;
	}




	// metodo 8
	public static void caballo(int cor1, int cor2) {
		int cor_x_ = 0, cor_y_ = 0, orden_ = 1;

		System.out.println("caballo(" + cor1 + ", " + cor2 + ")");

		if(cor1 > 0  &&  cor1 < 9  &&  cor2 > 0  &&  cor2 < 9) {

			for(int i=0; i<8; i++) {

				switch(orden_) {
					case 1:
						cor_x_ = cor1-2;
						cor_y_ = cor2-1;
						break;
					case 2:
						cor_x_ = cor1-2;
						cor_y_ = cor2+1;
						break;
					case 3:
						cor_x_ = cor1-1;
						cor_y_ = cor2-2;
						break;
					case 4:
						cor_x_ = cor1-1;
						cor_y_ = cor2+2;
						break;
					case 5:
						cor_x_ = cor1+1;
						cor_y_ = cor2-2;
						break;
					case 6:
						cor_x_ = cor1+1;
						cor_y_ = cor2+2;
						break;
					case 7:
						cor_x_ = cor1+2;
						cor_y_ = cor2-1;
						break;
					case 8:
						cor_x_ = cor1+2;
						cor_y_ = cor2+1;
						break;
				}
				orden_++;

				if(cor_x_ > 0  &&  cor_x_ < 9  &&  cor_y_ > 0  &&  cor_y_ < 9) {
					System.out.println(cor_x_ + " " + cor_y_);
				}

			}
			
		} else {

			System.out.println("posicion incorrecta");

		}
	}




	// metodo que calcula el numero de divisiones impares entre 2 que hay que hacer para alcanzar 1
	public static int numDivisiones(int num) {
		int n_ = 0, aux_ = num;

		while(aux_ > 1) {
			if(aux_%2 != 0) {
				n_++;
			}
			aux_ /= 2;
		}
		n_++;

		return n_;
	}




	// metodo 9
	public static int[] multrusa(int ando, int ador) {
		int[] devuelve_ = null;
		int aux_ando_ = ando, aux_ador_ = ador, contador_ = 0;

		if(ando > 0  &&  ador > 0) {

			devuelve_ = new int[numDivisiones(ador)];
			while(aux_ador_ > 1) {
				if(aux_ador_%2 != 0) {
					devuelve_[contador_++] = aux_ando_;
				}
				aux_ador_ /= 2;
				aux_ando_ *= 2;
			}
			devuelve_[contador_++] = aux_ando_;

		}

		return devuelve_;
	}





	// metodo que calcula si el indice no esta en la matriz de indices de las cadenas
	public static boolean estaEn(String[] v, String pal, int tam) {
		boolean devuelve_ = false;

		for (int i=0; i<tam; i++) {
			if (pal.equals(v[i])) {
				devuelve_ = true;
				break;
			}
		}

		return devuelve_;
	}





	// metodo que calcular cuantas veces se repite la palabra en el string
	public static int numRep(String[] v, String pal) {
		int devuelve_ = 0;

		for (int i=0; i<v.length; i++) {
			if (pal.equals(v[i])) {
				devuelve_++;
			}
		}

		return devuelve_;
	}





	// metodo 10
	public static String[][] histograma(String s, int i) {
		String[] cadenas_ = s.split(" "), cad_ = new String[cadenas_.length];
		String[][] devuelve_ = null; 
		int contador_ = 0, repeticiones_ = 0, indice_;
		int[] frec_ = new int[cadenas_.length];

		for (int x=0; x<cadenas_.length; x++) {
			repeticiones_ = numRep(cadenas_, cadenas_[x]);
			if (!estaEn(cad_, cadenas_[x], contador_)  &&  repeticiones_ >= i) {
				cad_[contador_] = cadenas_[x];
				frec_[contador_++] = repeticiones_;
			}
		}

		// creamos la matriz
		if (contador_ > 0) {
			devuelve_ = new String[contador_][2];
			for (int x=0; x<contador_; x++) {
				devuelve_[x][0] = cad_[x];
				devuelve_[x][1] = Integer.toString(frec_[x]);
			}
		}

		return devuelve_;
	}



}