
package diegoLibraries.sorting;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayListSortingAlgorithms{
		
	public static final int BUBBLE_SORT=1;
	public static final int SELECTION_SORT=2;
	public static final int INSERT_SORT=3;
	public static final int SHELL_SORT=4;
	public static final int QUICK_SORT=5;
	public static final int MERGE_SORT=6;
	
	public static<T> void sort(int whichTypeOfSorting,
			ArrayList<T>elements, int n,Comparator<T> comparator){
		switch(whichTypeOfSorting){ 
			case BUBBLE_SORT:bubbleSort(elements, n,comparator);break;
			case SELECTION_SORT:selectionSort(elements, n,comparator);break;
			case INSERT_SORT:insertSort(elements, n,comparator);break;
			case SHELL_SORT:shellSort(elements, n,comparator);break;
			case QUICK_SORT:quickSortInicial(elements, n,comparator);break;
			case MERGE_SORT:mergeSortInicial(elements, n, comparator);break;
			default:System.out.println("Ordenamiento no contemplado");	
		}		
	}
	public static<T> void swap(ArrayList<T>elementos, int i,int j){
		T temp=elementos.get(j);
		elementos.set(j, elementos.get(i));
		elementos.set(i, temp);
	}
	public static<T> void bubbleSort(ArrayList<T> elementos,int n,Comparator<T> comparador){
		int i,j,k,l;
		boolean huboIntercambios=true;
	    for(i=0,k=n-1;i<k && huboIntercambios;i++){
	    	huboIntercambios=false;
	        for(j=n-1,l=i+1;j>=l;j--){ 
	        	if(comparador.compare(elementos.get(j), elementos.get(j-1))<0){
	        		swap(elementos, j, j-1);
	        		huboIntercambios=true;
	        	}
	        }
	    }
	}
	public static<T> void insertSort(ArrayList<T> elementos,int n,
			Comparator<T> comparador){
	    int i,j;
	    for(i=1;i<n;i++){
	        j=i;
	        while(j>0 && (comparador.compare(elementos.get(j), elementos.get(j-1))<0)){
	            swap(elementos, j, j-1);
	            j--;
	        }
	    }
	}
	public static<T> void selectionSort(ArrayList<T> elementos,int n,Comparator<T> comparador){
	    int i,j,indiceMenor;
	    T claveMenor;
	    for(i=0;i<n-1;i++){
	        indiceMenor=i;
	        claveMenor=elementos.get(i);
	        for(j=i+1;j<n;j++){        	
	            if (comparador.compare(elementos.get(j),claveMenor)<0){
	                claveMenor=elementos.get(j);
	                indiceMenor=j;
	            }
	        }
	        if(i!=indiceMenor)
	        	swap(elementos, indiceMenor, i);
	    }
	}
	public static<T> void shellSort(ArrayList<T> elementos,int n,Comparator<T> comparador){
	    int i,j,incremento;
	    T temp;
	    incremento=n/2;
	    while(incremento>0){
	        for(i=incremento;i<n;i++){
	            j=i;
	            temp=elementos.get(i);	 	            
	            while((j>=incremento)&&(comparador.compare(elementos.get(j-incremento), temp))>0){
	            	elementos.set(j, elementos.get(j-incremento));
	                j=j-incremento;
	            }
	            elementos.set(j, temp);
	        }
	        incremento/=2;
	    }
	}
	public static<T> void quickSortInicial(ArrayList<T> elementos,int n,Comparator<T> comparador){
	    quickSort(elementos,0,n-1,comparador);
	}

	private static<T>void quickSort(ArrayList<T>elementos,int i,int j,
			Comparator<T> comparador){
	    int k,posPivote;
	    T pivote;
	    posPivote=encuentraPivote(elementos,i,j,comparador);
	    if (posPivote>=0){
	        pivote=elementos.get(posPivote);
	        k=particion(elementos,i,j,pivote,comparador);
	        quickSort(elementos,i,k-1,comparador);
	        quickSort(elementos,k,j,comparador);
	    }
	}

	private static<T>int encuentraPivote(ArrayList<T> elementos,int i,int j,
			Comparator<T> comparador){
	    T primeraClave;
	    int k,pivote=-1;
	    primeraClave=elementos.get(i);
	    for(k=i+1;k<=j;k++){	    	
	        if (comparador.compare(elementos.get(j),primeraClave)>0)
	            return k;
	        if (comparador.compare(elementos.get(k),primeraClave)<0)
	            return i;
	    }
	    return pivote;
	}

	private static<T>int particion(ArrayList<T> elementos,int i,int j,T pivote,
			Comparator<T> comparador) {
		int izq, der;
		izq = i;
		der = j;
		do {
			swap(elementos, izq, der);		
			while (comparador.compare(elementos.get(izq), pivote) < 0) {
				izq++;
			}
			while (comparador.compare(elementos.get(der), pivote)>=0) {
				der--;
			}
		} while (izq < der);
		return izq;
	}
	public static<T>  void mergeSortInicial(ArrayList<T> elementos,int n,Comparator<T> comparador){
		mergeSort(elementos, n, comparador);
	}
	private static<T> void mergeSort(ArrayList<T> registros,int n,Comparator<T> comparador){
		ArrayList<T> vector1,vector2;
		int n1,n2,x,y;
		if(n>1){
			if (n%2==0){
	            n1=n2=n/2;
	        }
	        else{
	            n1=n/2;
	            n2=n1+1;
	        }
			vector1=new ArrayList<T>();
			vector2=new ArrayList<T>();
			for(x=0;x<n1;x++)
	            vector1.add(x, registros.get(x));
	        for(y=0;y<n2;x++,y++)
	            vector2.add(y,registros.get(x));
	        mergeSort(vector1,n1,comparador);
	        mergeSort(vector2,n2,comparador);
	        fusion(vector1,n1,vector2,n2,registros,comparador);
	        vector1=null;
	        vector2=null;
		} 
	}
	private static<T> void fusion(ArrayList<T> arr1,int n1,ArrayList<T> arr2,
			int n2,ArrayList<T> destino,
			Comparator<T> comparador){
	    int x1=0,x2=0,x3=0;

	    while(x1<n1 && x2<n2){
	        if (comparador.compare(arr1.get(x1), arr2.get(x2))<0) {
	            destino.set(x3, arr1.get(x1));
	            x1++;
	        }
	        else{
	        	destino.set(x3, arr2.get(x2));
	            x2++;
	        }
	        x3++;
	    }
	    while(x1<n1){
	        destino.set(x3, arr1.get(x1));
	        x1++;
	        x3++;
	    }
	    while(x2<n2){
	    	destino.set(x3, arr2.get(x2));
	        x2++;
	        x3++;
	    }
	}
	static class ComparadorEntero implements Comparator<Integer>{
		@Override
		public int compare(Integer x, Integer y) {
			return Integer.compare(x,y);
		}	
	};
	static class Persona{
		private String nombre;
		private String apellido;
		public Persona(String nombre, String apellido) {
			this.nombre = nombre;
			this.apellido = apellido;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellido() {
			return apellido;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		@Override
		public String toString() {
			return String.format("Persona [nombre=%s, apellido=%s]", nombre,
					apellido);
		}
		
		
	}
	public static void main(String[] args) {
		ordenaPersonas();
	}
	public static void ordenaEnteros(){
		ArrayList<Integer> enteros=new ArrayList<>();
		Comparator<Integer> compEnteros=new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				return Integer.compare(x,y);
			}
		};
		
		
		for(int i=10;i>4;i--){
			enteros.add(i);
		}
		sort(MERGE_SORT, enteros,enteros.size(),compEnteros);
		//ComparadorEntero compEnteros2= new ComparadorEntero();
		//aplicarOrdenamiento(MERGE_SORT, enteros,enteros.size(),compEnteros2);
		enteros.forEach(e->System.out.println(e));
	}
	public static void ordenaPersonas(){
		Comparator<Persona> compPersonasApellido=new Comparator<ArrayListSortingAlgorithms.Persona>() {
			@Override
			public int compare(Persona x, Persona y) {
				return x.getApellido().compareTo(y.getApellido());
			}
		};
		Comparator<Persona> compPersonasNombre=new Comparator<ArrayListSortingAlgorithms.Persona>() {
			@Override
			public int compare(Persona x, Persona y) {
				return x.getNombre().compareTo(y.getNombre());
			}
		};
		ArrayList<Persona> personas=new ArrayList<>();
		personas.add(new Persona("Diego","Olvera"));
		personas.add(new Persona("Lucy","Layseca"));

		personas.add(new Persona("Jesus","Gutierrez"));

		personas.add(new Persona("Arturo","Ad"));
		sort(MERGE_SORT, personas,personas.size(),compPersonasNombre);
		
		personas.forEach(p->System.out.println(p));
		System.out.println("---");
		
		sort(MERGE_SORT, personas,personas.size(),compPersonasApellido);
		
		personas.forEach(p->System.out.println(p));
		System.out.println("---");


	}
}
