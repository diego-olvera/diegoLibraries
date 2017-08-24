package diegoLibraries.processManagement;

import java.util.Date;

import diegoLibraries.date.TimeInterval;

public interface ThreadLogger {
	
	void storeTimeIntervals(Date startDate,Date finishDate);
	Iterable<TimeInterval> getTimeIntervalIterator();
	long getRunningTimes();
	void emptyLog();
}
