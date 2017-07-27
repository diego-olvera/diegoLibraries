package diegoLibraries.repository;

public class MultipleKey extends IdElement<Object[]>{
	public Object[] keys;
	
	public MultipleKey(int n) {
		keys=new Object[n];
	}
	
}
