package diegoLibraries.wrappers;

public class WrapperChar {
	public char value;
	public WrapperChar(){}
	public WrapperChar(char value) {
		this.value = value;
	}
	public int hashCode(){
		return Character.hashCode(value);
	}
	public boolean equals(Object o){
		return value==(((WrapperChar)o).value);
	}
	public String toString(){
		return String.valueOf(value);
	}

}
