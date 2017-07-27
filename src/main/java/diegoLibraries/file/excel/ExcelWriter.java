package diegoLibraries.file.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.CellView;
import jxl.Workbook;
import jxl.biff.CellReferenceHelper;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
 
public class ExcelWriter {
	private WritableWorkbook excelFile;
	private File file;
	private WritableSheet currentSheet;
	
	public ExcelWriter(File file,String sheetName)throws Exception {
		this.file=file;
		if(file.exists()){
			excelFile=Workbook.createWorkbook(file,Workbook.getWorkbook(file));
		}
		else{
			excelFile=Workbook.createWorkbook(file);
			excelFile.createSheet(sheetName,excelFile.getNumberOfSheets());
		}	
		currentSheet=excelFile.getSheet(0);
	}
	public ExcelWriter(File file)throws Exception {
		this(file,"Sheet 1");
	}
	public void setCurrentSheet(int numHojaActual) {
		currentSheet=excelFile.getSheet(numHojaActual);
	}
	public void setCurrentSheet(String sheetName) {
		currentSheet=excelFile.getSheet(sheetName);
		if(currentSheet==null){
			addSheet(sheetName);
			currentSheet=excelFile.getSheet(sheetName);
		}
	}
	public void addSheet(String sheetName){
		excelFile.createSheet(sheetName,excelFile.getNumberOfSheets());
	}

	public boolean close(){
		return ExcelUtilities.cerrarDocumentoExcel(excelFile);
	}
	public String getAbsolutePath(){
		return file.getAbsolutePath();
	}
	public boolean write(Object d,int row,int column){
		return write(d.toString(),row,column);
	}
	public boolean write(Object d,String cell){
		return write(d.toString(),cell);
	}
	public boolean write(double d,int row,int column){
		return write(String.valueOf(d),row,column);
	}
	public boolean write(double d,String cell){
		return write(String.valueOf(d),cell);
	}
	public boolean write(String contents,int row,int column){
		try {
			WritableSheet hoja=currentSheet;
			CellView cell;
			try{
				hoja.addCell(new Number(column,row,Double.parseDouble(contents)));
			}catch(NumberFormatException e){
				hoja.addCell(new Label(column,row,contents));
			}	
			cell=hoja.getColumnView(column);
		    cell.setAutosize(true);
		    hoja.setColumnView(column, cell);
			return true;
		} 
		catch (Exception e) {
			return false;
		}
	}
	public boolean write(String contents,String cell){		
		return write(contents,CellReferenceHelper.getRow(cell),CellReferenceHelper.getColumn(cell));
	}
	public String getFileName() {
		return file.getName();
	}
	public boolean write(File src,ArrayList<Integer> columns,ArrayList<Integer> destinationColumns) 
			throws BiffException, IOException{
		ExcelReader srcExcel=new ExcelReader(src);
		int row,totalRows,destinationFileRowCounter=0,maxCol=srcExcel.getMaxColumns();
		for(Integer column:columns){
			if(column<maxCol){
				for(row=0,totalRows=srcExcel.getMaxRows();row<totalRows;row++){
					this.write(srcExcel.read(row,column),row,destinationFileRowCounter);
				}
				destinationFileRowCounter++;
			}	
		}
		srcExcel.close();
		return true;	
	}	
}
