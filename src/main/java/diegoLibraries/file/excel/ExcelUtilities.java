package diegoLibraries.file.excel;
  
import jxl.Cell;
import jxl.CellType;
import jxl.FormulaCell;
import jxl.Sheet;
import jxl.biff.CellReferenceHelper;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtilities {
	/***
	 * Find the row column in an excel format (A1,A2,B3,...). This functions is commonly used to
	 * make formulas
	 * @param row
	 * @param column
	 * @return The excel format row column that matches the row and column
	 */   
	public static String matrixPointToExcelRowColumn(int row,int column){
		//this function has a limit, thats why is encapsulated, the intenttion is to code a new one
		return CellReferenceHelper.getCellReference(column, row);
	}  
	/***
	 * It closes and write(save) a WritableWorkBook
	 * @param documentoExcel Document that will be updated and write(save)
	 * @return true if no exception is thrown, false otherwise
	 */
	public static boolean cerrarDocumentoExcel(WritableWorkbook documentoExcel){	
		try {
			documentoExcel.write();
			documentoExcel.close(); 
			return true;
		} 
		catch (Exception e) {
			return false;
		}	
	}
	/***
	 * This modify a WritableCell with content of the String s
	 * @param celda WritableCell to update
	 * @param s String to add int celda
	 * @return true if the cell has been modified, false otherwise
	 */
	public static boolean modificarCelda(WritableCell celda,String s){
		try{
			((Label)celda).setString(s);
			return true;
		}
		catch(ClassCastException e){
			return false;
		}
	}
	/**
	 * Finds a WritableCell that contains contenido
	 * @param hoja WritableSheet to search
	 * @param contenido String to find in all the sheet cells
	 * @return A WritablleCell if it found a match, null otherwise
	 */
	public static WritableCell buscarCeldaEscribible(WritableSheet hoja,String contenido){
		int i,j,k,l;
		WritableCell celda;
		for(i=0,l=hoja.getColumns();i<l;i++){
			for(j=0,k=hoja.getColumn(i).length;j<k;j++){
				celda=hoja.getWritableCell(i, j);
				if(celda.getContents().equals(contenido)){
					return celda;
				}
			}
		}
		return null;
	}
	/**
	 * Finds a Cell that contains contenido
	 * @param hoja Sheet to search
	 * @param contenido String to find in all the sheet cells
	 * @return A WritablleCell if it found a match, null otherwise
	 */
	public static Cell buscarCelda(Sheet hoja,String contenido){
		int i,j,k,l;
		Cell celda;
		for(i=0,l=hoja.getColumns();i<l;i++){
			for(j=0,k=hoja.getColumn(i).length;j<k;j++){
				celda=hoja.getCell(i, j);
				if(celda.getContents().equals(contenido)){
					return celda;
				}
			}
		}
		return null;
	}

	/***
	 * Return the index of the namedSheet
	 * @param workBook
	 * @param sheetName
	 * @return the index of the namedSheet -1 otherwise
	 */
	public static int getIndex(WritableWorkbook workBook,String sheetName){
		for(int i=0,j=workBook.getNumberOfSheets();i<j;i++){ 
			if(workBook.getSheet(i).getName().equals(sheetName)){
				return i;
			}
		}
		return -1;
	}
	
	public static boolean copy(WritableSheet source,WritableSheet destiny,int firstCol,int lastCol){
		try{
			int i,j,l,n=source.getColumns();
			CellType typeOfReadCell;
			Cell readCell;
			for(i=firstCol;i<n && i<lastCol;i++){
				for(j=0,l=source.getColumn(i).length;j<l;j++){
					readCell=source.getCell(i,j);
					typeOfReadCell=readCell.getType();
					if(typeOfReadCell==CellType.NUMBER_FORMULA){					
						destiny.addCell(new Formula(i,j,((FormulaCell) readCell).getFormula()));
					}
					else if(typeOfReadCell==CellType.NUMBER){
						destiny.addCell(new Number(i,j, 
								Double.parseDouble(readCell.getContents())));
					}
					else{//Its just a String
						destiny.addCell(new Label(i,j, readCell.getContents()));
					}	
				}
			}
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
}
