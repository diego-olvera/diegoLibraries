package diegoLibraries.wrappers;

public class WrapperByte {
	public byte value;
	public WrapperByte(){}
	public WrapperByte(byte value) {
		this.value=value;
	}
	public int hashCode(){
		return Byte.hashCode(value);
	}
	public boolean equals(Object o){
		return value==(((WrapperByte)o).value);
	}
	public String toString(){
		return String.valueOf(value);
	}
	
}
