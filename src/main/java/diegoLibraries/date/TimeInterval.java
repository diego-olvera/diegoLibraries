package diegoLibraries.date;

import java.util.Date;

public class TimeInterval {
	private java.util.Date startDate;
	private java.util.Date finishDate;
	
	public TimeInterval() {
	}
	public TimeInterval(Date startDate, Date finishDate) {
		this.startDate = startDate;
		this.finishDate = finishDate;
	}
	public java.util.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	public java.util.Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(java.util.Date finishDate) {
		this.finishDate = finishDate;
	}
	
	
}
