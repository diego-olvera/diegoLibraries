package diegoLibraries.processManagement;

import java.util.Date;
import java.util.LinkedList;

import diegoLibraries.date.TimeInterval;

public class ProcessLogger implements ThreadLogger{
	private LinkedList<TimeInterval> timeIntervals=new LinkedList<>();

	@Override
	public void storeTimeIntervals(Date startDate, Date finishDate) {
		timeIntervals.add(new TimeInterval(startDate, finishDate));		
	}

	@Override
	public Iterable<TimeInterval> getTimeIntervalIterator() {
		return timeIntervals;
	}

	@Override
	public long getRunningTimes() {
		return timeIntervals.size();
	}

	@Override
	public void emptyLog() {
		timeIntervals=new LinkedList<>();

	}

}
