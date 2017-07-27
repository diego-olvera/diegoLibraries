package diegoLibraries.math;

import diegoLibraries.interfaces.CloneObject;
import diegoLibraries.string.StringUtil;

public class Fraction implements CloneObject<Fraction>{
    private  long numerator;
    private long denominator;

    public Fraction(){
        numerator=1;
        denominator=1;
    }
    public Fraction(long num, long den){
        setDenominator(den);
        setNumerator(num);
        if(denominator<0){
            numerator = -numerator;
            denominator = -denominator;
        }
    }
    public Fraction(final Fraction r){
    	this(r.getNumerator(),r.getDenominator());
    }
    public long getNumerator(){ 
    	return numerator;
    }
    public boolean setNumerator(long n){
    	if(n!=0){
    		numerator=n;
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    public long getDenominator(){
    	return denominator;
    }
    public boolean setDenominator(long d){
    	if(d!=0){
    		denominator=d;
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    public Fraction clone(){
    	return new Fraction(this);
    }
    public double toDecimal(){
    	return numerator/(double)denominator;
    }
    public static Fraction toFraction(double num){
    	int i=0,cant=1,j=0;
    	while(num<1){
    		num*=10;
    		//System.out.println(num+"en while");
    		i++;
    	}
    	while(j<i){
    		cant*=10;
    		j++;
    	}
    	return new Fraction((long)num,cant).simplify();
    }
    /**
     * 	greates common divisor
     * @param a
     * @param b
     * @return
     */
    public static long gcd(long a, long b){
	    long mcd, temp, resto;
	    mcd = Math.abs(a);
	    temp = Math.abs(b);
	    while(temp>0){
	        resto = mcd %temp;
	        mcd = temp;
	        temp = resto;
	    }
	    return mcd;
	}
    public Fraction simplify(){
    	long mcd=gcd(numerator,denominator);
    	if(mcd>1){
    		 numerator /= mcd;
             denominator /= mcd;
    	}
        return this;
    }
    public Fraction add(final Fraction f){
        return new Fraction(numerator*f.denominator + denominator * f.numerator,
                denominator * f.denominator);               
    }
    public Fraction subtract(final Fraction f){
        return new Fraction(numerator*f.denominator - denominator * f.numerator,
                denominator * f.denominator);               
    }
    public Fraction multiply(final Fraction f){
    	return new Fraction(numerator*f.numerator,denominator*f.denominator);
    }
    public Fraction divide(final Fraction f){
    	return new Fraction(numerator*f.denominator,denominator*f.numerator);
    }
    public boolean equals( Object r){
        return r instanceof Fraction?equals((Fraction)r):false;
    }  
    public boolean equals( final Fraction r){
        return(numerator * r.denominator ==
                denominator * r.numerator );
    }     
    public String toString(){
        return numerator + "/" + denominator;
    }
    public  boolean isValidFraction(String fraccion){
        int i, diagonalCounter,fractionSize,diagonalIndex,sizeOfNumbers;
        fractionSize=fraccion.length();
        if( fractionSize == 0){ 
        	return false;
        }
        else{
        	for(i=diagonalIndex=diagonalCounter=0;i<fractionSize;i++){
        		if(fraccion.charAt(i)=='/'){
        			diagonalIndex=i; 
        			diagonalCounter++;
        		}
        	}    	
        	if(diagonalCounter==1 && diagonalIndex>0){
        		for(i=sizeOfNumbers=0;i<fractionSize;i++){
            		if(Character.isDigit(fraccion.charAt(i))){
            			sizeOfNumbers++;
            		}
            	}
        		if(sizeOfNumbers>=2){
        			if(StringUtil.hasNumbersWithSeparator(fraccion.replaceAll("-", ""), "/")){
                		this.setNumerator(Long.parseLong(fraccion.substring(0, diagonalIndex)));
                		this.setDenominator(Long.parseLong(fraccion.substring(diagonalIndex+1, 
                				fractionSize)));
                		return true;
        				
        			}
        			else{
        				return false;
        			}    			         		
        		}
        		else{
        			return false;
        		}
        	}    	
        	else{
        		return false;
        	} 	
        }
    }
    public Fraction changeSign(){
        return new Fraction(-numerator, denominator);
    }
    
}
