package diegoLibraries.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.directory.InvalidAttributesException;

import diegoLibraries.commons.Util;


public class CurrentDate{
	
	private static GregorianCalendar getCurrentGregorianCalendar(){
		return new GregorianCalendar();
	}
    public static int getDia(){
        return getCurrentGregorianCalendar().get(Calendar.DAY_OF_MONTH); 
    }
    public  static int getMes(){
        return getCurrentGregorianCalendar().get(Calendar.MONTH);  
    }
    public  static int getAnio(){
        return getCurrentGregorianCalendar().get(Calendar.YEAR); 
    }
    public static int getHoraEn24Horas(){
    	return getCurrentGregorianCalendar().get(Calendar.HOUR_OF_DAY);
    }
    public static int getMinutos(){
    	return getCurrentGregorianCalendar().get(Calendar.MINUTE);
    }
    public static int getSegundos(){
    	return getCurrentGregorianCalendar().get(Calendar.SECOND);
    }
    public static Date getDate(){
    	return new Date(getTimeStamp());
    }
    public static TimeStamp getTimeStamp(){
		TimeStamp estampa=new TimeStamp();
		try {
			estampa.setHour(getHoraEn24Horas());
			estampa.setMinutes(getMinutos());
			estampa.setSeconds(getSegundos());
			estampa.fijaAnio(getAnio());
			estampa.fijaMes(getMes());
			estampa.fijaDia(getDia());
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
