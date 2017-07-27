package diegoLibraries.file;

import java.io.File;
import java.io.IOException;

import diegoLibraries.date.CurrentDate;
import diegoLibraries.file.compress.Zip;
import diegoLibraries.string.StringUtil;

public class VersionControl {
	private File file;
	private String descriptionOfNewVersion;
	public VersionControl(String fileName){
		this(new File(fileName));
	}
	public VersionControl(File file){
		this(file,null);
	}
	public VersionControl(File file,String descriptionOfNewVersion){
		setFile(file);
		setDescriptionOfNewVersion(descriptionOfNewVersion);
	}
	public VersionControl(String file,String descriptionOfNewVersion){
		this(new File(file),descriptionOfNewVersion);
	}
	public String getDescriptionOfNewVersion() {
		return descriptionOfNewVersion;
	}
	public boolean setDescriptionOfNewVersion(String i) {
		if(StringUtil.isValidSentence(i)){
			descriptionOfNewVersion=i;
			return true;
		}
		return false;
	}
	public String getCompleteFileName(){
		return getFile().getAbsolutePath();
	}
	public File getFile(){
		return file;
	}
	public boolean setFile(File a){
		if(a!=null){
			file=a;
			return true;
		}
		return false;
	}
	public boolean setFileName(String n){
		return setFile(new File(n));
	}
	public String getNewNameVersion(){
		return getCompleteFileName()+" "+getCurrentVersionName()+".zip";
	}
	public String getCurrentVersionName(){
		return CurrentDate.getTimeStamp().toString('-','-');
	}
	public boolean createNewVersion(){
		return createNewVersion(getNewNameVersion());
	}
	public boolean createNewVersion(String versionName){ 
		String infoNextVersion;
		MyRandomAccessFile archInfo;
		try {
			if(new File(versionName).createNewFile()){
				Zip.zip(getCompleteFileName(),versionName);
				if((infoNextVersion=getDescriptionOfNewVersion())!=null){
					archInfo=new MyRandomAccessFile(versionName+".txt","rw");
					archInfo.writeUTFCharacters(infoNextVersion);
					archInfo.close();
				}
				return true;
			} 
			else{
				return false;
			}		
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean createNewVersion(File folder,String description) {
		return new VersionControl(folder, description).createNewVersion();
	}
	public static boolean createNewVersion(String folder,String description) {
		return createNewVersion(new File(folder),description);
	}
	public static void main(String[] args) {
		//procedure(args);
		createNewVersion("C:\\Users\\Diego Olvera\\Documents\\dev\\java\\diegoLibraries",
				"Árbol ñoño");
	}
	public static void procedure(String[] args) {
		(args.length>=2?new VersionControl(args[0],args[1]):new VersionControl(args[0],null)).createNewVersion();
	}
}
