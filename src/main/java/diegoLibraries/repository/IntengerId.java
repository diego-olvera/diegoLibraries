package diegoLibraries.repository;

public class IntengerId extends IdElement<Integer>{
	public IntengerId(){
		super();
	}
	public IntengerId(Integer id) {
		super(id);
	}
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	
}
