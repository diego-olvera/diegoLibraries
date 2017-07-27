package diegoLibraries.system;

public interface OperativeSystems {
	public static int WINDOWS=1;
	public static int LINUX=2;
	public static int UNKOWN_SO=3;
	
	public static int getOperativeSystem(){
		String so=System.getProperty("os.name");
		if(so.contains("Windows")) {
			return WINDOWS;
		}
		else if(so.contains("Ubuntu")||so.contains("Debian")) {
			return LINUX;
		}
		else {
			return UNKOWN_SO;
		}
	}
}
