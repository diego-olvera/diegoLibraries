package diegoLibraries.file.excel;
import java.io.File;
import java.io.IOException;

import diegoLibraries.file.FileReader;
import diegoLibraries.wrappers.WrapperInt;
import diegoLibraries.wrappers.WrapperString;
import jxl.Cell;
import jxl.CellType;
import jxl.FormulaCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReader implements FileReader{
	public static final int NUMBER_FORMULA=1;
	public static final int NUMBER=2;
	public static final int CHARACTERS=3;
	
	private Workbook excelFile;
	private Sheet currentSheet;
	private File file;
	
	public ExcelReader(File archivo) throws BiffException, IOException{
		this.file=archivo;
		excelFile= Workbook.getWorkbook(archivo);
		setCurrentSheet(excelFile.getSheet(0));
	}
	@Override
	public void close(){
		excelFile.close();
	}
	
	protected Sheet getCurrentSheet(){
		return currentSheet;
	}
	protected boolean setCurrentSheet(Sheet s){
		if(s!=null){
			currentSheet=s;
			return true;
		}
		return false;
	}
	public boolean setCurrentSheet(int num) {
		try{
			currentSheet = excelFile.getSheet(num);
			return true;
		}catch(IndexOutOfBoundsException e){
			return false;
		}
	}
	public boolean setCurrentSheet(String nombre){
		Sheet hojaAux=excelFile.getSheet(nombre);
		if(hojaAux!=null){
			currentSheet=hojaAux;
			return true;
		}
		else{
			return false;
		}
	}
		
	protected static String getCharacters(Cell celda){
		return celda.getContents();
	}
	protected static double getNumber(Cell celda){
		return Double.parseDouble(celda.getContents());
	}
	protected static String getFormula(Cell celda){
		try {
			return ((FormulaCell) celda).getFormula();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Object readCell(int columna,int fila,WrapperInt tipoCelda){
		return readCell(columna, fila, tipoCelda,null);
	}
	public Object readCell(int columna,int fila,WrapperInt tipoCelda,WrapperString formula){
		Cell cell=getCurrentSheet().getCell(columna,fila);
		CellType typeOfReadCell=cell.getType();
		Object valorCelda;
		if(typeOfReadCell==CellType.NUMBER_FORMULA){	
			tipoCelda.value=NUMBER_FORMULA;
			valorCelda=getCharacters(cell);
			if(formula!=null){
				formula.value=getFormula(cell);
			}
		}
		else if(typeOfReadCell==CellType.NUMBER){
			tipoCelda.value=NUMBER;
			valorCelda=getNumber(cell);
		}
		else{
			tipoCelda.value=CHARACTERS;
			valorCelda=getCharacters(cell);
		}
		return valorCelda;
	}
	@Override
	public String read(int registro, int campo) {
		return readFieldValue(registro, campo,0);
	}
	private String readFieldValue(int registro, int campo, int hoja){
		try{
			return getCurrentSheet().getCell(campo,registro).getContents();
		}catch(IndexOutOfBoundsException e){
			return null;
		}	
	}
	public String readFieldValue(String celda){
		try{
			return getCurrentSheet().getCell(celda).getContents();
		}catch(IndexOutOfBoundsException e){
			return null;
		}
	}
	public String getName(){
		return file.getName();
	}
	@Override
	public int getMaxRows() {
		return getCurrentSheet().getRows();
	}
	@Override
	public int getMaxColumns() {
		return getCurrentSheet().getColumns();
	}
	public int getColumns(int cualFila){
		return getCurrentSheet().getRow(cualFila).length;
	}
	public int getRows(int cualColumna){
		return getCurrentSheet().getColumn(cualColumna).length;
	}
}
