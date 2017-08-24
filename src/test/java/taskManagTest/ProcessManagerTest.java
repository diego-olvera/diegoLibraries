package taskManagTest;

import java.util.concurrent.TimeUnit;

import diegoLibraries.processManagement.DailyHourProcess;
import diegoLibraries.processManagement.Process;
import diegoLibraries.processManagement.ProcessManager;

public class ProcessManagerTest {
	
	public static Process getHourProcess(String name) {
		DailyHourProcess process=new DailyHourProcess(new Runnable() {			
			@Override
			public void run() {
				System.out.println(name+" process running");
			}
		});
		process.addHours();
		process.setSleepingMinutes(1);
		process.setName(name);
		process.setFormatSleepingDuration(Process.FORMAT_MINUTES);
		return process;
	}
	public static Process getProcess(String name) {
		Process process=new Process(new Runnable() {			
			@Override
			public void run() {
				System.out.println("Normal process running");
	
			}
		});
		process.setSleepBetweenRuns(false);
		//process.setPrinter(null);
		process.setThreadLogger(null);
		return process;
	}
	public static void main(String[] args) {
		ProcessManager manager=new ProcessManager();
		manager.addProcess(getHourProcess("Hour process 1"));
		manager.addProcess(getHourProcess("Hour process 2"));
		manager.addProcess(getHourProcess("Normal process 1"));
		manager.addProcess(getHourProcess("Normal process 2"));
		
		manager.startAllProcesses();
		
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(15));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		manager.stopAllProcesses(true);
	}
}
