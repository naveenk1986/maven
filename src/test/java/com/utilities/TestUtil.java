package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class TestUtil {
	
	public static Object[] getData(String sheetname) throws BiffException, IOException
	{
		FileInputStream fs = new FileInputStream("D:\\Naveen_Work\\Selenium_work\\MavenSelTest\\src\\test\\java\\com\\data\\DBook.xls");
		Workbook wb = Workbook.getWorkbook(fs);
		Sheet s1 = wb.getSheet(sheetname);
		int rowcount = s1.getRows();
		int cloumcount = s1.getColumns();
		Hashtable[] data = new Hashtable[rowcount-1];
		for (int i = 1; i < rowcount; i++) {
			Hashtable<String, String> datatable = new Hashtable<String,String>();
			for (int j = 0; j < cloumcount; j++) {
				datatable.put(s1.getCell(j,0).getContents(), s1.getCell(j, i).getContents());
			}
			data[i-1] = datatable;
		}

		wb.close();
		fs.close();

		return data;
	}


}
