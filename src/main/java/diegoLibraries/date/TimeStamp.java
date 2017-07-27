package diegoLibraries.date;

import javax.naming.directory.InvalidAttributesException;

public class TimeStamp extends CompleteDate {

	private int seconds;
	
	public TimeStamp() {
		super();
	}

	public TimeStamp(String str, char sep, char sepHourAndMinute) throws InvalidAttributesException {
		super(str, sep, sepHourAndMinute);
		int lastIndex=str.lastIndexOf(sepHourAndMinute);
		try {
			setSeconds(Integer.parseInt(str.substring(lastIndex+1)));
		}catch(Exception e) {
			throw new InvalidAttributesException(e.getMessage());
		}
	}

	public TimeStamp(TimeStamp src) {
		super(src);
		try {
			setSeconds(src.getSeconds());
		} catch (InvalidAttributesException e) {
			e.printStackTrace();
		}
	}
	public TimeStamp clone() {
		return new TimeStamp(this);
	}
	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int s)throws InvalidAttributesException {
		if(s>=0 && s<=60){
			seconds=s;
		}
		else{
			throw new InvalidAttributesException("seconds must be between 0 and 60");
		}	
	}
	public int compareTo(TimeStamp e){
		int comp=super.compareTo(e);
		return (comp==0)?Integer.compare(getSeconds(),e.getSeconds()):comp;
	}
	public String toString(char dateFormat,char hourMinuteFormat) {
		return new StringBuilder()
				.append(super.toString(dateFormat,hourMinuteFormat))
				.append(hourMinuteFormat).append(getFormat(getSeconds())).toString();
	}
	public static void main(String[] args) throws InvalidAttributesException {
		TimeStamp fecha=new TimeStamp("2017/05/31 10:20:04", '/',':');
		TimeStamp date2=new TimeStamp("2017/05/31 10:20:04", '/',':');
		System.out.println(fecha.toString('-',':'));
		
		System.out.println(date2.compareTo(fecha));
	}

}
