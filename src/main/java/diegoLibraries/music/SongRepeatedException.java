package diegoLibraries.music;

public class SongRepeatedException extends Exception{
	private static final long serialVersionUID = 1L;

	public SongRepeatedException(String message) {
		super(message);
	}

}
