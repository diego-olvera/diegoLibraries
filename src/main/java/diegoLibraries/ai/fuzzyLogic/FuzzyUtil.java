package diegoLibraries.ai.fuzzyLogic;

public class FuzzyUtil {

	public static int posNivMemMay(FuzzyElement[] conjuntos){
        int i,j,biggetsIndex;
        for(i=biggetsIndex=0,j=conjuntos.length;i<j;i++){
        	if(conjuntos[i].getMembershipLevel()>conjuntos[biggetsIndex].getMembershipLevel()){
        		biggetsIndex=i;
        	}
        }
        return biggetsIndex;
    }

}
