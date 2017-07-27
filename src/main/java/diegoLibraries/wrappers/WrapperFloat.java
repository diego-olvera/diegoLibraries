package diegoLibraries.wrappers;

public class WrapperFloat {
	public float value;
	public WrapperFloat(){}
	public WrapperFloat(float value) {
		this.value = value;
	}
	public int hashCode(){
		return Float.hashCode(value);
	}
	public boolean equals(Object o){
		return value==(((WrapperFloat)o).value);
	}
	public String toString(){
		return String.valueOf(value);
	}
	
	
}
