package diegoLibraries.wrappers;

public class WrapperInt {
	public int value;
	public WrapperInt(){}
	public WrapperInt(int value) {
		this.value = value;
	}
	public int hashCode(){
		return Integer.hashCode(value);
	}
	public boolean equals(Object o){
		return value==(((WrapperInt)o).value);
	}
	public String toString(){
		return String.valueOf(value);
	}
	
	
}
