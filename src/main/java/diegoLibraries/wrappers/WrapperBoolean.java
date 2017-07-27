package diegoLibraries.wrappers;

public class WrapperBoolean {
	public boolean value;
	public WrapperBoolean(){}
	public WrapperBoolean(boolean value){
		this.value=value;
	}
	public int hashCode(){
		return Boolean.hashCode(value);
	} 
	public boolean equals(Object o){
		return value==(((WrapperBoolean)o).value);
	}
	public String toString(){
		return String.valueOf(value);
	}
	
}
