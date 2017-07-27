package diegoLibraries.commons;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class ScanF {	 
	private static BufferedReader input=new BufferedReader(new InputStreamReader(System.in));	
	
	public static void main(String[] args) {
	}
	public static boolean isPrime(BigInteger n){
        BigInteger two=new BigInteger(String.valueOf(2));
        BigInteger one=new BigInteger(String.valueOf(1));
        BigInteger zero=new BigInteger(String.valueOf(0));
        for(BigInteger i=two;two.multiply(i).compareTo(n)<0;i.add(one)) {
            if(n.mod(i).compareTo(zero)==0)
                return false;
        }
        return true;
    }
	public static byte readByte(){
		return (byte)readDouble();
	}
	public static short readShort(){
		return (short)readDouble();
	}
	public static int readInt(){
		return (int)readDouble();		
	}
	public static float readFloat(){
		return (float)readDouble();
	}
	public static long readLong(){
		return (long)readDouble();
	}
	public static double readDouble(){
		try {
			return Double.parseDouble(input.readLine());
		} 
		catch (Exception e){
			return 0;
		}
	}
	public static char readChar(){
		try{
			return readString().charAt(0);
		}
		catch(IndexOutOfBoundsException e){
			return ' ';
		}
	}
	public static String readString(){
		try{
			return input.readLine();
		} 
		catch (Exception e){
			return "";
		}
	}
	public static BigInteger readBigInteger() {
		return new BigInteger(readString());
	}
	public static BigDecimal readBigDecimal() {
		return new BigDecimal(readString());
	}
}
