package diegoLibraries.ai.fuzzyLogic;

import java.io.IOException;

import diegoLibraries.commons.ScanF;
import diegoLibraries.file.MyRandomAccessFile;


public class FuzzyRulesGenerator {
	
	static String elementos1[]={"\"bajo\"","\"moderado\"","\"fuerte\"","\"inteso\""};
	static String elementos2[]={"\"bajo\"","\"moderado\"","\"fuerte\"","\"inteso\""};
	static String elementos3[]={"\"bajo\"","\"moderado\"","\"fuerte\"","\"inteso\""};
	static String elementos4[]={"\"bajo\"","\"moderado\"","\"fuerte\"","\"inteso\""};
	
	static String varElementos1="usoDrogas";
	static String varElementos2="contenidoSexual";
	static String varElementos3="violencia";
	static String varElementos4="lenguajeSoez";
	
	
	public static String compCodigo(String x,String y){
		return x+".equals("+y+")";
	}
	public static String compPseudoCodigo(String x,String y){
		return x+"=="+y;
	}
	public static void pseudoCodigo(boolean pregunta){
		int cuenta=1;
		int i,j,k,l;
		String cad="";
		for(i=0;i<elementos1.length;i++){
			for(j=0;j<elementos2.length;j++){
				for(k=0;k<elementos3.length;k++){
					for(l=0;l<elementos4.length;l++){
						System.out.println((cuenta++)+")");
						cad+=(cuenta-1)+")"+"\n";
						System.out.println(("si("+compPseudoCodigo(varElementos1,elementos1[i])
								+" y "+compPseudoCodigo(varElementos2,elementos2[j])
								+" y "+compPseudoCodigo(varElementos3,elementos3[k])
								+" y "+compPseudoCodigo(varElementos4,elementos4[l])
								+")entonces"));
						cad+="si("+compPseudoCodigo(varElementos1,elementos1[i])
								+" y "+compPseudoCodigo(varElementos2,elementos2[j])
								+" y "+compPseudoCodigo(varElementos3,elementos3[k])
								+" y "+compPseudoCodigo(varElementos4,elementos4[l])
								+")entonces";
						cad+="\n";
						System.out.print("\tclasificacion=");
						cad+="\tclasificacion=";
						if(pregunta){
							cad+=ScanF.readString();
						}
						System.out.println("");
						cad+="\n";
					}
				}		
			}
		}
		escribeArchivo(cad,"pseudocodigo.txt");
	}
	public static void escribeArchivo(String cad,String nombre){
		try {
			MyRandomAccessFile f=new MyRandomAccessFile(nombre,"rw");
			f.setLength(0);
			f.writeCharacters(cad);
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void codigo(boolean pregunta){
		int cuenta=1;
		int i,j,k,l;
		cuenta=1;
		String cad="";
		for(i=0;i<elementos1.length;i++){
			for(j=0;j<elementos2.length;j++){
				for(k=0;k<elementos3.length;k++){
					for(l=0;l<elementos4.length;l++){
						System.out.println("\\\\"+(cuenta++)+")");
						cad+="\\\\"+(cuenta-1)+")"+"\n";
						System.out.println(("if("+compCodigo(varElementos1,elementos1[i])
								+" && "+compCodigo(varElementos2,elementos2[j])
								+" && "+compCodigo(varElementos3,elementos3[k])
								+" && "+compCodigo(varElementos4,elementos4[l])
								+")"));
						cad+="if("+compCodigo(varElementos1,elementos1[i])
								+" && "+compCodigo(varElementos2,elementos2[j])
								+" && "+compCodigo(varElementos3,elementos3[k])
								+" && "+compCodigo(varElementos4,elementos4[l])
								+")";
						cad+="\n";
						System.out.print("\treturn");
						cad+="\treturn";
						if(pregunta){
							cad+=ScanF.readString();
						}
						System.out.println(";");
						cad+=";";
						cad+="\n";
					}
				}		
			}
		}
		escribeArchivo(cad,"codigo.java");

	}
	public static void main(String[] args) {
		
		//System.out.println("Pseudocódigo");
		pseudoCodigo(true);
		//System.out.println("Codigo");
		//codigo(false);
		
	}
	

}
