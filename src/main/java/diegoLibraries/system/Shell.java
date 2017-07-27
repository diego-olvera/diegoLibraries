package diegoLibraries.system;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Shell implements OperativeSystems{
	public static String CLEAR_SCREEN="Clear";
	public static String EXEC_SCRIPT="execute local script";
	
	private Runtime runtime=Runtime.getRuntime();
	private HashMap<String, String> commands;
	
	public Shell(){
		this(OperativeSystems.getOperativeSystem());
	}
	public Shell(int os) {
		commands=new HashMap<>();
		initCommands(os);
	}
	public Process exec(String command,String params) throws IOException {
		return exec(commands.get(command)+" "+params);
	}
	public Process exec(String arg) throws IOException {
		return runtime.exec(arg);
	}
	private boolean initCommands(int so){
		switch(so){
			case LINUX:iniciaLinux();break;
			case WINDOWS:iniciaWindows();break;
			default:return false;		
		}
		return true;
	}
	public void addCommand(String name,String command) {
		commands.put(name, command);
	}

	private void iniciaLinux(){
		addCommand(CLEAR_SCREEN,"clear");
		addCommand(EXEC_SCRIPT,"bash");
	}
	private void iniciaWindows(){
		addCommand(CLEAR_SCREEN,"cls");
		addCommand(EXEC_SCRIPT,"batch");

	}
	public void runScript(String fileName){
		try {
			exec(new File(fileName).getAbsolutePath());
		} catch (IOException e) {
			// No deberia entrar aqui
			e.printStackTrace();
		}
	}
	public void cleanScreen(){
		try {
			exec(CLEAR_SCREEN,"");
		} catch (IOException e) {
			// No deberia entrar aqui
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Shell ejecutador=new Shell();
		String sep=File.separator;
		ejecutador.runScript("script tests"+sep+"createDir.bat");
	}

}
