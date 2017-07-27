package diegoLibraries.sorting;

import java.lang.reflect.Array;
import java.util.Comparator;

public class SortingAlgorithms{
		
	public static final int BUBBLE_SORT=1;
	public static final int SELECTION_SORT=2;
	public static final int INSERT_SORT=3;
	public static final int SHELL_SORT=4;
	public static final int QUICK_SORT=5;
			 
	public static<T> void sort(int ordenamiento,
			T[]elementos, int n,Comparator<T> comparador){
		switch(ordenamiento){ 
			case BUBBLE_SORT:bubbleSort(elementos, n,comparador);break;
			case SELECTION_SORT:selectionSort(elementos, n,comparador);break;
			case INSERT_SORT:insertSort(elementos, n,comparador);break;
			case SHELL_SORT:shellSort(elementos, n,comparador);break;
			case QUICK_SORT:quickSortInicial(elementos, n,comparador);break;
			default:System.out.println("Ordenamiento no contemplado");	
		}
	}
	public static<T> void intercambia(T[]elementos, int i,int j){
		T temp=elementos[j];
    	elementos[j]=elementos[i];
    	elementos[i]=temp;
	}
	private static<T> void bubbleSort(T[] elementos,int n,
			Comparator<T> comparador){
		int i,j,k,l;
		boolean huboIntercambios=true;
	    for(i=0,k=n-1;i<k && huboIntercambios;i++){
	    	huboIntercambios=false;
	        for(j=n-1,l=i+1;j>=l;j--){         	
	           if(comparador.compare(elementos[j], elementos[j-1])<0){
	        	   intercambia(elementos, j, j-1);
	        	   huboIntercambios=true;
	           }
	        }
	    }
	}
	private static<T> void insertSort(T elementos[],int n,
			Comparator<T> comparador){
	    int i,j;
	    for(i=1;i<n;i++){
	        j=i;        
	        while(j>0 && (comparador.compare(elementos[j], elementos[j-1])<0)){
	            intercambia(elementos, j, j-1);
	            j--;
	        }
	    }
	}
	private static<T> void selectionSort(T elementos[],int n
			,Comparator<T> comparador){
	    int i,j,indiceMenor;
	    T claveMenor;
	    for(i=0;i<n-1;i++){
	        indiceMenor=i;
	        claveMenor=elementos[i];
	        for(j=i+1;j<n;j++){	        	
	            if (comparador.compare(elementos[j],claveMenor)<0){
	                claveMenor=elementos[j];
	                indiceMenor=j;
	            }
	        }
	        if(i!=indiceMenor)
	        	intercambia(elementos, indiceMenor, i);
	    }
	}
	private static<T> void shellSort(T elementos[],int n
			,Comparator<T> comparador){
	    int i,j,incremento;
	    T temp;
	    incremento=n/2;
	    while(incremento>0){
	        for(i=incremento;i<n;i++){
	            j=i;
	            temp=elementos[i];	 	            
	            while((j>=incremento)&&(comparador.compare(elementos[j-incremento], temp)>0)){
	                elementos[j]=elementos[j-incremento];
	                j=j-incremento;
	            }
	            elementos[j]=temp;
	        }
	        incremento/=2;
	    }
	}
	private static<T>void quickSortInicial(T elementos[],int n
			,Comparator<T> comparador){
	    quickSort(elementos,0,n-1,comparador);
	}

	private static<T>void quickSort(T elementos[],int i,int j,Comparator<T> comparador){
	    int k,posPivote;
	    T pivote;
	    posPivote=encuentraPivote(elementos,i,j, comparador);
	    if (posPivote>=0){
	        pivote=elementos[posPivote];
	        k=particion(elementos,i,j,pivote, comparador);
	        quickSort(elementos,i,k-1, comparador);
	        quickSort(elementos,k,j,comparador);
	    }
	}
	private static<T>int encuentraPivote(T elementos[],int i,int j
			,Comparator<T> comparador){
	    T primeraClave;
	    int k,pivote=-1;

	    primeraClave=elementos[i];
	    for(k=i+1;k<=j;k++){	    	
	        if (comparador.compare(elementos[k], primeraClave)>0)
	            return k;
	        if (comparador.compare(elementos[k],primeraClave)<0)
	            return i;
	    }
	    return pivote;
	}

	private static<T>int particion(T elementos[], int i, int j, T pivote
			,Comparator<T> comparador) {
		int izq, der;
		izq = i;
		der = j;
		do {
			intercambia(elementos, izq, der);			
			while (comparador.compare(elementos[izq], pivote) < 0) {
				izq++;
			}			
			while (comparador.compare(elementos[der],pivote)>=0) {
				der--;
			}
		} while (izq < der);
		return izq;
	}
	public static<T>  void mergeSortInicial(T[] elementos,int n,Comparator<T> comparador
			,Class<?> tipoElemento){
		mergeSort(elementos, n, comparador, tipoElemento);
	}
	private static<T> void mergeSort(T[] registros,int n,Comparator<T> comparador
			,Class<?> tipoElemento){
		T[] vector1,vector2;
		int n1,n2,x,y;
		if(n>1){
			if (n%2==0){
	            n1=n2=n/2;
	        }
	        else{
	            n1=n/2;
	            n2=n1+1;
	        }
			vector1=reservaEspacio(tipoElemento,n1);
			vector2=reservaEspacio(tipoElemento,n2);
			for(x=0;x<n1;x++)
	            vector1[x]=registros[x];
	        for(y=0;y<n2;x++,y++)
	            vector2[y]=registros[x];
	        mergeSort(vector1,n1,comparador,tipoElemento);
	        mergeSort(vector2,n2,comparador,tipoElemento);
	        fusion(vector1,n1,vector2,n2,registros,comparador);
	        vector1=null;
	        vector2=null;
		} 
	}
	private static<T> void fusion(T arr1[],int n1,T arr2[],int n2,T destino[],
			Comparator<T> comparador){
	    int x1=0,x2=0,x3=0;

	    while(x1<n1 && x2<n2){
	        if (comparador.compare(arr1[x1], arr2[x2])<0) {
	            destino[x3]=arr1[x1];
	            x1++;
	        }
	        else{
	            destino[x3]=arr2[x2];
	            x2++;
	        }
	        x3++;
	    }
	    while(x1<n1){
	        destino[x3]=arr1[x1];
	        x1++;
	        x3++;
	    }
	    while(x2<n2){
	        destino[x3]=arr2[x2];
	        x2++;
	        x3++;
	    }
	}
	@SuppressWarnings("unchecked")
	private static<T> T[] reservaEspacio(Class<?> tipoElemento,int tamanio){
		return (T[])Array.newInstance(tipoElemento, tamanio);
	}
}
