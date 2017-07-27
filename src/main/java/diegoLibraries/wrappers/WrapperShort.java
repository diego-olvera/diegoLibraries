package diegoLibraries.wrappers;

public class WrapperShort {
	public short value;
	public WrapperShort(){}
	public WrapperShort(short value) {
		this.value = value;
	}
	public int hashCode(){
		return Short.hashCode(value);
	}
	public boolean equals(Object o){
		return value==(((WrapperChar)o).value);
	}
	public String toString(){
		return String.valueOf(value);
	}
	
	
}
