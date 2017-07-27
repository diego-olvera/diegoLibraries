package diegoLibraries.wrappers;

public class WrapperDouble {
	public double value;
	public WrapperDouble(){}
	public WrapperDouble(double value) {
		this.value = value;
	}
	public int hashCode(){
		return Double.hashCode(value);
	}
	public boolean equals(Object o){
		return value==(((WrapperDouble)o).value);
	}
	public String toString(){
		return String.valueOf(value);
	}
	
}
