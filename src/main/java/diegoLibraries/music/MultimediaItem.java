package diegoLibraries.music;

import diegoLibraries.commons.NameItem;

public class MultimediaItem implements NameItem{
	protected String name;

	public MultimediaItem() {}
	public MultimediaItem(String name) {
		setName(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	
}
