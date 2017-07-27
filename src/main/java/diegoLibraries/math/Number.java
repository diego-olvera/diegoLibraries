package diegoLibraries.math;

import java.util.ArrayList;


public class Number {
	public static double getRandomNumber(double limiteInferior,double limiteSuperior){
		return limiteInferior + ( creaRandom()* (limiteSuperior - limiteInferior));
	} 
	public static double creaRandom(){
		return Math.random();
		//return new java.util.Random().nextDouble();
	}
	public static int decimal(String numeroEnBinario){
		if(numeroEnBinario==null){
			return 0;
		}   
		numeroEnBinario=numeroEnBinario.replaceAll(" ","");
		int i,potencia,resultado;
		boolean esNegativo=numeroEnBinario.charAt(0)=='1';
		for(i=numeroEnBinario.length()-1,potencia=resultado=0;i>=1;i--,potencia++){
			if(numeroEnBinario.charAt(i)=='1'){
				resultado+=(int)Math.pow(2, potencia);
			}
			//else no se suma		
		}		
		return esNegativo?resultado*-1:resultado;
	}
	public static int decimalSinSigno(String numeroEnBinario){
		if(numeroEnBinario==null){
			return 0;
		}   
		numeroEnBinario=numeroEnBinario.replaceAll(" ","");
		int i,potencia,resultado;
		for(i=numeroEnBinario.length()-1,potencia=resultado=0;i>=0;i--,potencia++){
			if(numeroEnBinario.charAt(i)=='1'){
				resultado+=(int)Math.pow(2, potencia);
			}
			//else no se suma		
		}		
		return resultado;
	}
	public static String num_a_binario(int num){
		StringBuilder binarioCompleto=new StringBuilder();
		binarioCompleto.append(num>0?"0":"1");
		if(num<0){
			num*=-1;
		}
		String binIncompleto=Integer.toString(num, 2);
		int ceros_a_rellenar=31-binIncompleto.length();
		for(int i=0;i<ceros_a_rellenar;i++){
			binarioCompleto.append("0");
		}
		return binarioCompleto.append(binIncompleto).toString();
	}
	public static void main(String[] args) {
		ArrayList<Integer> numeros=new ArrayList<Integer>();
		numeros.add(2139095039);
		numeros.add(1073741824);
		numeros.add(1083130432);
		numeros.add(1090519040);
		numeros.add(2139095039);
		numeros.add(2139095039);
		numeros.add(1128824832);
		numeros.add(1137197056);
		numeros.add(1145577472);
		numeros.forEach(num->System.out.println(num+", "+Float.intBitsToFloat(num)));
	}
}
