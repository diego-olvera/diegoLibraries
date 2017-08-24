package diegoLibraries.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import diegoLibraries.time.Timer;


import static diegoLibraries.file.FileUtil.*;

public class FileUtilTest {

	@Test
	public void testDeleteEmptyFolders() {
		String folder;
		folder="C:\\Users\\Diego Olvera\\Music\\iTunes\\iTunes Music";
		//folder="C:\\Users\\Diego Olvera\\Music\\iTunes\\iTunes Music\\Atreyu";
		File file=new File(folder);
		System.out.println("Deleting these empty folders");
		List<File> folders=deleteEmptyFolders(file);
		folders.forEach(f->{
			System.out.println(f);
		});
	}
	@Test
	public static void testFolders() {
		String sep=File.separator;
		String fileName="path1"+sep+"path2"+sep+"path3"+sep+"fileName.pdf";
		ArrayList<String> folders=getParentFolders(fileName,sep);
		ArrayList<Integer> indexesFoldersCreated=createFolders(folders);
		System.out.println("Folders created");
		for(int index:indexesFoldersCreated) {
			System.out.println(folders.get(index));
		}
	}
	static void testCopyFolders() throws IOException {
		String src="C:\\Users\\Diego Olvera\\Documents\\dev\\test\\Universidad semestre 1";
		String dest="C:\\Users\\Diego Olvera\\Documents\\dev\\test\\dest";
		String folderToDelete=dest+"\\Universidad semestre 1";
		Timer timer;
		ArrayList<Float> myCodeTimes=new ArrayList<>();
		ArrayList<Float> internetCodetimes=new ArrayList<>();
		int rounds=50;
		int roundsWonByMyCode=0,roundsWonByInternetsCode=0,ties=0;
		float timeLef,timeRight;
		System.out.println("Folder to del:"+folderToDelete);
		System.out.println("Doing competition of "+rounds+ " rounds");
		for(int i=0;i<rounds;i++) {
			System.out.println("Round #"+(i+1));
			timer=new Timer();
			timer.start();
			copyFolderByDiego(src,dest);
			timer.stop();
			myCodeTimes.add(timeLef=timer.getSeconds());
			deleteDirectory(folderToDelete);
			timer=new Timer();
			timer.start();
			copyFolderByGuyFromInternet(src,dest);
			timer.stop();
			internetCodetimes.add(timeRight=timer.getSeconds());
			System.out.println("mine:"+timeLef+",internet:"+internetCodetimes.get(i)); 
			if(timeLef<timeRight) {
				System.out.println("Mine won");
				roundsWonByMyCode++;
			}
			else if(timeRight<timeLef) {
				System.out.println("Internet won");
				roundsWonByInternetsCode++;
			}
			else {
				System.out.println("Tie");
				ties++;
			}
			System.out.println("Mine victories:"+roundsWonByMyCode);
			System.out.println("Internet victories:"+roundsWonByInternetsCode);
			System.out.println("Ties:"+ties);
			deleteDirectory(folderToDelete);
		}
		System.out.println("Battle finished");		
	}

}
