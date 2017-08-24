package diegoLibraries.processManagement;

public class Hour {
	private int hour;
	private int minute;
	

	private boolean executed;
	
	public Hour(int hour, int minute) {
		this(hour,minute,0,1);
	}
	
	public Hour(int hour, int minute, int timesToExecuteEveryMinute, int timesToExecuteEveryHour) {
		this.hour = hour;
		this.minute = minute;
	}

	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public boolean isExecuted() {
		return executed;
	}
	
	public void setExecuted(boolean b) {
		executed=b;
	}
}
