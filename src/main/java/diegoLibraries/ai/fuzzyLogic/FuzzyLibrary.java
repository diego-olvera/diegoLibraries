/**
 * 
 */
package diegoLibraries.ai.fuzzyLogic;

import diegoLibraries.commons.Util;

/**
 * @author Diego Olvera
 *
 */
public class FuzzyLibrary {
	
	public static double trapecioAbiertoDer(double u,double a,double b ){
		if(u>b)return 1;
		if(u<a)return 0;
		if(a<=u && u<=b) return (u-a)/(b-a);
		return -1;
	}
	
	public static double trapecioAbiertoIzq(double u,double a,double b ){
		if(u>b)return 0;
		if(u<a)return 1;
		if(u>=a && u<=b) return (b-u)/(b-a);
		return -1;
	}

	public static double triangular(double u,double a,double b,double c){
		if(u<a || u>c)return 0;
		if(a<=u && u<b)return (u-a)/(b-a);
		if(b<=u && u<=c) return (c-u)/(c-b);
		return -1;
	}
	
	public static double trapezoidal(double u,double a,double b,double c,double d){
		if(u<a || u>d)return 0;
		if(u>=b && u<=c)return 1;
		if(u>=a && u<b)return (u-a)/(b-a);
		if(c<u && u<=d) return (d-u)/(d-c);
		return -1;
	}

	public static double curva_s(double u,double a,double b){
		if(u>b)return 1.0;
		if(u<a)return 0.0;
		if(a<=u && u<=b)return (1+Math.cos(((u-b)/(b-a))*Math.PI))/2.0;
		return -1;
	}
	
	public static double curva_z(double u,double a,double b){
		if(u>b)return 0.0;
		if(u<a)return 1.0;
		if(a<=u && u<=b) return  (1+Math.cos(((u-a)/(b-a))*Math.PI))/2.0;
		return -1;
	}
	
	public static double triangularSuave(double u,double a,double b,double c){
		if(u<a || u>c) return 0.0;
		if(a<=u && u<b) return (1+Math.cos(((u-b)/(b-a))*Math.PI))/2.0;
		if(b<=u && u<=c) return (1+Math.cos(((b-u)/(c-b))*Math.PI))/2.0;
		return -1;
	}
	
	public static double trapezoidalSuave(double u,double a,double b,double c,double d){
		if(u<a || u>d)return  0.0;
		if(b<=u && u<=c) return 1.0;
		if(a<=u && u<b) return (1+Math.cos(((u-b)/(b-a))*Math.PI))/2.0;
		if(c<u && u<=d) return (1+Math.cos(((c-u)/(d-c))*Math.PI))/2.0;
		return -1;
	}
	
	private static double min(double a,double b){
		return Double.min(a, b);
	}
	
	private static double max(double a,double b){
		return Double.max(a, b);
	}
	
	public static double compAND(double ma_u,double mb_u){
		return min(ma_u,mb_u);
	}
	
	public static double compOR(double ma_u,double mb_u){
		return max(ma_u,mb_u);
	}
	
	public static double niega(double ma_u){
		return 1.0-ma_u;
	}
	
	public static double implicaZadeh(double ma_x,double mb_y){
		return max(min(ma_x,mb_y),niega(ma_x));
	}
	
	public static double implicaMamdani(double ma_x,double mb_y){
		return min(ma_x,mb_y);
	}
	
	public static double implicaGodel(double ma_x,double mb_y){
		if(ma_x<=mb_y)return  1;
		else return mb_y;
	}
	
	public static void main(String[] args) {
		double u,a,b,c,d;
		double dif;
		final double reps;
		a=12;
		b=28;
		c=b+16;
		d=c+(b-a);
		dif=2;
		reps=30;
		Util.printCentered("Trapecio abierto por la derecha","*");
		for(u=a-dif;u<reps;u++){
			System.out.println("u="+u+", "+trapecioAbiertoDer(u, a, b));
		}
		Util.printCentered("Trapecio abierto por la izquierda","*");
		for(u=a-dif;u<reps;u++){
			System.out.println("u="+u+", "+trapecioAbiertoIzq(u, a, b));
		}
		Util.printCentered("Curva S","*");
		for(u=a-dif;u<reps;u++){
			System.out.println("u="+u+", "+curva_s(u, a, b));
		}
		Util.printCentered("Curva Z","*");
		for(u=a-dif;u<reps;u++){
			System.out.println("u="+u+", "+curva_z(u, a, b));
		}
		Util.printCentered("Triangular","*");
		for(u=a-dif;u<c+2;u++){
			System.out.println("u="+u+", "+triangular(u, a, b,c));
		}
		Util.printCentered("Triangular suave","*");
		for(u=a-dif;u<c+2;u++){
			System.out.println("u="+u+", "+triangularSuave(u, a, b,c));
		}
		Util.printCentered("Trapezoidal","*");
		for(u=a-dif;u<c+18;u++){
			System.out.println("u="+u+", "+trapezoidal(u, a, b,c,d));
		}
		Util.printCentered("Trapezoidal suave","*");
		for(u=a-dif;u<c+18;u++){
			System.out.println("u="+u+", "+trapezoidalSuave(u, a, b,c,d));
		}
	}
}
