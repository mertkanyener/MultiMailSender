import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read {
	
	/***
	 * Reads the file and returns the text of the file as String
	 * @param fileName = name of the file to be read
	 * @return textStr = file text
	 */
	public String readText(String fileName){
		Scanner inputStream = null;
		try{
			inputStream = new Scanner(new FileInputStream(fileName));
		}catch(FileNotFoundException e){
			String errMsg = fileName + " was not found or could not be opened.";
			System.out.println(errMsg);
			System.exit(0);
		}
		String textStr = "";
		String textLine = inputStream.nextLine();
		while(textLine != null){
			textStr = textStr + "\n" + textLine;
			try{
				textLine = inputStream.nextLine();
			}catch(NoSuchElementException e){
				System.out.println("The reading is done.");
				break;
			}
		}
		return textStr;
	}
	public String[] readExcel(String fileName, int colNum, String sheetName) throws IOException{
	
		InputStream exFile = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(exFile);
		XSSFSheet sheet = wb.getSheet(sheetName);
		Iterator<Row> rowIterator = sheet.iterator();
		int rowNum = sheet.getLastRowNum();
		String[] mailArr = new String[rowNum+1];
		int i = 0;
		
		while(rowIterator.hasNext()){
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				int index = cell.getColumnIndex();
				if(index == colNum){
					mailArr[i] = cell.getStringCellValue();
				}
				
			}
			i +=1;
			
		}
		wb.close();
		System.out.println("Read excel is done.");
		return mailArr;
	}
	
	
	public void printArr(String[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.println(arr[i]);
		}
	}

}
