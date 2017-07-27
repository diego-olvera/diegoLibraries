package diegoLibraries.commons;

import diegoLibraries.sorting.SortingAlgorithms;

public class Util implements UnicodeCharacters { 
	 
	public static final char A_UPPER_CASE=65;
	public static final char Z_UPPER_CASE=90;
	public static final String JUMP_LINE="\r\n";
	public static final char CERO=48;
	public static final char NINE=57; 
	
	
	public static final int JUMP_LINES=25;
	public static final int CHARACTERS_PER_ROW=80;
	static final int NUM_VALID_OF_SEPARATORS=2;
	
	public static<T> void invert(T[] array,int n){
		if(n>=1){
			for(int i=0,j=n-1;i<j;i++,j--){
				SortingAlgorithms.intercambia(array, i, j);
			}
		}		
	}
	public static void cleanScreen(int saltosDeLinea){
		for(int i=0;i<saltosDeLinea;i++)
			System.out.println();
	}
	public static void cleanScreen(){
		cleanScreen(JUMP_LINES);
	}
	public static void pause(long milisegundos){
		try{
			Thread.sleep(milisegundos);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public static void pause(String mensaje){
		System.out.print(mensaje);
		ScanF.readChar(); 
	}
	public static void pause(){
		pause("\nPresione entrar para continuar . . .");
	}
	public static void printCentered(String texto,String relleno){
	    System.out.println(getCenteredString(texto, relleno)); 
	}
	public static String getCenteredString(String texto, String relleno){
		StringBuilder cadenaCentrada= new StringBuilder();
		int longitudTexto=texto.length();
	    int espacios=(CHARACTERS_PER_ROW-longitudTexto)/2;
	    int i;
	    for(i=0;i<espacios;i++){
	        cadenaCentrada.append(relleno);
	    }
	    cadenaCentrada.append(texto);   
	    for(i+=longitudTexto;i<CHARACTERS_PER_ROW;i++){
	    	cadenaCentrada.append(relleno);
	    }
	    return cadenaCentrada.append("\n").toString();
	}	
	
	
	public static<T> void printArray(T[] array){
		System.out.println(arraytoString(array));
	}
	public static<T> String arraytoString(T[] array){
		StringBuilder s=new StringBuilder();
		for(T element: array){
			s.append(element).append("\r\n");
		}
		return s.toString();
	}
}
