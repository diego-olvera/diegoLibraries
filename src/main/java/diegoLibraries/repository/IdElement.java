package diegoLibraries.repository;

import diegoLibraries.commons.MyJSONObject;

public class IdElement<T> extends MyJSONObject{
	protected T id;
	
	public IdElement() {}
	public IdElement(T id) {
		setId(id);
	}
	public T getId() {
		return id;
	}
	public void setId(T id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return getId().toString();
	}	
}
