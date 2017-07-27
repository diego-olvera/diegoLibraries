
package diegoLibraries.time;

import diegoLibraries.commons.Util;

public class Timer implements Comparable<Timer>{
	private long beginningTime;
	private long endingTime;
	private long totalTime;
	
	public  boolean equals(Object obj){
		return obj instanceof Timer?((Timer)obj).getNanoseconds()==getNanoseconds():false;
	}
	public int hashCode() {
		return Long.hashCode(getNanoseconds());
	}
	public void start(){
		beginningTime = System.nanoTime();
	}
	public void stop(){
		endingTime = System.nanoTime();
		setTotalTime(endingTime-beginningTime); 
	}
	public void setTotalTime(long time){
		totalTime=time;
	}
	public long getNanoseconds(){
		return totalTime;
	}
	public long getMiliseconds(){
		return totalTime/1000000;
	}
	public float getSeconds(){
		return (float)getMiliseconds()/1000;
	}
	public float getMinutes(){
		return getSeconds()/60;
	}
	public float getHours(){
		return getMinutes()/60;
	}
	public float getDays(){
		return getHours()/24;
	}
	public float getWeeks(){
		return getDays()/7;
	}
	@Override
	public int compareTo(Timer o) {
		return Long.compare(getNanoseconds(),o.getNanoseconds());
	}
	
	public String toString() {
		StringBuilder info=new StringBuilder();
		info.append("Weeks:"+getWeeks()).append("\n");
		info.append("Days:"+getDays()).append("\n");
		info.append("getHours:"+getHours()).append("\n");
		info.append("Minutes:"+getMinutes()).append("\n");
		info.append("Seconds:"+getSeconds()).append("\n");
		info.append("Miliseconds:"+getMiliseconds()).append("\n");
		info.append("Nanoseconds:"+getNanoseconds()).append("\n");
		
		return info.toString();
	}
	public static void wait(int milis){
		Timer timer=new Timer();
		timer.start();
		do{
			timer.stop();
		}while(timer.getMiliseconds()<milis);
	}
	public static void main(String[] args) {
		System.out.println("Hello world");
		Timer m=new Timer();
		m.start();
		Util.pause("Press to stop timer...");
		m.stop();
		System.out.println(m);
	}
}
