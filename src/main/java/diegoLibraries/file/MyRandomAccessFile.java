package diegoLibraries.file;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyRandomAccessFile extends java.io.RandomAccessFile{
	
	public MyRandomAccessFile(String name, String mode)
			throws FileNotFoundException {
		super(name, mode);
	}
	public MyRandomAccessFile(File fileName, String mode)
			throws FileNotFoundException {
		super(fileName, mode);
	}
	public String readAllAsUTF8() {
		return readAllAsString(Charset.forName("UTF-8"));
	}
	public String readAllAsString(Charset charset) {
		return new String(readAllAsArray(),charset);
	}
	public byte[] readAllAsArray() {
		byte bytes[]=null;
		try {
			long i=getFilePointer(),j=length();
			bytes=new byte[(int) (j-i)];
			int currentPos;
			for(currentPos=0;i<j;i++,currentPos++) {
				bytes[currentPos]=readByte();
			}
			return bytes;
		}catch(IOException e) {
		}
		return bytes;
		
	}
	public long readAll(ArrayList<Byte> bytes) {
		long size=bytes.size();
		try {
			for(long i=getFilePointer(),j=length();i<j;i++) {
				bytes.add(readByte());
			}
		}catch(IOException e) {
		}
		return bytes.size()-size;
	}
	public ArrayList<Byte> readAll() {
		ArrayList<Byte> bytes=new ArrayList<>();
		readAll(bytes);
		return bytes;
	}
	public void writeUTFCharacters(String s) throws IOException{
		char c;
		for(int i=0,j=s.length();i<j;i++){
			c=s.charAt(i);
			if(c>='\u0001' && c<= '\u007f'){
				writeByte(c);
				//System.out.println("Un byte:"+c);
			}
			else if(c=='\u0000' ||( c>= '\u0080' && c<='\u07ff') ){
				writeByte((byte)(0xc0 | (0x1f & (c >> 6))));
				writeByte((byte)(0x80 | (0x3f & c)));
				//System.out.println("Dos bytes:"+c);
			}
			else if(c>='\u0800' && c<='\uffff'){
				writeByte((byte)(0xe0 | (0x0f & (c >> 12))));
				writeByte( (byte)(0x80 | (0x3f & (c >>  6))));
				writeByte((byte)(0x80 | (0x3f & c)));
				//System.out.println("3 bytes:"+c);
			}
		}
	}
	@SuppressWarnings("unused")
	public String readUTFCharacters() throws IOException{
		LinkedList<Byte> bytes=new LinkedList<>();
		int i,length;
		byte primerByte,segundoByte,tercerbyte;
		StringBuilder utfCharacters=new StringBuilder();
		long numShort;
		long numShort2;
		for(i=(int) getFilePointer(),length=(int) length();i<length;i++){
			bytes.add(readByte());
		}
		for(length=bytes.size(),i=0;i<length;i++){
			System.out.println("Byte "+i+"="+bytes.get(i));
			primerByte=bytes.get(i);
			try{
				if(primerByte==-61){
					//Significa que se tiene que leer un segundo byte
					segundoByte=bytes.get(++i);
					numShort=(long)segundoByte;
					numShort2=(long)primerByte;
					System.out.println("byte 1:"+numShort2);
					System.out.println("byte 2:"+numShort);
					numShort=(long) (numShort|numShort2);
					System.out.println("num:"+numShort);
					utfCharacters.append((char)(numShort));
					i++;
				}
				else if(primerByte==-96){
					//Se leen 3 bytes en total, faltan 2
				}
				else{
					utfCharacters.append((char)primerByte);
				}
			}catch(IndexOutOfBoundsException e){
				utfCharacters.append(primerByte);
			}
		}
		return utfCharacters.toString();
	}
	public String readChars() throws IOException{
		StringBuilder chars=new StringBuilder();
		try{
			for(long i=getFilePointer(),j=length();i<j;i+=2){
				chars.append(readChar());
			}
		}catch(EOFException eof){		
		}
		return chars.toString();
	}
	public String readCharacters(String separator) throws IOException{
		/*StringBuilder chars=new StringBuilder(); 
		char c,sep=separator.charAt(0);
		boolean isThereAnyCharToRead=true;
		while(isThereAnyCharToRead){
			try {
				if((c=readCharacter())!=sep){
					chars.append(c);
				}
				else{
					isThereAnyCharToRead=false;
				}			
			} 
			catch (IOException e) {
				isThereAnyCharToRead=false;
			}
		}
		return chars.toString();*/
		StringBuilder info=new StringBuilder();
        int sizeSep=separator.length();
        char s;
        char sep=separator.charAt(0);
        int i;
        String complement="";
        boolean equals;
        do{
            info.append(complement);
            while((s=readCharacter())!=sep){
                info.append(s);
            }
            complement="";
            i=1;
            equals=true;
            complement+=sep;
            while(i<sizeSep){
                s=readCharacter();
                complement+=s;
                if(s!=separator.charAt(i)){
                    equals=false;
                }
                i++;
            }
        }while(i!=sizeSep || !equals);
        return info.toString();
	}
	public void writeCharacters(String s) throws IOException{
		for(int i=0,j=s.length();i<j;i++){
			writeByte(s.charAt(i));
		}
	}
	public char readCharacter() throws IOException{
		return (char)readByte(); 
	}
	public String readCharacters(){ 
		StringBuilder chars=new StringBuilder();
		boolean isThereAnyCharToRead=true;
		while(isThereAnyCharToRead){ 
			try {
				chars.append(readCharacter());
			} 
			catch (IOException e) {
				isThereAnyCharToRead=false;
			}
		}
		return chars.toString();	
	}
	public boolean copy(MyRandomAccessFile destiny,long bytes){
        if(bytes>=1){
        	try {
        		for(long i=0;i<bytes;i++){      
					destiny.writeByte(readByte());
        		}
            }
        	catch (IOException e) {
				return false;
			}
            return true;
        }
        else{
            return false;
        }
    }
	public boolean copyFromBeginning(MyRandomAccessFile destiny){
        try {
			return copyFromBeginning(destiny,length());
		} catch (IOException e) {
			return false;
		}
    } 
    public boolean copyFromBeginning(MyRandomAccessFile destiny,long bytes){       
        try {
        	seek(0);
			destiny.seek(0);
		} 
        catch (IOException e) {
			return false;
		}
        return copy(destiny,bytes);
    }
    public boolean copy(MyRandomAccessFile destiny){
        try {
			while(true) {
				destiny.writeByte(readByte());
			}
		} 
        catch (IOException e) {
			return true;
		}
    }
    MyRandomAccessFile concat(MyRandomAccessFile arg,String destinyName){
        MyRandomAccessFile concatenation;
		try {
			concatenation = new MyRandomAccessFile(destinyName,"rw");
			copy(concatenation);
	        arg.copy(concatenation);  
	        return concatenation;
		} 
		catch (FileNotFoundException e) {
			return null;
		}
        
    }
	private static final String SEPARATOR="-";
	
	public static void pruebaMiUTFCharacters(){
		MyRandomAccessFile archivo;
		String cad;
		try {
			archivo = new MyRandomAccessFile("pruebaUTF.txt","rw");
			cad="Árbol añejo";
			try {
				System.out.println("Escribiendo:"+cad);
				archivo.writeUTFCharacters(cad);
				archivo.seek(0);
				System.out.println(archivo.readUTFCharacters());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				archivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected static void pruebaCaracteres(){
		MyRandomAccessFile archivo;
		try {
			archivo = new MyRandomAccessFile("Prueba Caracteres.txt","rw");
			archivo.writeCharacters("Primera palabra"+SEPARATOR+"Segunda Palabra"+SEPARATOR
					+"Tercera palabra"+SEPARATOR+"Cuarta palabra"+SEPARATOR);
			archivo.seek(0);
			for(int i=0;i<4;i++){
				System.out.println(archivo.readCharacters(SEPARATOR));
			}
			archivo.seek(0);
			System.out.println("Ahora todo de golpe:");
			System.out.println(archivo.readCharacters());
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void escrituraLectura() {
		MyRandomAccessFile file=null;
		try {
			file=new MyRandomAccessFile("testIO.txt","rw");
			file.setLength(0);
			String s="Árbol sísñps ´+}{?¿";
			//file.writeChars(s);
			file.writeUTFCharacters(s);
			file.seek(0); 
			System.out.println(file.readAllAsString(Charset.forName("UTF-8")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(file!=null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		escrituraLectura();
	}

}
