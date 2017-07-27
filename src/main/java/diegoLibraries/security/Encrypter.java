package diegoLibraries.security;

import diegoLibraries.typeConverter.FullConverter;

public abstract class Encrypter {
	public abstract byte[] encrypt(byte[] bytes);
	public abstract byte[] decrypt(byte[] bytes);
	
	public double simpleEncrypt(double d){
		return FullConverter.toDouble(encrypt(FullConverter.toBytes(d)));
	}
	public double simpleDesencrypt(double d){
		return FullConverter.toDouble(decrypt(FullConverter.toBytes(d)));
	}
	public byte[] encrypt(char[] chars){
		return encrypt(new String(chars));
	}
	public byte[] encrypt(String s){
		return encrypt(s.getBytes());
	}
	public byte[] encrypt(int i){
		return encrypt(FullConverter.toBytes(i));
	}
	public byte[] encrypt(float f){
		return encrypt(FullConverter.toBytes(f));
	}
	public byte[] encrypt(long l){
		return encrypt(FullConverter.toBytes(l));
	}
	public byte[] encrypt(double d){
		return encrypt(FullConverter.toBytes(d));
	}
}