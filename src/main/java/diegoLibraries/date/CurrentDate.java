package diegoLibraries.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.directory.InvalidAttributesException;

import diegoLibraries.commons.Util;


public class CurrentDate{
	
	private static GregorianCalendar getCurrentGregorianCalendar(){
		return new GregorianCalendar();
	}
    public static int getDay(){
        return getCurrentGregorianCalendar().get(Calendar.DAY_OF_MONTH); 
    }
    public  static int getMonth(){
        return getCurrentGregorianCalendar().get(Calendar.MONTH);  
    }
    public  static int getYear(){
        return getCurrentGregorianCalendar().get(Calendar.YEAR); 
    }
    public static int getHour(){
    	return getCurrentGregorianCalendar().get(Calendar.HOUR_OF_DAY);
    }
    public static int getMinutes(){
    	return getCurrentGregorianCalendar().get(Calendar.MINUTE);
    }
    public static int getSeconds(){
    	return getCurrentGregorianCalendar().get(Calendar.SECOND);
    }
    public static Date getDate(){
    	return new Date(getTimeStamp());
    }
    public static TimeStamp getTimeStamp(){
		TimeStamp estampa=new TimeStamp();
		try {
			estampa.setHour(getHour());
			estampa.setMinutes(getMinutes());
			estampa.setSeconds(getSeconds());
			estampa.fijaAnio(getYear());
			estampa.fijaMes(getMonth());
			estampa.fijaDia(getDay());
		} catch (InvalidAttributesException e) {
			e.printStackTrace();
		}		
		return estampa;
    }
    public static CompleteDate getCompleteDate(){
    	return new CompleteDate(getTimeStamp());
    }
    public static void main(String[] args) { 
    	for(int i=0;i<100;i++){
    		System.out.println(getDate().toString('/'));
    		System.out.println(getCompleteDate().toString('/',':'));
    		System.out.println(getTimeStamp().toString('/',':'));
    		Util.pause();
    	}
		//System.out.println(getFechaActual());
	}
}
