package diegoLibraries.string;
   
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import diegoLibraries.exception.UtilException;
import diegoLibraries.math.Number;
import diegoLibraries.wrappers.WrapperInt;
 
/**
 * 
 * @author Diego Olvera
 *
 */
public class StringUtil {
	public static final char A_UPPER_CASE=65;
	public static final char Z_UPPER_CASE=90;
	
	public static final char ZERO=48;
	public static final char NINE=57;
	
	public static final char ASCII_START=33;
	public static final char ASCII_END=126;
	
	public static final int ONLY_LETTERS=1;
	public static final int ONLY_DIGITS=2;
	public static final int DIGITS_AND_LETTERS=3;
	public static final int ANY_CHARACTER=4;
	
	public static final int RFC_SIZE=13;
	//RFC Validations
	public static final int CHAR_FIRST_VALIDATION=4,CHAR_SECONCD_VALIDATION=10;  
	
	public static Pattern pwdPattern = Pattern
				.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,100})");
	
	public static boolean anagram(ArrayList<String> strings) {
		int i,stringsSize=strings.size();
		ArrayList<HashMap<Character,WrapperInt>> stringFrequencies=new ArrayList<>(stringsSize);
		boolean anagram;
		for(String string:strings) {
			stringFrequencies.add(getFrequencies(string));		
		}
		anagram=true;
		for(i=0;i<stringsSize && anagram;i++) {
			if(i+1<stringsSize) {
				anagram=stringFrequencies.get(i).equals(stringFrequencies.get(i+1));
			}
		}
		return anagram && stringsSize>1;
	}
	public static String getCamelCaseStringWithNoSpaces(String str) {
		StringBuilder newString=new StringBuilder();
		char aux;
		int state=0;
		for(int i=0,j=str.length();i<j;i++){
			aux=str.charAt(i);
			switch(state) {
			case 0:
				if(Character.isWhitespace(aux)) {
					state=0;
				}
				else if(Character.isLetter(aux)){
					aux=Character.toUpperCase(aux);
					state=1;
				}
				break;
			case 1:
				state=Character.isWhitespace(aux)?0:1;
				break;
			default:
				//How to handle this kind of things?
				throw UtilException.getRunTimeException(
					"State default in diegoLibraries.string.StringUtil.getCamelCaseStringWithSpaces");
				
			}
			if(!Character.isWhitespace(aux)) {
				newString.append(aux);
			}
		}
		return newString.toString();
	} 
	public static String getCamelCaseStringWithSpaces(String str) {
		StringBuilder newString=new StringBuilder();
		char aux;
		int state=0;
		for(int i=0,j=str.length();i<j;i++){
			aux=str.charAt(i);
			switch(state) {
			case 0:
				if(Character.isWhitespace(aux)) {
					state=0;
				}
				else if(Character.isLetter(aux)){
					aux=Character.toUpperCase(aux);
					state=1;
				}
				break;
			case 1:
				state=Character.isWhitespace(aux)?0:1;
				break;
			default:
				//How to handle this kind of things?
				throw UtilException.getRunTimeException(
					"State default in diegoLibraries.string.StringUtil.getCamelCaseStringWithSpaces");
				
			}
			newString.append(aux);
		}
		return newString.toString();
	}   
	public static HashMap<Character,WrapperInt> getFrequencies(String str){
		HashMap<Character,WrapperInt> frequencies=new HashMap<>();
		char aux;
		WrapperInt wrapperInt;
		for(int i=0,j=str.length();i<j;i++) {
			aux=Character.toLowerCase(str.charAt(i));
			wrapperInt=frequencies.get(aux);
			if(wrapperInt!=null) {
				wrapperInt.value++;
			}
			else {
				frequencies.put(aux, new WrapperInt(1));
			}
			
		}
		return frequencies;
	}
	public static String getRandomRFC(boolean fullRFC){
		StringBuilder rfc;
		int firstCharValid,secondCharValid;
		int rfcSize;
		if(fullRFC){
			rfc=new StringBuilder(RFC_SIZE);
			rfcSize=RFC_SIZE;
			firstCharValid=CHAR_FIRST_VALIDATION;
			secondCharValid=CHAR_SECONCD_VALIDATION;
		}
		else{
			rfcSize=RFC_SIZE-1;
			firstCharValid=CHAR_FIRST_VALIDATION-1;
			secondCharValid=CHAR_SECONCD_VALIDATION-1;
			rfc=new StringBuilder(RFC_SIZE-1);
		}
		rfc.append(getRandomString(firstCharValid,ONLY_LETTERS));
		rfc.append(getRandomString(secondCharValid-firstCharValid,
				ONLY_DIGITS));
		rfc.append(getRandomString(rfcSize-secondCharValid,
					DIGITS_AND_LETTERS));
		return rfc.toString();	
	}
	public static String getRandomString(int size,int stringType){
		int[] limits;
		int i,lowerLimit,higherLimit;
		char c;
		StringBuilder s=new StringBuilder(size);
		if(stringType==DIGITS_AND_LETTERS){			
			i=0;
			while(i<size){			
				c=(char)Number.getRandomNumber(ZERO, Z_UPPER_CASE);
				if(Character.isLetterOrDigit(c)){
					s.append(c);
					i++;
				}//else dont add string
			}			
		}
		else{
			limits=getRandomWordLimits(stringType);
			for(lowerLimit=limits[0],higherLimit=limits[1],i=0;i<size;i++){
				s.append((char)Number.getRandomNumber(lowerLimit,higherLimit));
			} 	
		}		
		return s.toString();
	}
	private static int[] getRandomWordLimits(int tipo){
		int limites[]=new int[2]; 
		switch (tipo) {
			case ONLY_LETTERS:
				limites[0]=A_UPPER_CASE;
				limites[1]=Z_UPPER_CASE;
				break;
			case ONLY_DIGITS:
				limites[0]=ZERO;
				limites[1]=NINE;
				break;
			case ANY_CHARACTER:
				limites[0]=ASCII_START;
				limites[1]=ASCII_END;
				break;
			default:;
		}
		return limites;
	}
	public static String getStringWithNotAccents(String string){
		StringBuilder normalString = new StringBuilder(string.length());
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        char c;      
    	for(int i=0,j=string.length();i<j;i++) {
        	c=string.charAt(i);
        	if (c<='\u007F')
                	normalString.append(c);  
    	}     
        return normalString.toString();
	}
	/***    
	 * Return a camel case string with spacesW
	 * @param str String like OlveraGutierrezDiegoJesus
	 * @return String with spaces, like Olvera Gutierrez Diego Jesus
	 */
	public static String getStringWithSpaces(String str){
		StringBuilder strSpaces=new StringBuilder();
		char aux;
		for(int i=0,j=str.length();i<j;i++){
			if( Character.isUpperCase(aux=str.charAt(i)) ) {
				 strSpaces.append(' ');
			}
			strSpaces.append(aux);
		}
		return strSpaces.toString();
	}
	public static String[] getWords(String str,ArrayList<String> fillers){
		str=str.trim();
		ArrayList<String> cadenas=new ArrayList<String>();
		int indiceComa,indiceEspacio,desde=0,hasta;
		int longStr=str.length();
		boolean aunHayPalabrasPorLeer;
		String rellenador;
		int cuantasVecesHastaEsIgualAMenos1=1;
		char aux;
		int cuentaComas;
		if(longStr>=1){
			do{		
				indiceEspacio=str.indexOf(" ",desde);
				indiceComa=str.indexOf(",",desde);
				if(indiceComa!=-1 && indiceEspacio!=-1){
					hasta=Math.min(indiceComa, indiceEspacio);
				}
				else{
					hasta=indiceComa==-1?indiceEspacio:indiceComa;
					if(hasta==-1 && cuantasVecesHastaEsIgualAMenos1==1){
						hasta=longStr;
						cuantasVecesHastaEsIgualAMenos1++;
					}
				}
				aunHayPalabrasPorLeer=hasta!=-1;
				if(aunHayPalabrasPorLeer){
					if(str.charAt(hasta-1)==')'){
						hasta--;
					}
					cadenas.add(str.substring(desde,hasta));
					rellenador="";	
					if(hasta+1<longStr && str.charAt(hasta+1)==','){
						hasta++;
					}
					desde=hasta;
					cuentaComas=1;
					for(;desde<longStr;desde++){
						if( (aux=str.charAt(desde)) ==' '){
							rellenador+=' ';
						}
						else if(aux==',' && cuentaComas==1){
							rellenador+=',';
							cuentaComas++;
						}
						else if(aux=='(' || aux==')'){
							rellenador+=aux;
						}
						else{
							break;
						}
					}
					fillers.add(rellenador);
				}	
			}while(aunHayPalabrasPorLeer);	
		}
		return (String[]) cadenas.toArray(new String[0]);
	}
	public static String getWrappedText(String t){
		return "<html>"+t+"</html>";
	}
	public static boolean hasDigitsOrLetters(String cadena){
		if(cadena==null)
			return false;
		int i=0,j;
		for(i=0,j=cadena.length();i<j;i++){
			if(!Character.isLetterOrDigit(cadena.charAt(i)))
				return false;
			//else sigue comparando
		}
		return j>=1;
	}
	public static boolean hasLettersAndDigits(String cadena){
		if(cadena==null)
			return false;
		int i=0,j;
		int cuentaDigitos,cuentaLetras;
		char aux;
		for(i=cuentaDigitos=cuentaLetras=0,j=cadena.length();i<j;i++){
			if(Character.isDigit(aux=cadena.charAt(i))){
				cuentaDigitos++;
			}
			else if(Character.isLetter(aux)){
				cuentaLetras++;
			}
			else{
				return false;
			}
		}
		return cuentaDigitos>=1 && cuentaLetras>=1;
	}
	
	public static boolean hasNumbersWithSeparator(String cadena,String separador){
		return hasOnlyDigits(cadena.replaceAll(separador, ""));
	}
	public static boolean hasOnlyDigits(String t){
		if(t==null) 
			return false;
		int i=0,j;
		for(i=0,j=t.length();i<j;i++){
			if(!Character.isDigit(t.charAt(i))){
				return false;
			}
			//else sigue comparando
		}
		return j>=1;
	}
	public static boolean hasSpaces(String s){
		return s.indexOf(" ")>=0;
	}
	public static boolean hasWordsAndOptionallyNumbers(String cadena){
		if(cadena==null)
			return false;
		int i=0,j;
		int cuentaDigitos,cuentaLetras;
		char aux;
		for(i=cuentaDigitos=cuentaLetras=0,j=cadena.length();i<j;i++){
			if(Character.isDigit(aux=cadena.charAt(i))){
				cuentaDigitos++;
			}
			else if(Character.isLetter(aux)){
				cuentaLetras++;
			}
			else{
				return false;
			}
		}
		return cuentaDigitos>=0 && cuentaLetras>=1;
	}
	public static boolean isAnagram(String a,String b) {
		ArrayList<String> strings=new ArrayList<>(2);
		strings.add(a);
		strings.add(b);
		return anagram(strings);
	}   
	public static boolean isPalindromic(String str){ 
		for(int left=0,right=str.length()-1;left<right;left++,right--){
			if(str.charAt(left)!=str.charAt(right)){
				return false;
			}
		}
		return true;
	}
	public static boolean isValidName(String n){
		if(n!=null){ 
			n= n.replaceAll("\\s", ""); 
			int i,j;
			for(i=0,j=n.length();i<j;i++){
				if(!Character.isLetter(n.charAt(i))){
					return false;
				}
				//else sigue comparando
			}
			return j>=1;
		}
		else{
			return false;
		}			
	}
	public static boolean isValidPassword(String p) {
		return pwdPattern.matcher(p).matches();
	}
		
	public static boolean isValidRFC(String r){
		if(r==null)
			return false;
		int longitudRfc=r.length(),caracterPrimeraValidacion,caracterSegundaValidacion;	
		boolean esRfcCompleto=longitudRfc==RFC_SIZE;
		if( esRfcCompleto || longitudRfc==RFC_SIZE-1){
			caracterPrimeraValidacion=esRfcCompleto?
					CHAR_FIRST_VALIDATION:CHAR_FIRST_VALIDATION-1;
			caracterSegundaValidacion=esRfcCompleto?
					CHAR_SECONCD_VALIDATION:CHAR_SECONCD_VALIDATION-1;	
			if(!isValidName(r.substring(0, caracterPrimeraValidacion))){
				return false;
			}
			if (!hasOnlyDigits(r.substring(
					caracterPrimeraValidacion, caracterSegundaValidacion))) {
				return false;
			}
			return hasDigitsOrLetters(r.substring(caracterSegundaValidacion,longitudRfc));			
		}
		else{
			return false;
		}
	}
	public static boolean isValidSentence(String o){
		if(o==null){
			return false;
		}
		return !o.replaceAll("\\s", "").equals("");
	}
	public static boolean isValidTelephoneWithNumbersAndScores(String t){
		return hasNumbersWithSeparator(t, "-");
	}
	public static boolean isValidUsername(String s) {
		String regex;
		//regex="^\\p{Alpha}+[\\p{Alnum|_}]*$";
		regex="[a-zA-Z_]+[{a-zA-Z}|{0-9}|_]*$";
		return s.matches(regex);
	}
	
	public static String removeSpaces(String s){
		return s.replaceAll("\\s","");
	}
	public static String replace(String str,int[] whichWordsToReplace,String newStrings[]){
		str=str.trim();
		StringBuilder newStr=new StringBuilder();
		int indiceEspacio,indiceComa,desde=0,hasta,longStr=str.length();
		int cuentaComas;
		char aux;
		int cuentaPalabras=0,longIndicePalabras=whichWordsToReplace.length;
		boolean hayOtraPalabra;
		if(longStr>=1){
			do{
				indiceEspacio=str.indexOf(" ",desde);
				indiceComa=str.indexOf(",",desde);
				if(indiceComa!=-1 && indiceEspacio!=-1){
					hasta=Math.min(indiceComa, indiceEspacio);
				}
				else{
					hasta=indiceComa==-1?indiceEspacio:indiceComa;
				}
				newStr.append(newStrings[whichWordsToReplace[cuentaPalabras++]]);
				if(hayOtraPalabra=hasta!=-1){
					//buscar cocatenaciones extras
					cuentaComas=1;
					for(;hasta<longStr;hasta++){
						aux=str.charAt(hasta);
						if(aux==' '){
							newStr.append(aux);
						}
						else if(aux==','){
							if(cuentaComas==1){
								newStr.append(aux);
								cuentaComas++;
							}
						}
						else{
							break;
						}		
					}
					desde=hasta+1;
				}
				if(cuentaPalabras==longIndicePalabras && hayOtraPalabra){
					newStr.append(str.substring(desde-1,str.length()));
					break;
				}	
			}while(hayOtraPalabra);	
		}
		return newStr.toString();	
	}
	public static String replaceWithUpperCase(String str,ArrayList<Integer> whichWordsToReplace){
		StringBuilder newStr=new StringBuilder();
		char aux;
		boolean itCanChangeCharacter;
		for(int i=0,j=str.length(),countingWords=1;i<j;i++){
			if((aux=str.charAt(i))==' '){
				newStr.append(aux);
				countingWords++;
				itCanChangeCharacter=false;
				for(Integer num:whichWordsToReplace){
					if(num.equals(new Integer(countingWords))){
						itCanChangeCharacter=true;
						break;
					}
				}
				for(++i;i<j;i++){
					if((aux=str.charAt(i))==' '){			
						i--;
						break;
					}
					else{
						newStr.append(itCanChangeCharacter?Character.toUpperCase(aux):aux);
					}	
				}
			}
			else{
				newStr.append(aux);
			}
		}		
		return newStr.toString();
	}
	public static int searchDigit(String s, int start){
		for(int i=start,j=s.length();i<j;i++){
			if(Character.isDigit(s.charAt(i))){
				return i;
			}
		}
		return -1;
	}
	public static ArrayList<String> splitWords(String text){
		ArrayList<String> words=new ArrayList<>();
        //\\P{L} means is not a unicode code point that has the property "Letter"
		for(String word:text.trim().split("\\P{L}+")) {
			if(word.length()>=1) {
				words.add(word);
			}
		}
		return words;
	}
	public static String stringWithNoAccentedVowels(String string){
		StringBuilder normalString = new StringBuilder(string.length());
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        char c,auxCharacter;
    	boolean eneMayus;
    	for(int i=0,j=string.length();i<j;i++) {
        	c=string.charAt(i);
    		eneMayus=c=='N';
    		if(c=='n' || eneMayus && (i+1)<j){
    			auxCharacter=string.charAt(i+1);
				normalString.append(auxCharacter>'\u007F'?eneMayus?'�':'�':c);     			
    		}   			
    		else if(c<='\u007F'){
                normalString.append(c);
    		}
    	}
		return normalString.toString();
	}	
}
