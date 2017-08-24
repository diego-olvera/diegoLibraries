package diegoLibraries.processManagement;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import diegoLibraries.date.CurrentDate;
import diegoLibraries.date.TimeInterval;
import diegoLibraries.stream.Printer;

public class DailyHourProcess extends Process{
	private HashMap<Integer,Hour> hours=new HashMap<>();
	
	public DailyHourProcess(Runnable runnable, Printer printer, String name, long sleepingMilis) {
		super(runnable, printer, name, sleepingMilis);
	}

	public DailyHourProcess(Runnable runnable, String name) {
		super(runnable, name);
	}

	public DailyHourProcess(Runnable runnable) {
		super(runnable);
	}
	public Hour addHour(Hour hour) {
		return hours.put(hour.getHour(),hour);
	}
	public Hour removeHour(int hour) {
		return hours.remove(hour);
	}
	public Hour getHour(int hour) {
		return hours.get(hour);
	}
	public void addHours(int firstHour,int endHour,int min) {
		for(int hour=firstHour;hour<=endHour;hour++) {
			addHour(new Hour(hour, min));
		}
	}
	public void addHours(int firstHour,int endHour) {
		addHours(firstHour,endHour,0);
	}
	public void addHours(int firstHour) {
		addHours(firstHour,23,0);
	}
	public void addHours() {
		addHours(0,23);
	}
	@Override
	public boolean isTimeToRun() {
		Hour hour=hours.get(CurrentDate.getHour());
		return hour!=null && !hour.isExecuted() && CurrentDate.getMinutes()>=hour.getMinute();
	}
	@Override
	public void setRunningFields() {
		int currentHour=CurrentDate.getHour();
		for(Entry<Integer,Hour> entry:hours.entrySet()) {
			entry.getValue().setExecuted(entry.getKey()==currentHour);
		}
	}
	public static void main(String[] args) {
		int waitingSeconds=30;
		DailyHourProcess process=new DailyHourProcess(new Runnable() {			
			@Override
			public void run() {
				for(int i=0;i<5;i++) {
					System.out.println("i="+i);
				}	
			}
		});
		process.addHours();
		process.setSleepingMinutes(25);
		process.setName("Every 25 minutes update");
		process.setFormatSleepingDuration(Process.FORMAT_MINUTES);
		process.start();
		System.out.println("Waiting "+waitingSeconds +" seconds for interrupting the thread");
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(waitingSeconds));
			System.out.println("Stopping thread");
			process.forceSetActive(false);
			Thread.sleep(TimeUnit.SECONDS.toMillis(2));
			for(TimeInterval t:process.timeIntervalsIterable()) {
				System.out.println(
						"Start date:"+process.getFormattedDate(t.getStartDate())
						+", Finish date:"+process.getFormattedDate(t.getFinishDate())
				);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
