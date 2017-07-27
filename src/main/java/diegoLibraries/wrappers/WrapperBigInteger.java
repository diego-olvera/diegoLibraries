package diegoLibraries.wrappers;

import java.math.BigInteger;

public class WrapperBigInteger {
	public BigInteger value;
	public WrapperBigInteger(){}
	public WrapperBigInteger(BigInteger value) {
		this.value = value;
	}
	public int hashCode(){
		return value.hashCode();
	}
	public boolean equals(Object o){
		return value.equals(o);
	}
	public String toString(){
		return value.toString();
	}
	
	
}
