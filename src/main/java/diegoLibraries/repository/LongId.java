package diegoLibraries.repository;

public class LongId extends IdElement<Long> implements LongIdItem{
	public LongId(){
		super();
	}
	public LongId(long id) {
		super(id);
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	
}
