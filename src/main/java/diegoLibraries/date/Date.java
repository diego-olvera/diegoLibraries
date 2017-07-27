package diegoLibraries.date;

import javax.naming.directory.InvalidAttributesException;

import diegoLibraries.interfaces.CloneObject;

public class Date implements Comparable<Date>,CloneObject<Date>{ 
	static public final int JANUARY=1;
	static public final int FEBRAURY=2; 
	static public final int APRIL=3;
	static public final int JUNE=6; 
	static public final int SEPTEMBER=9;
	static public final int NOVEMBER=11;
	static public final int DECEMBER=12;
	
	private static final int COMPARE_TO_DAY=2;
	private static final int COMPARE_TO_MONTH=1;
 	static public char separadorFecha='/'; 
 	public static final String IMP_ANIO="%4d";
 	
    private int day;
    private int month;
    private int year;
    
    public Date(){
    	day=1;
        month=JANUARY;
        year=1;
    } 
    public Date(String str,char sep) throws InvalidAttributesException{
    	this();
    	try{
    		fechaStringATipoMiFecha(str, sep);
    	}catch(InvalidAttributesException e) {
    		throw e;
    	} 
    }
    public Date(Date src){
    	day=src.day;
    	month=src.month;
    	year=src.year;
    }
    public Date clone(){
    	return new Date(this);
    }
    protected final boolean isValidDate(int d,int m,int a){
        if((d>=1 && d<=31) && (m>=JANUARY && m<=DECEMBER)){
            switch(m){
                case APRIL: case JUNE: case SEPTEMBER:
                case NOVEMBER:
                	return d<=30;
                case FEBRAURY:
                	return d<=28?true: (d==29 && a%4==0 && (!(a%100==0) || a%400==0));
				default:
                    return true;
            }
        }
        else{
            return false;
        }
    }
	public int getDay(){
        return day;
	}
	public int getMonth(){
        return month;
	}
	public int dameAnio(){
        return year;
	}
	public String toString(String sep) {
		return String.format(IMP_ANIO+sep+"%02d" + sep + "%02d",dameAnio(),
				getMonth(), getDay());
	}
	public String toString(char sep) {
		return String.format(IMP_ANIO+sep+"%02d" + sep + "%02d",dameAnio(),
				getMonth(), getDay());
	}
	@Override
	public boolean equals(Object fecha){
		return fecha instanceof Date?compareTo((Date)fecha)==0:false;
	}
	public boolean fijaDia(int d){
        if(isValidDate(d,month,year)){
            day=d;
            return true;
        }
        else{
            return false;
        }
	}
	public boolean fijaMes(int m){
	    if(isValidDate(day,m,year)){
	        month=m;
	        return true;
	    }
	    else{
	        return false;
	    }
	}
	public boolean fijaAnio(int a){
        if(isValidDate(day,month,a)){
            year=a;
            return true;
        }
        else{
            return false;
        }
	}
	@Override
	public int compareTo(Date fecha){		
		int x=dameAnio(),y=fecha.dameAnio(),igualdades=0,comp;
		boolean seCompararonTodosLosAtributos=false;
		do{
			if((comp=Integer.compare(x, y))!=0){
				return comp;
			}
			else{//"x" y "y" iguales
				switch (++igualdades) {
					case COMPARE_TO_MONTH:
						x=month;
						y=fecha.month;
						break;
					case COMPARE_TO_DAY:
						x=day;
						y=fecha.day;
						break;
					default:seCompararonTodosLosAtributos=true;
				}
			}
		}while(!seCompararonTodosLosAtributos);
		return 0;//Fechas iguales 	
	}
	protected void fechaStringATipoMiFecha(String fechaCompleta,
			char separador) throws InvalidAttributesException {
		String[] data=fechaCompleta.split(String.valueOf(separador));
		try{
			fijaAnio(Integer.parseInt(data[0]));
		}catch(Exception e) {
			throw new InvalidAttributesException(e.getMessage());
		}
		try{
			fijaMes(Integer.parseInt(data[1]));
		}catch(Exception e) {
			throw new InvalidAttributesException(e.getMessage());
		}
		try{
			fijaDia(Integer.parseInt(data[2]));
		}catch(Exception e) {
			throw new InvalidAttributesException(e.getMessage());
		}
	}
	public static void main(String[] args) throws InvalidAttributesException {
		Date fecha=new Date("2017/05/31", '/');
		System.out.println(fecha.toString('-')); 
	}
}
