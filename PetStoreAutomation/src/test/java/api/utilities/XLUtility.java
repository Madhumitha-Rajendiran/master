package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workBook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public XLUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {

		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workBook.close();
		fi.close();

		return rowCount;

	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {

		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workBook.close();
		fi.close();

		return cellCount;

	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {

		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
	
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
		data = formatter.formatCellValue(cell);
		}
		catch (Exception e) {
			data="";
		}
		
		workBook.close();
		fi.close();

		return data;

	}
	
	public void setCellData(String sheetname, int rowNum,  int colNum, String data) throws IOException {
		File xlFile = new File(path);
		if (!xlFile.exists()) {
				workBook = new XSSFWorkbook();
				fo = new FileOutputStream(path);
				workBook.write(fo);
		}
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		
		if (workBook.getSheetIndex(sheetname)==-1) 
			workBook.createSheet(sheetname);
		workBook.getSheet(sheetname);
		
		if(sheet.getRow(rowNum) == null) 
			sheet.createRow(rowNum);
		sheet.getRow(rowNum);
		
		cell =row.createCell(colNum);
		cell.setCellValue(data);
		fo= new FileOutputStream(path);
		workBook.write(fo);
		workBook.close();
		fi.close();
		fo.close();
		
		
	}
}
