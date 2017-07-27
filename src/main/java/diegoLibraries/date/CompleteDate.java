package diegoLibraries.date;

import javax.naming.directory.InvalidAttributesException;

public class CompleteDate extends Date {
	/**
	 * Its the hour in 24 format.
	 */
	private int hour;
	private int minutes;
	
	public CompleteDate() {
		super();
		hour=1;
		minutes=1;
	}
	public CompleteDate(CompleteDate s) {
		super(s);
		hour=s.hour;
		minutes=s.minutes;
	}
	public CompleteDate clone() {
		return new CompleteDate(this);
	}
	public CompleteDate(String str, char sep,char sepHourAndMinute) throws InvalidAttributesException {
		super(getOnlyDate(str), sep);
		String[] data=getOnlyHourMinute(str).split(String.valueOf(sepHourAndMinute));
		setHour(Integer.parseInt(data[0]));
		setMinutes(Integer.parseInt(data[1]));

	}
	static protected String getOnlyDate(String s) {
		return s.substring(0,s.indexOf(" "));
	}
	static protected String getOnlyHourMinute(String s) {
		return s.substring(s.indexOf(" ")+1);
	}
	public CompleteDate(Date src) {
		super(src);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int h) throws InvalidAttributesException{
		if(h>=0 && h<=23){
			hour=h;
		}
		else{
			throw new InvalidAttributesException("hour must be between 0 and 23");
		}
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int m)throws InvalidAttributesException {
		if(m>=0 && m<=60){
			minutes=m;
		}
		else{
			throw new InvalidAttributesException("minutes must be between 0 and 60");
		}	
	}
	public int compareTo(CompleteDate e){
		int comp=super.compareTo(e);
		if(comp==0){
			comp=Integer.compare(getHour(),e.getHour());
			if(comp==0){
				return Integer.compare(getMinutes(),e.getMinutes());
			}
		}
		return comp;
	}
	public String toString(char dateFormat,char hourMinuteFormat) {
		return new StringBuilder()
				.append(super.toString(dateFormat)).append(" ")
				.append(getFormat(getHour())).append(hourMinuteFormat)
				.append(getFormat(getMinutes())).toString();
	}
	public String getFormat(int num){
		return num<10?("0"+num):String.valueOf(num);
	}
	public static void main(String[] args) throws InvalidAttributesException {
		CompleteDate fecha=new CompleteDate("2017/05/31 10:20", '/',':');
		System.out.println(fecha.toString('-',':'));
	}
}
