// DNI 20521823 CASTRO VALERO, ALEJANDRO
public class Metodos {
	
	public static int[] fusionCR(int[] a, int[] b) {
		int[] narray = null;
		int i=0, longitud = 0, posa = 0, posb = 0;
		
		if(a != null && b != null) {
			
			if (a.length > 0  &&  b.length > 0) {

				longitud = a.length + b.length;
				narray = new int[longitud];

				// aqui rellenamos el array con numeros repetidos si los hay de menor a mayor
				while (i<longitud) {

					if (posa < a.length  && posb < b.length) {
						if (a[posa] > b[posb])
							narray[i++] = b[posb++];
						else
							narray[i++] = a[posa++];
					}
					else if (posa >= a.length  &&  posb < b.length) {
						narray[i++] = b[posb++];
					}
					else if (posa < a.length  &&  posb >= b.length) {
						narray[i++] = a[posa++];
					}
				}
			}
			else if (a.length > 0  &&  b.length == 0) {
				narray = a;
			}
			else if (a.length == 0  &&  b.length > 0) {
				narray = b;
			}
			else if (a.length == 0  &&  b.length == 0) {
				narray = new int[0];
			}
		}
		return narray;
	}


	
	public static int[] fusionSR(int[] a, int[] b) {
		int[] narray = null, aux = null;
		int repes = 0, x = 0;

		if(a != null && b != null) {

			// llamamos al metodo fusionCR para obtener la cadena con numeros repetidos a tratar
			aux = fusionCR(a,b);

			if (aux.length > 1) {

				// averiguamos los numeros repetidos del array obtenido
				for (int i=1; i<aux.length; i++) {
					if (aux[i-1] == aux[i]) {
						repes++;
					}
				}

				narray = new int[aux.length-repes];
				narray[x++] = aux[0];

				// lo rellenamos sin numeros repetidos
				for (int i=1; i<aux.length; i++) {
					if (aux[i-1] != aux[i]) {
						narray[x++] = aux[i];
					}
				}
			}

		}

		return narray;

	}




	public static boolean capicua(String a) {
		boolean iguales = true;
		int down = 0;

		if(a != null  &&  a.length()>0) {

			int up = a.length()-1;
			// recorremos el array por arriba y por abajo para ver si los caracteres son iguales
			while (down <= up) {
				if (a.charAt(down) != a.charAt(up)) {
					iguales = false;
					break;
				}
				else {
					down++;
					up--;
				}
			}
			
		}

		return iguales;
	}



	
	public static int kesimo(int[] v, int k) {
		int elemento = -1, iaux = 0;
		int []aux;

		if(v != null  &&  v.length > 0  &&  k > 0) {
			
			aux = new int[v.length];
			for (int i=0; i<v.length; i++) {
				aux[i] = v[i];
			}
			
			// ordena el array auxiliar y luego saca el kesimo elemento más pequeño
			ordenaBurbuja(aux);
			for (int i=1; i<aux.length; i++) {
				if(aux[i-1] < aux[i]) {
					iaux++;
				}
				if (iaux == k-1) {
					elemento = aux[i];
					break;
				}
			}

		}

		return elemento;

	}



	
	public static int kposicion(int [] v, int k) {
		int elemento = -1, iaux = 0, kpos = 0;
		int []aux;

		if(v != null  &&  v.length > 0  &&  k > 0) {
			
			// creamos un array auxiliar
			aux = new int[v.length];
			for (int i=0; i<v.length; i++) {
				aux[i] = v[i];
			}
			ordenaBurbuja(aux);
			
			// averiguamos el valor del numero en cuestion
			for (int i=1; i<aux.length; i++) {
				if(aux[i-1] < aux[i]) {
					iaux++;
				}
				if (iaux == k-1) {
					elemento = aux[i];
					break;
				}
			}
			
			// buscamos la posicion de ese valor
			for (int i=0; i<v.length; i++) {
				if(v[i] == elemento) {
					kpos = i;
					break;
				}
			}
		}

		return kpos;
		
	}




	public static int[][] matriz(int[] v, int[] w) {
		int[][] m = null;

		if (v != null  &&  w != null  &&  v.length > 0  &&  w.length > 0) {

			m = new int[v.length][w.length];

			// rellenamos la matriz con los resultados
			for (int i=0; i<v.length; i++) {
				for (int j=0; j<w.length; j++) {
					m[i][j] = v[i] * w[j];
				}
			}
			
		}

		return m;

	}



	public static String[][] procesa(String s) {
		String[][] matriz = null;
		String[] filas = null, dimensiones = null, columnas = null;
		int f = 0, c = 0, x = 1;

		if (s != null) {
			
			// sacamos las dimensiones de la matriz spliteando por filas
			filas = s.split("#");
			dimensiones = filas[0].split(" ");
			f = Integer.parseInt(dimensiones[0]);
			c = Integer.parseInt(dimensiones[1]);

			if(c > 0 && f > 0) {
				matriz = new String[f][c];
	
				for (int i=0; i<f; i++) {
					
					// aqui spliteamos cada fila en columnas saltandonos la de las dimensiones y rellenamos
					if (i < (filas.length-1))
						columnas = filas[x++].split(" ");
					for (int j=0; j<c; j++) {
						if(j < columnas.length)
							matriz[i][j] = columnas[j];
						else
							matriz[i][j] = "libre";
					}
				}
			}
			
		}

		return matriz;

	}



	public static int borra(String[] v,String s) {
		int repes = 0;

		if (v != null  &&  s != null  &&  v.length > 0) {
			
			for (int i=0; i<v.length; i++) {
				if (v[i] != null  &&  s.compareToIgnoreCase(v[i]) > 0) {
					v[i] = null;
					repes++;
				}
			}

		}

		return repes;

	}



	public static int[] elimina(int[] v,int i) {
		int[] ordenado = null;
		int repes = 0, j = 0;

		if (v != null  &&  v.length > 0) {
			
			// averiguamos cuantos valores coinciden con el parametro i
			for (int x=0; x<v.length; x++) {
				if (v[x] == i) {
					repes++;
				}
			}
			// creamos el array a devolver y lo rellenamos sin los valores de i
			ordenado = new int[v.length-repes];
			for (int x=0; x<v.length; x++) {
				if (v[x] != i) {
					ordenado[j++] = v[x];
				}
			}
			ordenaBurbuja(ordenado);

		}

		return ordenado;

	}



	public static int organiza(boolean[] v) {
		int cambios = 0, pos = 0;

		if (v != null  &&  v.length > 0) {

			for (int i=0; i<v.length; i++) {
				if (v[i]) pos++;
			}
			
			// cambiamos los valores de las posiciones de manera secuencial contando los cambios al reorganizarlos
			for (int i=0; i<v.length; i++) {
				if (pos > 0) {
					pos--;
					if (!v[i]) {
						cambios++;
						v[i] = true;
					}
				}
				else {
					if (v[i]) {
						cambios++;
						v[i] = false;
					}
				}
			}

		}

		return cambios;

	}




	public static void ordenaBurbuja(int[] o){ // el peor metodo de ordenacion pero funciona
		int aux = 0;

        for(int i=0; i<o.length-1; i++) {
            for(int j=0; j<o.length-i-1; j++) {
                if(o[j+1] < o[j]) {
                    aux = o[j+1];
                    o[j+1] = o[j];
                    o[j] = aux;
                }
            }
        }

	}

}