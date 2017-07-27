package diegoLibraries.wrappers;

import java.math.BigDecimal;

public class WrapperBigDecimal {
	public BigDecimal value;
	public WrapperBigDecimal(){}
	public WrapperBigDecimal(BigDecimal value) {
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
