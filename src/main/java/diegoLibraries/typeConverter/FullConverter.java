package diegoLibraries.typeConverter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FullConverter {
	
	public static ByteOrder order=ByteOrder.BIG_ENDIAN;
	
	public static String toBinary(int n){
		return Integer.toString(n, 2);
	}
	public static byte[] toBytes(String s){
		byte[] size=toBytes(s.length());
		byte[] bytesString=s.getBytes();
		byte[] bytes=new byte[size.length+bytesString.length];
		int p=0;
		for(byte b:size){
			bytes[p++]=b;
		}
		for(byte b:bytesString){
			bytes[p++]=b;
		}
		return bytes;
	}
	public static void toBytes(byte[] bytes,int beginPos,String s){
		int p=beginPos;
		for(byte b:toBytes(s)){
			bytes[p++]=b;
		}
	}
	public static String toString(byte[] bytes,int beginPos){
		int size=FullConverter.toInt(bytes,beginPos);
		int p=beginPos;
		byte bytesNew[]=new byte[size+Integer.BYTES];
		for(int i=0,j=bytesNew.length;i<j;i++){
			bytesNew[i]=bytes[p++];
		}
		return toString(bytesNew);
	}
	public static String toString(byte[] bytes){
		byte[] sizeBytes=new byte[Integer.BYTES];
		int currentBytesPos,i;
		for(i=currentBytesPos=0;i<Integer.BYTES;i++){
			sizeBytes[i]=bytes[currentBytesPos++];
		}
		int size=toInt(sizeBytes);
		byte[] stringBytes=new byte[size];
		for(i=0;i<size;i++){
			stringBytes[i]=bytes[currentBytesPos++];
		}
		return new String(stringBytes);
		
	}
	public static void toBytes(byte buffer[],int beginningPos,int x){
		int i=beginningPos;
		for(byte b: toBytes(x)){
			buffer[i++]=b;
		}
	}
	public static void toBytes(byte buffer[],int beginningPos,short x){
		int i=beginningPos;
		for(byte b: toBytes(x)){
			buffer[i++]=b;
		}
	}
	public static void toBytes(byte buffer[],int beginningPos,long x){
		int i=beginningPos;
		for(byte b: toBytes(x)){
			buffer[i++]=b;
		}
	}
	public static void toBytes(byte buffer[],int beginningPos,float x){
		int i=beginningPos;
		for(byte b: toBytes(x)){
			buffer[i++]=b;
		}
	}
	public static void toBytes(byte buffer[],int beginningPos,double x){
		int i=beginningPos;
		for(byte b: toBytes(x)){
			buffer[i++]=b;
		}
	}
	public static byte[] toBytes(int myInteger){
	    return ByteBuffer.allocate(4).order(order).putInt(myInteger).array();
	}
	public static  byte[] toBytes(short s){
	    return ByteBuffer.allocate(2).order(order).putShort(s).array();
	}
	public static int toInt(byte [] bytes){
	    return ByteBuffer.wrap(bytes).order(order).getInt();
	}
	public static short toShort(byte [] bytes){
	    return ByteBuffer.wrap(bytes).order(order).getShort();
	}
	public static byte[] toBytes(float f){
		return ByteBuffer.allocate(4).order(order).putFloat(f).array();
	}
	public static float toFloat(byte[] bytes){
		return ByteBuffer.wrap(bytes).order(order).getFloat();
	}
	public static byte[] toBytes(long l) {
		return ByteBuffer.allocate(8).order(order).putLong(l).array();
	}

	public static long toLong(byte[] bytes) {
		return ByteBuffer.wrap(bytes).order(order).getLong();

	}
	public static byte[] toBytes(double value) {
		return ByteBuffer.allocate(8).order(order).putDouble(value).array();

	}
	public static long toLong(byte[] bytes,int beginPos){
	    return ByteBuffer.wrap(bytes).order(order).getLong(beginPos);
	}
	public static short toShort(byte[] bytes,int beginPos){
	    return ByteBuffer.wrap(bytes).order(order).getShort(beginPos);
	}
	public static int toInt(byte[] bytes,int beginPos){
	    return ByteBuffer.wrap(bytes).order(order).getInt(beginPos);
	}
	public static Double toDouble(byte[] bytes,int beginPos){
	    return ByteBuffer.wrap(bytes).order(order).getDouble(beginPos);
	}
	public static Float toFloat(byte[] bytes,int beginPos){
	    return ByteBuffer.wrap(bytes).order(order).getFloat(beginPos);
	}
	public static double toDouble(byte[] bytes) {
	    return ByteBuffer.wrap(bytes).order(order).getDouble();
	}
	public static void main(String[] args) {
		int x=40;
		int y=80;
		String string="Hola";
		byte[] buffer=new byte[8+Integer.BYTES+string.length()];
		FullConverter.toBytes(buffer,0,x);
		FullConverter.toBytes(buffer,4,y);
		FullConverter.toBytes(buffer,8,string);
		System.out.println(FullConverter.toInt(buffer,0));
		System.out.println(FullConverter.toInt(buffer,4));
		
		System.out.println(FullConverter.toString(buffer,8));
	}
}