package diegoLibraries.wrappers;

public class WrapperLong {
	public long value;
	public WrapperLong(){}
	public WrapperLong(long value) {
		this.value = value;
	}
	public int hashCode(){
		return Long.hashCode(value);
	}
	public boolean equals(Object o){
		return value==(((WrapperLong)o).value);
	}
	public String toString(){
		return String.valueOf(value);
	}
	
	
}
