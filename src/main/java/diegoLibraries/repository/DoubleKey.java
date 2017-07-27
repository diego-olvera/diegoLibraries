package diegoLibraries.repository;

public class DoubleKey<FirstKey,SecondKey> extends MultipleKey {

	public DoubleKey() {
		super(2);
	}
	public DoubleKey(FirstKey f,SecondKey s) {
		super(2);
		setFirstKey(f);
		setSecondKey(s);
	}
	public void setFirstKey(FirstKey f) {
		keys[0]=f;
	}
	public void setSecondKey(SecondKey f) {
		keys[1]=f;
	}
	@SuppressWarnings("unchecked")
	public FirstKey getFirstKey() {
		return (FirstKey) keys[0];
	}
	@SuppressWarnings("unchecked")
	public SecondKey getSecondKey() {
		return (SecondKey) keys[1];
	}
}
