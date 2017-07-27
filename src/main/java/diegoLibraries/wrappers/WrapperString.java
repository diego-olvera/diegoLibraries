package diegoLibraries.wrappers;


public class WrapperString {
	public String value;
	public WrapperString(){}
	public WrapperString(String value) {
		this.value = value;
	}
	public int hashCode(){
		return value.hashCode();
	}
	public boolean equals(Object e){
		return value.equals(((WrapperString)e).value);
	}
	public String toString(){
		return value;
	}		
}
