package diegoLibraries.processManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import diegoLibraries.date.TimeInterval;
import diegoLibraries.stream.Printer;

public class Process extends Thread{
	public static final int FORMAT_MILIS=0;
	public static final int FORMAT_SECONDS=1;

	public static final int FORMAT_MINUTES=2;
	public static final int FORMAT_HOURS=3;
	public static final int FORMAT_DAYS=4;
	
	public static final ThreadLogger emptyLogger=new ThreadLogger() {		
		@Override
		public void storeTimeIntervals(Date startDate, Date finishDate) {				
		}

		@Override
		public Iterable<TimeInterval> getTimeIntervalIterator() {
			return new ArrayList<TimeInterval>(0);
		}

		@Override
		public long getRunningTimes() {
			return 0;
		}
		@Override
		public void emptyLog() {			
		}
	};
	public static final Printer emptyPrinter=new Printer() {		
		@Override
		public void print(Object o) {			
		}
	};

	private Date terminatedDate;
	private Runnable runnable;
	private Printer printer;
	private long sleepingMilis;
	private int formatSleepingDuration=FORMAT_SECONDS;
	private boolean active;
	private boolean sleepBetweenRuns=true;
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	private ThreadLogger threadLogger;
	
	public Process() {
		
	}
	public Process(Runnable runnable) {
		this(runnable,"Unknown thread");
	}
	public Process(Runnable runnable,String name) {
		this(runnable,System.out::print,name,2000);
	}
	public Process(Runnable runnable, Printer printer,String name,long sleepingMilis) {
		setRunnable(runnable);
		setPrinter(printer);
		setName(name);
		setSleepingMilis(sleepingMilis);
		setActive(true);
		setThreadLogger(null);
	}
	
	public static ThreadLogger getEmptyLogger() {
		return emptyLogger;
	}
	public static Printer getEmptyPrinter() {
		return emptyPrinter;
	}
	public ThreadLogger getThreadLogger() {
		return threadLogger;
	}
	public void setThreadLogger(ThreadLogger t) {
		if(t==null) {
			t=getEmptyLogger();
		}
		threadLogger = t;
	}
	public boolean isSleepBetweenRuns() {
		return sleepBetweenRuns;
	}
	public void setSleepBetweenRuns(boolean sleepBetweenRuns) {
		this.sleepBetweenRuns = sleepBetweenRuns;
	}
	public void println(Object o) {
		printer.println(o);
	}
	public void print(Object o) {
		printer.println(o);
	}
	public long getRunningTimes() {
		return threadLogger.getRunningTimes();
	}
	public Date getTerminatedDate() {
		return terminatedDate;
	}
	protected void setTerminatedDate(Date terminatedDate) {
		this.terminatedDate = terminatedDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
		String threadName=getName();
		println((active)?"Activating thread '"+threadName+"'":"Deactivating thread '"+threadName+"'");
	}
	public void forceSetActive(boolean active) {
		setActive(active);
		interrupt();
	}
	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}
	public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat;
	}
	public Printer getPrinter() {
		return printer;
	}
	public void setPrinter(Printer p) {
		if(p==null) {
			p=getEmptyPrinter();
		}
		printer = p;
	}
	public Runnable getRunnable() {
		return runnable;
	}
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}
	public Iterable<TimeInterval> timeIntervalsIterable() {
		return threadLogger.getTimeIntervalIterator();
	}
	public int getFormatSleepingDuration() {
		return formatSleepingDuration;
	}
	public void setFormatSleepingDuration(int formatSleepingDuration) {
		this.formatSleepingDuration = formatSleepingDuration;
	}
	public double getSleepingDuration() {
		switch(getFormatSleepingDuration()) {
			case FORMAT_DAYS:return getSleepingDays();
			case FORMAT_HOURS:return getSleepingHours();
			case FORMAT_SECONDS:return getSleepingSeconds();
			case FORMAT_MINUTES:return getSleepingMinutes();
			case FORMAT_MILIS:default: return getSleepingMilis();
		}
	}
	public String getSleepingUnit() {
		switch(getFormatSleepingDuration()) {
			case FORMAT_DAYS:return "days";
			case FORMAT_HOURS:return "hours";
			case FORMAT_SECONDS:return "seconds";
			case FORMAT_MINUTES:return "minutes";
			case FORMAT_MILIS:default: return "miliseconds";
		}
	}
	public String getSleepingSentence() {
		return String.format("%.2f %s",getSleepingDuration(),getSleepingUnit());
	}
	public String getFormattedDate(Date date) {
		return date!=null?getSimpleDateFormat().format(date):null;
	}	
	public long getSleepingMilis() {
		return sleepingMilis;
	}
	public long getSleepingSeconds() {
		return TimeUnit.MILLISECONDS.toSeconds(getSleepingMilis());
	}
	public long getSleepingMinutes() {
		return TimeUnit.MILLISECONDS.toMinutes(getSleepingMilis());
	}
	public long getSleepingHours() {
		return TimeUnit.MILLISECONDS.toHours(getSleepingMilis());
	}
	public long getSleepingDays() {
		return TimeUnit.MILLISECONDS.toDays(getSleepingMilis());
	}
	public void setSleepingMilis(long sleepingMilis) {
		this.sleepingMilis = sleepingMilis;
	}
	public void setSleepingSeconds(long value) {
		setSleepingMilis(TimeUnit.SECONDS.toMillis(value));
	}
	public void setSleepingMinutes(long value) {
		setSleepingMilis(TimeUnit.MINUTES.toMillis(value));
	}
	public void setSleepingHours(long value) {
		setSleepingMilis(TimeUnit.HOURS.toMillis(value));
	}
	public void setSleepingDays(long value) {
		setSleepingMilis(TimeUnit.DAYS.toMillis(value));
	}
	public boolean isTimeToRun() {
		return true; 
	}
	public void setRunningFields() {
		
	}
	public void emptyLog() {
		threadLogger.emptyLog();
	}
	@Override
	public int hashCode() {
		return getName().hashCode();
	}
	public boolean equals(Object o) {
		return o instanceof Process?getName().equals(((Process)o).getName()):false;
	}
	public synchronized void run() {
		Date startDate;
		Date finishDate;
		while(isActive()) {
			if(isTimeToRun()) {
				startDate=(new Date());
				println("Thread '"+getName()+"' running at "+getFormattedDate(startDate)); 
				setRunningFields();
				runnable.run();
				finishDate=(new Date());
				println("Thread '"+getName()+"' finished running "+getFormattedDate(finishDate));
				threadLogger.storeTimeIntervals(startDate, finishDate);
			}
			if(isSleepBetweenRuns()) {
				try {
					println("Thread '"+getName()+"' sleeping for "+getSleepingSentence()); 
					wait(getSleepingMilis());
				} catch (InterruptedException e) {
					println("Thread '"+getName()+"' interrupted while waiting"); 
				}
			}
			
		}
		setTerminatedDate(new Date());
		println("Thread '"+getName()+"' terminated at "+getFormattedDate(getTerminatedDate())); 
		println("Thread '"+getName()+"' executed "+getRunningTimes()+ " times"); 

	}
	
	public static void main(String[] args) {
		int waitingSeconds=3;
		Process process=new Process(new Runnable() {			
			@Override
			public void run() {
				for(int i=0;i<5;i++) {
					System.out.println("i="+i);
				}	
			}
		});
		process.setSleepBetweenRuns(false);
		//process.setPrinter(null);
		process.setThreadLogger(null);
		process.start();
		System.out.println("Waiting "+waitingSeconds +" seconds for interrupting the thread");
		try {			
			Thread.sleep(TimeUnit.SECONDS.toMillis(waitingSeconds));
			System.out.println("Stopping thread");
			process.forceSetActive(false);
			/*Thread.sleep(TimeUnit.SECONDS.toMillis(2));
			for(TimeInterval t:process.timeIntervalsIterable()) {
				System.out.println(
						"Start date:"+process.getFormattedDate(t.getStartDate())
						+", Finish date:"+process.getFormattedDate(t.getFinishDate())
				);
			}*/
			Thread.sleep(TimeUnit.SECONDS.toMillis(2));
			System.out.println(process.getState().name());
			System.out.println(process.getState().equals(State.TERMINATED));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
