package diegoLibraries.processManagement;

import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class ProcessManager implements Iterable<Process>{
	private HashMap<String,Process> processes=new HashMap<>();

	public ProcessManager() {
	}

	public Iterable<Process> getProcessesIterable() {
		return processes.values();
	}
	@Override
	public Iterator<Process> iterator() {
		return getProcessesIterable().iterator();
	}
	public boolean addProcess(Process p) {
		String name=p.getName();
		if(processes.containsKey(name)) {
			return false;
		}
		return processes.put(name,p)==null;
	}
	public Process getProcess(String name) {
		return processes.get(name);
	}
	public Process removeProcess(String name) {
		return processes.remove(name);
	}

	public boolean removeProcess(Process p) {
		return processes.remove(p.getName())!=null;
	}
	public boolean stopProcess(String name) {
		return stopProcess(name,true);
	}
	public boolean stopProcess(String name,boolean waitForTermination) {
		Process p=new Process();
		p.setName(name);
		return stopProcess(p,waitForTermination);
	}
	public boolean stopProcess(Process p) {
		return stopProcess(p,true);
	}
	public boolean stopProcess(Process p,boolean waitForTermination) {
		p=getProcess(p.getName());
		if(p==null)return false;
		p.forceSetActive(false);
		if(waitForTermination) {
			while(!p.getState().equals(State.TERMINATED)) {
			}
		}		
		return true;
	}
	public boolean startProcess(String name) {
		Process p=new Process();
		p.setName(name);
		return startProcess(p);
	}
	public boolean startProcess(Process p) {
		p=getProcess(p.getName());
		if(p==null)return false;
		p.forceSetActive(true);
		p.start();
		return true;
	}
	public void startAllProcesses() {
		for(Process p:this) {
			startProcess(p);
		}
	}
	public void stopAllProcesses() {
		stopAllProcesses(true);
	}
	public void stopAllProcesses(boolean waitForTermination) {
		for(Process p:this) {
			stopProcess(p,waitForTermination);
		}		
	}
	public static void main(String[] args) {
		int waitingSeconds=3;
		ProcessManager manager=new ProcessManager();
		Process process=new Process(new Runnable() {			
			@Override
			public void run() {
				for(int i=0;i<5;i++) {
					System.out.println("i="+i);
				}	
			}
		});
		process.setSleepBetweenRuns(false);
		process.setPrinter(null);
		process.setThreadLogger(null);
		manager.addProcess(process);
		System.out.println("Process started?"+manager.startProcess(process));
		System.out.println("Waiting "+waitingSeconds +" seconds for interrupting the thread");
		try {			
			Thread.sleep(TimeUnit.SECONDS.toMillis(waitingSeconds));
			System.out.println("Stopping thread");
			if(manager.stopProcess(process)) {
				System.out.println("Thread has been stop");
			}
			else {
				System.out.println("Thread has NOT been stop");
			}
			System.out.println(process.getState().name());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
